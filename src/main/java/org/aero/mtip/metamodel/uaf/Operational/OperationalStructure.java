package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;

public class OperationalStructure extends ActivityDiagram {
	public OperationalStructure(String name, String EAID)
	{
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_STRUCTURE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STRUCTURE;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_STRUCTURE;
	}
}
