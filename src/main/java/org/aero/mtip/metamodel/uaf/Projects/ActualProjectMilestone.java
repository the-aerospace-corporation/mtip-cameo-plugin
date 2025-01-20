package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ActualProjectMilestone extends InstanceSpecification {

	public ActualProjectMilestone(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROJECT_MILESTONE;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT_MILESTONE;
	}
}
