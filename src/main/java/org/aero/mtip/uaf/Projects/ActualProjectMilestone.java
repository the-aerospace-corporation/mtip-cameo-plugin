package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualProjectMilestone extends InstanceSpecification {

	public ActualProjectMilestone(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROJECT_MILESTONE;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT_MILESTONE;
	}
}
