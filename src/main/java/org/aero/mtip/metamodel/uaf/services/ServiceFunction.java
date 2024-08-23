package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.Activity;

public class ServiceFunction extends Activity {

	public ServiceFunction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_FUNCTION;
		this.xmlConstant = XmlTagConstants.SERVICE_FUNCTION;
	}
	
}
