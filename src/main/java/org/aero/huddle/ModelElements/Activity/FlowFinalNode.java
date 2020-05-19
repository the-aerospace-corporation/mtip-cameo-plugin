package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class FlowFinalNode extends CommonElement {

	public FlowFinalNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FLOWFINALNODE;
		this.xmlConstant = XmlTagConstants.FLOWFINALNODE;
		this.sysmlElement = f.createFlowFinalNodeInstance();
	}
}
