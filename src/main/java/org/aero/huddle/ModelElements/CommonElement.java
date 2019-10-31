package org.aero.huddle.ModelElements;

import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.impl.ElementsFactory;

public abstract class CommonElement {
	protected String name;
	protected String EAID;
	
	public CommonElement(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
	}
	
	public abstract Element createElement(Project project, Element owner, XMLItem xmlElement);
	
	public abstract void writeToXML(Element element, Project project, Document xmlDoc);
	
	public static Element createClassWithStereotype(Project project, String name,  Stereotype stereotype, Element owner) {;
		boolean externalSession = false;
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		} else {
			externalSession = true;
		}
	
		Class sysmlElement = project.getElementsFactory().createClassInstance();
		sysmlElement.setName(name);
		if (stereotype != null) {
			StereotypesHelper.addStereotype(sysmlElement, stereotype);
		}
	
		if (owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		if(!externalSession) {
			SessionManager.getInstance().closeSession(project);
		}
		return sysmlElement;
	}
	
	public static Region createRegion(Project project, Element owner) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Region Element");
		}
		ElementsFactory elementsFactory = project.getElementsFactory();
		Region region = elementsFactory.createRegionInstance();
		
		//Region must be a child of a state machine
		if(owner != null) {
			region.setOwner(owner);
		}
		
		return region;
	}
	
	public org.w3c.dom.Element createBaseXML(Element element, Document xmlDoc) {
		org.w3c.dom.Element data = xmlDoc.createElement("data");
		
		//Add attributes
		org.w3c.dom.Element attributes = xmlDoc.createElement("attributes");
		
		//Add Name
		org.w3c.dom.Element name = xmlDoc.createElement("name");
		name.appendChild(xmlDoc.createTextNode(this.name));
		attributes.appendChild(name);
		data.appendChild(attributes);
		
		//Add ID
		org.w3c.dom.Element id = xmlDoc.createElement("id");
		org.w3c.dom.Element cameoID = xmlDoc.createElement("cameo");
		cameoID.appendChild(xmlDoc.createTextNode(element.getLocalID()));
		id.appendChild(cameoID);
		data.appendChild(id);
		
		//Add parent relationship
		org.w3c.dom.Element relationship = xmlDoc.createElement("relationships");
		
		if(element.getOwner() != null) {
			org.w3c.dom.Element hasParent = xmlDoc.createElement("hasParent");
			Element parent = element.getOwner();
			hasParent.appendChild(xmlDoc.createTextNode(parent.getLocalID()));
			relationship.appendChild(hasParent);
		}
		data.appendChild(relationship);
		
		return data;
	}
	
	public org.w3c.dom.Element getAttributes(NodeList dataNodes) {
		org.w3c.dom.Element attributes = null;
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				if(dataNode.getNodeName().equals("attributes")) {
					attributes = (org.w3c.dom.Element) dataNode;
				}
			}
		}
		return attributes;
	}
	
	public org.w3c.dom.Element getType(NodeList dataNodes, String type) {
		org.w3c.dom.Element attributes = null;
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				if(dataNode.getNodeName().equals(type.toLowerCase())) {
					attributes = (org.w3c.dom.Element) dataNode;
				}
			}
		}
		return attributes;
	}
}
