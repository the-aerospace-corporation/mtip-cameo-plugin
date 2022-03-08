/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class TimeEvent extends ActivityNode {

	public TimeEvent(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.TIMEEVENT;
		this.xmlConstant = XmlTagConstants.TIMEEVENT;
		this.sysmlElement = f.createTimeEventInstance();
	}
}
