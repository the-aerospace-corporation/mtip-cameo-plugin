package org.aero.mtip.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.Parameter;

public class OperationalParameter extends Parameter {

	public OperationalParameter(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PARAMETER;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PARAMETER;
	}
}
