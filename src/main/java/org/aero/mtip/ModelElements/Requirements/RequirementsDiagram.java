/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Requirements;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.requirements.util.RequirementsConstants;

public class RequirementsDiagram  extends AbstractDiagram{

	public RequirementsDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = RequirementsConstants.SYSML_REQUIREMENTS_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.REQUIREMENTSDIAGRAM;
		 this.allowableElements = SysmlConstants.REQ_TYPES;
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
