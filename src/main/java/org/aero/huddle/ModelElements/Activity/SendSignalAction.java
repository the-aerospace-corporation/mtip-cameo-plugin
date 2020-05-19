package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class SendSignalAction extends CommonElement {

	public SendSignalAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.SENDSIGNALACTION;
		this.xmlConstant = XmlTagConstants.SENDSIGNALACTION;
		this.sysmlElement = f.createSendSignalActionInstance();
	}
}
