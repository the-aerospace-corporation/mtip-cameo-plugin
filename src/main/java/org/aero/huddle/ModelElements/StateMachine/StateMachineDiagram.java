package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

//import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class StateMachineDiagram  extends AbstractDiagram{

	public StateMachineDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	@Override
	public String getSysmlConstant() {
		return DiagramTypeConstants.UML_STATECHART_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.STATEMACHINEDIAGRAM;
	}
}
