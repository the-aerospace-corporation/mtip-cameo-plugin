package org.aero.mtip.uaf.personnellower;

import org.aero.mtip.ModelElements.Sequence.SequenceDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelInteractionScenario extends SequenceDiagram {

	public PersonnelInteractionScenario(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.PERSONNEL_INTERACTION_SCENARIO_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PERSONNEL_INTERACTION_SCENARIO_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_INTERACTION_SCENARIO;
		 this.allowableElements = UAFConstants.PR_IS_TYPES;
	}
}
