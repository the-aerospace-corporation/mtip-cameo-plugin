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
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
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
		Property property = (Property) project.getElementByID(Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.RELATIONSHIP_PROPERTY)));
		Type type = property.getType();
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot propertySlot = ModelHelper.createSlot((InstanceSpecification) owner, property, false);
		
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE)) {
			List<String> values = xmlElement.getListAttributes(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE);
			if(!values.isEmpty()) {
				CameoUtils.logGui("Creating slot value for instance value.");
				for(String value : values) {
					
					Element instanceValue = (Element) project.getElementByID(Importer.idConversion(value));
					ValueSpecification valueSpecification = ModelHelper.createValueSpecification(Project.getProject(propertySlot), type, "", (Collection<? extends ValueSpecification>) Collections.emptySet());
					ModelHelper.setValueSpecificationValue(valueSpecification, value);
					propertySlot.getValue().add(valueSpecification);
				}
			}
		}
		
		if(xmlElement.hasListAttributes(XmlTagConstants.ATTRIBUTE_KEY_VALUE)) {
			List<String> values = xmlElement.getListAttributes(XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			if(!values.isEmpty()) {
				CameoUtils.logGui("Creating slot value for list of string values");
				for(String value : values) {
					ValueSpecification valueSpecification = ModelHelper.createValueSpecification(Project.getProject(propertySlot), type, "", (Collection<? extends ValueSpecification>) Collections.emptySet());
					ModelHelper.setValueSpecificationValue(valueSpecification, value);
					propertySlot.getValue().add(valueSpecification);
				}
			}
		} else if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_VALUE)) {
			String value = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			if(value != null) {
				CameoUtils.logGui("Creating slot value for single value");
				propertySlot = ModelHelper.createSlot((InstanceSpecification) owner, property, false);
				ValueSpecification valueSpecification = ModelHelper.createValueSpecification(Project.getProject(propertySlot), type, "", (Collection<? extends ValueSpecification>) Collections.emptySet());
				if(valueSpecification != null) {
					propertySlot.getValue().add(valueSpecification);
					ModelHelper.setSlotValue(propertySlot, value);
				} else {
					Logger.log("Value specification was null for slot with id:" + propertySlot.getID());
				}
				
			}
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.RELATIONSHIP_DEFAULT_VALUE)) {
			String valueElementID = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_VALUE);
			String cameoValueElementID = Importer.idConversion(valueElementID);
			if(cameoValueElementID != null) {
				CameoUtils.logGui("Add element value ValueSpecification here for element with id " + cameoValueElementID);
			}
		}
		
		return propertySlot;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.RELATIONSHIP_PROPERTY)) {
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.RELATIONSHIP_PROPERTY)));
		}
		
		if(modelElement.hasAttribute(XmlTagConstants.RELATIONSHIP_DEFAULT_VALUE)) {
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.RELATIONSHIP_DEFAULT_VALUE)));
		}
		
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE)) {
			if(CameoUtils.isCameoID(modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE))) {
				Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_INSTANCE_VALUE)));
			}
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeValue(attributes, element);
		writeDefiningFeature(relationships, element);
		
		return data;
	}
	
	private void writeValue(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot slot = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot)element;
		List<ValueSpecification> vss = slot.getValue();
		
		if(vss.size() == 0) {
			return;
		}
		
		ValueSpecification vs = vss.get(0);
		org.w3c.dom.Element valueTag = XmlWriter.createAttributeFromValueSpecification(vs, XmlTagConstants.ATTRIBUTE_KEY_VALUE);
		XmlWriter.add(attributes, valueTag);
	}
	
	private void writeDefiningFeature(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot slot = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot)element;
		StructuralFeature sf = slot.getDefiningFeature();
		
		if (sf instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration) {
			org.w3c.dom.Element enumerationTag = XmlWriter.createMtipRelationship(sf, XmlTagConstants.RELATIONSHIP_ENUMERATION);
			XmlWriter.add(relationships, enumerationTag);
		} 
		
		if(sf instanceof Property) {
			Property property = (Property)sf;
			org.w3c.dom.Element propertyTag = XmlWriter.createMtipRelationship(property, XmlTagConstants.RELATIONSHIP_PROPERTY);
			XmlWriter.add(relationships, propertyTag);
		}
	}
}
