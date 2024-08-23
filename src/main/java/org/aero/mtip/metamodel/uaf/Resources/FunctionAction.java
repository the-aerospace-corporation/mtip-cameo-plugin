package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.CallBehaviorAction;

public class FunctionAction extends CallBehaviorAction {
	
	public FunctionAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FUNCTION_ACTION;
		this.xmlConstant = XmlTagConstants.FUNCTION_ACTION;
	}
}
