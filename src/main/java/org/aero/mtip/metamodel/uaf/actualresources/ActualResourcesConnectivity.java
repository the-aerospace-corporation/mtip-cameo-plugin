package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class ActualResourcesConnectivity extends BlockDefinitionDiagram {

	public ActualResourcesConnectivity(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.ACTUAL_RESOURCES_CONNECTIVITY;
	}
}
