package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityStructure extends BlockDefinitionDiagram {

	public SecurityStructure(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = UAFConstants.SECURITY_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_STRUCTURE_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_STRUCTURE;
		 this.allowableElements = UAFConstants.SC_SR_TYPES;
	}
}
