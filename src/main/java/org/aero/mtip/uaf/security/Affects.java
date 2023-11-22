package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Affects extends Dependency {

	public Affects(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.AFFECTS;
		this.xmlConstant = XmlTagConstants.AFFECTS;
	}

}
