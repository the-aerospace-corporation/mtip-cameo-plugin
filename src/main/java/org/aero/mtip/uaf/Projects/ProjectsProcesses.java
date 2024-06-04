package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectsProcesses extends BlockDefinitionDiagram{
    public ProjectsProcesses(String name, String EAID) {
         super(name, EAID);
         this.metamodelConstant = UAFConstants.PROJECTS_PROCESSES_DIAGRAM;
         this.xmlConstant = XmlTagConstants.PROJECTS_PROCESSES_DIAGRAM;
         this.cameoDiagramConstant = CameoDiagramConstants.PROJECTS_PROCESSES;
    }
}