package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class CallBehaviorAction extends Action {

	public CallBehaviorAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CALLBEHAVIORACTION;
		this.xmlConstant = XmlTagConstants.CALLBEHAVIORACTION;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}
}
