package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcesConnectivity extends BlockDefinitionDiagram{
    public ResourcesConnectivity(String name, String EAID) {
         super(name, EAID);
         this.metamodelConstant = UAFConstants.RESOURCES_CONNECTIVITY_DIAGRAM;
         this.xmlConstant = XmlTagConstants.RESOURCES_CONNECTIVITY_DIAGRAM;
         this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_CONNECTIVITY;
    }
}