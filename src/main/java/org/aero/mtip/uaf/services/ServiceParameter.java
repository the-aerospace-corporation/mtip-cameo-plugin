package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Activity.Parameter;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceParameter extends Parameter {

	public ServiceParameter(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_PARAMETER;
		this.xmlConstant = XmlTagConstants.SERVICE_PARAMETER;
	}
}
