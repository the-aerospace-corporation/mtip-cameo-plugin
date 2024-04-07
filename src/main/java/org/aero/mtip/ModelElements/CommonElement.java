/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.CheckForNull;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
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
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
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
	protected String name;
	protected String EAID;
	protected String sysmlConstant;
	protected String xmlConstant;
	protected String creationType;
	protected ElementsFactory f;
	protected Element element;
	protected Stereotype creationStereotype;
	protected List<Stereotype> initialStereotypes;
	protected Element classifier;
	protected Project project;
	
	public CommonElement(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
		this.project = Application.getInstance().getProject();
		this.f = Application.getInstance().getProject().getElementsFactory();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		this.project = project;
		
		setBaseElement();
		setName();
		setOwner(owner);
		addInitialStereotype();
		setClassifier();
		applyClassifier();
		addDocumentation(xmlElement);
		addMultiplicity(xmlElement);
		addType(xmlElement);
		
		return element;
	}
	
	protected String setName() {
		if(element instanceof NamedElement) {
			((NamedElement)element).setName(name);
		}
		
		return name;
	}
	
	protected Element setBaseElement() {
		if(this.creationType.contentEquals(XmlTagConstants.ELEMENTSFACTORY)) {
			return element;
		}
		
		element = project.getElementsFactory().createClassInstance();
		
		if (creationStereotype == null) {
			return element;	
		}
		
		StereotypesHelper.addStereotype(element, creationStereotype);
		return element;
	}
	
	public void setOwner(Element owner) {
		if(element == null) {
			ImportLog.log("Sysml Element was not created. Cannot set owner");
			return;
		}
		
		if(owner == null) {
			try {
				element.setOwner(project.getPrimaryModel());
			} catch(IllegalArgumentException iae){
				ImportLog.log(String.format("Primary model is invalid parent for %s.", element.getHumanName()));
				element.dispose();
			}
			
			return;
		}
		
		try {
			element.setOwner(owner);
		} catch(IllegalArgumentException iae){
			ImportLog.log(String.format("%s is invalid parent for %s.", owner.getHumanType(), element.getHumanName()));
			element.dispose();
		}		
	}
	
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		createTaggedValues(parsedXML, modelElement);
		createTypedBy(parsedXML, modelElement);
	}
	
	public void createTaggedValues(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(!modelElement.hasTaggedValues()) {
			return;
		}
		
		for(TaggedValue tv : modelElement.getTaggedValues()) {
			if(!tv.getValueType().contentEquals(SysmlConstants.ELEMENT)) {
				continue;
			}
			
			if(!tv.isMultiValue()) {
				ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(tv.getValue()));
				continue;
			}
			
			List<String> values = tv.getValues();
			
			for(String value : values) {
				ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(value));
			}
		}
	}
	
	public void createTypedBy(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(!modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			return;
		}
		
		if(!parsedXML.containsKey(modelElement.getAttribute(XmlTagConstants.TYPED_BY))) {
			return;
		}
		
		ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.TYPED_BY)));
	}
	
	@CheckForNull
	public org.w3c.dom.Element writeToXML(Element element) {
		this.element = element;
		
		if (xmlConstant == null || (element.getOwner() == null && !(element instanceof com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdmodels.Model))) {
			ExportLog.log(String.format("Internal Error: element of type %s has no XML constant set or has no owner. Id: ", element.getHumanType(), element.getID()));
			return null;
		}
		
		org.w3c.dom.Element data = XmlWriter.createDataTag();
		
		writeAttributes(data);
		writeId(data);
		writeRelationships(data);
		writeType(data);
		
		XmlWriter.addToRoot(data);
		
		return data;
	}
	
	public void writeId(org.w3c.dom.Element data) {
		org.w3c.dom.Element idTag = XmlWriter.createMtipIdTag(element.getID());
		XmlWriter.add(data, idTag);
	}
	
	public void writeType(org.w3c.dom.Element data) {
		org.w3c.dom.Element typeTag = XmlWriter.createMtipTypeTag(xmlConstant);
		XmlWriter.add(data, typeTag);
	}
	
	protected void writeRelationships(org.w3c.dom.Element data) {
		org.w3c.dom.Element relationshipsTag = XmlWriter.createMtipRelationshipsTag();
		
		writeParent(relationshipsTag);
		writeTypedBy(relationshipsTag);
		
		XmlWriter.add(data, relationshipsTag);
	}
	
	protected void writeParent(org.w3c.dom.Element relationships) {
		Element owner = element.getOwner();
		
		if (owner == null) {
			if (element == project.getPrimaryModel()) {
				return;
			}
			
			ExportLog.log(String.format(
					"No parent element found for %s of type %s with id %s.", 
					element.getHumanName(), 
					element.getHumanType(), 
					element.getID()));
			return;
		}
		
		org.w3c.dom.Element parentTag = XmlWriter.createMtipHasParentTag(element.getOwner());
		XmlWriter.add(relationships, parentTag);
	}
	
	protected void writeTypedBy(org.w3c.dom.Element relationships) {
		if(!(element instanceof TypedElement)) {
			return;
		}
		
		org.w3c.dom.Element typedByTag = XmlWriter.createTypedByTag(element);
		
		if(typedByTag == null) {
			return;
		}
		
		XmlWriter.add(relationships, typedByTag);		
	}
	
	public void writeAttributes(org.w3c.dom.Element data) {
		org.w3c.dom.Element attributes = XmlWriter.createMtipAttributesTag();
		XmlWriter.addAttribute(attributes, XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		writeName(attributes);
		writeStereotypes(attributes);
		writeDocumentation(attributes);
		writeMultiplicity(attributes);
		
		XmlWriter.add(data, attributes);
	}
	
	public void writeMultiplicity(org.w3c.dom.Element attributes) {
		if(!(element instanceof MultiplicityElement)) {
			return;
		}
		
		String multiplicity = ModelHelper.getMultiplicity((MultiplicityElement) element);
		
		if(multiplicity.isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element multiplicityTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_MULTIPLICITY, multiplicity);
		XmlWriter.add(attributes, multiplicityTag);
	}
	
	public void writeName(org.w3c.dom.Element attributes) {
		if(name == null && !name.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element nameTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_NAME, name);
		XmlWriter.add(attributes, nameTag);
	}
	
	public void writeStereotypes(org.w3c.dom.Element attributes) {
		for(Stereotype stereotype : StereotypesHelper.getStereotypes(element)) {
			org.w3c.dom.Element stereotypeTag = XmlWriter.createStereotypeTag(stereotype);
			
			if (stereotypeTag == null) {
				continue;
			}
			
			XmlWriter.add(attributes, stereotypeTag);
			writeTaggedValues(element, stereotype, attributes);
		}
	}
	
	public void writeTaggedValues(Element element, Stereotype stereotype, org.w3c.dom.Element attributes) {
		List<Property> properties = StereotypesHelper.getPropertiesWithDerivedOrdered(stereotype);
		for(Property property : properties) {
			Slot slot = StereotypesHelper.getSlot(element, stereotype, property, false, false);
			
			if(slot == null) {
				continue;
			}
			
			List<ValueSpecification> vss = slot.getValue();
			
			if(vss == null || vss.isEmpty()) {
				continue;
			}
			
			ValueSpecification vs = vss.get(0);
			String valueType = getValueSpecificationValueType(vs);
			
			if(valueType == null) {
				ExportLog.log(String.format("Value type could not be determined creating tagged values for element with id %s.", element.getID()));
				continue;
			}
			
			org.w3c.dom.Element taggedValueTag = XmlWriter.createTaggedValueTag(stereotype, property.getName(), valueType, vss);
			
			if (taggedValueTag != null) {
				XmlWriter.add(attributes, taggedValueTag);
			}
		}
	}
	
	public void writeDocumentation(org.w3c.dom.Element attributes) {
		String documentation = ModelHelper.getComment(element).replaceAll("\\<.*?>","").replace("p {padding:0px; margin:0px;}", "").trim();
		
		if(documentation.isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element documentationTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION, documentation);
		XmlWriter.add(attributes, documentationTag);
	}
	
	@CheckForNull
	public String getValueSpecificationValueType(ValueSpecification vs) {
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
			return SysmlConstants.ENUMERATION_LITERAL;
		} else if(vs instanceof InstanceValue) {
			return SysmlConstants.INSTANCE_VALUE;
//		} else if(vs instanceof OpaqueExpression) {
//			return SysmlConstants.OPAQUEEXPRESSION;
		} else {
			ExportLog.log(String.format("Value specification with id %s was not string, real, int, or bool.", vs.getID()));
		}
		return null;
	}
	
	public Element createClassWithStereotype(Project project, Stereotype stereotype, Element owner) {
		element = project.getElementsFactory().createClassInstance();
		if (stereotype != null) {
			StereotypesHelper.addStereotype(element, stereotype);
		}
		
		return element;
	}
	public Element createClassWithStereotype(Project project, String name, Stereotype stereotype, Element owner) {
		element = project.getElementsFactory().createClassInstance();
	
		if(element instanceof NamedElement) {
			((NamedElement)element).setName(name.replaceAll("[^a-zA-Z0-9]+",""));
		}
		
		if (stereotype != null) {
			StereotypesHelper.addStereotype(element, stereotype);
		}
		
		return element;
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
		}
		
		return true;
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
	
	protected void addDocumentation(XMLItem xmlElement) {
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION)) {
			ModelHelper.setComment(element, xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DOCUMENTATION));
		}
	}
	
	protected void addMultiplicity(XMLItem xmlElement) {
		if(element instanceof MultiplicityElement) {
			if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_MULTIPLICITY)) {
				ModelHelper.setMultiplicity(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_MULTIPLICITY), (MultiplicityElement) element);
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
			if(element instanceof TypedElement) {
				Element typeElement = null;
				if(xmlElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
					String typeImportID = xmlElement.getAttribute(XmlTagConstants.TYPED_BY);
					
					if(CameoUtils.isPrimitiveValueType(typeImportID)) {
						typeElement = CameoUtils.getPrimitiveValueType(typeImportID);
					} else if(typeImportID.startsWith("_9_")) {
						if(typeImportID.contentEquals("_9_0_2_91a0295_1110274713995_297054_0")) {
							typeElement = ModelHelper.findElementWithPath("UML Standard Profile::UML2 Metamodel::PrimitiveTypes::String");
						}
					} else {
						try {
							typeElement = (Element) project.getElementByID(typeImportID);
						} catch (NullPointerException npe) {
							ImportLog.log("Type not found with id. Please check that element is created");
						}
					}
					
					if(typeElement == null) {
						String cameoID = ImportXmlSysml.idConversion(typeImportID);
						typeElement = (Element) project.getElementByID(cameoID);
					}
					if(typeElement instanceof Type) {
						((TypedElement)element).setType((Type) typeElement);
					} else {
						ImportLog.log(String.format("typedBy element not a Type. Type field cannot be set for element with id: %s", EAID));
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
	
	protected void addInitialStereotype() {
		if(initialStereotypes == null) {
			return;
		}
		
		for(Stereotype stereotype : initialStereotypes) {
			if(stereotype == null) {
				ImportLog.log(String.format("Error adding initial stereotype to %s with id %s", name, EAID));
				continue;
			}
			
			StereotypesHelper.addStereotype(element, stereotype);
		}
	}
	
	protected void setClassifier() {
		
	}
	
	protected void applyClassifier() {
		if (classifier == null) {
			return;
		}
		
		if (!(classifier instanceof Classifier)) {
			ImportLog.log(String.format("Classifier %s cannot be cast to classifier class for element %s with id %s.", classifier.getHumanName(), name, EAID));
			return;
		}
		
		if (!(element instanceof InstanceSpecification)) {
			ImportLog.log(String.format("Cannot apply classifier to non-InstanceSpecification subclass. Use setClassifier for element %s with id %s.", element.getHumanName(), EAID));
		}
		
		Classifier classifier = (Classifier) this.classifier;
		ModelHelper.setClassifierForInstanceSpecification(classifier, (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification) element, true);
	}	
	
	public void addStereotypeTaggedValues(XMLItem xmlElement) {
		for(TaggedValue tv : xmlElement.getTaggedValues()) {
			try {
				Profile profile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), tv.getProfileName());
				Stereotype stereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), tv.getStereotypeName(), profile);
				Property prop = StereotypesHelper.getPropertyByName(stereotype, tv.getValueName());
				Slot slot = StereotypesHelper.getSlot(element, prop, true, false);
				
				if(tv.isMultiValue()) {
					for(String value : tv.getValues()) {
						ValueSpecification vs = createValueSpecification(tv.getValueType(), value);
						if(value != null) {
							slot.getValue().add(vs);
						}
					}
				} else {
					ValueSpecification vs = createValueSpecification(tv.getValueType(), tv.getValue());
					if(vs != null) {
						slot.getValue().add(vs);
					}
				}
			} catch (NullPointerException npe) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				npe.printStackTrace(pw);
				String sStackTrace = sw.toString();
				ImportLog.log("Unable to add stereotype tagged value to element." + sStackTrace);
			}
		}
	}
	
	protected ValueSpecification createValueSpecification(String valueType, String value) {
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
		} else if(valueType.contentEquals(XmlTagConstants.OPAQUE_EXPRESSION)) {
			OpaqueExpression oe = f.createOpaqueExpressionInstance();
			List<String> bodies= oe.getBody();
			bodies.add(value);
			return oe;
		} else if(valueType.contentEquals(SysmlConstants.INSTANCE_VALUE)) {
			return null;
		}
		
		return null;
	}
	
	public String getElementType() {
		return this.sysmlConstant;
	}
	
	public String getElementID() {
		if(element != null) {
			return element.getID();
		}
		return "";
	}

	public void createReferencedElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {		
	}
}
