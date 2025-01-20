package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class EnvironmentProperty extends Property {

	public EnvironmentProperty(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ENVIRONMENT_PROPERTY;
		this.xmlConstant = XmlTagConstants.ENVIRONMENT_PROPERTY;
	}
}
