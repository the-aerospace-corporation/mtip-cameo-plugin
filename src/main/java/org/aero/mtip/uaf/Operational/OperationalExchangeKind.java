package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalExchangeKind extends Enumeration{
	public OperationalExchangeKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.OPERATIONAL_EXCHANGE_KIND;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_EXCHANGE_KIND;
	}
}
