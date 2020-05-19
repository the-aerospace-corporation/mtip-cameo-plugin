package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class OutputPin extends CommonElement {

	public OutputPin(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OUTPUTPIN;
		this.xmlConstant = XmlTagConstants.OUTPUTPIN;
		this.sysmlElement = f.createOutputPinInstance();
	}
}
