/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;

public class FullPort extends Port {
	public FullPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.FULL_PORT;
		this.xmlConstant = XmlTagConstants.FULL_PORT;
		this.element = f.createPortInstance();
		this.creationStereotype = SysML.getFullPortStereotype();
	}
}
