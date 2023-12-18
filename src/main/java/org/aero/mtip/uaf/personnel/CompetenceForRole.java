package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CompetenceForRole extends Abstraction {

	public CompetenceForRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.COMPETENCE_FOR_ROLE;
		this.xmlConstant = XmlTagConstants.COMPETENCE_FOR_ROLE;
	}

}
