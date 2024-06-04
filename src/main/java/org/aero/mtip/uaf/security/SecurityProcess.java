package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SecurityProcess extends Activity {

	public SecurityProcess(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SECURITY_PROCESS;
		this.xmlConstant = XmlTagConstants.SECURITY_PROCESS;
	}

}
