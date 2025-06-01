package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ServiceSpecificationRole extends Property {

	public ServiceSpecificationRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_SPECIFICATION_ROLE;
		this.xmlConstant = XmlTagConstants.SERVICE_SPECIFICATION_ROLE;
	}
}