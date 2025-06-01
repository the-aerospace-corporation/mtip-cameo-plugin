package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.Parameter;

public class ServiceParameter extends Parameter {

	public ServiceParameter(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_PARAMETER;
		this.xmlConstant = XmlTagConstants.SERVICE_PARAMETER;
	}
}
