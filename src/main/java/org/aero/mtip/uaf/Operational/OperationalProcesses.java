package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalProcesses extends BlockDefinitionDiagram{
	
	public OperationalProcesses(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PROCESSES_DIAGRAM;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PROCESSES_DIAGRAM;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_PROCESSES;
	}
}
