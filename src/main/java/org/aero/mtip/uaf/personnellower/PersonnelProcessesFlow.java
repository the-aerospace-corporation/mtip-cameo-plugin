package org.aero.mtip.uaf.personnellower;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelProcessesFlow extends ActivityDiagram {

	public PersonnelProcessesFlow(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.PERSONNEL_PROCESSES_FLOW_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_PROCESSES_FLOW_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_PROCESS_FLOW;
		 this.allowableElements = UAFConstants.PR_PR_FLOW_TYPES;
	}
}
