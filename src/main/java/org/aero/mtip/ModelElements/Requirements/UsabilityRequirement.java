package org.aero.mtip.ModelElements.Requirements;

import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

// Usability Requirement is not an element type as of SysML v1.6 but is a Cameo specific Non-normative extension
public class UsabilityRequirement extends Requirement {

	public UsabilityRequirement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.USABILITY_REQUIREMENT;
		this.xmlConstant = XmlTagConstants.USABILITY_REQUIREMENT;
		this.creationStereotype = SysML.getUsabilityRequirementStereotype();
	}
}
