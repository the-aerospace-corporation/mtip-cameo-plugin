package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.InternalBlock.InformationFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceExchange extends InformationFlow {

	public ResourceExchange(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_EXCHANGE;
		this.xmlConstant = XmlTagConstants.RESOURCE_EXCHANGE;
	}
}