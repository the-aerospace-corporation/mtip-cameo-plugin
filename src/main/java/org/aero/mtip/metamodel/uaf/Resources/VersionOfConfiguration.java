package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class VersionOfConfiguration extends Property {

	public VersionOfConfiguration(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.VERSION_OF_CONFIGURATION;
		this.xmlConstant = XmlTagConstants.VERSION_OF_CONFIGURATION;
	}
}
