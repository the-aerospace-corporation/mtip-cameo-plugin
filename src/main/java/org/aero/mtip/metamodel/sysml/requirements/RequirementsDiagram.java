/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.metamodel.sysml.requirements;

import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;
import com.nomagic.requirements.util.RequirementsConstants;

public class RequirementsDiagram extends AbstractDiagram {
  public RequirementsDiagram(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = RequirementsConstants.SYSML_REQUIREMENTS_DIAGRAM;
    this.xmlConstant = XmlTagConstants.REQUIREMENTSDIAGRAM;
    this.cameoDiagramConstant = RequirementsConstants.SYSML_REQUIREMENTS_DIAGRAM;
  }
}
