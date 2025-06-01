package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ControlFlow;

public class FunctionControlFlow extends ControlFlow {
	
	public FunctionControlFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FUNCTION_CONTROL_FLOW;
		this.xmlConstant = XmlTagConstants.FUNCTION_CONTROL_FLOW;
	}
}
