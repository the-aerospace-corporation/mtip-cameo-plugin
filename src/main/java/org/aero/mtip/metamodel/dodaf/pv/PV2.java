package org.aero.mtip.metamodel.dodaf.pv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class PV2 extends AbstractDiagram {

	public PV2(String name, String EAID) {
		super(name, EAID);		
		this.metamodelConstant = DoDAFConstants.PV2;
		this.xmlConstant = XmlTagConstants.PV2;
		this.cameoDiagramConstant = "";
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
