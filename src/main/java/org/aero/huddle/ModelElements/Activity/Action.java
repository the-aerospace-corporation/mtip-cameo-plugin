package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Action extends CommonElement {
	public Action(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTION;
		this.xmlConstant = XmlTagConstants.ACTION;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}
}
