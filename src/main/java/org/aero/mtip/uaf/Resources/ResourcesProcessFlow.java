package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcesProcessFlow extends ActivityDiagram{
	public ResourcesProcessFlow(String name, String EAID)
	{
		super(name, EAID);
		this.sysmlConstant = UAFConstants.RESOURCES_PROCESS_FLOW;
		this.xmlConstant = XmlTagConstants.RESOURCES_PROCESS_FLOW;
		this.cameoDiagramConstant = "Resources Process Flow";
		this.allowableElements = UAFConstants.RESOURCES_PROCESS_FLOW_TYPES;
		
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
