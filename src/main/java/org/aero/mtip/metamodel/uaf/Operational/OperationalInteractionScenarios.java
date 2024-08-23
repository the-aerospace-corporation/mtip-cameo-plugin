package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OperationalInteractionScenarios extends AbstractDiagram{
	
	public OperationalInteractionScenarios(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_INTERACTION_SCENARIOS;
	}
}

