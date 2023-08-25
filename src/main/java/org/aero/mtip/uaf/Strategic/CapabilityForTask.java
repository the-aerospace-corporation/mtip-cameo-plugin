package org.aero.mtip.uaf.Strategic;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class CapabilityForTask extends Abstraction {
	
	public CapabilityForTask(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CAPABILITY_FOR_TASK;
		this.sysmlConstant = UAFConstants.CAPABILITY_FOR_TASK;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.CAPABILITY_FOR_TASK_STEREOTYPE);
	}
}
