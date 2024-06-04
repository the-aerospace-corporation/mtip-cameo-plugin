package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectSequence extends Dependency {

	public ProjectSequence(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_SEQUENCE;
		this.xmlConstant = XmlTagConstants.PROJECT_SEQUENCE;
	}
}
