package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class EnvironmentDiagram extends BlockDefinitionDiagram {
  public EnvironmentDiagram(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.ENVIRONMENT_DIAGRAM;
    this.xmlConstant = XmlTagConstants.ENVIRONMENT_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.ENVIRONMENT;
  }
}
