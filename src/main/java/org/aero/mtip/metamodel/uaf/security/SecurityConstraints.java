package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.ParametricDiagram;

public class SecurityConstraints extends ParametricDiagram {

	public SecurityConstraints(String name, String EAID) {
		super(name, EAID);
		 this.metamodelConstant = UAFConstants.SECURITY_CONSTRAINTS_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SECURITY_CONSTRAINTS_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.SECURITY_CONSTRAINTS;
	}
}
