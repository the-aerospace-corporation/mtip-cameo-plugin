package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class ProjectSequence extends Dependency {

	public ProjectSequence(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_SEQUENCE;
		this.xmlConstant = XmlTagConstants.PROJECT_SEQUENCE;
	}
}
