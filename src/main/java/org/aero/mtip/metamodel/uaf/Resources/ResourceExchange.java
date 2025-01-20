package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.InformationFlow;

public class ResourceExchange extends InformationFlow {

	public ResourceExchange(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_EXCHANGE;
		this.xmlConstant = XmlTagConstants.RESOURCE_EXCHANGE;
	}
}