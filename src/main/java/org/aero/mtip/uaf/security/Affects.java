package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Affects extends Dependency {

	public Affects(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.AFFECTS;
		this.xmlConstant = XmlTagConstants.AFFECTS;
		this.initialStereotypes = Arrays.asList(UAFProfile.AFFECTS_STEREOTYPE);
	}

}
