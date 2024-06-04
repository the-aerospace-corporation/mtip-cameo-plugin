package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityControlFamily extends CommonElement {

	public SecurityControlFamily(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.SECURITY_CONTROL_FAMILY;
		this.xmlConstant = XmlTagConstants.SECURITY_CONTROL_FAMILY;
	}
}
