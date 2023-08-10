package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class OperationalMitigation extends CommonElement {
	
	public OperationalMitigation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.OPERATIONAL_MITIGATION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_MITIGATION;
		this.creationStereotype = UAFProfile.OPERATIONAL_MITIGATION_STEREOTYPE;
	}
}
