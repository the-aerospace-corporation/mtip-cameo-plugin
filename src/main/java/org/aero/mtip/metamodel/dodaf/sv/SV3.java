package org.aero.mtip.metamodel.dodaf.sv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.matrix.AbstractMatrix;

public class SV3 extends AbstractMatrix {

	public SV3(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.SV3;
		this.xmlConstant = XmlTagConstants.SV3;
		this.cameoDiagramConstant = "SV-3 Systems-Systems Matrix";
	}
	
	@Override
	public String getCameoDiagramConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}
