package org.aero.mtip.dodaf.pv;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class PV1 extends AbstractDiagram {

	public PV1(String name, String EAID) {
		super(name, EAID);		
		this.sysmlConstant = DoDAFConstants.PV1;
		this.xmlConstant = XmlTagConstants.PV1;
		this.cameoDiagramConstant = "PV-1 Project Portfolio Relationships";
		this.allowableElements = DoDAFConstants.PV1_TYPES;
	}

	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}

	@Override
	public String getDiagramType() {
		return xmlConstant;
	}

}
