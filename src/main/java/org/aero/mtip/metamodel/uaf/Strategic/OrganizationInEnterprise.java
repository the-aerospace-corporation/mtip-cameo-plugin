package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class OrganizationInEnterprise extends Abstraction {
	public OrganizationInEnterprise(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.ORGANIZATION_IN_ENTERPRISE;
		this.metamodelConstant = UAFConstants.ORGANIZATION_IN_ENTERPRISE;
	}
}
