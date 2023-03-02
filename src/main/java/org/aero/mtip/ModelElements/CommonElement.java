/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.CheckForNull;

import org.aero.mtip.XML.Export.ExportXmlSysml;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Classifier;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
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
		setName();
		setOwner(project, owner);
		addInitialStereotype();
		setClassifier();
		applyClassifier();
		addDocumentation(xmlElement);
		addMultiplicity(xmlElement);
		addType(xmlElement);
		
		return sysmlElement;
	}
	
	protected String setName() {
		if(sysmlElement instanceof NamedElement) {
			((NamedElement)sysmlElement).setName(name);
		}
		return name;
	}
		
	protected Element createElementByElementFactory(Project project, Element owner, XMLItem xmlElement) {
		this.project = project;		
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
					CameoUtils.logGUI(invalidParentMessage);
					ImportLog.log(invalidParentMessage);
					sysmlElement.dispose();
				}
			}
		} else {
			ImportLog.log("Sysml Element was not created. Cannot set owner");
		}
	}
	
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasTaggedValues()) {
			for(TaggedValue tv : modelElement.getTaggedValues()) {
				if(tv.getValueType().contentEquals(SysmlConstants.ELEMENT)) {
					if(tv.isMultiValue()) {
						List<String> values = tv.getValues();
						for(String value : values) {
							ImportLog.log("Creating tagged value element with id " + value);
							ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(value), value);
						}
					} else {
						String value = tv.getValue();
						ImportLog.log("Creating tagged value element with id " + value);
						ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(value), value);
					}
				}
			}
		}
		if(modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			if(parsedXML.containsKey(modelElement.getAttribute(XmlTagConstants.TYPED_BY))) {
				ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.TYPED_BY)), modelElement.getAttribute(XmlTagConstants.TYPED_BY));
			}
		}
	}
	
	public void addDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = xmlDoc.createElement(XmlTagConstants.DATA);
		org.w3c.dom.Element attributes = xmlDoc.createElement(XmlTagConstants.ATTRIBUTES);
		attributes.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		if(name != null && !name.isEmpty()) {
			org.w3c.dom.Element nameTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME,	this.name);
			attributes.appendChild(nameTag);
		} 

		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		for(Stereotype stereotype : stereotypes) {
			org.w3c.dom.Element attributeStereotypeTag = createStereotype(xmlDoc, stereotype);
			attributes.appendChild(attributeStereotypeTag);
			
			createStereotypeAttributes(xmlDoc, project, element, stereotype, attributes);
		}
		String documentation = ModelHelper.getComment(element).replaceAll("\\<.*?>","").replace("p {padding:0px; margin:0px;}", "").trim();
		if(!documentation.isEmpty()) {
			org.w3c.dom.Element documentationTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION, documentation);
			attributes.appendChild(documentationTag);
		}
		
		
		org.w3c.dom.Element id = createIdTag(xmlDoc, element);
		
		org.w3c.dom.Element relationships = createRelationships(xmlDoc, element);
		data.appendChild(relationships);
		
		org.w3c.dom.Element type = createTypeTag(xmlDoc);
		
		data.appendChild(id);
		data.appendChild(attributes);
		data.appendChild(type);
		
		if(element instanceof TypedElement) {
			org.w3c.dom.Element typedByTag = createTypedByTag(element, xmlDoc);
			if(typedByTag != null) {
				relationships.appendChild(typedByTag);
			}
		}
		
		if(element instanceof MultiplicityElement) {
			String multiplicity = ModelHelper.getMultiplicity((MultiplicityElement) element);
			if(!multiplicity.isEmpty()) {
				org.w3c.dom.Element multiplicityTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_MULTIPLICITY, multiplicity);
				attributes.appendChild(multiplicityTag);
			}
		}
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
		return data;
	}
	
	public void createStereotypeAttributes(Document xmlDoc, Project project, Element element, Stereotype stereotype, org.w3c.dom.Element attributes) {
		List<Property> properties = StereotypesHelper.getPropertiesWithDerivedOrdered(stereotype);
		for(Property property : properties) {
			Slot slot = StereotypesHelper.getSlot(element, stereotype, property, false, false);
			if(slot != null) {
				List<ValueSpecification> vss = slot.getValue();
				if(vss != null && (!vss.isEmpty())) {
					ValueSpecification vs = vss.get(0);
					String valueType = getValueSpecificationValueType(vs);
					if(valueType != null) {
						ExportLog.log("Creating tagged value block for element " + element.getID() + " with type " + valueType);
						org.w3c.dom.Element stereotypeAttributeTag = createStereotypeAttributeTag(xmlDoc, stereotype.getName(), StereotypesHelper.getProfileForStereotype(stereotype).getName(), property.getName(), valueType, vss);
						attributes.appendChild(stereotypeAttributeTag);
					} else {
						ExportLog.log("Value type or slot value null. Value specification may not be string, real, int, bool, or opaque expression.");
					}
				} else {
					// null valuespecification list from slot
				}
			}
		}
	}
	
	@CheckForNull
	public String getValueSpecificationValueType(ValueSpecification vs) {
		ExportLog.log("Value specification type " + vs.getHumanType());
		if(vs instanceof LiteralString) {
			return SysmlConstants.STRING;
		} else if(vs instanceof LiteralReal) {
			return SysmlConstants.REAL;
		} else if(vs instanceof LiteralInteger) {
			return SysmlConstants.INTEGER;
		} else if(vs instanceof LiteralBoolean) {
			return SysmlConstants.BOOLEAN;
		} else if(vs instanceof ElementValue) {
			return SysmlConstants.ELEMENT;
		} else if(vs instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral) {
			return SysmlConstants.ENUMERATIONLITERAL;
		} else if(vs instanceof InstanceValue) {
			return SysmlConstants.INSTANCEVALUE;
//		} else if(vs instanceof OpaqueExpression) {
//			return SysmlConstants.OPAQUEEXPRESSION;
		} else {
			String message = "Value specification with id " + vs.getID() + " was not string, real, int, or bool.";
			ExportLog.log(message);
			CameoUtils.logGUI(message);
		}
		return null;
	}
	
	@CheckForNull
	public String getSlotValueAsString(ValueSpecification vs) {
		String strVal = null;
		if(vs instanceof LiteralString) {
			LiteralString ls = (LiteralString)vs;
			strVal = ls.getValue();
		} else if(vs instanceof LiteralReal) {
			LiteralReal lr = (LiteralReal)vs;
			double value = lr.getValue();
			strVal = String.valueOf(value);
		} else if(vs instanceof LiteralInteger) {
			LiteralInteger lr = (LiteralInteger)vs;
			int value = lr.getValue();
			strVal = String.valueOf(value);
		} else if(vs instanceof LiteralBoolean) {
			LiteralBoolean lr = (LiteralBoolean)vs;
			boolean value = lr.isValue();
			strVal = String.valueOf(value);
		} else if(vs instanceof ElementValue) {
			ElementValue ev = (ElementValue)vs;
			strVal = ev.getElement().getID();
		} else if(vs instanceof OpaqueExpression) {
			OpaqueExpression oe = (OpaqueExpression)vs;
			List<String> bodies = oe.getBody();
			Iterator<String> bodyIter = bodies.iterator();
			if(bodyIter.hasNext()) {
				strVal = bodyIter.next();
			}
		} else if(vs instanceof EnumerationLiteral) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral literal = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral)vs;
			strVal = literal.getName();
		} else if(vs instanceof InstanceValue ) {
			strVal = ModelHelper.getValueString(vs);
//			EnumerationLiteral  litVal = (EnumerationLiteral)ValueSpecificationHelper.getValueSpecficationValue(vs);
//			InstanceValue iv = (InstanceValue)vs;
//			InstanceSpecification is = iv.getInstance();
//			ValueSpecification vs2 = is.getSpecification();
//			strVal = getSlotValueAsString(vs2);
		}else {
			String message = "Value specification with id " + vs.getID() + " was not string, real, int, bool, or opaque expression.";
			ExportLog.log(message);
			CameoUtils.logGUI(message);
		}
		return strVal;
	}
	
	public org.w3c.dom.Element createStereotypeAttributeTag(Document xmlDoc, String stereotypeName, String profileName, String propertyName, String valueType, List<ValueSpecification> valueSpecifications) {
		org.w3c.dom.Element attribute = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		attribute.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		attribute.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.STEREOTYPE_TAGGED_VALUE);
		
		org.w3c.dom.Element stereotypeNameTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		stereotypeNameTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		stereotypeNameTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME);
		stereotypeNameTag.appendChild(xmlDoc.createTextNode(stereotypeName));
		attribute.appendChild(stereotypeNameTag);
		
		org.w3c.dom.Element profileNameTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		profileNameTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		profileNameTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME);
		profileNameTag.appendChild(xmlDoc.createTextNode(profileName));		
		
		org.w3c.dom.Element taggedValueNameTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		taggedValueNameTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		taggedValueNameTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.TAGGED_VALUE_NAME);
		taggedValueNameTag.appendChild(xmlDoc.createTextNode(propertyName));
		
		org.w3c.dom.Element taggedValueTypeTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		taggedValueTypeTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		taggedValueTypeTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.TAGGED_VALUE_TYPE);
		taggedValueTypeTag.appendChild(xmlDoc.createTextNode(valueType));
		
		if(valueSpecifications.size() == 1) {
			ValueSpecification vs = valueSpecifications.get(0);
			String slotValue = getSlotValueAsString(vs);
			
			org.w3c.dom.Element taggedValueValueTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
			taggedValueValueTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
			taggedValueValueTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.TAGGED_VALUE_VALUE);
			taggedValueValueTag.appendChild(xmlDoc.createTextNode(slotValue));
			
			attribute.appendChild(taggedValueValueTag);
		} else if(valueSpecifications.size() > 1) {
			int valueCount = 0;
			org.w3c.dom.Element listTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
			listTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_LIST);
			listTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.TAGGED_VALUE_VALUE);
			
			for(ValueSpecification vs : valueSpecifications) {
				String slotValue = getSlotValueAsString(vs);
				if(slotValue != null) {
					org.w3c.dom.Element taggedValueValueTag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
					taggedValueValueTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
					taggedValueValueTag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, Integer.toString(valueCount));
					taggedValueValueTag.appendChild(xmlDoc.createTextNode(slotValue));
					
					listTag.appendChild(taggedValueValueTag);
					valueCount++;
				}
			}
			attribute.appendChild(listTag);
		}
		
		attribute.appendChild(stereotypeNameTag);
		attribute.appendChild(profileNameTag);
		attribute.appendChild(taggedValueNameTag);
		attribute.appendChild(taggedValueTypeTag);
		
		
		return attribute;		
	}
	
	public Element createClassWithStereotype(Project project, Stereotype stereotype, Element owner) {
		sysmlElement = project.getElementsFactory().createClassInstance();
		if (stereotype != null) {
			StereotypesHelper.addStereotype(sysmlElement, stereotype);
		}
		
		return sysmlElement;
	}
	public Element createClassWithStereotype(Project project, String name, Stereotype stereotype, Element owner) {
		sysmlElement = project.getElementsFactory().createClassInstance();
	
		if(sysmlElement instanceof NamedElement) {
			((NamedElement)sysmlElement).setName(name.replaceAll("[^a-zA-Z0-9]+",""));
		}
		
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
	
	protected void addMultiplicity(XMLItem xmlElement) {
		if(sysmlElement instanceof MultiplicityElement) {
			if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_MULTIPLICITY)) {
				ModelHelper.setMultiplicity(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_MULTIPLICITY), (MultiplicityElement) sysmlElement);
			}
		}
	}
	/**
	 * Gets typed by attribute and sets the type field based on the id in the typedBy field under relationships. 
	 * Checks for element in model with project.getElementByID() with the import ID, then checks for newly created 
	 * element with ImportXmlSysml.idConversion().
	 * @param xmlElement XMLItem containing attributes in memory from XML file such as typed by.
	 */
	@SuppressWarnings("deprecation")
	protected void addType(XMLItem xmlElement) {
		try {
			if(sysmlElement instanceof TypedElement) {
				Element typeElement = null;
				if(xmlElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
					String typeImportID = xmlElement.getAttribute(XmlTagConstants.TYPED_BY);
					CameoUtils.logGUI("Looking for type with id " + typeImportID);
					
					if(CameoUtils.isPrimitiveValueType(typeImportID)) {
						typeElement = CameoUtils.getPrimitiveValueType(typeImportID);
					} else if(typeImportID.startsWith("_9_")) {
						if(typeImportID.contentEquals("_9_0_2_91a0295_1110274713995_297054_0")) {
							CameoUtils.logGUI("Getting reference to UML primitive String ValueType.");
							typeElement = ModelHelper.findElementWithPath("UML Standard Profile::UML2 Metamodel::PrimitiveTypes::String");
							CameoUtils.logGUI(typeElement.getHumanName());
						}
					} else {
						try {
							typeElement = (Element) project.getElementByID(typeImportID);
						} catch (NullPointerException npe) {
							ImportLog.log("Type not found with id. Please check that element is created");
						}
					}
					
					if(typeElement == null) {
						CameoUtils.logGUI("Getting cameo id for type with import id: " + typeImportID);
						
						String cameoID = ImportXmlSysml.idConversion(typeImportID);
						CameoUtils.logGUI("Cameo id of type is: " + cameoID);
						typeElement = (Element) project.getElementByID(cameoID);
					}
					if(typeElement instanceof Type) {
						((TypedElement)sysmlElement).setType((Type) typeElement);
					} else {
						CameoUtils.logGUI("typedBy element not a Type. Type field cannot be set.");
						ImportLog.log("typedBy element not a Type. Type field cannot be set for element with id: " + this.EAID);
					}
				}
			}
		} catch(NullPointerException npe) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			npe.printStackTrace(pw);
			String sStackTrace = sw.toString();
			ImportLog.log("Null pointer exception setting type for element with id " + this.EAID);
			ImportLog.log(sStackTrace);
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
	
	protected org.w3c.dom.Element createKeyValueAttribute(Document xmlDoc, String keyValue, String dataTypeValue, String attributeValue) {
		org.w3c.dom.Element tag = xmlDoc.createElement(XmlTagConstants.ATTRIBUTE);
		if(!(attributeValue == null)) {
			tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, dataTypeValue);
			tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, keyValue);
			tag.appendChild(xmlDoc.createTextNode(attributeValue));
		}
		return tag;
	}
	
	protected org.w3c.dom.Element createStringAttribute(Document xmlDoc, String keyValue, String attributeValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_STRING, attributeValue);
	}
	
	protected org.w3c.dom.Element createFloatAttribute(Document xmlDoc, String keyValue, String attributeValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_FLOAT, attributeValue);
	}
	
	protected org.w3c.dom.Element createIntAttribute(Document xmlDoc, String keyValue, String attributeValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_INT, attributeValue);
	}
	
	protected org.w3c.dom.Element createBoolAttribute(Document xmlDoc, String keyValue, String attributeValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_BOOL, attributeValue);
	}
	
	protected org.w3c.dom.Element createDictAttribute(Document xmlDoc, String keyValue) {
		return createAttribute(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_DICT, null);
	}
	
	protected org.w3c.dom.Element createListElement(Document xmlDoc, String keyValue) {
		return createElement(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_LIST, null);
	}
	
	protected org.w3c.dom.Element createDictElement(Document xmlDoc, String keyValue) {
		return createElement(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_DICT, null);
	}
	
	protected org.w3c.dom.Element createDictRelationship(Document xmlDoc, String keyValue) {
		return createRelationship(xmlDoc, keyValue, XmlTagConstants.ATTRIBUTE_TYPE_DICT, null);
	}
	
	protected org.w3c.dom.Element createListRelationship(Document xmlDoc, String tagName) {
		org.w3c.dom.Element tag = xmlDoc.createElement(tagName);
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_LIST);
		return tag;
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
			org.w3c.dom.Element stereotypeNameAttribute = createKeyValueAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, stereotype.getName());
			org.w3c.dom.Element stereotypeIdAttribute = createKeyValueAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_ID, XmlTagConstants.ATTRIBUTE_TYPE_STRING, stereotype.getID());
			org.w3c.dom.Element profileNameAttribute = createKeyValueAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, profile.getName());
			org.w3c.dom.Element profileIdAttribute = createKeyValueAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_ID, XmlTagConstants.ATTRIBUTE_TYPE_STRING, profile.getID());
			
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
		
		// Create Local ID
		org.w3c.dom.Element cameoID = xmlDoc.createElement(XmlTagConstants.CAMEO);
		cameoID.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		cameoID.appendChild(xmlDoc.createTextNode(element.getID()));
		
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
		if(element.getOwner() != null) {
			org.w3c.dom.Element hasParent = createRel(xmlDoc, element.getOwner(), XmlTagConstants.HAS_PARENT);
			relationships.appendChild(hasParent);
		}
		
		
		return relationships;
	}
	protected org.w3c.dom.Element createListRel(Document xmlDoc, Element element, String xmlTag, String index) {
		org.w3c.dom.Element hasRel = xmlDoc.createElement(xmlTag);
		hasRel.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, index);
		hasRel.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		org.w3c.dom.Element type = xmlDoc.createElement(XmlTagConstants.TYPE);
		type.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		String typeStr = ExportXmlSysml.getElementType(element);
		type.appendChild(xmlDoc.createTextNode("sysml." + typeStr));
		
		org.w3c.dom.Element value = xmlDoc.createElement(XmlTagConstants.ID);
		value.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		value.appendChild(xmlDoc.createTextNode(element.getID()));
		
		org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
		relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		hasRel.appendChild(type);
		hasRel.appendChild(value);
		hasRel.appendChild(relDataTag);
		return hasRel;
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
		value.appendChild(xmlDoc.createTextNode(element.getID()));
		
		org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
		relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		hasRel.appendChild(type);
		hasRel.appendChild(value);
		hasRel.appendChild(relDataTag);
		return hasRel;
	}
	
	protected org.w3c.dom.Element createRel(Document xmlDoc, Element element, String xmlTag, boolean useLocalID) {
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
		if(useLocalID) {
			value.appendChild(xmlDoc.createTextNode(element.getLocalID()));
		} else {
			value.appendChild(xmlDoc.createTextNode(element.getID()));
		}
		
		
		org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
		relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		hasRel.appendChild(type);
		hasRel.appendChild(value);
		hasRel.appendChild(relDataTag);
		return hasRel;
	}
	
	protected org.w3c.dom.Element createPrimitiveTypedByTag(Document xmlDoc, Element element) {
		org.w3c.dom.Element hasRel = xmlDoc.createElement(XmlTagConstants.TYPED_BY);
		hasRel.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		org.w3c.dom.Element value = xmlDoc.createElement(XmlTagConstants.ID);
		value.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		value.appendChild(xmlDoc.createTextNode(CameoUtils.primitiveValueTypesByID.get(element.getLocalID())));
		
		org.w3c.dom.Element type = xmlDoc.createElement(XmlTagConstants.TYPE);
		type.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.PRIMITIVE_VALUE_TYPE));
		
		hasRel.appendChild(type);
		hasRel.appendChild(value);
		return hasRel;
	}
	
	protected void addInitialStereotype() {
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
	
	protected boolean isElement(String strValue) {
		if(strValue.matches("_\\d{1,2}_(0)")) {
			return true;
		}
		return false;
	}
	
	public org.w3c.dom.Element createAttributefromValueSpecification(ValueSpecification vs, String attrName, Document xmlDoc) {
		org.w3c.dom.Element strAttr = null;
		if(vs instanceof LiteralString) {
			LiteralString ls = (LiteralString)vs;
			String value = ls.getValue();
			strAttr = createStringAttribute(xmlDoc, attrName, value);
		} else if(vs instanceof LiteralReal) {
			LiteralReal lr = (LiteralReal)vs;
			double value = lr.getValue();
			String strVal = String.valueOf(value);
			strAttr = createFloatAttribute(xmlDoc, attrName, strVal);
		} else if(vs instanceof LiteralInteger) {
			LiteralInteger lr = (LiteralInteger)vs;
			int value = lr.getValue();
			String strVal = String.valueOf(value);
			strAttr = createIntAttribute(xmlDoc, attrName, strVal);
		} else if(vs instanceof LiteralBoolean) {
			LiteralBoolean lr = (LiteralBoolean)vs;
			boolean value = lr.isValue();
			String strVal = String.valueOf(value);
			strAttr = createBoolAttribute(xmlDoc, attrName, strVal);
		} else if(vs instanceof ElementValue) {
			ElementValue ev = (ElementValue)vs;
			String strVal = ev.getElement().getID();
			strAttr = createStringAttribute(xmlDoc, attrName, strVal);
		} else if(vs instanceof InstanceValue) {
			InstanceValue iv = (InstanceValue)vs;
			String strVal = iv.getInstance().getID();
			strAttr = createStringAttribute(xmlDoc, attrName, strVal);
		} else if(vs instanceof OpaqueExpression) {
			OpaqueExpression oe = (OpaqueExpression)vs;
			List<String> bodies= oe.getBody();
			Iterator<String> bodyIter = bodies.iterator();
			String body = null;
			if(bodyIter.hasNext()) {
				body = bodyIter.next();
			}
			if(body != null) {
				strAttr = createStringAttribute(xmlDoc, attrName, body);
			} else {
				ExportLog.log("Body of opaque expression with id " + vs.getID() + " has empty body.");
			}
		} else {
			String message = "Value specification with id " + vs.getID() + " was not string, real, int, or bool.";
			ExportLog.log(message);
			CameoUtils.logGUI(message);
		}
		return strAttr;
	}
	
	@CheckForNull
	protected org.w3c.dom.Element createTypedByTag(Element element, Document xmlDoc) {
		org.w3c.dom.Element typedByTag = null;
		Type cameoType =  ((TypedElement)element).getType();
		if(cameoType != null) {
			String typeName = ((NamedElement)cameoType).getName();
			ExportLog.log("Typed by element/value type with name: " + typeName);
			if(cameoType != null) {
				if(CameoUtils.isPrimitiveValueType(cameoType)) {
					typedByTag = createPrimitiveTypedByTag(xmlDoc, cameoType);
					CameoUtils.logGUI("Element with id " + element.getID() + " typed by primitive value");
					ExportLog.log("Element with id " + element.getID() + " typed by primitive value");
				}else {
					ExportLog.log("Element with id: " + element.getID() + " has type with id: " + cameoType.getLocalID());
					typedByTag = createRel(xmlDoc, cameoType, XmlTagConstants.TYPED_BY);
				}
			}
		}
		
		return typedByTag;
	}
	
	public void addStereotypeTaggedValues(XMLItem xmlElement) {
		CameoUtils.logGUI("Adding " + xmlElement.getTaggedValues().size() + " tagged values to element with id " + xmlElement.getEAID());
		for(TaggedValue tv : xmlElement.getTaggedValues()) {
			try {
				CameoUtils.logGUI(tv.toString());
				Profile profile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), tv.getProfileName());
				Stereotype stereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), tv.getStereotypeName(), profile);
				Property prop = StereotypesHelper.getPropertyByName(stereotype, tv.getValueName());
				Slot slot = StereotypesHelper.getSlot(sysmlElement, prop, true, false);
				if(tv.isMultiValue()) {
					for(String value : tv.getValues()) {
						ValueSpecification vs = updateValue(tv.getValueType(), value);
						if(value != null) {
							slot.getValue().add(vs);
						}
					}
				} else {
					ValueSpecification vs = updateValue(tv.getValueType(), tv.getValue());
					if(vs != null) {
						slot.getValue().add(vs);
					}
				}
				
				CameoUtils.logGUI("Set slot value of " + tv.getValueName() + " to " + tv.getValue() + ".");
			} catch (NullPointerException npe) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				npe.printStackTrace(pw);
				String sStackTrace = sw.toString();
				ImportLog.log("Unable to add stereotype tagged value to element." + sStackTrace);
			}
		}
	}
	
	protected ValueSpecification updateValue(String valueType, String value) {
		if(valueType.contentEquals(SysmlConstants.STRING)) {
			LiteralString ls = f.createLiteralStringInstance();
			ls.setValue(value);
			return ls;
		} else if(valueType.contentEquals(SysmlConstants.REAL)) {
			LiteralReal lr = f.createLiteralRealInstance();
			lr.setValue(Double.parseDouble(value));
			return lr;
		} else if(valueType.contentEquals(SysmlConstants.INTEGER)) {
			LiteralInteger li = f.createLiteralIntegerInstance();
			li.setValue(Integer.parseInt(value));
			return li;
		} else if(valueType.contentEquals(SysmlConstants.BOOLEAN)) {
			LiteralBoolean lb = f.createLiteralBooleanInstance();
			lb.setValue(Boolean.parseBoolean(value));
			return lb;
		} else if(valueType.contentEquals(SysmlConstants.ELEMENT)) {
			// Add checker to create dependent elements of common element 
			ElementValue ev = f.createElementValueInstance();
			ev.setElement((Element) ImportXmlSysml.getProject().getElementByID(ImportXmlSysml.idConversion(value)));
			return ev;
		} else if(valueType.contentEquals(XmlTagConstants.OPAQUEEXPRESSION)) {
			OpaqueExpression oe = f.createOpaqueExpressionInstance();
			List<String> bodies= oe.getBody();
			bodies.add(value);
			return oe;
		}
		return null;
	}
	
	public org.w3c.dom.Element createDefaultValueTag(Document xmlDoc, ValueSpecification vs, org.w3c.dom.Element attributes, org.w3c.dom.Element relationships) {
		Object obj = ModelHelper.getValueSpecificationValue(vs);
		Element instance = null;
		
		if(obj instanceof String) {
			String defaultValue = (String)obj;
			org.w3c.dom.Element defaultValueTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, defaultValue);
			attributes.appendChild(defaultValueTag);
			return defaultValueTag;
		}
		
		if(obj instanceof Boolean) {
			boolean defaultValue = (boolean)obj;
			org.w3c.dom.Element defaultValueTag = createBoolAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, Boolean.toString(defaultValue));
			attributes.appendChild(defaultValueTag);
			return defaultValueTag;
		}
		
		if(obj instanceof Integer) {
			int defaultValue = (int)obj;
			org.w3c.dom.Element defaultValueTag = createIntAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, Integer.toString(defaultValue));
			attributes.appendChild(defaultValueTag);
			return defaultValueTag;
		}
		
		if(obj instanceof Double) {
			Double defaultValue = (Double)obj;
			org.w3c.dom.Element defaultValueTag = createFloatAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, Double.toString(defaultValue));
			attributes.appendChild(defaultValueTag);
			return defaultValueTag;
		}
		
		if(vs instanceof InstanceValue) {
			InstanceValue iv = (InstanceValue)vs;
			instance = iv.getInstance();
			if(instance != null) {
				org.w3c.dom.Element defaultValueTag = createRel(xmlDoc, instance, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE);
				relationships.appendChild(defaultValueTag);
				return defaultValueTag;
			}
		}
		return null;
	}
	
	public String getElementID() {
		if(sysmlElement != null) {
			return sysmlElement.getID();
		}
		return "";
	}
}
