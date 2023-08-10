package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityTaxonomy extends BlockDefinitionDiagram {
	public SecurityTaxonomy(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = UAFConstants.SECURITY_TAXONOMY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_TAXONOMY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_TAXONOMY;
		 this.allowableElements = UAFConstants.SC_TX_TYPES;
	}
}
