package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachineDiagram;

public class ResourcesStates extends StateMachineDiagram {
  public ResourcesStates(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.RESOURCES_STATES_DIAGRAM;
    this.xmlConstant = XmlTagConstants.RESOURCES_STATES_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_STATES;
  }
}
