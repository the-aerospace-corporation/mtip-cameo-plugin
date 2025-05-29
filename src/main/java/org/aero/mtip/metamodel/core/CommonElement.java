/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.CheckForNull;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.profiles.UAF;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.jmi.helpers.TagsHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.BooleanTaggedValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Classifier;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementTaggedValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.IntegerTaggedValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.RealTaggedValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.StringTaggedValue;
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
	protected String metamodelConstant;
	protected String xmlConstant;
	protected String creationType;
	protected ElementsFactory f;
	protected Element element;
	protected Stereotype creationStereotype;
	protected List<Stereotype> initialStereotypes;
	protected Element classifier;
	protected Project project;
	protected XMLItem xmlElement;
	
	public CommonElement(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
		this.project = Application.getInstance().getProject();
		this.f = Application.getInstance().getProject().getElementsFactory();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		this.project = project;
		this.xmlElement = xmlElement;
		
		setBaseElement();
		setName();
		setOwner(owner);
		addInitialStereotype();
		addMetamodelConstantStereotype();
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
		if (this.creationType.contentEquals(XmlTagConstants.ELEMENTS_FACTORY)) {
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
		if (element == null) {
			Logger.log("Sysml Element was not created. Cannot set owner");
			return;
		}
		
		if (owner == null) {
		    if (ModelHelper.canMoveChildInto(project.getPrimaryModel(), element)) {
		      element.setOwner(project.getPrimaryModel());
		      return;
		    }
		    
		    ModelHelper.dispose(Collections.singletonList(element));
		    return;
		}
		
		
		if (ModelHelper.canMoveChildInto(owner, element)) {
		  element.setOwner(owner);
		  return;
		}
		
		owner = ModelHelper.findAcceptableParentFor(element, owner);
		
		if (owner == null) {
		  ModelHelper.dispose(Collections.singletonList(element));
		  return;
		}
		
		element.setOwner(owner);		
	}
	
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
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
				Importer.getInstance().buildElement(parsedXML, parsedXML.get(tv.getValue()));
				continue;
			}
			
			List<String> values = tv.getValues();
			
			for(String value : values) {
				Importer.getInstance().buildElement(parsedXML, parsedXML.get(value));
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
		
		Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.TYPED_BY)));
	}
	
	@CheckForNull
	public org.w3c.dom.Element writeToXML(Element element) {
		this.element = element;
		
		if (xmlConstant == null || (element.getOwner() == null && !(element instanceof com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdmodels.Model))) {
			Logger.log(String.format("Internal Error: element of type %s has no XML constant set or has no owner. Id: ", element.getHumanType(), MtipUtils.getId(element)));
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
		org.w3c.dom.Element idTag = XmlWriter.createMtipIdTag(MtipUtils.getId(element));
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
			
			Logger.log(String.format(
					"No parent element found for %s of type %s with id %s.", 
					element.getHumanName(), 
					element.getHumanType(), 
					MtipUtils.getId(element)));
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
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TaggedValue taggedValue = TagsHelper.getTaggedValue(element, property);
			
			if(taggedValue == null) {
				continue;
			}
			
			List<?> taggedValueObjects = taggedValue.getValue();
			
			if(taggedValueObjects == null || taggedValueObjects.isEmpty()) {
				continue;
			}
			
			String valueType = getTaggedValueType(taggedValue);
			
			if(valueType == null) {
				Logger.log(String.format("Value type could not be determined creating tagged values for element with id %s.", MtipUtils.getId(element)));
				continue;
			}
			
			List<String> taggedValues = getTaggedValueValues(valueType, taggedValueObjects);
			org.w3c.dom.Element taggedValueTag = XmlWriter.createTaggedValueTag(stereotype, property.getName(), valueType, taggedValues);
			
			if (taggedValueTag == null) {
				Logger.log(String.format("WARNING: Could not create tagged value for %s tagged value of  %s with id %s.", 
						taggedValue.getHumanName(), 
						element.getHumanName(), 
						MtipUtils.getId(element)));
				continue;
			}
			
			XmlWriter.add(attributes, taggedValueTag);
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
	public String getTaggedValueType(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TaggedValue taggedValue) {
		if (taggedValue instanceof BooleanTaggedValue) {
			return XmlTagConstants.TV_TYPE_BOOLEAN;
		} else if (taggedValue instanceof IntegerTaggedValue) {
			return XmlTagConstants.TV_TYPE_INTEGER;
		} else if (taggedValue instanceof RealTaggedValue) {
			return XmlTagConstants.TV_TYPE_REAL;
		} else if (taggedValue instanceof StringTaggedValue) {
			return XmlTagConstants.TV_TYPE_STRING;
		} else if (taggedValue instanceof ElementTaggedValue) {
			if (((ElementTaggedValue)taggedValue).getValue() instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral) {
				return XmlTagConstants.TV_TYPE_ENUMERATION_LITERAL;
			}
			
			return XmlTagConstants.TV_TYPE_ELEMENT;
		}
		
		Logger.log(String.format("WARNING: Unable to determine type of tagged value for %s of %s.", taggedValue.getHumanName(), taggedValue.getOwner().getHumanName()));
		return null;
	}
	
	@CheckForNull
	public List<String> getTaggedValueValues(String valueType, List<?> values) {
		if (valueType.contentEquals(XmlTagConstants.TV_TYPE_BOOLEAN)) {
			return values.stream()
						.map(x -> Boolean.toString((Boolean)x))
						.collect(Collectors.toList());
		} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_INTEGER)) {
				return values.stream()
						.map(x -> Integer.toString((Integer)x))
						.collect(Collectors.toList());
		} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_REAL)) {
				return values.stream()
						.map(x -> Double.toString((Double)x))
						.collect(Collectors.toList());
		} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_STRING)) {
				return values.stream()
						.map(x -> (String)x)
						.collect(Collectors.toList());
		} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_ENUMERATION_LITERAL)) {
				// TODO: Implement
				return null;
		} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_ELEMENT)) {
			return values.stream()
					.map(x -> MtipUtils.getId(((Element)x)))
					.collect(Collectors.toList());
		}
		
		Logger.log(String.format("WARNING: %s value type is not yet supported.", valueType));
		return null;
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
			ev.setElement((Element) Application.getInstance().getProject().getElementByID(Importer.idConversion(value)));
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
			Logger.log(String.format("Value specification with id %s was not string, real, int, or bool.", MtipUtils.getId(vs)));
		}
		return null;
	}
	
	public String getTaggedValueType(Object value) {
		// Find Primitive Types
		if (value instanceof Boolean) {
			return SysmlConstants.BOOLEAN;
		} else if (value instanceof String) {
			return SysmlConstants.STRING;
		} else if (value instanceof Double) {
			return SysmlConstants.REAL;
		} else if (value instanceof Integer) {
			return SysmlConstants.INTEGER;
		} else if (value instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral) {
			return SysmlConstants.ENUMERATION_LITERAL;
		} else if (value instanceof Element) {
			return SysmlConstants.ELEMENT;
		}
		return null;
	}
	
	@CheckForNull
	public String getTaggedValueValueAsString(String valueType, Object value) {
		// Find Primitive Types
		if (valueType.contentEquals(SysmlConstants.BOOLEAN)) {
			return SysmlConstants.BOOLEAN;
		} else if (valueType.contentEquals(SysmlConstants.STRING)) {
			return (String)value;
		} else if (valueType.equals(SysmlConstants.REAL)) {
			return Double.toString((Double)value);
		} else if (valueType.contentEquals(SysmlConstants.INTEGER)) {
			return Integer.toString((Integer)value);
		} else if (valueType.contentEquals(SysmlConstants.ENUMERATION_LITERAL)) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral literal = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral)value;
			return literal.getName();
		} else if (valueType.equals(SysmlConstants.ELEMENT)) {
			return MtipUtils.getId((Element)value);
		}		
		return value.toString();
	}
	
//	@CheckForNull
//	public String getTaggedValueValueAsString(Object value) {
//		String strVal = null;
//		if(value instanceof LiteralString) {
//			LiteralString ls = (LiteralString)value;
//			strVal = ls.getValue();
//		} else if(value instanceof LiteralReal) {
//			LiteralReal lr = (LiteralReal)value;
//			double tempVal = lr.getValue();
//			strVal = String.valueOf(tempVa;);
//		} else if(value instanceof LiteralInteger) {
//			LiteralInteger lr = (LiteralInteger)value;
//			int tempVal = lr.getValue();
//			strVal = String.valueOf(tempVal);
//		} else if(vs instanceof LiteralBoolean) {
//			LiteralBoolean lr = (LiteralBoolean)value;
//			boolean tempVal = lr.isValue();
//			strVal = String.valueOf(value);
//		} else if(vs instanceof ElementValue) {
//			ElementValue ev = (ElementValue)vs;
//			strVal = ev.getElement().getLocalID();
//		} else if(vs instanceof OpaqueExpression) {
//			OpaqueExpression oe = (OpaqueExpression)vs;
//			List<String> bodies = oe.getBody();
//			Iterator<String> bodyIter = bodies.iterator();
//			if(bodyIter.hasNext()) {
//				strVal = bodyIter.next();
//			}
//		} else if(vs instanceof EnumerationLiteral) {
//			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral literal = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral)vs;
//			strVal = literal.getName();
//		} else if(vs instanceof InstanceValue ) {
//			strVal = ModelHelper.getValueString(vs);
////			EnumerationLiteral  litVal = (EnumerationLiteral)ValueSpecificationHelper.getValueSpecficationValue(vs);
////			InstanceValue iv = (InstanceValue)vs;
////			InstanceSpecification is = iv.getInstance();
////			ValueSpecification vs2 = is.getSpecification();
////			strVal = getSlotValueAsString(vs2);
//		}else {
//			String message = "Value specification with id " + vs.getLocalID() + " was not string, real, int, bool, or opaque expression.";
//			ExportLog.log(message);
//			CameoUtils.logGUI(message);
//		}
//		return strVal;
//	}
	
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
	protected void addType(XMLItem xmlElement) {
		try {
			if(!(element instanceof TypedElement)) {
				return;
			}
			if(!xmlElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
				return;
			}
			
			Element typeElement = getTypeElement(xmlElement.getAttribute(XmlTagConstants.TYPED_BY));
			
			if(typeElement == null) {
				return;
			}
			
			if(!(typeElement instanceof Type)) {
				Logger.log(String.format("typedBy element not a Type. Type field cannot be set for element with id: %s", this.EAID));
				return;
			}
			
			((TypedElement)element).setType((Type) typeElement);			
		} catch(NullPointerException npe) {
			Logger.log(String.format("Null pointer exception setting type for element with id %s.", EAID));
			Logger.logException(npe);
		}
	}
	
	protected Element getTypeElement(String importID) {
		if(CameoUtils.isPrimitiveValueType(importID)) {
			return CameoUtils.getPrimitiveValueType(importID);
		} 
		
		if(importID.startsWith("_9_")) {
			// TODO return CameoUtils.getUmlPrimitiveValueType(importID);
		} 
		
		Element importElement = (Element) project.getElementByID(importID);
		
		if (importElement != null) {
			return importElement;
		}
		
		return (Element) project.getElementByID(Importer.idConversion(importID));
		
	}
	
	protected void addInitialStereotype() {
		if(initialStereotypes == null) {
			return;
		}
		
		for(Stereotype stereotype : initialStereotypes) {
			if(stereotype == null) {
				Logger.log(String.format("Error adding initial stereotype to %s with id %s", name, EAID));
				continue;
			}
			
			StereotypesHelper.addStereotype(element, stereotype);
		}
	}
	
	protected void addMetamodelConstantStereotype() {
		if (!MtipUtils.isUafEntity(metamodelConstant)) {
			return;
		}
		
		Stereotype metamodelConstantStereotype = UAF.getStereotype(metamodelConstant);
		
		if (metamodelConstantStereotype == null) {
			Logger.log(String.format("Unable to get %s stereotype from UAF Profile adding initial stereotypes.", metamodelConstant));
			return;
		}
		
		StereotypesHelper.addStereotype(element, metamodelConstantStereotype);
	}
	
	protected void setClassifier() {
		
	}
	
	protected void applyClassifier() {
		if (classifier == null) {
			return;
		}
		
		if (!(classifier instanceof Classifier)) {
			Logger.log(String.format("Classifier %s cannot be cast to classifier class for element %s with id %s.", classifier.getHumanName(), name, EAID));
			return;
		}
		
		if (!(element instanceof InstanceSpecification)) {
			Logger.log(String.format("Cannot apply classifier to non-InstanceSpecification subclass. Use setClassifier for element %s with id %s.", element.getHumanName(), EAID));
		}
		
		Classifier classifier = (Classifier) this.classifier;
		ModelHelper.setClassifierForInstanceSpecification(classifier, (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification) element, true);
	}
	
	public void addStereotypeTaggedValues(XMLItem xmlElement) {
		for(TaggedValue tv : xmlElement.getTaggedValues()) {
			try {
				Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), tv.getProfileName());
				Stereotype stereotype = StereotypesHelper.getStereotype(Application.getInstance().getProject(), tv.getStereotypeName(), profile);
				Property prop = StereotypesHelper.getPropertyByName(stereotype, tv.getValueName());
				
				if (profile == null
						|| stereotype == null
						|| prop == null) {
					
					Logger.log(String.format("WARNING: Unable to import tagged value %s. Profile, stereotype, or property could not be found.", tv.getValueName()));
					continue;
				}
				
				com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TaggedValue taggedValue = TagsHelper.getTaggedValueOrCreate(element, stereotype, prop, false);
				
				if (!tv.getValueType().contentEquals(SysmlConstants.ELEMENT)) {
					taggedValue.addConvertedValue(tv.getValue());
					continue;
				}
				
				Element valueElement = null;
				
				if (CameoUtils.isAuxiliaryElement(tv.getValue())) {
					valueElement = (Element) Application.getInstance().getProject().getElementByID(tv.getValue());
				} else {
					valueElement = (Element) Application.getInstance().getProject().getElementByID(Importer.idConversion(tv.getValue()));
					taggedValue.addConvertedValue(valueElement);
				}
				
				if (valueElement == null) {
					Logger.log(String.format("Could not find tagged value element with id %s.", tv.getValue()));
					continue;
				}

				taggedValue.addConvertedValue(valueElement);
			} catch (NullPointerException npe) {
				Logger.log("Unable to add stereotype tagged value to element. See stack trace:");
				Logger.logException(npe);
			} catch (IllegalArgumentException iae) {
				Logger.log("Unable to add stereotype tagged value to element. See stack trace:");
				Logger.logException(iae);
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
			ev.setElement((Element) Application.getInstance().getProject().getElementByID(Importer.idConversion(value)));
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
		return this.metamodelConstant;
	}
	
	public String getElementID() {
		if(element != null) {
			return MtipUtils.getId(element);
		}
		return "";
	}

	public void createReferencedElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {		
	}
}
