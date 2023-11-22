package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualMeasurementSet extends InstanceSpecification {

	public ActualMeasurementSet(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_MEASUREMENT_SET;
		this.xmlConstant = XmlTagConstants.ACTUAL_MEASUREMENT_SET;
	}
}
