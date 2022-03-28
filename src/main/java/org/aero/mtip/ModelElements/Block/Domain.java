/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Domain extends CommonElement {

	public Domain(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.DOMAIN;
		this.xmlConstant = XmlTagConstants.BLOCK;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype domainStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.DOMAIN_STEREOTYPE, sysmlProfile);
		if (domainStereotype != null) {
			return createClassWithStereotype(project, name, domainStereotype, owner);
		}
		return null;
	}
}
