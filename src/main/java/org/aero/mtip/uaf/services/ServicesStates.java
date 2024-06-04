package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServicesStates extends StateMachineDiagram {

	public ServicesStates(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = UAFConstants.SERVICES_STATES_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SERVICES_STATES_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SERVICES_STATES;
	}
}
