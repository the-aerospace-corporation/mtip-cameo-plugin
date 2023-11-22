package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualPropertySet extends InstanceSpecification {

	public ActualPropertySet(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROPERTY_SET;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROPERTY_SET;
	}
}
