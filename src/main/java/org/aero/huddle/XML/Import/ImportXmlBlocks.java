package org.aero.huddle.XML.Import;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.swing.JOptionPane;

import org.aero.huddle.util.XmlTagConstants;
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
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

// Class corresponding to the XML (Puddle) option in ImportXmlAction
public class ImportXmlBlocks {

	private static HashMap<String, Element> idElementPairing = new HashMap<String, Element>();
	private static final String profileName = "Huddle Stereotypes";
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
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package rootPackage = ImportXmlPuddle.buildPackage(splash, model);
		//Splash does not have a huddle id... associateIdAndElement(splash, rootPackage);
		
		Profile profile = ImportXmlPuddle.buildProfile(profileName, rootPackage);
		
		List<Stereotype> stereotypes = ImportXmlPuddle.createStereotypes(splash, profile);
		
		ImportXmlPuddle.createStereotypeProperties(splash, stereotypes);
		
		populateBlocks(splash, rootPackage);
		
		linkBlocks(splash, rootPackage);
		
		BddCreator.createDiagram(rootPackage);
	}
	
	public static void populateBlocks(Node splash, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		NodeList childNodes = splash.getChildNodes();
		for(int i = 0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			
			//All child nodes are elements, and assume the only non-element child tag is name (for splash)
			if(childNode.getNodeType() == Node.ELEMENT_NODE && !childNode.getNodeName().equals("name"))
			{
				NodeList elementDetails = childNode.getChildNodes();
				Map<String, String> kvPairs = new HashMap<String, String>();
				String id = "";
				String name = "";
				for(int j=0; j<elementDetails.getLength(); j++)
				{
					Node elementDetail = elementDetails.item(j);
					if(elementDetail.getNodeType() == Node.ELEMENT_NODE && elementDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID))
						id = elementDetail.getTextContent();
					if(elementDetail.getNodeType() == Node.ELEMENT_NODE && elementDetail.getNodeName().equals(XmlTagConstants.CHARACTERIZATIONS))
						kvPairs = ImportXmlPuddle.getTagsAndValues(elementDetail);
				}
				
				///////// NOTE /////////
				// These are implementation specific to a particular use case and are currently hard coded.
				// A more generic mechanism should be implemented
				if(kvPairs.containsKey(systemNameKey))
					name = kvPairs.get(systemNameKey);
				else if(kvPairs.containsKey(interactionNameKey))
					name = kvPairs.get(interactionNameKey);
				else if(kvPairs.containsKey(functionNameKey))
					name = kvPairs.get(functionNameKey);
				else if(kvPairs.containsKey(eventNameKey))
					name = kvPairs.get(eventNameKey);
				else
					name = id;
				
				Element block = ImportXmlPuddle.buildBlock(name, pkg);
				
				//Apply the stereotype
				Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), profileName);
				Stereotype stereotype = ImportXmlPuddle.getStereotypeFromProfile(profile, childNode.getNodeName());
				StereotypesHelper.addStereotype(block, stereotype);
				
				//Apply the tag values
				for(Entry<String, String> kvpair : kvPairs.entrySet())
				{
					StereotypesHelper.setStereotypePropertyValue(block, stereotype, kvpair.getKey(), kvpair.getValue());
				}
				
				//Link id and element
				idElementPairing.put(id, block);
			}
		}
	}
	
	public static void linkBlocks(Node splash, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		NodeList childNodes = splash.getChildNodes();
		for(int i = 0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			
			///////// NOTE /////////
			// These are specific to a particular ontologies and are currently hard coded.
			// A more generic mechanism should be implemented. This may come from modifying the XML format.
			
			//All child nodes are elements, and assume the only non-element child tag is name (for splash)
			if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("sos:system")) {
				link(childNode, pkg, XmlTagConstants.PERFORMS, "behavior:process");
			} else if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("sos:interaction")) {
				link(childNode, pkg, XmlTagConstants.JOINS, "sos:system");
			} else if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("behavior:process")) {
				link(childNode, pkg, XmlTagConstants.EXECUTES, "behavior:function");
			} else if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("behavior:function")) {
				link(childNode, pkg, XmlTagConstants.EXPOSES, "behavior:outport");
			} else if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("behavior:outport")) {
				link(childNode, pkg, XmlTagConstants.PRODUCES, "analysis:eventTrace");
			} else if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("analysis:eventTrace")) {
				link(childNode, pkg, XmlTagConstants.POINTS_TO, "analysis:event");
			} else if(childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals("analysis:event")) {
				//Nothing (event does not link downward)
			}
		}
	}
	
	public static void link(Node elementNode, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg, String relationshipType, String participantTag)
	{
		//List to hold all relationship ids
		ArrayList<String> relationIds = new ArrayList<String>();
		//Id of the element
		String elementId = "";
		
		NodeList elementDetails = elementNode.getChildNodes();
		for(int i=0; i<elementDetails.getLength(); i++)
		{
			Node elementDetail = elementDetails.item(i);
			if(elementDetail.getNodeType() == Node.ELEMENT_NODE && elementDetail.getNodeName().equals(relationshipType)) {
				NodeList relationNodes = elementDetail.getChildNodes();
				for(int j=0; j<relationNodes.getLength();j++)
				{
					Node relationNode = relationNodes.item(j);
					//This is necessary because the XML contains hidden nodes (like #text) which should not be included
					//As an alternative could perform check on string value below, i.e., if(relationId != null)...buildAssociation...
					//However, this method is already specific to a particular terminology
					if(relationNode.getNodeType() == Node.ELEMENT_NODE && relationNode.getNodeName().equals(participantTag))
						relationIds.add(relationNode.getTextContent());
				}
			} else if(elementDetail.getNodeType() == Node.ELEMENT_NODE && elementDetail.getNodeName().equals(XmlTagConstants.HUDDLE_ID)) {
				elementId = elementDetail.getTextContent();
			}
		}
		
		//Get the block based on the id
		Element element = idElementPairing.get(elementId);

		for(String relationId : relationIds)
		{			
			Element relation = idElementPairing.get(relationId);
			buildAssociation(relationshipType, element, relation, pkg);
		}
	}
	
	public static void buildAssociation(String name, Element supplier, Element client, com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package parentElement)
	{
		Project project = Application.getInstance().getProject();
		Association association = null;
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create Association");
			
			ElementsFactory ef = project.getElementsFactory();
			ModelElementsManager em = ModelElementsManager.getInstance();
			
			//Create a new association, set its name
			association = ef.createAssociationInstance();
			association.setName(name);
			
			
			//Set the client and supplier
			ModelHelper.setSupplierElement(association, supplier);
			ModelHelper.setClientElement(association, client);
			
			try
			{
				em.addElement(association, parentElement);
			}
			catch (ReadOnlyElementException roee) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create Association");
			}
			
			SessionManager.getInstance().closeSession(project);
		}
	}
}
