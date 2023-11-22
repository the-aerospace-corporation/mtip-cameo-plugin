package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.ObjectFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class FunctionObjectFlow extends ObjectFlow{
	
	public FunctionObjectFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FUNCTION_OBJECT_FLOW;
		this.xmlConstant = XmlTagConstants.FUNCTION_OBJECT_FLOW;
	}
}
