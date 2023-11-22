package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ObjectFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalObjectFlow extends ObjectFlow {

	public OperationalObjectFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_OBJECT_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_OBJECT_FLOW;
	}
}
