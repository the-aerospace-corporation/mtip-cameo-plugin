/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;

public class SystemContext extends CommonElement {

	public SystemContext(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		metamodelConstant = SysmlConstants.SYSTEM_CONTEXT;
		xmlConstant = XmlTagConstants.BLOCK;
		creationStereotype = SysML.getSystemContextStereotype();
	}
}
