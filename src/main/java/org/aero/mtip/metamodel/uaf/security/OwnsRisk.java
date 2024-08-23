package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class OwnsRisk extends Abstraction {

	public OwnsRisk(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OWNS_RISK;
		this.xmlConstant = XmlTagConstants.OWNS_RISK;
	}
}
