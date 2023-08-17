package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalInternalConnectivity extends AbstractDiagram{
	
	public OperationalInternalConnectivity(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_INTERNAL_CONNECTIVITY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_INTERNAL_CONNECTIVITY;
		this.cameoDiagramConstant = "Operational Internal Connectivity";
		this.allowableElements = UAFConstants.OPERATIONAL_INTERNAL_CONNECTIVITY_TYPES;
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

