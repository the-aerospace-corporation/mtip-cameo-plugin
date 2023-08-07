/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class System extends CommonElement {

	public System(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.SYSTEM;
		this.xmlConstant = XmlTagConstants.BLOCK;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype systemStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.SYSTEM_STEREOTYPE, sysmlProfile);
		if (systemStereotype != null) {
			return createClassWithStereotype(project, name, systemStereotype, owner);
		}
		return null;
	}
}
