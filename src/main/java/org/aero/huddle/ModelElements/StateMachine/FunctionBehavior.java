package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class FunctionBehavior extends CommonElement {

	public FunctionBehavior(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FUNCTIONBEHAVIOR;
		this.xmlConstant = XmlTagConstants.FUNCTIONBEHAVIOR;
		this.sysmlElement = f.createFunctionBehaviorInstance();
	}
}
