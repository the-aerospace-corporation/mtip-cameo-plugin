package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class AffectsInContext extends Dependency {

	public AffectsInContext(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.AFFECTS_IN_CONTEXT;
		this.xmlConstant = XmlTagConstants.AFFECTS_IN_CONTEXT;
		this.initialStereotypes = Arrays.asList(UAFProfile.AFFECTS_IN_CONTEXT_STEREOTYPE);
	}
}
