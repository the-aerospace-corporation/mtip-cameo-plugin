package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ActivityFinalNode extends ActivityNode {

	public ActivityFinalNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTIVITYFINALNODE;
		this.xmlConstant = XmlTagConstants.ACTIVITYFINALNODE;
		this.sysmlElement = f.createActivityFinalNodeInstance();
	}
}
