package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalConstraintsDefinition extends AbstractDiagram {
	public OperationalConstraintsDefinition(String name, String EAID)
	{
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_CONSTRAINTS_DEFINITION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONSTRAINTS_DEFINITION;
		this.cameoDiagramConstant = "Operational Constraints Definition";
		this.allowableElements = UAFConstants.OPERATIONAL_CONSTRAINTS_DEFINITION_TYPES;
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