package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ForkNode extends CommonElement {

	public ForkNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FORKNODE;
		this.xmlConstant = XmlTagConstants.FORKNODE;
		this.sysmlElement = f.createForkNodeInstance();
	}
}
