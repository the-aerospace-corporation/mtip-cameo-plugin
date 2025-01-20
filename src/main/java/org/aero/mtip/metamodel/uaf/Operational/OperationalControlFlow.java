package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ControlFlow;

public class OperationalControlFlow extends ControlFlow {

	public OperationalControlFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONTROL_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONTROL_FLOW;
	}
}
