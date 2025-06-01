package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OperationalConstraintsDefinition extends AbstractDiagram {
	public OperationalConstraintsDefinition(String name, String EAID)
	{
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONSTRAINTS_DEFINITION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONSTRAINTS_DEFINITION;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_CONSTRAINTS_DEFINITION;
	}
}