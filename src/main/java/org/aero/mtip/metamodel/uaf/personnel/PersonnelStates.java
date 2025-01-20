package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachineDiagram;

public class PersonnelStates extends StateMachineDiagram {

	public PersonnelStates(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_STATES_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_STATES_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_STATES;
	}
}
