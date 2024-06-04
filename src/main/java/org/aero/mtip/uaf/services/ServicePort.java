package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Block.Port;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServicePort extends Port {

	public ServicePort(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_PORT;
		this.xmlConstant = XmlTagConstants.SERVICE_PORT;
	}
}
