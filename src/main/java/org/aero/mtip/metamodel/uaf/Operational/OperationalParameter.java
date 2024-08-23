package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityParameterNode;

public class OperationalParameter extends ActivityParameterNode {

	public OperationalParameter(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_PARAMETER;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PARAMETER;
	}
}
