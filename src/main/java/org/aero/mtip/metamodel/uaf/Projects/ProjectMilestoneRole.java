package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ProjectMilestoneRole extends Property{
	public ProjectMilestoneRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_MILESTONE_ROLE;
		this.xmlConstant = XmlTagConstants.PROJECT_MILESTONE_ROLE;
	}
}
