package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.SequenceDiagram;

public class PersonnelInteractionScenarios extends SequenceDiagram {

	public PersonnelInteractionScenarios(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_INTERACTION_SCENARIOS;
	}
}
