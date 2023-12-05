package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceFunction extends Activity {

	public ServiceFunction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_FUNCTION;
		this.xmlConstant = XmlTagConstants.SERVICE_FUNCTION;
	}
	
}
