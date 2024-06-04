package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcesStates extends StateMachineDiagram {
  public ResourcesStates(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.RESOURCES_STATES_DIAGRAM;
    this.xmlConstant = XmlTagConstants.RESOURCES_STATES_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_STATES;
  }
}
