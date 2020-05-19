package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class LoopNode extends CommonElement {

	public LoopNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.LOOPNODE;
		this.xmlConstant = XmlTagConstants.LOOPNODE;
		this.sysmlElement = f.createLoopNodeInstance();
	}
}
