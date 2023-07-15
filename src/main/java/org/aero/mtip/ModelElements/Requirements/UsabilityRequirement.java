package org.aero.mtip.ModelElements.Requirements;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.profiles.SysMLProfile;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

// Usability Requirement is not an element type as of SysML v1.6 but is a Cameo specific Non-normative extension
public class UsabilityRequirement extends Requirement {

	public UsabilityRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.USABILITYREQUIREMENT;
		this.xmlConstant = XmlTagConstants.USABILITYREQUIREMENT;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.SYSML_PROFILE_NAME); 
		this.creationStereotype = SysMLProfile.USABILITY_REQUIREMENT_STEREOTYPE;
	}
}
