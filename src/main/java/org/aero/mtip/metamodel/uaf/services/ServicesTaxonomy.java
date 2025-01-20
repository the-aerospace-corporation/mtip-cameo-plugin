package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class ServicesTaxonomy extends BlockDefinitionDiagram {

	public ServicesTaxonomy(String name, String EAID) {
		super(name, EAID);

		 this.metamodelConstant = UAFConstants.SERVICES_TAXONOMY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SERVICES_TAXONOMY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SERVICES_TAXONOMY;
	}
}
