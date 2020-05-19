package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ChangeEvent extends CommonElement {

	public ChangeEvent(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CHANGEEVENT;
		this.xmlConstant = XmlTagConstants.CHANGEEVENT;
		this.sysmlElement = f.createChangeEventInstance();
	}
}
