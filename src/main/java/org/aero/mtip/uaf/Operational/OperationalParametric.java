package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalParametric extends AbstractDiagram{
	
	public OperationalParametric(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_PARAMETRIC;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PARAMETRIC;
		this.cameoDiagramConstant = "Operational Parametric";
		this.allowableElements = UAFConstants.OPERATIONAL_PARAMETRIC_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}

