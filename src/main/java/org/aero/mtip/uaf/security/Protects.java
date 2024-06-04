package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Protects extends Dependency {

	public Protects(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROTECTS;
		this.xmlConstant = XmlTagConstants.PROTECTS;
	}
}
