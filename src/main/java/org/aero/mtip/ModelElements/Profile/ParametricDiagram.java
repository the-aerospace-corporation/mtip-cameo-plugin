/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class ParametricDiagram extends AbstractDiagram {

	public ParametricDiagram(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_PARAMETERIC_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PARAMETRICDIAGRAM;
		 this.allowableElements = SysmlConstants.PAR_TYPES;
	}

	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_PARAMETERIC_DIAGRAM;
	}

	@Override
	public String getDiagramType() {
		return XmlTagConstants.PARAMETRICDIAGRAM;
	}
}
