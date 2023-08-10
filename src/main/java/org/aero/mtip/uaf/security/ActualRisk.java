package org.aero.mtip.uaf.security;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ActualRisk extends InstanceSpecification {

	public ActualRisk(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_RISK;
		this.xmlConstant = XmlTagConstants.ACTUAL_RISK;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.ACTUAL_RISK_STEREOTYPE);
	}

}
