package org.aero.huddle.ModelElements.Requirements;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;
import com.nomagic.requirements.util.RequirementsConstants;
import com.nomagic.requirements.util.RequirementsHelper;
import com.nomagic.uml2.UML2Constants;

public class RequirementsDiagram  extends AbstractDiagram{

	public RequirementsDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	@Override
	public String getSysmlConstant() {
		return RequirementsConstants.SYSML_REQUIREMENTS_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.REQUIREMENTSDIAGRAM;
	}
}
