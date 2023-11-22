package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalRole extends Property {

	public OperationalRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_ROLE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ROLE;
	}
}
