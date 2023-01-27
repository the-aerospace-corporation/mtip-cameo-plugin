package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Requirements.Requirement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFElement;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class EnterpriseGoal extends Requirement implements UAFElement {
	
	public EnterpriseGoal(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.ENTERPRISE_GOAL;
		this.xmlConstant = XmlTagConstants.ENTERPRISE_GOAL;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.ENTERPRISE_GOAL, creationProfile);
	}
}
