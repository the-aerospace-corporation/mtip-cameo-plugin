/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.requirements;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;

public class PhysicalRequirement extends Requirement {

	public PhysicalRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = SysmlConstants.PHYSICAL_REQUIREMENT;
		this.xmlConstant = XmlTagConstants.PHYSICAL_REQUIREMENT;
		this.creationStereotype = SysML.getPhysicalRequirementStereotype();
	}
}
