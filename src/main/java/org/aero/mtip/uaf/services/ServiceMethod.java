package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Block.Operation;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceMethod extends Operation {

	public ServiceMethod(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_METHOD;
		this.xmlConstant = XmlTagConstants.SERVICE_METHOD;
	}
}
