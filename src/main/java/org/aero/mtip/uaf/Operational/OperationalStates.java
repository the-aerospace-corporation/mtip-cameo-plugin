package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalStates extends AbstractDiagram{
	
	public OperationalStates(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_STATES;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STATES;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_STATES;
	}
}

