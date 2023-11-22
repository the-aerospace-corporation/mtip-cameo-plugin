package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.StateMachine.StateMachine;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalStateDescription extends StateMachine {

	public OperationalStateDescription(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STATE_DESCRIPTION;
	}
}
