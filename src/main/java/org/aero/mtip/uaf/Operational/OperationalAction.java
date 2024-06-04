package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalAction extends CallBehaviorAction {
	public OperationalAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_ACTION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ACTION;
	}
}
