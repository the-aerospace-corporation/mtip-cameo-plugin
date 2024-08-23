package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualEnduringTask extends InstanceSpecification {

	public ActualEnduringTask(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.ACTUAL_ENDURING_TASK;
		this.xmlConstant = XmlTagConstants.ACTUAL_ENDURING_TASK;
	}
}
