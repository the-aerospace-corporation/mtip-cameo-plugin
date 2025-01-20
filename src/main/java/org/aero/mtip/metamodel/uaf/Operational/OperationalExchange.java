package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.InformationFlow;

public class OperationalExchange extends InformationFlow {

	public OperationalExchange(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_EXCHANGE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_EXCHANGE;
	}
}
