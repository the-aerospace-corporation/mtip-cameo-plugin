package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Profile.Constraint;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class SecurityConstraint extends Constraint {

	public SecurityConstraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.SECURITY_CONSTRAINT;
		this.xmlConstant = XmlTagConstants.SECURITY_CONSTRAINT;
		this.sysmlElement = f.createConstraintInstance();
		this.creationStereotype = UAFProfile.SECURITY_CONSTRAINT_STEREOTYPE;
	}

}
