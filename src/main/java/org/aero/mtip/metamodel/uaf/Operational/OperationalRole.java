package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class OperationalRole extends Property {

	public OperationalRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_ROLE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ROLE;
	}
}
