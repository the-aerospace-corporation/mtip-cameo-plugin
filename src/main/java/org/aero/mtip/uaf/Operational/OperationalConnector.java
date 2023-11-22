package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.InternalBlock.Connector;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalConnector extends Connector {

	public OperationalConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONNECTOR;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONNECTOR;
	}
}
