package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Block.Signal;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalSignal extends Signal {

	public OperationalSignal(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_SIGNAL;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_SIGNAL;
	}
}
