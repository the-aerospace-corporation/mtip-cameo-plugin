package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class SecurityProcesses extends BlockDefinitionDiagram {

	public SecurityProcesses(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SECURITY_PROCESSES_DIAGRAM;
		this.xmlConstant = XmlTagConstants.SECURITY_PROCESSES_DIAGRAM;
		this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_PROCESSES;
	}

}
