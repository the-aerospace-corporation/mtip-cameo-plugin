package org.aero.mtip.metamodel.uaf.security;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class OperationalMitigation extends CommonElement {
	
	public OperationalMitigation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.OPERATIONAL_MITIGATION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_MITIGATION;
	}
}
