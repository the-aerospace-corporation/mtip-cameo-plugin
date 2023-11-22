package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.StateMachine.StateMachine;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceStateDescription extends StateMachine {

	public ServiceStateDescription(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.SERVICE_STATE_DESCRIPTION;
	}
}
