package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualOrganization extends InstanceSpecification {

	public ActualOrganization(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_ORGANIZATION;
		this.xmlConstant = XmlTagConstants.ACTUAL_ORGANIZATION;
	}
}
