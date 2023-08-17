package org.aero.mtip.uaf.personnellower;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class CompetenceToConduct extends Abstraction {

	public CompetenceToConduct(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.COMPETENCE_TO_CONDUCT;
		this.xmlConstant = XmlTagConstants.COMPETENCE_TO_CONDUCT;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.COMPETENCE_TO_CONDUCT_STEREOTYPE);
	}
}
