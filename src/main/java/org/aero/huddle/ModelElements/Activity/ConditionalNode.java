package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ConditionalNode extends CommonElement {

	public ConditionalNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CONDITIONALNODE;
		this.xmlConstant = XmlTagConstants.CONDITIONALNODE;
		this.sysmlElement = f.createConditionalNodeInstance();
	}
}
