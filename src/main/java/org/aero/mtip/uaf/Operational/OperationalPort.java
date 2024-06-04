package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Block.Port;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalPort extends Port {

	public OperationalPort(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PORT;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PORT;
	}
}
