package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class CreateObjectAction extends ActivityNode {

	public CreateObjectAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CREATEOBJECTACTION;
		this.xmlConstant = XmlTagConstants.CREATEOBJECTACTION;
		this.sysmlElement = f.createCreateObjectActionInstance();
	}
}
