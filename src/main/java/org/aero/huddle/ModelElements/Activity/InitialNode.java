package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InitialNode extends ActivityNode {

	public InitialNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INITIALNODE;
		this.xmlConstant = XmlTagConstants.INITIALNODE;
		this.sysmlElement = f.createInitialNodeInstance();
	}
}
