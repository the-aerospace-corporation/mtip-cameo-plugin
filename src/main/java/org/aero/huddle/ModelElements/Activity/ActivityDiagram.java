package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;


public class ActivityDiagram extends AbstractDiagram{

	public ActivityDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_ACTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTIVITYDIAGRAM;
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_ACTIVITY_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.ACTIVITYDIAGRAM;
	}

}
