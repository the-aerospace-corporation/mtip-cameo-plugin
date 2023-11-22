package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalStructure extends ActivityDiagram {
	public OperationalStructure(String name, String EAID)
	{
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_STRUCTURE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STRUCTURE;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_STRUCTURE;
		this.allowableElements = UAFConstants.OPERATIONAL_STRUCTURE_TYPES;
	}
}
