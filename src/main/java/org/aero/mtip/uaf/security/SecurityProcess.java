package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class SecurityProcess extends Activity {

	public SecurityProcess(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.SECURITY_PROCESS;
		this.xmlConstant = XmlTagConstants.SECURITY_PROCESS;
		this.sysmlElement = f.createActivityInstance();
		this.creationStereotype = UAFProfile.SECURITY_PROCESS_STEREOTYPE;
	}

}
