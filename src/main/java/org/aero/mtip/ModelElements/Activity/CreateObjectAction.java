/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CreateObjectAction extends ActivityNode {

	public CreateObjectAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.CREATE_OBJECT_ACTION;
		this.xmlConstant = XmlTagConstants.CREATE_OBJECT_ACTION;
		this.element = f.createCreateObjectActionInstance();
	}
}
