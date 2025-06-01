package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class RequiresCompetence extends Abstraction {

	public RequiresCompetence(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.REQUIRES_COMPETENCE;
		this.xmlConstant = XmlTagConstants.REQUIRES_COMPETENCE;
	}
}
