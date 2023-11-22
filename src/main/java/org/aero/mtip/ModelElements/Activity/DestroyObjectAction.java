/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DestroyObjectAction extends ActivityNode {

	public DestroyObjectAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.DESTROYOBJECTACTION;
		this.xmlConstant = XmlTagConstants.DESTROYOBJECTACTION;
		this.element = f.createDestroyObjectActionInstance();
	}
}
