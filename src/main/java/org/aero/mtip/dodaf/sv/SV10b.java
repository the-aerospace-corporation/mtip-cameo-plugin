package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

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
