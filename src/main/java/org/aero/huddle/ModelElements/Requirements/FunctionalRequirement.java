/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Requirements;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class FunctionalRequirement extends CommonElement {

	public FunctionalRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.FUNCTIONALREQUIREMENT;
		this.xmlConstant = XmlTagConstants.FUNCTIONALREQUIREMENT;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.SYSML_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysMLProfile.FUNCTIONALREQUIREMENT_STEREOTYPE, creationProfile);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML");
		Stereotype requirementStereotype = StereotypesHelper.getStereotype(project,  "functionalRequirement", sysmlProfile);
		sysmlElement = createClassWithStereotype(project, name, requirementStereotype, owner);
		
		return sysmlElement;
	}
}
