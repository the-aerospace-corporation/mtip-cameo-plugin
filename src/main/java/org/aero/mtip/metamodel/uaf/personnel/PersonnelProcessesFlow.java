package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;

public class PersonnelProcessesFlow extends ActivityDiagram {

	public PersonnelProcessesFlow(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_PROCESSES_FLOW_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_PROCESSES_FLOW_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_PROCESS_FLOW;
	}
}
