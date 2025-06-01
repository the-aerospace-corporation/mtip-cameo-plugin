package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.DataType;

public class Condition extends DataType {
	public Condition (String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.CONDITION;
		this.xmlConstant = XmlTagConstants.CONDITION;
	}
}
