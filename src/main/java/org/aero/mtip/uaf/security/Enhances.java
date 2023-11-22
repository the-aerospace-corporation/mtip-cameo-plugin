package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Enhances extends Abstraction {

	public Enhances(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ENHANCES;
		this.xmlConstant = XmlTagConstants.ENHANCES;
	}
}
