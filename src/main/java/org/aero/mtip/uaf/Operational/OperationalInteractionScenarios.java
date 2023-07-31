package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalInteractionScenarios extends AbstractDiagram{
	
	public OperationalInteractionScenarios(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_INTERACTION_SCENARIOS;
		this.cameoDiagramConstant = "Operational Interaction Scenarios";
		this.allowableElements = UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}

