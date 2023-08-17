package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityConstraintsDefinition extends BlockDefinitionDiagram {

	public SecurityConstraintsDefinition(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = UAFConstants.SECURITY_CONSTRAINTS_DEFINITIOn_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_CONSTRAINTS_DEFINITION_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_CONSTRAINTS_DEFINITION;
		 this.allowableElements = UAFConstants.SC_CT_DEF_TYPES;
	}
	
}
