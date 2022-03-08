/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Requirements;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class DeriveRequirement extends CommonRelationship {

	public DeriveRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DERIVEREQUIREMENT;
		this.xmlConstant = XmlTagConstants.DERIVEREQUIREMENT;
		this.sysmlElement = f.createAbstractionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		
		Profile sysml = StereotypesHelper.getProfile(project, "SysML");
		Stereotype deriveRequirementStereotype = StereotypesHelper.getStereotype(project,  SysMLProfile.DERIVEREQT_STEREOTYPE, sysml);
		StereotypesHelper.addStereotype(sysmlElement, deriveRequirementStereotype);

		return sysmlElement;
	}
}
