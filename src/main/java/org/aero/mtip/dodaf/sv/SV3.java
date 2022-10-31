package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.Matrix.AbstractMatrix;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class SV3 extends AbstractMatrix {

	public SV3(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV3;
		this.xmlConstant = XmlTagConstants.SV3;
		this.cameoDiagramConstant = "SV-3 Systems-Systems Matrix";
		this.allowableElements = DoDAFConstants.SV3_TYPES;
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
