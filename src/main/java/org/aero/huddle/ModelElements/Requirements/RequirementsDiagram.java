package org.aero.huddle.ModelElements.Requirements;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class RequirementsDiagram  extends AbstractDiagram{

	public RequirementsDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_REQUIREMENTS_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.REQUIREMENTSDIAGRAM;
	}
}
