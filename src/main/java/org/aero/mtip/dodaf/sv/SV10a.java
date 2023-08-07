package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.Profile.ParametricDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SV10a extends ParametricDiagram {

	public SV10a(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV10A;
		this.xmlConstant = XmlTagConstants.SV10A;
		this.cameoDiagramConstant = "SV-10a Systems Parametric";
		this.allowableElements = DoDAFConstants.SV10A_TYPES;
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
