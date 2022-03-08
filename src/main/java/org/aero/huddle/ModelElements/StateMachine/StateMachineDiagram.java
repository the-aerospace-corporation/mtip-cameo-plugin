/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class StateMachineDiagram  extends AbstractDiagram{

	public StateMachineDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_STATE_MACHINE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STATEMACHINEDIAGRAM;
		 this.allowableElements = SysmlConstants.STM_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_STATE_MACHINE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.STATEMACHINEDIAGRAM;
	}
}
