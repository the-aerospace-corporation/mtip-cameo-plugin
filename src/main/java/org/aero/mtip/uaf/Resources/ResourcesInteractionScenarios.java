package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Sequence.SequenceDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcesInteractionScenarios extends SequenceDiagram {
  public ResourcesInteractionScenarios(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.RESOURCES_INTERACTION_SCENARIOS_DIAGRAM;
    this.xmlConstant = XmlTagConstants.RESOURCES_INTERACTION_SCENARIOS_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_INTERACTION_SCENARIOS;
  }
}
