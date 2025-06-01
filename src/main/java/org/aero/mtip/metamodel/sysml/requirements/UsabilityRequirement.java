package org.aero.mtip.metamodel.sysml.requirements;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;

// Usability Requirement is not an element type as of SysML v1.6 but is a Cameo specific
// Non-normative extension
public class UsabilityRequirement extends Requirement {
  public UsabilityRequirement(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
    this.metamodelConstant = SysmlConstants.USABILITY_REQUIREMENT;
    this.xmlConstant = XmlTagConstants.USABILITY_REQUIREMENT;
    this.creationStereotype = SysML.getUsabilityRequirementStereotype();
  }
}
