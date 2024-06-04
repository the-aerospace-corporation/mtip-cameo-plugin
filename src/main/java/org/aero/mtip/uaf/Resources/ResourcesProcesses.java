package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcesProcesses extends BlockDefinitionDiagram{
    public ResourcesProcesses(String name, String EAID) {
         super(name, EAID);
         this.metamodelConstant = UAFConstants.RESOURCES_PROCESSES_DIAGRAM;
         this.xmlConstant = XmlTagConstants.RESOURCES_PROCESSES_DIAGRAM;
         this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_PROCESSES;
    }
}