package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Operation extends CommonElement {
	public Operation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OPERATION;
		this.xmlConstant = XmlTagConstants.OPERATION;
		this.sysmlElement = f.createOperationInstance();
	}
}
