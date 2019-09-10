package org.aero.huddle.XML.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;

import org.aero.huddle.csv.ImportFromHuddleAction;
import org.aero.huddle.util.XmlTagConstants;

// Class corresponding to the XML (Blocks) option in ImportXmlAction
public class ImportXmlPuddle {

	private static final String profileName = "Huddle Stereotypes";
	private static HashMap<String, Element> idElementPairing = new HashMap<String, Element>();
	private static final String systemNameKey = "Component";
	private static final String interactionNameKey = "Entity";
	private static final String functionNameKey = "name";
	private static final String eventNameKey = "Entity";
	
	public static void buildModel(Document doc) throws NullPointerException
	{
		//Root in XML
		Node splash = doc.getDocumentElement();
		
		//Base element in the model, under which all imported elements will be created
		Project project = Application.getInstance().getProject();
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package model = project.getPrimaryModel();
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package rootPackage = buildPackage(splash, model);
		//Splash does not have a huddle id... associateIdAndElement(splash, rootPackage);
		
		Profile profile = buildProfile(profileName, rootPackage);
		
		List<Stereotype> stereotypes = createStereotypes(splash, profile);
		
		createStereotypeProperties(splash, stereotypes);
		
		List<com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class> systems = createSystems(splash, rootPackage);
		List<Association> interactions = createInteractions(splash, rootPackage);
		List<Activity> functions = createFunctions(splash, rootPackage);
		List<Parameter> events = createEvents(splash, rootPackage);
		
		BddCreator.createDiagram(rootPackage);
	}
	
	//Create all of the huddle stereotypes
	public static List<Stereotype> createStereotypes(Node splash, Profile profile)
	{
		NodeList childNodes = splash.getChildNodes();
		
		//For each element
		for(int i=0; i<childNodes.getLength(); i++)
		{
			Node child = childNodes.item(i);
			List<Stereotype> stereotypes = StereotypesHelper.getStereotypesByProfile(profile);
			List<String> stereotypeNames = getStereotypeNames(stereotypes);
			//Check for element node avoids #text
			//Check for contains avoids duplication
			//Check for applicable stereotypes avoids unwanted stereotypes
			if(child.getNodeType() == Node.ELEMENT_NODE && !stereotypeNames.contains(child.getNodeName()) && !child.getNodeName().equals("name"))
				buildStereotype(child.getNodeName(), profile);
		}
		
		return StereotypesHelper.getStereotypesByProfile(profile);
	}
	
	//Populate stereotypes with properties
	public static void createStereotypeProperties(Node splash, List<Stereotype> stereotypes)
	{
		//All elements in splash
		NodeList elementObjs = splash.getChildNodes();
		for(int i=0; i<elementObjs.getLength(); i++)
		{
			Node elementObj = elementObjs.item(i);
			//Get the correct stereotype associated with the element
			//If the stereotype is not present, continue to the next element
			Stereotype s = null;
			for(Stereotype stereotype : stereotypes)
			{
				if(stereotype.getName().equals(elementObj.getNodeName()))
					s = stereotype;
			}
			if(s == null)
				continue;
			
			//For each element, get its children
			NodeList elementObjChildNodes = elementObj.getChildNodes();
			for(int j=0; j<elementObjChildNodes.getLength(); j++)
			{
				//If one of the children is characterizations
				Node elementObjChildNode = elementObjChildNodes.item(j);
				if(elementObjChildNode.getNodeName().equals(XmlTagConstants.CHARACTERIZATIONS))
				{
					//Get each characterization
					NodeList charNodes = elementObjChildNode.getChildNodes();
					for(int k=0; k<charNodes.getLength(); k++)
					{
						//Get the name-value nodes
						Node charNode = charNodes.item(k);
						NodeList kv = charNode.getChildNodes();
						
						//Find the name node
						String propertyName = null;
						for(int l=0; l<kv.getLength(); l++)
						{
							if(kv.item(l).getNodeName().equals(XmlTagConstants.NAME))
							{
								propertyName = kv.item(1).getTextContent();
								break;
							}
						}
						
						//Get all the property names
						Set<Property> properties = StereotypesHelper.getPropertiesWithDerived(s);
						ArrayList<String> propertyNames = new ArrayList<String>();
						for(Property property : properties)
							propertyNames.add(property.getName());
						
						//If there is no property with the tag name, add it
						if(propertyName != null && !propertyNames.contains(propertyName))
							buildProperty(propertyName, s);
					}
				}
			}
		}
	}
	
	// Create systems as blocks
	public static List<com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class> createSystems(Node splash, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		NodeList childNodes = splash.getChildNodes();
		ArrayList<com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class> systems = new ArrayList<com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class>();
		for(int i = 0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("sos:system"))
			{
				NodeList systemDetails = childNode.getChildNodes();
				Map<String, String> kvPairs = new HashMap<String, String>();
				String id = "";
				String name = "";
				for(int j=0; j<systemDetails.getLength(); j++)
				{
					Node systemDetail = systemDetails.item(j);
					if(systemDetail.getNodeType() == Node.ELEMENT_NODE && systemDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
						id = systemDetail.getTextContent();
					if(systemDetail.getNodeType() == Node.ELEMENT_NODE && systemDetail.getNodeName().equals(XmlTagConstants.CHARACTERIZATIONS))
						kvPairs = getTagsAndValues(systemDetail);
				}
				
				if(kvPairs.containsKey(systemNameKey))
					name = kvPairs.get(systemNameKey);
				
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class system = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class)buildBlock(name, pkg);
				systems.add(system);
				
				//Apply the stereotype
				Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), profileName);

				Stereotype stereotype = getStereotypeFromProfile(profile, childNode.getNodeName());

				StereotypesHelper.addStereotype(system, stereotype);
				
				//Apply the tag values
				for(Entry<String, String> kvpair : kvPairs.entrySet())
				{
					StereotypesHelper.setStereotypePropertyValue(system, stereotype, kvpair.getKey(), kvpair.getValue());
				}
				
				//Link id and element
				associateIdAndElement(childNode, system);
			}
				
		}
		
		return systems;
	}
	
	//Create interactions as associations
	public static List<Association> createInteractions(Node splash, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		ArrayList<Association> interactions = new ArrayList<Association>();
		
		NodeList childNodes = splash.getChildNodes();
		for(int i = 0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("sos:interaction"))
			{
				NodeList interactionDetails = childNode.getChildNodes();
				Map<String, String> kvPairs = new HashMap<String, String>();
				String id = "";
				String name = "";
				List<String> joinIds = new ArrayList<String>();
				for(int j=0; j<interactionDetails.getLength(); j++)
				{
					Node interactionDetail = interactionDetails.item(j);
					if(interactionDetail.getNodeType() == Node.ELEMENT_NODE && interactionDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
						id = interactionDetail.getTextContent();
					if(interactionDetail.getNodeType() == Node.ELEMENT_NODE && interactionDetail.getNodeName().equals(XmlTagConstants.CHARACTERIZATIONS))
						kvPairs = getTagsAndValues(interactionDetail);
					if(interactionDetail.getNodeType() == Node.ELEMENT_NODE && interactionDetail.getNodeName().equals(XmlTagConstants.JOINS))
						joinIds = getJoinIds(interactionDetail);
				}
				
				if(kvPairs.containsKey(interactionNameKey))
					name = kvPairs.get(interactionNameKey);
				
				Association interaction = (Association)buildAssociation(name, joinIds, pkg);
				interactions.add(interaction);
				
				//Apply the stereotype
				Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), profileName);
				Stereotype stereotype = getStereotypeFromProfile(profile, childNode.getNodeName());
				StereotypesHelper.addStereotype(interaction, stereotype);
				
				//Apply the tag values
				for(Entry<String, String> kvpair : kvPairs.entrySet())
				{
					StereotypesHelper.setStereotypePropertyValue(interaction, stereotype, kvpair.getKey(), kvpair.getValue());
				}
				
				//Link id and element
				associateIdAndElement(childNode, interaction);
			}
				
		}
		
		return interactions;
	}
	
	//Create functions as activities
	public static List<Activity> createFunctions(Node splash, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		ArrayList<Activity> functions = new ArrayList<Activity>();
		
		NodeList childNodes = splash.getChildNodes();
		for(int i = 0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("behavior:function"))
			{
				NodeList functionDetails = childNode.getChildNodes();
				Map<String, String> kvPairs = new HashMap<String, String>();
				String id = "";
				String name = "";
				String performingSystemId = "";
				for(int j=0; j<functionDetails.getLength(); j++)
				{
					Node functionDetail = functionDetails.item(j);
					if(functionDetail.getNodeType() == Node.ELEMENT_NODE && functionDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
						id = functionDetail.getTextContent();
					if(functionDetail.getNodeType() == Node.ELEMENT_NODE && functionDetail.getNodeName().equals(XmlTagConstants.CHARACTERIZATIONS))
						kvPairs = getTagsAndValues(functionDetail);
					if(functionDetail.getNodeType() == Node.ELEMENT_NODE && functionDetail.getNodeName().equals(XmlTagConstants.EXECUTED_BY))
						performingSystemId = getPerformingSystemId(functionDetail, splash);
				}
				
				if(kvPairs.containsKey(functionNameKey))
					name = kvPairs.get(functionNameKey);

				Element parentSystem = idElementPairing.get(performingSystemId);
				Activity function = (Activity)buildActivity(name, parentSystem);
				functions.add(function);
				
				//Apply the stereotype
				Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), profileName);
				Stereotype stereotype = getStereotypeFromProfile(profile, childNode.getNodeName());
				StereotypesHelper.addStereotype(function, stereotype);
				
				//Apply the tag values
				for(Entry<String, String> kvpair : kvPairs.entrySet())
				{
					StereotypesHelper.setStereotypePropertyValue(function, stereotype, kvpair.getKey(), kvpair.getValue());
				}
				
				//Link id and element
				associateIdAndElement(childNode, function);
			}
				
		}
		
		return functions;
	}
	
	//Create events as parameters
	public static List<Parameter> createEvents(Node splash, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		ArrayList<Parameter> events = new ArrayList<Parameter>();
		
		NodeList childNodes = splash.getChildNodes();
		for(int i = 0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("analysis:event"))
			{
				NodeList eventDetails = childNode.getChildNodes();
				Map<String, String> kvPairs = new HashMap<String, String>();
				String id = "";
				String name = "";
				for(int j=0; j<eventDetails.getLength(); j++)
				{
					Node eventDetail = eventDetails.item(j);
					if(eventDetail.getNodeType() == Node.ELEMENT_NODE && eventDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
						id = eventDetail.getTextContent();
					if(eventDetail.getNodeType() == Node.ELEMENT_NODE && eventDetail.getNodeName().equals(XmlTagConstants.CHARACTERIZATIONS))
						kvPairs = getTagsAndValues(eventDetail);
				}
				
				if(kvPairs.containsKey(eventNameKey))
					name = kvPairs.get(eventNameKey);

				String portId = getProducingPortId(id, splash);
				String functionId = getExposingFunctionId(portId, splash);
				Element parentFunction = idElementPairing.get(functionId);
				Parameter event = (Parameter)buildParameter(name, parentFunction);
				events.add(event);
				
				//Apply the stereotype
				Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), profileName);
				Stereotype stereotype = getStereotypeFromProfile(profile, childNode.getNodeName());
				StereotypesHelper.addStereotype(event, stereotype);
				
				//Apply the tag values
				for(Entry<String, String> kvpair : kvPairs.entrySet())
				{
					StereotypesHelper.setStereotypePropertyValue(event, stereotype, kvpair.getKey(), kvpair.getValue());
				}
				
				//Link id and element
				associateIdAndElement(childNode, event);
			}
				
		}
		
		return events;
	}
	
	//Create package element
	public static com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package buildPackage(Node n, Element parentElement)
	{
		//Get the name from XML, if there is one
		String name = getNameElement(n);
		
		Project project = Application.getInstance().getProject();
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Package");
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new package
			pkg = ef.createPackageInstance();
			if(name != null)
				pkg.setName(name);
			
			//Add the package to the model
			try
			{
				em.addElement(pkg, parentElement);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Package");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
		
		return pkg;
	}
	
	//Create profile element
	public static Profile buildProfile(String name, Element pkg)
	{		
		Project project = Application.getInstance().getProject();
		Profile profile = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Profile");
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new profile, set its name if there is one, and set its owner
			profile = ef.createProfileInstance();
			if(name != null)
			{
				profile.setURI("http://huddle-csm.com/"+name);
				profile.setName(name);
			}
			
			//Add the profile to the parent
			try
			{
				em.addElement(profile, pkg);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Profile");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
		
		return profile;
	}
	
	//Create stereotype
	public static Stereotype buildStereotype(String name, Profile profile)
	{
		Project project = Application.getInstance().getProject();
		Stereotype stereo = null;

		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Stereotype");
			
			//Get the metaclass
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class metaclass = StereotypesHelper.getMetaClassByName(project, "Element");
			
			//Create the stereotype with name and metaclass set and apply to profile
			stereo = StereotypesHelper.createStereotype(profile, name, Arrays.asList(metaclass));
			
			SessionManager.getInstance().closeSession(project);
		}
		
		return stereo;
	}
	
	//Generate the tag definitions for a given stereotype from the collection of tag names
	public static void buildProperty(String propertyName, Stereotype stereotype)
	{
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Define Stereotype Tags: " + stereo.getName());
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Define Property");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			Property property = ef.createPropertyInstance();
			property.setName(propertyName);
			
			//Would set classifier type here if necessary
			
			try
			{
				em.addElement(property, stereotype);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Define Stereotype Tags");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
	}
	
	//Create block element
	public static Element buildBlock(String name, Element parentElement)
	{		
		Project project = Application.getInstance().getProject();
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class block = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Block");
			
			//MagicDraw helper classes
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new class, set its name if there is one
			block = ef.createClassInstance();
			if(name != null)
				block.setName(name);
			
			//Apply the <<Block>> stereotype to the class element
			Stereotype blockStereotype = StereotypesHelper.getStereotype(project, "Block", StereotypesHelper.getProfile(project, "SysML"));
			if(blockStereotype != null)
			{
				StereotypesHelper.addStereotype(block, blockStereotype);
			}
			
			//Add the block to its parent
			try
			{
				em.addElement(block, parentElement);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Block");
			}
			
			SessionManager.getInstance().closeSession(project);
		}

		return block;
	}
	
	//Create association element
	public static Element buildAssociation(String name, List<String> joinIds, Element parentElement)
	{
		Project project = Application.getInstance().getProject();
		Association association = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Association");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new association, set its name if it has one
			association = ef.createAssociationInstance();
			if(name != null)
				association.setName(name);
			
			try {
				//Set the client and supplier by searching through all created elements, finding the one matching the relevant ID
				ModelHelper.setSupplierElement(association, idElementPairing.get(joinIds.get(0)));
				ModelHelper.setClientElement(association, idElementPairing.get(joinIds.get(1)));
			} catch (IndexOutOfBoundsException ex) {
				throw new IndexOutOfBoundsException("Missing join to create association " + ex.getMessage());
			}
			
			try
			{
				em.addElement(association, parentElement);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Association");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
		
		return association;
	}
	
	//Create activity element
	public static Element buildActivity(String name, Element parentElement)
	{
		Project project = Application.getInstance().getProject();
		Activity activity = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Activity");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			activity = ef.createActivityInstance();
			if(name != null)
				activity.setName(name);
			
			//Add activity to the parent
			try
			{
				em.addElement(activity,  parentElement);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Activity");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
		
		return activity;
	}
	
	//Create parameter element
	public static Element buildParameter(String name, Element parentElement)
	{		
		Project project = Application.getInstance().getProject();
		Parameter parameter = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Parameter");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			parameter = ef.createParameterInstance();
			if(name != null)
				parameter.setName(name);
			
			//Add the element to the owning function (i.e., activity)
			try
			{					
				em.addElement(parameter, parentElement);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Parameter");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
		
		return parameter;
	}
	
	public static String getNameElement(Node n)
	{
		//Retrieve the name, if there is one
		NodeList nodes = n.getChildNodes();
		String name = null;
		
		//For each child node
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Node node = nodes.item(i);
			//If there is an element node "name"
			if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(XmlTagConstants.NAME))
			{
				//Set and break (there should only be one "name" node)
				name = node.getTextContent();
				break;
			}
		}
		
		return name;
	}
	
	//Helper method returning the names of all stereotypes from a list of stereotypes
	public static List<String> getStereotypeNames(List<Stereotype> stereotypes)
	{
		ArrayList<String> stereotypeNames = new ArrayList<String>();
		
		for(Stereotype s : stereotypes)
		{
			stereotypeNames.add(s.getName());
		}
		
		return stereotypeNames;
	}
	
	//Helper method to pair XML elements to their corresponding SysML element
	public static void associateIdAndElement(Node n, Element e)
	{
		NodeList childNodes = n.getChildNodes();
		for(int i=0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
			{
				idElementPairing.put(childNode.getTextContent(), e);
				break;
			}
		}
	}
	
	//Retrieve the SysML element from the collection based on id
	public static Element getElementByXmlId(String id)
	{
		return idElementPairing.get(id);
	}
	
	//Get the characterization key-value pairs from the XML
	public static Map<String, String> getTagsAndValues(Node characterizationsNode)
	{
		HashMap<String, String> kvPairs = new HashMap<String, String>();
		NodeList charNodes = characterizationsNode.getChildNodes();
		for(int i = 0; i < charNodes.getLength(); i++)
		{
			Node charNode = charNodes.item(i);
			NodeList kvNodes = charNode.getChildNodes();
			String name = "";
			String value = "";
			for(int j = 0; j < kvNodes.getLength(); j++)
			{
				Node kvNode = kvNodes.item(j);
				if(kvNode.getNodeType() == Node.ELEMENT_NODE && kvNode.getNodeName().equals(XmlTagConstants.NAME))
					name = kvNode.getTextContent();
				if(kvNode.getNodeType() == Node.ELEMENT_NODE && kvNode.getNodeName().equals(XmlTagConstants.VALUE))
					value = kvNode.getTextContent();
			}
			
			if(!name.equals(""))
				kvPairs.put(name, value);
		}
		
		return kvPairs;
	}
	
	//Helper to get the ids associated with the XML joins element.
	public static List<String> getJoinIds(Node joinsNode)
	{
		ArrayList<String> joinIds = new ArrayList<String>();
		NodeList systemNodes = joinsNode.getChildNodes();
		for(int i=0; i<systemNodes.getLength(); i++)
		{
			Node systemNode = systemNodes.item(i);
			if(systemNode.getNodeType() == Node.ELEMENT_NODE && systemNode.getNodeName().equals("sos:system"))
				joinIds.add(systemNode.getTextContent());
		}
		
		return joinIds;
	}
	
	//Helper method link a function to a system, bypassing process.
	public static String getPerformingSystemId(Node executedByNode, Node splash)
	{
		String executedById = getExecutedById(executedByNode);
		return getPerformedById(getProcessNode(executedById, splash));
	}
	
	//Get the process node in XML associated with ID
	public static Node getProcessNode(String id, Node splash)
	{
		//Get the process node
		NodeList childNodes = splash.getChildNodes();
		for(int i=0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("behavior:process"))
			{
				NodeList processDetails = childNode.getChildNodes();
				for(int j=0;j<processDetails.getLength();j++)
				{
					Node processDetail = processDetails.item(j);
					if(processDetail.getNodeType() == Node.ELEMENT_NODE && processDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
					{
						String processId = processDetail.getTextContent();
						if(processId.equals(id))
							return childNode;
					}
				}
			}
		}
		
		return null;
	}
	
	//Helper method to get the id associated with the XML performedBy element.
	public static String getPerformedById(Node processNode)
	{
		String performedById = "";
		NodeList processDetails = processNode.getChildNodes();
		for(int i=0; i<processDetails.getLength(); i++)
		{
			Node processDetail = processDetails.item(i);
			if(processDetail.getNodeType() == Node.ELEMENT_NODE && processDetail.getNodeName().equals(XmlTagConstants.PERFORMED_BY))
			{
				NodeList systemNodes = processDetail.getChildNodes();
				//Note, accessed by list, but there is only one performs node
				for(int j=0;j<systemNodes.getLength();j++)
				{
					Node systemNode = systemNodes.item(j);
					if(systemNode.getNodeType() == Node.ELEMENT_NODE && systemNode.getNodeName().equals("sos:system"))
						performedById = systemNode.getTextContent();
				}
			}
		}
		return performedById;
	}
	
	//Get the port id associated with the event
	public static String getProducingPortId(String eventId, Node splash)
	{
		NodeList childNodes = splash.getChildNodes();
		for(int i=0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("analysis:eventTrace"))
			{
				NodeList eventTraceDetails = childNode.getChildNodes();
				Node pointsToNode = null;
				Node producedByNode = null;
				for(int j=0; j<eventTraceDetails.getLength(); j++)
				{
					Node eventTraceDetail = eventTraceDetails.item(j);
					//Find the points to and produced by nodes
					if(eventTraceDetail.getNodeType() == Node.ELEMENT_NODE && eventTraceDetail.getNodeName().equals(XmlTagConstants.POINTS_TO))
						pointsToNode = eventTraceDetail;
					if(eventTraceDetail.getNodeType() == Node.ELEMENT_NODE && eventTraceDetail.getNodeName().equals(XmlTagConstants.PRODUCED_BY))
						producedByNode = eventTraceDetail;
				}
			
				//check if the points to is equal. Note list should be a single element
				NodeList eventNodes = pointsToNode.getChildNodes();
				for(int j=0; j<eventNodes.getLength(); j++)
				{
					Node eventNode = eventNodes.item(j);
					//If the event is associated with the event trace (i.e., equal), get the outport associated with the event trace
					if(eventNode.getNodeType() == Node.ELEMENT_NODE && eventNode.getNodeName().equals("analysis:event") && eventNode.getTextContent().equals(eventId))
					{
						NodeList outportNodes = producedByNode.getChildNodes();
						for(int k=0; k<outportNodes.getLength(); k++)
						{
							Node outportNode = outportNodes.item(k);
							if(outportNode.getNodeType() == Node.ELEMENT_NODE && outportNode.getNodeName().equals("behavior:outport"))
								return outportNode.getTextContent();
						}
					}
				}
			}
		}
		
		return "";
	}
	
	//Get the function id associated with a port
	public static String getExposingFunctionId(String portId, Node splash)
	{
		//All nodes
		NodeList childNodes = splash.getChildNodes();
		for(int i=0; i<childNodes.getLength(); i++)
		{
			//Get outport nodes
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("behavior:outport"))
			{
				NodeList outportDetails = childNode.getChildNodes();
				for(int j=0;j<outportDetails.getLength();j++)
				{
					//Get huddle id node
					Node outportDetail = outportDetails.item(j);
					if(outportDetail.getNodeType() == Node.ELEMENT_NODE && outportDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
					{
						//if huddle id not equal break and move onto next outport
						if(!outportDetail.getTextContent().equals(portId))
							break;
						else
						{
							for(int k=0; k<outportDetails.getLength(); k++)
							{
								//get exposed by node
								Node outportDetail2 = outportDetails.item(k);
								if(outportDetail2.getNodeType() == Node.ELEMENT_NODE && outportDetail2.getNodeName().equals(XmlTagConstants.EXPOSED_BY))
								{
									NodeList functionNodes = outportDetail2.getChildNodes();
									for(int m=0; m<functionNodes.getLength(); m++)
									{
										//get function node
										Node functionNode = functionNodes.item(m);
										if(functionNode.getNodeType() == Node.ELEMENT_NODE && functionNode.getNodeName().equals("behavior:function"))
										{
											//return function id
											return functionNode.getTextContent();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return "";
	}
	
	//Helper method to get the id associated with the executedBy XML element
	public static String getExecutedById(Node executedByNode)
	{
		String executedBy = "";
		//Note, accessed by list, but there is only one performs node
		NodeList processNodes = executedByNode.getChildNodes();
		for(int i=0; i<processNodes.getLength(); i++)
		{
			Node processNode = processNodes.item(i);
			if(processNode.getNodeType() == Node.ELEMENT_NODE && processNode.getNodeName().equals("behavior:process"))
				executedBy = processNode.getTextContent();
		}
		
		return executedBy;
	}
	
	//Helper method to get the specified stereotype from a SysML profile.
	public static Stereotype getStereotypeFromProfile(Profile profile, String name)
	{
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypesByProfile(profile);
		for(Stereotype stereotype : stereotypes)
		{
			if(stereotype.getName().equals(name))
				return stereotype;
		}
		
		return null;
	}
}
