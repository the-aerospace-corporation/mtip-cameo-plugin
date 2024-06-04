package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceFunctionAction extends CallBehaviorAction {

	public ServiceFunctionAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_FUNCTION_ACTION;
		this.xmlConstant = XmlTagConstants.SERVICE_FUNCTION_ACTION;
	}
}
