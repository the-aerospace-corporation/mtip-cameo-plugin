/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ValueProperty extends Property {
	public ValueProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.VALUE_PROPERTY;
		this.xmlConstant = XmlTagConstants.VALUEPROPERTY;
		this.element = f.createPropertyInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomSysml = StereotypesHelper.getProfile(project, "MD Customization for SysML");
		Stereotype valPropStereotype = StereotypesHelper.getStereotype(project,  "ValueProperty", mdCustomSysml);
		StereotypesHelper.addStereotype(element, valPropStereotype);
		
		return element;
	}
}
