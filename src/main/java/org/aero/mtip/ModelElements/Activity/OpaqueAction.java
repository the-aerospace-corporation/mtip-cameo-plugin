/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OpaqueAction extends CommonElement {

	public OpaqueAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.OPAQUE_ACTION;
		this.xmlConstant = XmlTagConstants.OPAQUE_ACTION;
		this.element = f.createOpaqueActionInstance();
	}
}
