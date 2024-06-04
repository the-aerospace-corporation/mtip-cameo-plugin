package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceInterface extends CommonElement {

	public ServiceInterface(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.SERVICE_INTERFACE;
		this.xmlConstant = XmlTagConstants.SERVICE_INTERFACE;
	}
}
