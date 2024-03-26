/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class FullPort extends Port {

	public FullPort(String name, String EAID) {
		super(name, EAID);
		sysmlConstant = SysmlConstants.FULL_PORT;
		xmlConstant = XmlTagConstants.FULL_PORT;
		creationStereotype = SysML.getFullPortStereotype();
	}
}
