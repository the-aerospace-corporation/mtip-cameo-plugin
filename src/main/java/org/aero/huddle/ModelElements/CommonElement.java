package org.aero.huddle.ModelElements;

import java.util.HashMap;
import java.util.List;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.impl.ElementsFactory;

public abstract class CommonElement {
	protected String name;
	protected String EAID;
	protected String sysmlConstant;
	protected String xmlConstant;
	
	public CommonElement(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
	}
	
	public abstract Element createElement(Project project, Element owner, XMLItem xmlElement);
	
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
				
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(this.xmlConstant));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
	
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
	
	public Element createElementFromElementsFactory(Project project, Element owner) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		
		Element sysmlElement = f.createClassInstance();
		((NamedElement)sysmlElement).setName(name);
		
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
	
	public HashMap<String, Element> createElementInstanceMap() {
		return null;
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
		if(!name.equals("") && !name.equals(null)) {
			org.w3c.dom.Element name = xmlDoc.createElement("name");
			name.appendChild(xmlDoc.createTextNode(this.name));
			attributes.appendChild(name);
		} else {
			org.w3c.dom.Element name = xmlDoc.createElement("name");
			attributes.appendChild(name);
		}
		data.appendChild(attributes);
		
		//Get stereotypes
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		for(Stereotype stereotype : stereotypes) {
			CameoUtils.logGUI("Found stereotype : " + stereotype.getName());
			if(stereotype.getName() != "" && stereotype.getName() != null) {
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package profile = StereotypesHelper.getProfileForStereotype(stereotype);
				String profileName = profile.getName();
				
				org.w3c.dom.Element xmlStereotype = xmlDoc.createElement("stereotype");
				xmlStereotype.setAttribute("profile", profileName);
				xmlStereotype.setAttribute("profileId",  profile.getLocalID());
				xmlStereotype.appendChild(xmlDoc.createTextNode(stereotype.getName()));
				attributes.appendChild(xmlStereotype);
			}
			
		}
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
			Element parent = null;
			if((element instanceof Pseudostate) || (element instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)) {
				Element region = element.getOwner();
				parent = region.getOwner();
			} else {
				parent = element.getOwner();
			}
			hasParent.appendChild(xmlDoc.createTextNode(parent.getLocalID()));
			relationship.appendChild(hasParent);
		}
		data.appendChild(relationship);
		
		return data;
	}
	
	public Element createNestedPorts(Project project, Element owner) {
		Element sysmlPackage = CameoUtils.findNearestPackage(project, owner);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype blockStereotype = StereotypesHelper.getStereotype(project, "Block", sysmlProfile);
		
		Element block = createClassWithStereotype(project, owner.getHumanName().replace("Port ", ""), blockStereotype, sysmlPackage);
		return block;
	}
	
	public Element createNestedProperties(Project project, Element owner) {
		Element sysmlPackage = CameoUtils.findNearestPackage(project, owner);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype blockStereotype = StereotypesHelper.getStereotype(project, "Block", sysmlProfile);
		
		Element block = createClassWithStereotype(project, name, blockStereotype, sysmlPackage);
		return block;
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
	
	public boolean isTyped(Element element) {
		TypedElement elementTyped = (TypedElement)element;
		Type type = elementTyped.getType();
		if(type == null) {
			return false;
		} else {
			return true;
		}
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
