package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Mitigates extends Dependency {

	public Mitigates(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.MITIGATES;
		this.xmlConstant = XmlTagConstants.MITIGATES;
	}
}
