/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.UseCase;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;

public class UseCaseDiagram  extends AbstractDiagram{

	public UseCaseDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = SysMLConstants.SYSML_USE_CASE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.USECASEDIAGRAM;
		 this.cameoDiagramConstant = SysMLConstants.SYSML_USE_CASE_DIAGRAM;
		 this.allowableElements = SysmlConstants.UC_TYPES;
	}
}
