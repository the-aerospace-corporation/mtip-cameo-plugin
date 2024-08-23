package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class AffectsInContext extends Dependency {

	public AffectsInContext(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.AFFECTS_IN_CONTEXT;
		this.xmlConstant = XmlTagConstants.AFFECTS_IN_CONTEXT;
	}
}
