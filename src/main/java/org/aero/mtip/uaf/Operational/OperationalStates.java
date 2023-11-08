package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalStates extends AbstractDiagram{
	
	public OperationalStates(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_STATES;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STATES;
		this.cameoDiagramConstant = "Operational States";
		this.allowableElements = UAFConstants.OPERATIONAL_STATES_TYPES;
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

