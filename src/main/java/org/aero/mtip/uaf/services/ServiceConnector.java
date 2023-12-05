package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.InternalBlock.Connector;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceConnector extends Connector {

	public ServiceConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_CONNECTOR;
		this.xmlConstant = XmlTagConstants.SERVICE_CONNECTOR;
	}

}
