package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OwnsRisk extends Abstraction {

	public OwnsRisk(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OWNS_RISK;
		this.xmlConstant = XmlTagConstants.OWNS_RISK;
	}
}
