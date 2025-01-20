package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Port;

public class ResourcePort extends Port {

	public ResourcePort(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_PORT;
		this.xmlConstant = XmlTagConstants.RESOURCE_PORT;
	}
}
