package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.Slot;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualMeasurement extends Slot{
	public ActualMeasurement(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_MEASUREMENT;
		this.xmlConstant = XmlTagConstants.ACTUAL_MEASUREMENT;
	}
}
