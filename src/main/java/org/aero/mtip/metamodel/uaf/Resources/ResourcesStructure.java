package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class ResourcesStructure extends BlockDefinitionDiagram {
  public ResourcesStructure(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.RESOURCES_STRUCTURE_DIAGRAM;
    this.xmlConstant = XmlTagConstants.RESOURCES_STRUCTURE_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_STRUCTURE;
  }
}
