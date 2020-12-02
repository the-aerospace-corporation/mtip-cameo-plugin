package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Activity extends CommonElement{
	public Activity(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTIVITY;
		this.xmlConstant = XmlTagConstants.ACTIVITY;
		this.sysmlElement = f.createActivityInstance();
	}
}