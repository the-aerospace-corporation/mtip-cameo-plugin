package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class CapabilityForTask extends Abstraction {
	
	public CapabilityForTask(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.CAPABILITY_FOR_TASK;
		this.metamodelConstant = UAFConstants.CAPABILITY_FOR_TASK;
	}
}
