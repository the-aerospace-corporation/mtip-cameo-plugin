package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualResponsibility extends InstanceSpecification {

	public ActualResponsibility(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_RESPONSIBILITY;
		this.xmlConstant = XmlTagConstants.ACTUAL_RESPONSIBILITY;
	}
}
