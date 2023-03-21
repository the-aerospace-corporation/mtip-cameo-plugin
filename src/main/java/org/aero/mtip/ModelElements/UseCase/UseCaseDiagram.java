/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.UseCase;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class UseCaseDiagram  extends AbstractDiagram{

	public UseCaseDiagram(String name, String EAID) {
		 super(name, EAID);
		 // SysMLConstants.SYSML_USE_CASE_DIAGRAM
		 this.sysmlConstant = "SysML Use Case Diagram";
		 this.xmlConstant = XmlTagConstants.USECASEDIAGRAM;
		 this.allowableElements = SysmlConstants.UC_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return this.sysmlConstant;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.USECASEDIAGRAM;
	}
	
}
