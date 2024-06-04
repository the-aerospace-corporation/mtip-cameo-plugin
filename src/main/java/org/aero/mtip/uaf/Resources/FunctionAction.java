package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class FunctionAction extends CallBehaviorAction {
	
	public FunctionAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FUNCTION_ACTION;
		this.xmlConstant = XmlTagConstants.FUNCTION_ACTION;
	}
}
