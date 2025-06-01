package org.aero.mtip.metamodel.dodaf.sv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.ClassDiagram;

public class SV1 extends ClassDiagram {

	public SV1(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.SV1;
		this.xmlConstant = XmlTagConstants.SV1;
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
