package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Association;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalAssociation extends Association {

	public OperationalAssociation(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_ASSOCIATION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ASSOCIATION;
	}
}
