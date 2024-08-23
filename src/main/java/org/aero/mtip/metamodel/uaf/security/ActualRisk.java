package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualRisk extends InstanceSpecification {

	public ActualRisk(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_RISK;
		this.xmlConstant = XmlTagConstants.ACTUAL_RISK;
	}

}
