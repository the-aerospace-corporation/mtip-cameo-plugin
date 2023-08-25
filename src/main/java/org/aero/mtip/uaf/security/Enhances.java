package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Enhances extends Abstraction {

	public Enhances(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.ENHANCES;
		this.xmlConstant = XmlTagConstants.ENHANCES;
		this.initialStereotypes = Arrays.asList(UAFProfile.ENHANCES_STEREOTYPE);
	}
}
