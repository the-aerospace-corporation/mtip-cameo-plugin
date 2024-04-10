/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import java.util.Arrays;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class FlowProperty extends CommonElement {

	public FlowProperty(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTSFACTORY;
		sysmlConstant = SysmlConstants.FLOW_PROPERTY;
		xmlConstant = XmlTagConstants.FLOW_PROPERTY;
		element = f.createPropertyInstance();
		initialStereotypes = Arrays.asList(SysML.getFlowPropertyStereotype());
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		// TODO: update for 2021x
//		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION)) {
//			StereotypesHelper.setStereotypePropertyValue(
//					element, 
//					SysML.getFlowPropertyStereotype(), 
//					SysMLProfile.FLOWPROPERTY_DIRECTION_PROPERTY, 
//					xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION));
//		}
		
		return element;
	}
	
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeDirection(attributes, element);
		
		return data;
	}
	
	private void writeDirection(org.w3c.dom.Element attributes, Element element) {
		Property property = (Property)element;
	    List<String> directionList = StereotypesHelper.getStereotypePropertyValueAsString((Element) property, SysML.getFlowPropertyStereotype(), SysMLProfile.FLOWPROPERTY_DIRECTION_PROPERTY, false);
		
	    if(directionList.isEmpty()) {
	    	return;
	    }
	    
	    String direction = directionList.get(0);
	    
	    if(direction == null || direction.trim().isEmpty()) {
	    	return;
	    }
	    
    	org.w3c.dom.Element directionTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION, direction);
    	XmlWriter.add(attributes, directionTag);
	}
}
