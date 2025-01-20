package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class PersonnelProcesses extends BlockDefinitionDiagram {

	public PersonnelProcesses(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_PROCESSES_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_PROCESSES_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_PROCESSES;
	}
}
