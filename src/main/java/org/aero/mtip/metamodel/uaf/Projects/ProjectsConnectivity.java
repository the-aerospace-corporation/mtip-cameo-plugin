package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.InternalBlockDiagram;

public class ProjectsConnectivity extends InternalBlockDiagram {
  public ProjectsConnectivity(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.PROJECTS_CONNECTIVITY_DIAGRAM;
    this.xmlConstant = XmlTagConstants.PROJECTS_CONNECTIVITY_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.PROJECTS_CONNECTIVITY;
  }
}
