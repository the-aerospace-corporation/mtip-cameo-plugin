package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.Connector;

public class OperationalConnector extends Connector {

	public OperationalConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONNECTOR;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONNECTOR;
	}
}
