package org.aero.huddle.XML.Export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import org.aero.huddle.util.LinkingHelper;
import org.aero.huddle.util.PropertyCollector;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;


//Make this a class which will handle the export function to the XML using puddle structure
public class ExportXmlPuddle
{	
	//Returns true if the XML build discovered duplicate tags across stereotypes, otherwise false
	public static boolean buildXML(Document doc, Element pkg) throws NullPointerException
	{
		//First build the root
		org.w3c.dom.Element rootElement = buildXMLRoot(doc, pkg);

		//From the root, move down the children of the root element pkg
		return buildXMLChildren(doc, rootElement, pkg);
	}
	
	public static org.w3c.dom.Element buildXMLRoot(Document doc, Element pkg)
	{		
		//In this version of the XML, the root is always <splash>
		String tag = XmlTagConstants.SPLASH;
		
		//Creates the root element and append as root
		org.w3c.dom.Element rootElement = doc.createElement(tag);
		
		//Get the possible namespaces and add each to the root element
		Map<String, String> huddleNS = XmlTagConstants.getHuddleNS();
		for(Entry<String, String> ns : huddleNS.entrySet())
		{
			rootElement.setAttributeNS(XmlTagConstants.NS_URL, ns.getKey(), ns.getValue());
		}
		
		doc.appendChild(rootElement);
		return rootElement;
	}
	
	//From the model, find all blocks, associations, activities, and parameters beneath the top level package
	//In this version of the XML, all children are at the same level, that is the model hierarchy is not retained
	public static boolean buildXMLChildren(Document doc, org.w3c.dom.Element parentElement, Element pkg)
	{
		Collection<Element> systems = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class.class}, true);
		Collection<Element> interactions = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association.class}, true);
		Collection<Element> functions = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity.class}, true);
		Collection<Element> events = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter.class}, true);
		
		LinkingHelper lh = new LinkingHelper();
		
		boolean retval = false;
		retval = retval | buildXMLChildren(doc, parentElement, systems, XmlTagConstants.Type.SYSTEM, lh);
		retval = retval | buildXMLChildren(doc, parentElement, interactions, XmlTagConstants.Type.INTERACTION, lh);
		retval = retval | buildXMLChildren(doc, parentElement, functions, XmlTagConstants.Type.FUNCTION, lh);
		retval = retval | buildXMLChildren(doc, parentElement, events, XmlTagConstants.Type.EVENT, lh);
		
		//Do linking
		//Do nothing for systems, but do linking for interactions, functions, and events
		buildXMLInteractionLinks(doc, lh, interactions);
		buildXMLFunctionLinks(doc, parentElement, lh, functions);
		buildXMLEventLinks(doc, parentElement, lh, events);
		
		return retval;
	}
	
	public static boolean buildXMLChildren(Document doc, org.w3c.dom.Element parentElement, Collection<Element> elements, XmlTagConstants.Type type, LinkingHelper lh)
	{
		boolean retval = false;
		
		//For each element in the collection, make an XML node
		for(Element element : elements)
		{
			//Create a new node with the appropriate tag
			org.w3c.dom.Element childElement = null;
			switch (type)
			{
				case SYSTEM:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:sos"), "sos:"+XmlTagConstants.SYSTEM);
					break;
				case INTERACTION:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:sos"), "sos:"+XmlTagConstants.INTERACTION);
					break;
				case PROCESS:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.PROCESS);
					break;
				case FUNCTION:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.FUNCTION);
					break;
				case OUTPORT:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.OUTPORT);
					break;
				case EVENT_TRACE:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:analysis"), "analysis:"+XmlTagConstants.EVENT_TRACE);
					break;
				case EVENT:
					childElement = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:analysis"), "analysis:"+XmlTagConstants.EVENT);
					break;
				default:
					break;
			}
			
			if(childElement != null)
			{				
				//Build the huddle id
				buildXMLHuddleIdNode(doc, childElement, element);
				
				//Build local id node
				buildXMLLocalIdNode(doc, childElement, element);
				
				//Build the characterizations, if duplicate tags were discovered change retval to true
				retval = retval | buildXMLCharacterizations(doc, childElement, element);
				
				//Build the specifics of the element, based on its type
				switch (type)
				{
					case SYSTEM:
						buildXMLSystemSpecifics(doc, childElement, element);
						break;
					case INTERACTION:
						buildXMLInteractionSpecifics(doc, childElement, element);
						break;
					case PROCESS:
						buildXMLProcessSpecifics(doc, childElement, element);
						break;
					case FUNCTION:
						buildXMLFunctionSpecifics(doc, childElement, element);
						break;
					case OUTPORT:
						buildXMLOutportSpecifics(doc, childElement, element);
						break;
					case EVENT_TRACE:
						buildXMLEventTraceSpecifics(doc, childElement, element);
						break;
					case EVENT:
						buildXMLEventSpecifics(doc, childElement, element);
						break;
					default:
						break;
				}
				
				//Add the child node to the linkinghelper
				lh.addMapping(element.getLocalID(), childElement, type);
				
				//Last append the new node to the parent
				parentElement.appendChild(childElement);
			}
		}
		
		return retval;
	}
	
	//Build huddle id node
	//1. After creation of 'child' node, calls this function
	//2. create the _huddle_id node
	//3. if the element has a _huddle_id property in its stereotype apply it (this searches all stereotypes and returns first found huddle id property value).
	//		Made generic for readability and so there does not need to be a check for each different huddle stereotype. Potential later point of optimization.
	//4. Make _huddle_id node child of parent element
	public static void buildXMLHuddleIdNode(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element huddleIdElement = doc.createElement(XmlTagConstants.HUDDLE_ID);
		Object huddleId = getHuddleId(e);
		if(huddleId != null)
		{
			huddleIdElement.appendChild(doc.createTextNode(huddleId.toString()));
		}
		parentElement.appendChild(huddleIdElement);
	}
	
	//Given an element, search through its stereotypes for the huddle id property
	//If not found, returns null
	//Could combine this with PeropertyCollector (does same thing of going through the stereotypes and looking at properties)
	public static Object getHuddleId(Element e)
	{
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(e);

		//For each stereotype
		for(Stereotype stereotype : stereotypes)
		{
			//Get the properties (tags)
			List<Property> properties = StereotypesHelper.getPropertiesWithDerivedOrdered(stereotype);
			
			//For each property
			for(Property property : properties)
			{
				//Check if the tag name is _huddle_id and if so return its value
				if(property.getName().equals(XmlTagConstants.HUDDLE_ID))
					return StereotypesHelper.getStereotypePropertyFirst(e, stereotype, property.getName());
			}
		}
		
		return null;
	}
	
	//Builds a node containing the local id (used for linking when huddle id is not present
	public static void buildXMLLocalIdNode(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element localIdNode = doc.createElement(XmlTagConstants.LOCAL_ID);
		localIdNode.appendChild(doc.createTextNode(e.getLocalID()));
		parentElement.appendChild(localIdNode);
	}
	
	//Builds the XML using the single level characterization structure
	//Warn that duplicate properties were discovered, and caution the potential side effects
	public static boolean buildXMLCharacterizations(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		PropertyCollector collector = new PropertyCollector(e);
		
		if(!collector.getPropertyCollection().isEmpty())
		{
			//Create characterizations tag
			org.w3c.dom.Element characterizationsElement = doc.createElement(XmlTagConstants.CHARACTERIZATIONS);
			
			for(Map.Entry<String, Object> entry : collector.getPropertyCollection().entrySet())
			{
				//Create characterization tag
				org.w3c.dom.Element characterizationElement = doc.createElement(XmlTagConstants.CHARACTERIZATION);
				
				//Create and append name tag to characterization tag
				org.w3c.dom.Element nameElement = doc.createElement(XmlTagConstants.NAME);
				nameElement.appendChild(doc.createTextNode(entry.getKey()));
				characterizationElement.appendChild(nameElement);
				
				//Create and append value tag to characterization tag
				org.w3c.dom.Element valueElement = doc.createElement(XmlTagConstants.VALUE);
				if(entry.getValue() != null)
					valueElement.appendChild(doc.createTextNode(entry.getValue().toString()));
				characterizationElement.appendChild(valueElement);
				
				//Append characterization tag to characterizations tag
				characterizationsElement.appendChild(characterizationElement);
			}
			
			//Append characterizations
			parentElement.appendChild(characterizationsElement);
		}
		
		return collector.duplicatesExist();
	}
	
	//Build any system specifics into the XML
	public static void buildXMLSystemSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element performsNode = doc.createElement(XmlTagConstants.PERFORMS);
		parentElement.appendChild(performsNode);
	}
	
	public static void buildXMLInteractionSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element joinsNode = doc.createElement(XmlTagConstants.JOINS);
		parentElement.appendChild(joinsNode);
	}
	
	public static void buildXMLProcessSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element executesNode = doc.createElement(XmlTagConstants.EXECUTES);
		parentElement.appendChild(executesNode);
		
		org.w3c.dom.Element performedByNode = doc.createElement(XmlTagConstants.PERFORMED_BY);
		parentElement.appendChild(performedByNode);
		
		org.w3c.dom.Element exposesNode = doc.createElement(XmlTagConstants.EXPOSES);
		parentElement.appendChild(exposesNode);
	}
	
	public static void buildXMLFunctionSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element executedByNode = doc.createElement(XmlTagConstants.EXECUTED_BY);
		parentElement.appendChild(executedByNode);
		
		org.w3c.dom.Element exposesNode = doc.createElement(XmlTagConstants.EXPOSES);
		parentElement.appendChild(exposesNode);
	}
	
	public static void buildXMLOutportSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element exposedByNode = doc.createElement(XmlTagConstants.EXPOSED_BY);
		parentElement.appendChild(exposedByNode);
		
		org.w3c.dom.Element producesNode = doc.createElement(XmlTagConstants.PRODUCES);
		parentElement.appendChild(producesNode);
	}
	
	public static void buildXMLEventTraceSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		org.w3c.dom.Element producedByNode = doc.createElement(XmlTagConstants.PRODUCED_BY);
		parentElement.appendChild(producedByNode);
		
		org.w3c.dom.Element pointsToNode = doc.createElement(XmlTagConstants.POINTS_TO);
		parentElement.appendChild(pointsToNode);
	}
	
	public static void buildXMLEventSpecifics(Document doc, org.w3c.dom.Element parentElement, Element e)
	{
		//Tag and value, if used (not currently utilized)
	}
	
	//Populate the 'joins' links between interactions and systems
	public static void buildXMLInteractionLinks(Document doc, LinkingHelper lh, Collection<Element> elements)
	{
		for(Element element : elements)
		{
			String guid = element.getLocalID();
			org.w3c.dom.Element parentNode = lh.interactionLookup(guid);
			
			//There should be only one 'joins' element under each interaction
			Node joinsNode = parentNode.getElementsByTagName(XmlTagConstants.JOINS).item(0);
			
			org.w3c.dom.Element supplierNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:sos"), "sos:"+XmlTagConstants.SYSTEM);
			supplierNode.appendChild(doc.createTextNode(ModelHelper.getSupplierElement(element).getLocalID()));
			
			org.w3c.dom.Element clientNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:sos"), "sos:"+XmlTagConstants.SYSTEM);
			clientNode.appendChild(doc.createTextNode(ModelHelper.getClientElement(element).getLocalID()));
			
			joinsNode.appendChild(supplierNode);
			joinsNode.appendChild(clientNode);
			
			parentNode.appendChild(joinsNode);
		}
	}
	
	public static void buildXMLFunctionLinks(Document doc, org.w3c.dom.Element rootElement, LinkingHelper lh, Collection<Element> elements)
	{
		for(Element element : elements)
		{
			//Get the function based on guid
			String functionLocalId = element.getLocalID();
			org.w3c.dom.Element functionNode = lh.functionLookup(functionLocalId);
			
			//Get the system based on guid
			String systemLocalId = element.getOwner().getLocalID();
			org.w3c.dom.Element systemNode = lh.systemLookup(systemLocalId);
			
			//Create guid for new process element
			String processLocalId = UUID.randomUUID().toString();
			
			//Create process element and supply appropriate linking to system and function elements
			org.w3c.dom.Element processNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.PROCESS);
			org.w3c.dom.Element processHuddleIdNode = doc.createElement(XmlTagConstants.HUDDLE_ID);
			org.w3c.dom.Element processLocalIdNode = doc.createElement(XmlTagConstants.LOCAL_ID);
			processLocalIdNode.appendChild(doc.createTextNode(processLocalId));
			org.w3c.dom.Element processCharsNode = doc.createElement(XmlTagConstants.CHARACTERIZATIONS);
			org.w3c.dom.Element executesNode = doc.createElement(XmlTagConstants.EXECUTES);
			org.w3c.dom.Element executesRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.FUNCTION);
			executesRefNode.appendChild(doc.createTextNode(functionLocalId));
			org.w3c.dom.Element performedByNode = doc.createElement(XmlTagConstants.PERFORMED_BY);
			org.w3c.dom.Element performedByRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:sos"), "sos:"+XmlTagConstants.SYSTEM);
			performedByRefNode.appendChild(doc.createTextNode(systemLocalId));
			
			//Build the process node
			performedByNode.appendChild(performedByRefNode);
			executesNode.appendChild(executesRefNode);
			processNode.appendChild(processHuddleIdNode);
			processNode.appendChild(processLocalIdNode);
			processNode.appendChild(processCharsNode);
			processNode.appendChild(executesNode);
			processNode.appendChild(performedByNode);
			
			//Link the system to the process
			//There should be only one 'performs' element under each system
			Node performsNode = systemNode.getElementsByTagName(XmlTagConstants.PERFORMS).item(0);
			org.w3c.dom.Element performsRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.PROCESS);
			performsRefNode.appendChild(doc.createTextNode(processLocalId));
			performsNode.appendChild(performsRefNode);
			
			//Link the function to the process
			//There should be only one 'executedBy' element under each function
			Node executedByNode = functionNode.getElementsByTagName(XmlTagConstants.EXECUTED_BY).item(0);
			org.w3c.dom.Element executedByRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.PROCESS);
			executedByRefNode.appendChild(doc.createTextNode(processLocalId));
			executedByNode.appendChild(executedByRefNode);
			
			//Link the process to the root (splash)
			rootElement.appendChild(processNode);
		}
	}
	
	public static void buildXMLEventLinks(Document doc, org.w3c.dom.Element rootElement, LinkingHelper lh, Collection<Element> elements)
	{
		ArrayList<org.w3c.dom.Element> outportNodeList = new ArrayList<org.w3c.dom.Element>();
		ArrayList<org.w3c.dom.Element> eventTraceNodeList = new ArrayList<org.w3c.dom.Element>();
		
		for(Element element : elements)
		{
			//Get the event based on guid. Note there are no links from event to event trace, so the event node does not need modifying
			String eventLocalId = element.getLocalID();
			
			//Get the function based on guid
			String functionLocalId = element.getOwner().getLocalID();
			org.w3c.dom.Element functionNode = lh.functionLookup(functionLocalId);
			
			//Create guid for new outport and event trace elements
			String outportLocalId = UUID.randomUUID().toString();
			String eventTraceLocalId = UUID.randomUUID().toString();
			
			//Create eventTrace element
			org.w3c.dom.Element eventTraceNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:analysis"), "analysis:"+XmlTagConstants.EVENT_TRACE);
			org.w3c.dom.Element eventTraceHuddleIdNode = doc.createElement(XmlTagConstants.HUDDLE_ID);
			org.w3c.dom.Element eventTraceLocalIdNode = doc.createElement(XmlTagConstants.LOCAL_ID);
			eventTraceLocalIdNode.appendChild(doc.createTextNode(eventTraceLocalId));
			org.w3c.dom.Element eventTraceCharsNode = doc.createElement(XmlTagConstants.CHARACTERIZATIONS);
			org.w3c.dom.Element pointsToNode = doc.createElement(XmlTagConstants.POINTS_TO);
			org.w3c.dom.Element pointsToRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:analysis"), "analysis:"+XmlTagConstants.EVENT);
			pointsToRefNode.appendChild(doc.createTextNode(eventLocalId));
			org.w3c.dom.Element producedByNode = doc.createElement(XmlTagConstants.PRODUCED_BY);
			org.w3c.dom.Element producedByRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.OUTPORT);
			producedByRefNode.appendChild(doc.createTextNode(outportLocalId));
			org.w3c.dom.Element consumedByNode = doc.createElement(XmlTagConstants.CONSUMED_BY);
			
			
			//Link eventTrace element nodes
			producedByNode.appendChild(producedByRefNode);
			pointsToNode.appendChild(pointsToRefNode);
			eventTraceNode.appendChild(eventTraceHuddleIdNode);
			eventTraceNode.appendChild(eventTraceLocalIdNode);
			eventTraceNode.appendChild(eventTraceCharsNode);
			eventTraceNode.appendChild(pointsToNode);
			eventTraceNode.appendChild(producedByNode);
			eventTraceNode.appendChild(consumedByNode);
			
			//Create outport element
			org.w3c.dom.Element outportNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.OUTPORT);
			org.w3c.dom.Element outportHuddleIdNode = doc.createElement(XmlTagConstants.HUDDLE_ID);
			org.w3c.dom.Element outportLocalIdNode = doc.createElement(XmlTagConstants.LOCAL_ID);
			outportLocalIdNode.appendChild(doc.createTextNode(outportLocalId));
			org.w3c.dom.Element outportCharsNode = doc.createElement(XmlTagConstants.CHARACTERIZATIONS);
			org.w3c.dom.Element producesNode = doc.createElement(XmlTagConstants.PRODUCES);
			org.w3c.dom.Element producesRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:analysis"), "analysis:"+XmlTagConstants.EVENT_TRACE);
			producesRefNode.appendChild(doc.createTextNode(eventTraceLocalId));
			org.w3c.dom.Element exposedByNode = doc.createElement(XmlTagConstants.EXPOSED_BY);
			org.w3c.dom.Element exposedByRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.FUNCTION);
			exposedByRefNode.appendChild(doc.createTextNode(functionLocalId));
			
			//Link outport element nodes
			exposedByNode.appendChild(exposedByRefNode);
			producesNode.appendChild(producesRefNode);
			outportNode.appendChild(outportHuddleIdNode);
			outportNode.appendChild(outportLocalIdNode);
			outportNode.appendChild(outportCharsNode);
			outportNode.appendChild(producesNode);
			outportNode.appendChild(exposedByNode);
			
			Node exposesNode = functionNode.getElementsByTagName(XmlTagConstants.EXPOSES).item(0);
			org.w3c.dom.Element exposesRefNode = doc.createElementNS(XmlTagConstants.getHuddleNS().get("xmlns:behavior"), "behavior:"+XmlTagConstants.OUTPORT);
			exposesRefNode.appendChild(doc.createTextNode(outportLocalId));
			exposesNode.appendChild(exposesRefNode);
			
			//The addition to lists is for nicer printing/organization in the XML so that all outports and event traces can be grouped together
			outportNodeList.add(outportNode);
			eventTraceNodeList.add(eventTraceNode);
		}
		
		//Once all elements have been created add the outports and event traces to the XML
		for(org.w3c.dom.Element e : outportNodeList)
			rootElement.appendChild(e);
		
		for(org.w3c.dom.Element e : eventTraceNodeList)
			rootElement.appendChild(e);
	}
	
	public static void buildXMLEventLinks(Document doc, LinkingHelper lh, Collection<Element> elements)
	{
		for(Element element : elements)
		{
			String guid = element.getLocalID();
			org.w3c.dom.Element parentNode = lh.interactionLookup(guid);
		}
	}
}