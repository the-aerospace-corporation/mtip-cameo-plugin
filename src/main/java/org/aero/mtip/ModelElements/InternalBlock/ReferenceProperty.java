/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ReferenceProperty extends CommonElement {

	public ReferenceProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.REFERENCEPROPERTY;
		this.sysmlConstant = SysmlConstants.REFERENCEPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype referencePropertyStereotype = StereotypesHelper.getStereotype(project, "ReferenceProperty", mdCustomizationProfile);
		StereotypesHelper.addStereotype(sysmlElement, referencePropertyStereotype);

		return sysmlElement;
	}
}
