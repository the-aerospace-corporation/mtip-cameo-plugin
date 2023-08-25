package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Protects extends Dependency {

	public Protects(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.PROTECTS;
		this.xmlConstant = XmlTagConstants.PROTECTS;
		this.initialStereotypes = Arrays.asList(UAFProfile.PROTECTS_STEREOTYPE);
	}
}
