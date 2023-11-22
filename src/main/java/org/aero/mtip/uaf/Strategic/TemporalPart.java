package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class TemporalPart extends Property {

	public TemporalPart(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.TEMPORAL_PART;
		this.xmlConstant = XmlTagConstants.TEMPORAL_PART;
	}
}
