package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class OperationalSignalProperty extends Property {

	public OperationalSignalProperty(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_SIGNAL_PROPERTY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_SIGNAL_PROPERTY;
	}
}
