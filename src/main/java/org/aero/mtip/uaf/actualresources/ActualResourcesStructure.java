package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualResourcesStructure extends BlockDefinitionDiagram {
	
	public ActualResourcesStructure(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.ACTUAL_RESOURCES_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCES_STRUCTURE_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.ACTUAL_RESOURCES_STRUCTURE;
	}
}
