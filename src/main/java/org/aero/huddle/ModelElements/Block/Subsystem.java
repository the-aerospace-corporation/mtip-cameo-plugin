/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Subsystem extends CommonElement {

	public Subsystem(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.SUBSYSTEM;
		this.xmlConstant = XmlTagConstants.BLOCK;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype subsystemStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.SUBSYSTEM_STEREOTYPE, sysmlProfile);
		if (subsystemStereotype != null) {
			return createClassWithStereotype(project, name, subsystemStereotype, owner);
		}
		return null;
	}
}
