package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ProvidesCompetence extends CommonDirectedRelationship {

	public ProvidesCompetence(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.PROVIDES_COMPETENCE;
		this.xmlConstant = XmlTagConstants.PROVIDES_COMPETENCE;
		this.sysmlElement = f.createDependencyInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.PROVIDES_COMPETENCE_STEREOTYPE);
	}
}
