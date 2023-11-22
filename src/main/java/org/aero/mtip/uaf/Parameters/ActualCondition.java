package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualCondition extends InstanceSpecification {

	public ActualCondition(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_CONDITION;
		this.xmlConstant = XmlTagConstants.ACTUAL_CONDITION;
	}
}
