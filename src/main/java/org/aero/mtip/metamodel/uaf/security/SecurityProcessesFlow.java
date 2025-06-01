package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;

public class SecurityProcessesFlow extends ActivityDiagram {

	// Exports as Security Processes to match Sparx EA Security Processes
	public SecurityProcessesFlow(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = UAFConstants.SECURITY_PROCESSES_FLOW_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_PROCESSES_FLOW_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_PROCESSES_FLOW;
	}
}
