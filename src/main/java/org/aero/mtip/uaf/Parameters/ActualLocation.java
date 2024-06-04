package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualLocation extends InstanceSpecification {

	public ActualLocation(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_LOCATION;
		this.xmlConstant = XmlTagConstants.ACTUAL_LOCATION;
	}
}
