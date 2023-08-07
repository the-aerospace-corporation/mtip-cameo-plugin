/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.InternalBlock;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class FlowProperty extends CommonElement {

	public FlowProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FLOWPROPERTY;
		this.xmlConstant = XmlTagConstants.FLOWPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "SysML Profile"); 
		Stereotype flowPropertyStereotype = StereotypesHelper.getStereotype(project, "FlowProperty", mdCustomizationProfile);
		StereotypesHelper.addStereotype(sysmlElement, flowPropertyStereotype);
		
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION)) {
			StereotypesHelper.setStereotypePropertyValue(sysmlElement, flowPropertyStereotype, SysMLProfile.FLOWPROPERTY_DIRECTION_PROPERTY, xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION));
		}
		
		return sysmlElement;
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		Property property = (Property)element;
		Stereotype flowPropertyStereotype = SysMLProfile.getInstance((BaseElement) property).getFlowProperty();
	    List<String> directionList = StereotypesHelper.getStereotypePropertyValueAsString((Element) property, flowPropertyStereotype, SysMLProfile.FLOWPROPERTY_DIRECTION_PROPERTY, false);
		String direction = "";
	    if(!directionList.isEmpty()) {
	    	direction = directionList.get(0);
	    }
	    
	    if(!direction.isEmpty()) {
	    	org.w3c.dom.Element dirTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DIRECTION, direction);
	    	attributes.appendChild(dirTag);
	    }
		return data;
	}

}
