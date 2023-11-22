package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalInteractionScenarios extends AbstractDiagram{
	
	public OperationalInteractionScenarios(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.allowableElements = UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS_TYPES;
	}
}

