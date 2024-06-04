package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProblemDomain extends Property {

	public ProblemDomain(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROBLEM_DOMAIN;
		this.xmlConstant = XmlTagConstants.PROBLEM_DOMAIN;
	}
}
