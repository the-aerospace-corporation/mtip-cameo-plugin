package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OperationalParametric extends AbstractDiagram {
	
	public OperationalParametric(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PARAMETRIC;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PARAMETRIC;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_PARAMETRIC;
	}
}

