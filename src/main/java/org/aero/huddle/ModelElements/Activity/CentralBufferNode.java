package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class CentralBufferNode extends CommonElement {

	public CentralBufferNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CENTRALBUFFERNODE;
		this.xmlConstant = XmlTagConstants.CENTRALBUFFERNODE;
		this.sysmlElement = f.createCentralBufferNodeInstance();
	}
}
