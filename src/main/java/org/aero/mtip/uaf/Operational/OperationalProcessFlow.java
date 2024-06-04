package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalProcessFlow extends ActivityDiagram {
	public OperationalProcessFlow(String name, String EAID)
	{
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PROCESS_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PROCESS_FLOW;
		this.cameoDiagramConstant = CameoDiagramConstants.OPERATIONAL_PROCESS_FLOW;
	}
}
