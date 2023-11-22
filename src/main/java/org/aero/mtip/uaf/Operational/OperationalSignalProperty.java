package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalSignalProperty extends Property {

	public OperationalSignalProperty(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_SIGNAL_PROPERTY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_SIGNAL_PROPERTY;
	}
}
