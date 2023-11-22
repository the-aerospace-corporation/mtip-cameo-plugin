package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceRole extends Property {

	public ResourceRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_ROLE;
		this.xmlConstant = XmlTagConstants.RESOURCE_ROLE;
	}
}
