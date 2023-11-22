package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualEnvironment extends InstanceSpecification {

	public ActualEnvironment(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_ENVIRONMENT;
		this.xmlConstant = XmlTagConstants.ACTUAL_ENVIRONMENT;
	}
}
