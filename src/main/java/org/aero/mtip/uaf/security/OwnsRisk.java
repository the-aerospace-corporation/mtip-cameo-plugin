package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class OwnsRisk extends Abstraction {

	public OwnsRisk(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OWNS_RISK;
		this.xmlConstant = XmlTagConstants.OWNS_RISK;
		this.initialStereotypes = Arrays.asList(UAFProfile.OWNS_RISK_STEREOTYPE);
	}
}
