package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ResourceSignalProperty extends Property {

	public ResourceSignalProperty(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_SIGNAL_PROPERTY;
		this.xmlConstant = XmlTagConstants.RESOURCE_SIGNAL_PROPERTY;
	}
}
