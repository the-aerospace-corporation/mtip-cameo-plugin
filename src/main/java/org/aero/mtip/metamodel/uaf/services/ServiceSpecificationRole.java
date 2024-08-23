package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceSpecificationRole extends org.aero.mtip.metamodel.sysml.sequence.Property {

	public ServiceSpecificationRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_SPECIFICATION_ROLE;
		this.xmlConstant = XmlTagConstants.SERVICE_SPECIFICATION_ROLE;
	}

}
