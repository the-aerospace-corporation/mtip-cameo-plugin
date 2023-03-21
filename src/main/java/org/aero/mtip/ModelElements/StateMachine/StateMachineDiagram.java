/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class StateMachineDiagram  extends AbstractDiagram{

	public StateMachineDiagram(String name, String EAID) {
		 super(name, EAID);
		 // SysMLConstants.SYSML_STATE_MACHINE_DIAGRAM
		 this.sysmlConstant = "SysML State Machine Diagram";
		 this.xmlConstant = XmlTagConstants.STATEMACHINEDIAGRAM;
		 this.allowableElements = SysmlConstants.STM_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return this.sysmlConstant;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.STATEMACHINEDIAGRAM;
	}
}
