/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class PackageDiagram  extends AbstractDiagram{

	public PackageDiagram(String name, String EAID) {
		 super(name, EAID);
		 // SysMLConstants.SYSML_PACKAGE_DIAGRAM
		 this.sysmlConstant = "SysML Package Diagram";
		 this.xmlConstant = XmlTagConstants.PACKAGEDIAGRAM;
		 this.allowableElements = SysmlConstants.PKG_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return this.sysmlConstant;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.PACKAGEDIAGRAM;
	}
}
