package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ResourceRole extends Property {

	public ResourceRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_ROLE;
		this.xmlConstant = XmlTagConstants.RESOURCE_ROLE;
	}
}
