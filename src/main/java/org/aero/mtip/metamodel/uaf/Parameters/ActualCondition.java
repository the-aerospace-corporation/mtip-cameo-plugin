package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualCondition extends InstanceSpecification {

	public ActualCondition(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_CONDITION;
		this.xmlConstant = XmlTagConstants.ACTUAL_CONDITION;
	}
}
