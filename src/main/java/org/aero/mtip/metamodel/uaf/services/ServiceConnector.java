package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.Connector;

public class ServiceConnector extends Connector {

	public ServiceConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_CONNECTOR;
		this.xmlConstant = XmlTagConstants.SERVICE_CONNECTOR;
	}

}
