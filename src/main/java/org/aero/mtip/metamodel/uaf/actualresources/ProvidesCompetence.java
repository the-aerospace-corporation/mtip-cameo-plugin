package org.aero.mtip.metamodel.uaf.actualresources;

import java.util.Arrays;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;
import org.aero.mtip.profiles.UAF;

public class ProvidesCompetence extends Dependency {

	public ProvidesCompetence(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROVIDES_COMPETENCE;
		this.xmlConstant = XmlTagConstants.PROVIDES_COMPETENCE;
	}
}
