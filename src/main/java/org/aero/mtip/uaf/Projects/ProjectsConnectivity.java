package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.InternalBlock.InternalBlockDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectsConnectivity extends InternalBlockDiagram {
  public ProjectsConnectivity(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.PROJECTS_CONNECTIVITY_DIAGRAM;
    this.xmlConstant = XmlTagConstants.PROJECTS_CONNECTIVITY_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.PROJECTS_CONNECTIVITY;
  }
}
