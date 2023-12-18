package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServicesConstraintsDefinition extends BlockDefinitionDiagram {

	public ServicesConstraintsDefinition(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM;
		this.xmlConstant = XmlTagConstants.SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM;
		this.cameoDiagramConstant = CameoDiagramConstants.SERVICES_CONSTRAINTS_DEFINITION;
	}
}
