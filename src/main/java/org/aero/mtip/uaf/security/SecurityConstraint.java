package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Profile.Constraint;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityConstraint extends Constraint {

	public SecurityConstraint(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SECURITY_CONSTRAINT;
		this.xmlConstant = XmlTagConstants.SECURITY_CONSTRAINT;
	}

}
