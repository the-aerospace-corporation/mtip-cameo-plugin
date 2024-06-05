package org.aero.mtip.dodaf.pv;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PV1 extends AbstractDiagram {

	public PV1(String name, String EAID) {
		super(name, EAID);		
		this.metamodelConstant = DoDAFConstants.PV1;
		this.xmlConstant = XmlTagConstants.PV1;
		this.cameoDiagramConstant = "PV-1 Project Portfolio Relationships";
	}

	@Override
	public String getCameoDiagramConstant() {
		return cameoDiagramConstant;
	}

	@Override
	public String getDiagramType() {
		return xmlConstant;
	}

}
