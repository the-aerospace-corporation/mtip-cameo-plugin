package org.aero.mtip.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceSpecificationRole extends org.aero.mtip.ModelElements.Sequence.Property {

	public ServiceSpecificationRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_SPECIFICATION_ROLE;
		this.xmlConstant = XmlTagConstants.SERVICE_SPECIFICATION_ROLE;
	}

}
