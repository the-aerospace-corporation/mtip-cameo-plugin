package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class SecurityTaxonomy extends BlockDefinitionDiagram {
	
	public SecurityTaxonomy(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = UAFConstants.SECURITY_TAXONOMY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_TAXONOMY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_TAXONOMY;
	}
}
