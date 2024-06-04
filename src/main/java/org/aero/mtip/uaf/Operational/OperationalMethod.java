package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Block.Operation;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalMethod extends Operation {

	public OperationalMethod(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_METHOD;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_METHOD;
	}
}
