package org.aero.mtip.metamodel.dodaf.sv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.ClassDiagram;

public class SV4 extends ClassDiagram {

	public SV4(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.SV4;
		this.xmlConstant = XmlTagConstants.SV4;
		this.cameoDiagramConstant = "SV-4 Systems Functionality Description";
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
