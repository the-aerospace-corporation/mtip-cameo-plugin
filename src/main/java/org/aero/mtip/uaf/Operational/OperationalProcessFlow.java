package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalProcessFlow extends ActivityDiagram {
	public OperationalProcessFlow(String name, String EAID)
	{
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_PROCESS_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PROCESS_FLOW;
		this.cameoDiagramConstant = "Operational Process Flow";
		this.allowableElements = UAFConstants.OPERATIONAL_PROCESS_FLOW_TYPES;
	}
	
	@Override
	public String getSysmlConstant()
	{
		return this.cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType()
	{
		return this.xmlConstant;
	}
}
