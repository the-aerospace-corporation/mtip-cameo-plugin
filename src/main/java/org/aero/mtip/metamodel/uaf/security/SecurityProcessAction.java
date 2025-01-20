package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.CallBehaviorAction;

public class SecurityProcessAction extends CallBehaviorAction {
	
	public SecurityProcessAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SECURITY_PROCESS_ACTION;
		this.xmlConstant = XmlTagConstants.SECURITY_PROCESS_ACTION;
	}
}
