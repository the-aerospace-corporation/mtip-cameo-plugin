package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.InternalBlock.InformationFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalExchange extends InformationFlow {

	public OperationalExchange(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_EXCHANGE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_EXCHANGE;
	}
}
