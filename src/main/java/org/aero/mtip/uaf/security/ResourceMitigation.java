package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ResourceMitigation extends CommonElement {

	public ResourceMitigation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.RESOURCE_MITIGATION;
		this.xmlConstant = XmlTagConstants.RESOURCE_MITIGATION;
		this.creationStereotype = UAFProfile.RESOURCE_MITIGATION_STEREOTYPE;
	}

}
