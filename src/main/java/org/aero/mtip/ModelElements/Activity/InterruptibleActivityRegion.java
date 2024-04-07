/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;


import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class InterruptibleActivityRegion extends CommonElement {
	public InterruptibleActivityRegion(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.INTERRUPTIBLE_ACTIVITY_REGION;
		this.xmlConstant = XmlTagConstants.INTERRUPTIBLE_ACTIVITY_REGION;
		this.element = f.createInterruptibleActivityRegionInstance();
	}
}
