package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ActivityPartition extends CommonElement {

	public ActivityPartition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTIVITYPARTITION;
		this.xmlConstant = XmlTagConstants.ACTIVITYPARTITION;
		this.sysmlElement = f.createActivityPartitionInstance();
	}
}
