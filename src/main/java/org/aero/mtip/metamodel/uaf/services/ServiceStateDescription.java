package org.aero.mtip.metamodel.uaf.services;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachine;

public class ServiceStateDescription extends StateMachine {

	public ServiceStateDescription(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SERVICE_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.SERVICE_STATE_DESCRIPTION;
	}
}
