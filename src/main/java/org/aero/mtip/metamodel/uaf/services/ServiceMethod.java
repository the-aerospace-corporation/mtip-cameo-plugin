package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Operation;

public class ServiceMethod extends Operation {

	public ServiceMethod(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_METHOD;
		this.xmlConstant = XmlTagConstants.SERVICE_METHOD;
	}
}
