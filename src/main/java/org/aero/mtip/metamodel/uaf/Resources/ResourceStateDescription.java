package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachine;

public class ResourceStateDescription extends StateMachine {
	
	public ResourceStateDescription (String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.RESOURCE_STATE_DESCRIPTION;
	}
}
