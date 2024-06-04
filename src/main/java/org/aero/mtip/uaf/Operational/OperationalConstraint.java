package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Profile.Constraint;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OperationalConstraint extends Constraint {

	public OperationalConstraint(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.OPERATIONAL_CONSTRAINT;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONSTRAINT;
	}
}
