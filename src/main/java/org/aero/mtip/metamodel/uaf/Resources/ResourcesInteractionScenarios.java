package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.SequenceDiagram;

public class ResourcesInteractionScenarios extends SequenceDiagram {
  public ResourcesInteractionScenarios(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.RESOURCES_INTERACTION_SCENARIOS_DIAGRAM;
    this.xmlConstant = XmlTagConstants.RESOURCES_INTERACTION_SCENARIOS_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_INTERACTION_SCENARIOS;
  }
}