package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualPropertySet extends InstanceSpecification {

	public ActualPropertySet(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROPERTY_SET;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROPERTY_SET;
	}
}
