package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CompetenceToConduct extends Abstraction {

	public CompetenceToConduct(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.COMPETENCE_TO_CONDUCT;
		this.xmlConstant = XmlTagConstants.COMPETENCE_TO_CONDUCT;
	}
}
