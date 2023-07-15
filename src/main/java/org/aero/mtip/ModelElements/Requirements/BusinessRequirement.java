package org.aero.mtip.ModelElements.Requirements;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.profiles.SysMLProfile;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

// Business Requirement is not an element type as of SysML v1.6 but is a Cameo specific Non-normative extension
public class BusinessRequirement extends Requirement {

	public BusinessRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.BUSINESS_REQUIREMENT;
		this.xmlConstant = XmlTagConstants.BUSINESS_REQUIREMENT;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.SYSML_PROFILE_NAME); 
		this.creationStereotype = SysMLProfile.BUSINESS_REQUIREMENT_STEREOTYPE;
	}
}
