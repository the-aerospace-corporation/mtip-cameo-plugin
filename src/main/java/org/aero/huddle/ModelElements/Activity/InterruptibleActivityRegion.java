package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InterruptibleActivityRegion extends ActivityNode {

	public InterruptibleActivityRegion(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INTERRUPTIBLEACTIVITYREGION;
		this.xmlConstant = XmlTagConstants.INTERRUPTIBLEACTIVITYREGION;
		this.sysmlElement = f.createInterruptibleActivityRegionInstance();
	}

}
