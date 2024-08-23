package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ProblemDomain extends Property {

	public ProblemDomain(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROBLEM_DOMAIN;
		this.xmlConstant = XmlTagConstants.PROBLEM_DOMAIN;
	}
}
