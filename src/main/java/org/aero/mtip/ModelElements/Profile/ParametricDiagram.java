/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ParametricDiagram extends AbstractDiagram {

	public ParametricDiagram(String name, String EAID) {
		super(name, EAID);
		// SysMLConstants.SYSML_PARAMETERIC_DIAGRAM
		 this.sysmlConstant = "SysML Parametric Diagram";
		 this.xmlConstant = XmlTagConstants.PARAMETRICDIAGRAM;
		 this.allowableElements = SysmlConstants.PAR_TYPES;
	}

	@Override
	public String getSysmlConstant() {
		return this.sysmlConstant;
	}

	@Override
	public String getDiagramType() {
		return XmlTagConstants.PARAMETRICDIAGRAM;
	}
}
