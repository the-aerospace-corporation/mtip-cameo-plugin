package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SV10b extends StateMachineDiagram {

	public SV10b(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV10B;
		this.xmlConstant = XmlTagConstants.SV10B;
		this.cameoDiagramConstant = "SV-10b Systems State Transition Description";
		this.allowableElements = DoDAFConstants.SV10B_TYPES;
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
