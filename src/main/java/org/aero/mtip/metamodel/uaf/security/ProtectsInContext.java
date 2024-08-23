package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class ProtectsInContext extends Dependency {
	
	public ProtectsInContext(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROTECTS_IN_CONTEXT;
		this.xmlConstant = XmlTagConstants.PROTECTS_IN_CONTEXT;
	}
}
