/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLConstants;
//import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class PackageDiagram  extends AbstractDiagram{

	public PackageDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_PACKAGE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.PACKAGEDIAGRAM;
		 this.allowableElements = SysmlConstants.PKG_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return 	DiagramTypeConstants.UML_PACKAGE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.PACKAGEDIAGRAM;
	}
}
