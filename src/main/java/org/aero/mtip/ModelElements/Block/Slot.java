/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Block;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.StructuralFeature;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class Slot extends CommonElement {

	public Slot(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.SLOT;
		this.xmlConstant = XmlTagConstants.SLOT;
//		this.sysmlElement = f.createSlotInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Property property = (Property) project.getElementByID(ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.RELATIONSHIP_PROPERTY)));
		Type type = property.getType();
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot propertySlot = ModelHelper.createSlot((InstanceSpecification) owner, property, false);
		CameoUtils.logGUI("Type of property for slot is " + type.getHumanName());
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE)) {
			List<String> values = xmlElement.getListAttributes(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE);
			if(!values.isEmpty()) {
				CameoUtils.logGUI("Creating slot value for instance value.");
				for(String value : values) {
					
					Element instanceValue = (Element) project.getElementByID(ImportXmlSysml.idConversion(value));
					ValueSpecification valueSpecification = ModelHelper.createValueSpecification(Project.getProject(propertySlot), type, "", (Collection<? extends ValueSpecification>) Collections.emptySet());
					ModelHelper.setValueSpecificationValue(valueSpecification, value);
					propertySlot.getValue().add(valueSpecification);
				}
			}
		}
		
		if(xmlElement.hasListAttributes(XmlTagConstants.ATTRIBUTE_KEY_VALUE)) {
			List<String> values = xmlElement.getListAttributes(XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			if(!values.isEmpty()) {
				CameoUtils.logGUI("Creating slot value for list of string values");
				for(String value : values) {
					ValueSpecification valueSpecification = ModelHelper.createValueSpecification(Project.getProject(propertySlot), type, "", (Collection<? extends ValueSpecification>) Collections.emptySet());
					ModelHelper.setValueSpecificationValue(valueSpecification, value);
					propertySlot.getValue().add(valueSpecification);
				}
			}
		} else if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_VALUE)) {
			String value = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			if(value != null) {
				CameoUtils.logGUI("Creating slot value for single value");
				propertySlot = ModelHelper.createSlot((InstanceSpecification) owner, property, false);
				ValueSpecification valueSpecification = ModelHelper.createValueSpecification(Project.getProject(propertySlot), type, "", (Collection<? extends ValueSpecification>) Collections.emptySet());
				if(valueSpecification != null) {
					propertySlot.getValue().add(valueSpecification);
					ModelHelper.setSlotValue(propertySlot, value);
				} else {
					ImportLog.log("Value specification was null for slot with id:" + propertySlot.getID());
				}
				
			}
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.RELATIONSHIP_DEFAULT_VALUE)) {
			String valueElementID = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			String cameoValueElementID = ImportXmlSysml.idConversion(valueElementID);
			if(cameoValueElementID != null) {
				CameoUtils.logGUI("Add element value ValueSpecification here for element with id " + cameoValueElementID);
			}
		}
		
		return propertySlot;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.RELATIONSHIP_PROPERTY)) {
			ImportXmlSysml.getOrBuildElement(project, parsedXML, modelElement.getAttribute(XmlTagConstants.RELATIONSHIP_PROPERTY));
		}
		
		if(modelElement.hasAttribute(XmlTagConstants.RELATIONSHIP_DEFAULT_VALUE)) {
			ImportXmlSysml.getOrBuildElement(project, parsedXML, modelElement.getAttribute(XmlTagConstants.RELATIONSHIP_DEFAULT_VALUE));
		}
		
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE)) {
			if(CameoUtils.isCameoID(modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE))) {
				ImportXmlSysml.getOrBuildElement(project, parsedXML, modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE));
			}
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot slot = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot)element;
		
		List<ValueSpecification> vss = slot.getValue();
		if(vss.size() > 0) {
			ValueSpecification vs = vss.get(0);
			if(vs instanceof InstanceValue) {
				org.w3c.dom.Element attribute = createAttributefromValueSpecification(vs, XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE, xmlDoc);
				attributes.appendChild(attribute);
			} else {
				org.w3c.dom.Element attribute = createAttributefromValueSpecification(vs, XmlTagConstants.ATTRIBUTE_KEY_VALUE, xmlDoc);
				attributes.appendChild(attribute);
			}
		}
		
		StructuralFeature sf = slot.getDefiningFeature();
		if (sf instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration) {
			org.w3c.dom.Element enumerationTag = createRel(xmlDoc, sf, XmlTagConstants.RELATIONSHIP_ENUMERATION);
			relationships.appendChild(enumerationTag);
		} else if(sf instanceof Property) {
			Property property = (Property)sf;
			org.w3c.dom.Element propertyTag = createRel(xmlDoc, property, XmlTagConstants.RELATIONSHIP_PROPERTY);
			relationships.appendChild(propertyTag);
		}
		return data;
	}
}
