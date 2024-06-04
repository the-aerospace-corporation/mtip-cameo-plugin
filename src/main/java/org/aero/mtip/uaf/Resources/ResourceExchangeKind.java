package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceExchangeKind extends Enumeration{
	public ResourceExchangeKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.RESOURCE_EXCHANGE_KIND;
		this.xmlConstant = XmlTagConstants.RESOURCE_EXCHANGE_KIND;
	}
}