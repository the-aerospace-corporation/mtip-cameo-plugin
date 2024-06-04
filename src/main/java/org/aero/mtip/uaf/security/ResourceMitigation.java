package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceMitigation extends CommonElement {

	public ResourceMitigation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.RESOURCE_MITIGATION;
		this.xmlConstant = XmlTagConstants.RESOURCE_MITIGATION;
	}

}
