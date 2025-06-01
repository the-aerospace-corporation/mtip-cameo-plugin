package org.aero.mtip.metamodel.dodaf.sv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachineDiagram;

public class SV10b extends StateMachineDiagram {

	public SV10b(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.SV10B;
		this.xmlConstant = XmlTagConstants.SV10B;
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
