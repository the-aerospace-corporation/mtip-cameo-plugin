package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class OwnsRiskInContext extends Abstraction {

	public OwnsRiskInContext(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OWNS_RISK_IN_CONTEXT;
		this.xmlConstant = XmlTagConstants.OWNS_RISK_IN_CONTEXT;
	}
}