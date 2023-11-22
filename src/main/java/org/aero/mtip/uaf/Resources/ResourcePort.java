package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.Port;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcePort extends Port {

	public ResourcePort(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_PORT;
		this.xmlConstant = XmlTagConstants.RESOURCE_PORT;
	}
}
