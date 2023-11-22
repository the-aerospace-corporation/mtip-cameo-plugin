package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.ControlFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class FunctionControlFlow extends ControlFlow {
	
	public FunctionControlFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FUNCTION_CONTROL_FLOW;
		this.xmlConstant = XmlTagConstants.FUNCTION_CONTROL_FLOW;
	}
}
