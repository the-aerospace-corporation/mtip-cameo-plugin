package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

//import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;


public class ActivityDiagram extends AbstractDiagram{

	public ActivityDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	@Override
	public String getSysmlConstant() {
		return DiagramTypeConstants.UML_ACTIVITY_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.ACTIVITYDIAGRAM;
	}

}
