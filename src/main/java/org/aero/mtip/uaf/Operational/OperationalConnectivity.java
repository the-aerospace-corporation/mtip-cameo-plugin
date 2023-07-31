package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalConnectivity extends AbstractDiagram {
	public OperationalConnectivity(String name, String EAID)
	{
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_CONNECTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONNECTIVITY;
		this.cameoDiagramConstant = "Operational Connectivity";
		this.allowableElements = UAFConstants.OPERATIONAL_CONNECTIVITY_TYPES;
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