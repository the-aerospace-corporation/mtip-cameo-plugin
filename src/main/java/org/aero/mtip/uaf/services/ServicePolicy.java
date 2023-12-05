package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Profile.Constraint;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServicePolicy extends Constraint {

	public ServicePolicy(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_POLICY;
		this.xmlConstant = XmlTagConstants.SERVICE_POLICY;
	}
}
