package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalParametric extends AbstractDiagram{
	
	public OperationalParametric(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PARAMETRIC;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PARAMETRIC;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_PARAMETRIC;
		this.allowableElements = UAFConstants.OPERATIONAL_PARAMETRIC_TYPES;
	}
}

