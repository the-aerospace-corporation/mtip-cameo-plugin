package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class TemporalPart extends Property {

	public TemporalPart(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.TEMPORAL_PART;
		this.xmlConstant = XmlTagConstants.TEMPORAL_PART;
	}
}
