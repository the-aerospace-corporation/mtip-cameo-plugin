package org.aero.huddle.ModelElements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.CheckForNull;

import org.aero.huddle.XML.Export.ExportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ExportLog;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Classifier;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.impl.ElementsFactory;

public abstract class CommonElement {
	public static final String invalidParentRoot = "Invalid parent - not SysML compliant."; 
	protected String name;
	protected String EAID;
	protected String sysmlConstant;
	protected String xmlConstant;
	protected String creationType;
	protected ElementsFactory f;
	protected Element sysmlElement;
	protected Profile creationProfile;
	protected Stereotype creationStereotype;
	protected List<Stereotype> initialStereotypes;
	protected Element classifier;
	protected Project project;
	
	protected String requiredParentType;
	protected String invalidParentMessage;
	
	public CommonElement(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
		this.f = Application.getInstance().getProject().getElementsFactory();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if(this.creationType.contentEquals(XmlTagConstants.ELEMENTSFACTORY)) {
			createElementByElementFactory(project, owner, xmlElement);
		}
		if(this.creationType.contentEquals(XmlTagConstants.CLASS_WITH_STEREOTYPE)) {
			createClassWithStereotype(project, this.creationStereotype, owner);
		}
		addDocumentation(xmlElement);
		
		return sysmlElement;
	}
		
	protected Element createElementByElementFactory(Project project, Element owner, XMLItem xmlElement) {
		if(sysmlElement instanceof NamedElement) {
			((NamedElement)sysmlElement).setName(name);
		}
		setOwner(project, owner);
		addStereotype();
		setClassifier();
		applyClassifier();
		return sysmlElement;
	}
	
	public void setOwner(Project project, Element owner) {
		if(sysmlElement != null) {
			if(owner != null) {
				try {
					sysmlElement.setOwner(owner);
				} catch(IllegalArgumentException iae){
					setInvalidParentMessage(owner);
					ImportLog.log(invalidParentMessage);
					sysmlElement.dispose();
				}	
			} else {
				try {
					sysmlElement.setOwner(project.getPrimaryModel());
				} catch(IllegalArgumentException iae){
					String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
					CameoUtils.logGUI(invalidParentMessage);
					ImportLog.log(invalidParentMessage);
					sysmlElement.dispose();
				}
			}
		} else {
			ImportLog.log("Sysml Element was not created. Cannot set owner");
		}
	}
	
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = xmlDoc.createElement(XmlTagConstants.DATA);
		org.w3c.dom.Element attributes = xmlDoc.createElement(XmlTagConstants.ATTRIBUTES);
		
		if(name != null && !name.isEmpty()) {
			org.w3c.dom.Element nameTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME,	this.name);
			attributes.appendChild(nameTag);
		} 

		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		for(Stereotype stereotype : stereotypes) {
			org.w3c.dom.Element attributeStereotypeTag = createStereotype(xmlDoc, stereotype);
			attributes.appendChild(attributeStereotypeTag);
		}
		String documentation = ModelHelper.getComment(element);
		if(!documentation.isEmpty()) {
			org.w3c.dom.Element documentationTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION, documentation);
			attributes.appendChild(documentationTag);
		}
		
		
		org.w3c.dom.Element id = createIdTag(xmlDoc, element);
		if(element.getOwner() != null) {
			org.w3c.dom.Element relationships = createRelationships(xmlDoc, element);
			data.appendChild(relationships);
		}
		
		org.w3c.dom.Element type = createTypeTag(xmlDoc);
		
		data.appendChild(id);
		data.appendChild(attributes);
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
		return data;
	}
	
	public Element createClassWithStereotype(Project project, Stereotype stereotype, Element owner) {
		sysmlElement = project.getElementsFactory().createClassInstance();
		if (stereotype != null) {
			StereotypesHelper.addStereotype(sysmlElement, stereotype);
		}
		
		setOwner(project, owner);
		if(sysmlElement instanceof NamedElement) {
			((NamedElement)sysmlElement).setName(name);
		}

		return sysmlElement;
	}
	public Element createClassWithStereotype(Project project, String name, Stereotype stereotype, Element owner) {
		Class sysmlElement = project.getElementsFactory().createClassInstance();
		setOwner(project, sysmlElement);
		sysmlElement.setName(name);
		
		if (stereotype != null) {
			StereotypesHelper.addStereotype(sysmlElement, stereotype);
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

	
	public void logInvalidParent() {
		String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
		CameoUtils.logGUI(logMessage);
		ImportLog.log(logMessage);
		sysmlElement.dispose();
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
				if(dataNode.getNodeName().equals(XmlTagConstants.ATTRIBUTES)) {
					attributes = (org.w3c.dom.Element) dataNode;
				}
			}
		}
		return attributes;
	}
	
	public org.w3c.dom.Element getRelationships(NodeList dataNodes) {
		org.w3c.dom.Element attributes = null;
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				if(dataNode.getNodeName().equals(XmlTagConstants.RELATIONSHIPS)) {
					attributes = (org.w3c.dom.Element) dataNode;
				}
			}
		}
		return attributes;
	}
	
	public org.w3c.dom.Element getRelList(NodeList dataNodes) {
		org.w3c.dom.Element relList = null;
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				if(dataNode.getNodeName().equals(XmlTagConstants.HAS_REL)) {
					relList = (org.w3c.dom.Element) dataNode;
				}
			}
		}
		return relList;
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
	
	protected String setInvalidParentMessage(Element owner) {
		try {
			invalidParentMessage = CommonElement.invalidParentRoot + sysmlConstant + " must be a child of " + requiredParentType + ".\n\tName = " + name + "; ID = " + EAID + "; Invalid Parent Type = " + owner.getHumanType();
		} catch(NullPointerException npe) {
			invalidParentMessage = CommonElement.invalidParentRoot + sysmlConstant + " must be a child of " + requiredParentType + ".\n\tName = " + name + "; ID = " + EAID + "; Invalid Parent Type = null";
		}
		return invalidParentMessage;
	}
	
	protected void addDocumentation(XMLItem xmlElement) {
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION)) {
			ModelHelper.setComment(sysmlElement, xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION));
		}
	}

	protected org.w3c.dom.Element createAttribute(Document xmlDoc, String keyValue, String dataTypeValue, String attributeValue) {
		org.w3c.dom.Element tag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, keyValue);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		if(!(attributeValue == null)) {
			org.w3c.dom.Element subTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
			subTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, dataTypeValue);
			subTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			subTag.appendChild(xmlDoc.createTextNode(attributeValue));
			tag.appendChild(subTag);
		}

		return tag;
	}
	
	protected org.w3c.dom.Element createStringAttribute(Document xmlDoc, String keyValue, String attributeValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_STRING, attributeValue);
	}
	
	protected org.w3c.dom.Element createDictAttribute(Document xmlDoc, String keyValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_DICT, null);
	}
	
	protected org.w3c.dom.Element createListElement(Document xmlDoc, String keyValue) {
		return createElement(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_LIST, null);
	}
	
	protected org.w3c.dom.Element createDictRelationship(Document xmlDoc, String keyValue) {
		return createRelationship(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_DICT, null);
	}

	protected org.w3c.dom.Element createRelationship(Document xmlDoc, String keyValue, String dataTypeValue, String attributeValue) {
		org.w3c.dom.Element tag = xmlDoc.createElement(XmlTagConstants.DIAGRAM_CONNECTOR);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, keyValue);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, dataTypeValue);
		if(!(attributeValue == null)) {
			tag.appendChild(xmlDoc.createTextNode(attributeValue));
		}
		return tag;
	}
	
	protected org.w3c.dom.Element createElement(Document xmlDoc, String keyValue, String dataTypeValue, String attributeValue) {
		org.w3c.dom.Element tag = xmlDoc.createElement(XmlTagConstants.ELEMENT);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, keyValue);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, dataTypeValue);
		if(!(attributeValue == null)) {
			tag.appendChild(xmlDoc.createTextNode(attributeValue));
		}
		return tag;
	}
	
	@CheckForNull
	protected org.w3c.dom.Element createStereotype(Document xmlDoc, Stereotype stereotype) {
		CameoUtils.logGUI("Found stereotype : " + stereotype.getName());
		if(stereotype.getName() != "" && stereotype.getName() != null) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package profile = StereotypesHelper.getProfileForStereotype(stereotype);
			
			org.w3c.dom.Element stereotypeAttribute = createDictAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE);
			org.w3c.dom.Element stereotypeNameAttribute = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME, stereotype.getName());
			org.w3c.dom.Element stereotypeIdAttribute = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_ID, stereotype.getLocalID());
			org.w3c.dom.Element profileNameAttribute = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME, profile.getName());
			org.w3c.dom.Element profileIdAttribute = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_ID, profile.getLocalID());
			
			stereotypeAttribute.appendChild(stereotypeNameAttribute);
			stereotypeAttribute.appendChild(stereotypeIdAttribute);
			stereotypeAttribute.appendChild(profileNameAttribute);
			stereotypeAttribute.appendChild(profileIdAttribute);

			return stereotypeAttribute;
		}
		return null;
	}

	protected org.w3c.dom.Element createIdTag(Document xmlDoc, Element element) {
		org.w3c.dom.Element id = xmlDoc.createElement(XmlTagConstants.ID);
		id.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		org.w3c.dom.Element cameoID = xmlDoc.createElement(XmlTagConstants.CAMEO);
		cameoID.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		cameoID.appendChild(xmlDoc.createTextNode(element.getLocalID()));
		id.appendChild(cameoID);
		
		return id;
	}
	
	protected org.w3c.dom.Element createTypeTag(Document xmlDoc) {
		org.w3c.dom.Element type = xmlDoc.createElement(XmlTagConstants.TYPE);
		type.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		if(this.xmlConstant != null) {
			type.appendChild(xmlDoc.createTextNode(this.xmlConstant));
		} else {
			CameoUtils.logGUI("InternalError: xmlConstant not set for element with id " + this.EAID);
			ExportLog.log("InternalError: xmlConstant must defined for element with id " + this.EAID + " to export correctly.");
		}
		
		return type;
	}
	
	protected org.w3c.dom.Element createRelationships(Document xmlDoc, Element element) {
		org.w3c.dom.Element relationships = xmlDoc.createElement(XmlTagConstants.RELATIONSHIPS);
		relationships.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		org.w3c.dom.Element hasParent = createRel(xmlDoc, element.getOwner(), XmlTagConstants.HAS_PARENT);
		relationships.appendChild(hasParent);
		
		return relationships;
	}

	protected org.w3c.dom.Element createRel(Document xmlDoc, Element element, String xmlTag) {
		org.w3c.dom.Element hasRel = xmlDoc.createElement(xmlTag);
//		hasRel.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, index);
		hasRel.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		org.w3c.dom.Element type = xmlDoc.createElement(XmlTagConstants.TYPE);
		type.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		String typeStr = ExportXmlSysml.getElementType(element);
		type.appendChild(xmlDoc.createTextNode("sysml." + typeStr));
//		
		org.w3c.dom.Element value = xmlDoc.createElement(XmlTagConstants.ID);
		value.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		value.appendChild(xmlDoc.createTextNode(element.getLocalID()));
		
		org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
		relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		hasRel.appendChild(type);
		hasRel.appendChild(value);
		hasRel.appendChild(relDataTag);
		return hasRel;
	}
	
	protected void addStereotype() {
		if(initialStereotypes != null) {
			for(Stereotype stereotype : initialStereotypes) {
				if(stereotype != null) {
					StereotypesHelper.addStereotype(sysmlElement, stereotype);
				} else {
					ImportLog.log("Unable to add stereotype to " + this.name + " with id "  + this.EAID);
					CameoUtils.logGUI("Unable to add stereotype to " + this.name + " with id "  + this.EAID);
				}
			}
		}
	}
	
	protected void setClassifier() {
		
	}
	
	protected void applyClassifier() {
		Classifier classifier = (Classifier) this.classifier;
		if(classifier != null) {
			ModelHelper.setClassifierForInstanceSpecification(classifier, (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification) sysmlElement, true);
		}
//			} else {
//			ImportLog.log("Unable to add classifier to " + this.name + " with id "  + this.EAID);
//			CameoUtils.logGUI("Unable to add classifier to " + this.name + " with id "  + this.EAID);
//		}
	}
}
