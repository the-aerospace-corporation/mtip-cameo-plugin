package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class EnvironmentProperty extends Property {

	public EnvironmentProperty(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ENVIRONMENT_PROPERTY;
		this.xmlConstant = XmlTagConstants.ENVIRONMENT_PROPERTY;
	}
}
