package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class SecurityStructure extends BlockDefinitionDiagram {

	public SecurityStructure(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = UAFConstants.SECURITY_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_STRUCTURE_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_STRUCTURE;
	}
}
