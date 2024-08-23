package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.Activity;

public class SecurityProcess extends Activity {

	public SecurityProcess(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SECURITY_PROCESS;
		this.xmlConstant = XmlTagConstants.SECURITY_PROCESS;
	}

}
