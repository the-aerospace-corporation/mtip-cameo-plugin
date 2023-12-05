package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ServiceSpecification extends CommonElement {
	public ServiceSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.SERVICE_SPECIFICATION;
		this.xmlConstant = XmlTagConstants.SERVICE_SPECIFICATION;
	}
}
