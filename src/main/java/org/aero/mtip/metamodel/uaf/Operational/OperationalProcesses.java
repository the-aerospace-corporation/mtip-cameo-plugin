package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class OperationalProcesses extends BlockDefinitionDiagram {
	
	public OperationalProcesses(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PROCESSES_DIAGRAM;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PROCESSES_DIAGRAM;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_PROCESSES;
	}
}
