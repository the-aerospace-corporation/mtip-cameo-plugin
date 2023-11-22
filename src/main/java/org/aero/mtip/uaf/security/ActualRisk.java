package org.aero.mtip.uaf.security;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualRisk extends InstanceSpecification {

	public ActualRisk(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_RISK;
		this.xmlConstant = XmlTagConstants.ACTUAL_RISK;
	}

}
