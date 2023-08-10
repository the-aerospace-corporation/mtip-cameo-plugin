package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class SecurityEnclave extends CommonElement {

	public SecurityEnclave(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.SECURITY_ENCLAVE;
		this.xmlConstant = XmlTagConstants.SECURITY_ENCLAVE;
		this.creationStereotype = UAFProfile.SECURITY_ENCLAVE_STEREOTYPE;
	}
}
