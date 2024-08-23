/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.profile;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class ProfileDiagram extends AbstractDiagram {

	public ProfileDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = SysmlConstants.PROFILEDIAGRAM;
		 this.xmlConstant = XmlTagConstants.PROFILEDIAGRAM;
	}
}
