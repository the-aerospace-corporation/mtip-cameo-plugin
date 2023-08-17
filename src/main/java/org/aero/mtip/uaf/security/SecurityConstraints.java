package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Profile.ParametricDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityConstraints extends ParametricDiagram {

	public SecurityConstraints(String name, String EAID) {
		super(name, EAID);
		 this.sysmlConstant = UAFConstants.SECURITY_CONSTRAINTS_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_CONSTRAINTS_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_CONSTRAINTS;
		 this.allowableElements = UAFConstants.SC_CT_TYPES;
	}
}
