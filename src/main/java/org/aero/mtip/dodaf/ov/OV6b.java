package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OV6b extends StateMachineDiagram {

	public OV6b(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV6B;
		this.xmlConstant = XmlTagConstants.OV6B;
		this.cameoDiagramConstant = "OV-6b Operational State Transition Description";
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
