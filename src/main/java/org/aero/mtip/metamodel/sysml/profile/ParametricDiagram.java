/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.profile;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class ParametricDiagram extends AbstractDiagram {
	public ParametricDiagram(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = SysMLConstants.SYSML_PARAMETERIC_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PARAMETRICDIAGRAM;
	}
}
