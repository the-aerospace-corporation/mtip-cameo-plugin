/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Requirements;

import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class InterfaceRequirement extends Requirement {

	public InterfaceRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.INTERFACE_REQUIREMENT;
		this.xmlConstant = XmlTagConstants.INTERFACE_REQUIREMENT;
		this.creationStereotype = SysML.getInterfaceRequirementStereotype();
	}
}
