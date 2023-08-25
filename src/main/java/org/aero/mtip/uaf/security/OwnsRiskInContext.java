package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class OwnsRiskInContext extends Abstraction {

	public OwnsRiskInContext(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OWNS_RISK_IN_CONTEXT;
		this.xmlConstant = XmlTagConstants.OWNS_RISK_IN_CONTEXT;
		this.initialStereotypes = Arrays.asList(UAFProfile.OWNS_RISK_IN_CONTEXT_STEREOTYPE);
	}
}
