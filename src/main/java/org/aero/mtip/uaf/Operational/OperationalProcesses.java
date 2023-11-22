package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalProcesses extends AbstractDiagram{
	
	public OperationalProcesses(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PROCESSES;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PROCESSES;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_PROCESSES;
		this.allowableElements = UAFConstants.OPERATIONAL_PROCESSES_TYPES;
	}
}
