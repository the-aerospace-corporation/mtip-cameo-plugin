package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.InternalBlock.Connector;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceConnector extends Connector {

	public ResourceConnector(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_CONNECTOR;
		this.xmlConstant = XmlTagConstants.RESOURCE_CONNECTOR;
	}
}
