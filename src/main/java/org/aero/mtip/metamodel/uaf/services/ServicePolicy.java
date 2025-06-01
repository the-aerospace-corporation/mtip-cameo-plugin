package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.Constraint;

public class ServicePolicy extends Constraint {

	public ServicePolicy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_POLICY;
		this.xmlConstant = XmlTagConstants.SERVICE_POLICY;
	}
}
