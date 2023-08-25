package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Mitigates extends Dependency {

	public Mitigates(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.MITIGATES;
		this.xmlConstant = XmlTagConstants.MITIGATES;
		this.initialStereotypes = Arrays.asList(UAFProfile.MITIGATES_STEREOTYPE);
	}
}
