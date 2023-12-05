package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServicesProcesses extends BlockDefinitionDiagram {

	public ServicesProcesses(String name, String EAID) {
		super(name, EAID);

		 this.metamodelConstant = UAFConstants.SERVICES_PROCESSES_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SERVICES_PROCESSES_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SERVICES_PROCESSES;
	}
}
