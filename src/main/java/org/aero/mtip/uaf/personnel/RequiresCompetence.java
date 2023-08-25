package org.aero.mtip.uaf.personnel;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class RequiresCompetence extends Abstraction {

	public RequiresCompetence(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.REQUIRES_COMPETENCE;
		this.xmlConstant = XmlTagConstants.REQUIRES_COMPETENCE;
		this.initialStereotypes = Arrays.asList(UAFProfile.REQUIRES_COMPETENCE_STEREOTYPE);
	}
}
