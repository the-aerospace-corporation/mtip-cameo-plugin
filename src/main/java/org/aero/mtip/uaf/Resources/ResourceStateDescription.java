package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.StateMachine.StateMachine;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceStateDescription extends StateMachine {
	
	public ResourceStateDescription (String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.RESOURCE_STATE_DESCRIPTION;
	}
}
