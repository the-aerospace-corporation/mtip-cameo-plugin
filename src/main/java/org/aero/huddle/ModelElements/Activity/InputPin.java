package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InputPin extends ActivityNode {

	public InputPin(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INPUTPIN;
		this.xmlConstant = XmlTagConstants.INPUTPIN;
		this.sysmlElement = f.createInputPinInstance();
	}
}
