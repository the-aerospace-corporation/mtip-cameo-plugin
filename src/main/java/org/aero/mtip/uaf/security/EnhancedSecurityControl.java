package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class EnhancedSecurityControl extends CommonElement {

	public EnhancedSecurityControl(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.ENHANCED_SECURITY_CONTROL;
		this.xmlConstant = XmlTagConstants.ENHANCED_SECURITY_CONTROL;
		this.creationStereotype = UAFProfile.ENHANCED_SECURITY_CONTROL_STEREOTYPE;
	}
}
