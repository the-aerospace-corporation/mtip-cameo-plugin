package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class EnvironmentDiagram extends BlockDefinitionDiagram {
  public EnvironmentDiagram(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.ENVIRONMENT_DIAGRAM;
    this.xmlConstant = XmlTagConstants.ENVIRONMENT_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.ENVIRONMENT;
  }
}
