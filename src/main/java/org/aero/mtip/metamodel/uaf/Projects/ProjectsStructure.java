package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class ProjectsStructure extends BlockDefinitionDiagram {
  public ProjectsStructure(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.PROJECTS_STRUCTURE_DIAGRAM;
    this.xmlConstant = XmlTagConstants.PROJECTS_STRUCTURE_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.PROJECTS_STRUCTURE;
  }
}
