package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServicesStructure extends BlockDefinitionDiagram {

	public ServicesStructure(String name, String EAID) {
		super(name, EAID);

		 this.metamodelConstant = UAFConstants.SERVICES_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SERVICES_STRUCTURE_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SERVICES_STRUCTURE;
	}

}
