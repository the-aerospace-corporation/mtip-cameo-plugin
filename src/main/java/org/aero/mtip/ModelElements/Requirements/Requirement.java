/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Requirements;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Requirement extends CommonElement {

	public Requirement(String name, String EAID) {
		super(name, EAID);		
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = SysmlConstants.REQUIREMENT;
		this.xmlConstant = XmlTagConstants.REQUIREMENT; 
		this.creationStereotype = SysML.getRequirementStereotype();
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());

		writeRequirementText(attributes, element);
		writeRequirementId(attributes, element);

		return data;
	}
	
	protected void writeRequirementText(org.w3c.dom.Element attributes, Element element) {
		List<String> textList = StereotypesHelper.getStereotypePropertyValueAsString(element, SysML.getRequirementStereotype(), SysML.TEXT_PROPERTY_NAME);
		
		if(textList.size() == 0) {
			return;
		}
			
		String text = textList.get(0).replaceAll("<.*?>", "").replaceAll("&.*?;" , "").replaceAll("\\p{S}","");
		
		if(text.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element textTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_TEXT, text);
		XmlWriter.add(attributes, textTag);
	}
	
	protected void writeRequirementId(org.w3c.dom.Element attributes, Element element) {
		String id = (String) StereotypesHelper.getStereotypePropertyFirst(element, SysML.getRequirementStereotype(), SysML.ID_PROPERTY_NAME);
		
		org.w3c.dom.Element idTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_ID, id);
		XmlWriter.add(attributes, idTag);
	}
}
