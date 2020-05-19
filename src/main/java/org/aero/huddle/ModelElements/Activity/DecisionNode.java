package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class DecisionNode extends CommonElement {

	public DecisionNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DECISIONNODE;
		this.xmlConstant = XmlTagConstants.DECISIONNODE;
		this.sysmlElement = f.createDecisionNodeInstance();
	}
}
