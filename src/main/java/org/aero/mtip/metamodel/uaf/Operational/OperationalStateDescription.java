package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachine;

public class OperationalStateDescription extends StateMachine {

	public OperationalStateDescription(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STATE_DESCRIPTION;
	}
}
