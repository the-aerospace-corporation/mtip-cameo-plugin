package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualEnterprisePhase extends InstanceSpecification {

	public ActualEnterprisePhase(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_ENTERPRISE_PHASE;
		this.xmlConstant = XmlTagConstants.ACTUAL_ENTERPRISE_PHASE;
	}	
}
