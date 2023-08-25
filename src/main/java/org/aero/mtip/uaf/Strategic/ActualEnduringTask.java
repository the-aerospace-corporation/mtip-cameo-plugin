package org.aero.mtip.uaf.Strategic;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ActualEnduringTask extends InstanceSpecification {

	public ActualEnduringTask(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_ENDURING_TASK;
		this.xmlConstant = XmlTagConstants.ACTUAL_ENDURING_TASK;
		this.initialStereotypes = Arrays.asList(UAFProfile.ACTUAL_ENDURING_TASK_STEREOTYPE);
	}
}
