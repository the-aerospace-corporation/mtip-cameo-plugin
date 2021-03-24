package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class StateMachineDiagram  extends AbstractDiagram{

	public StateMachineDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_STATE_MACHINE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.STATEMACHINEDIAGRAM;
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
