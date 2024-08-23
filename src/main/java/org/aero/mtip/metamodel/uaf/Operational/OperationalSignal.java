package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Signal;

public class OperationalSignal extends Signal {

	public OperationalSignal(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_SIGNAL;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_SIGNAL;
	}
}
