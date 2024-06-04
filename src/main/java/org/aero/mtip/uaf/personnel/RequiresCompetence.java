package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class RequiresCompetence extends Abstraction {

	public RequiresCompetence(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.REQUIRES_COMPETENCE;
		this.xmlConstant = XmlTagConstants.REQUIRES_COMPETENCE;
	}
}
