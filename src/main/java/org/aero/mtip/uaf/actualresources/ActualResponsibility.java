package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualResponsibility extends InstanceSpecification {

	public ActualResponsibility(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_RESPONSIBILITY;
		this.xmlConstant = XmlTagConstants.ACTUAL_RESPONSIBILITY;
	}
}
