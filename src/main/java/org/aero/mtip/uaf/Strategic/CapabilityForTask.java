package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CapabilityForTask extends Abstraction {
	
	public CapabilityForTask(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.CAPABILITY_FOR_TASK;
		this.metamodelConstant = UAFConstants.CAPABILITY_FOR_TASK;
	}
}
