package org.aero.mtip.metamodel.dodaf.sv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.ParametricDiagram;

public class SV10a extends ParametricDiagram {

	public SV10a(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.SV10A;
		this.xmlConstant = XmlTagConstants.SV10A;
		this.cameoDiagramConstant = "SV-10a Systems Parametric";
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
