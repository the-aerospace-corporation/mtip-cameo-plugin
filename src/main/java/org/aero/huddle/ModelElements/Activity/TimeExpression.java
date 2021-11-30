package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class TimeExpression extends ActivityNode {

	public TimeExpression(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.TIMEEXPRESSION;
		this.xmlConstant = XmlTagConstants.TIMEEXPRESSION;
		this.sysmlElement = f.createTimeExpressionInstance();
	}

}
