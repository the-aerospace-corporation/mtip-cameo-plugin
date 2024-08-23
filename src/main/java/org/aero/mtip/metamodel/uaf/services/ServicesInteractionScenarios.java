package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.SequenceDiagram;

public class ServicesInteractionScenarios extends SequenceDiagram {

	public ServicesInteractionScenarios(String name, String EAID) {
		super(name, EAID);

		 this.metamodelConstant = UAFConstants.SERVICES_INTERACTION_SCENARIOS_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SERVICES_INTERACTION_SCENARIOS_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SERVICES_INTERACTION_SCENARIOS;
	}
}
