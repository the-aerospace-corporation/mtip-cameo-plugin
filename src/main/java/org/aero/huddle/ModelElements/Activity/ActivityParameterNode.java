package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ActivityParameterNode extends CommonElement{
	public ActivityParameterNode(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTIVITYPARAMETERNODE;
		this.xmlConstant = XmlTagConstants.ACTIVITYPARAMETERNODE;
		this.sysmlElement = f.createActivityParameterNodeInstance();
	}
}