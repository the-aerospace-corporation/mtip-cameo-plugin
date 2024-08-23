/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.profile;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class ClassDiagram extends AbstractDiagram {

	public ClassDiagram(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = SysmlConstants.CLASSDIAGRAM;
		this.xmlConstant = XmlTagConstants.CLASSDIAGRAM;
	}
	
	@Override
	public String getCameoDiagramConstant() {
		return 	DiagramTypeConstants.UML_PROFILE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.PROFILEDIAGRAM;
	}
}
