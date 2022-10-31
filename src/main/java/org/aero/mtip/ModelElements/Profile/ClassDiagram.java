/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class ClassDiagram extends AbstractDiagram {

	public ClassDiagram(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.CLASSDIAGRAM;
		this.xmlConstant = XmlTagConstants.CLASSDIAGRAM;
		this.allowableElements = SysmlConstants.CLASSDIAGRAM_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return 	DiagramTypeConstants.UML_PROFILE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.PROFILEDIAGRAM;
	}
}
