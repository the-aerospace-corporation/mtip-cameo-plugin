package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class DestroyObjectAction extends CommonElement {

	public DestroyObjectAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DESTROYOBJECTACTION;
		this.xmlConstant = XmlTagConstants.DESTROYOBJECTACTION;
		this.sysmlElement = f.createDestroyObjectActionInstance();
	}
}
