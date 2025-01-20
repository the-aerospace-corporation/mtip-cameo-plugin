package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.Connector;

public class ResourceConnector extends Connector {

	public ResourceConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_CONNECTOR;
		this.xmlConstant = XmlTagConstants.RESOURCE_CONNECTOR;
	}
}
