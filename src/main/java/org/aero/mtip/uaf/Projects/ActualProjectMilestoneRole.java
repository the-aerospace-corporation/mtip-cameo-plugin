package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.Slot;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualProjectMilestoneRole extends Slot{
	
	public ActualProjectMilestoneRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROJECT_MILESTONE_ROLE;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT_MILESTONE_ROLE;
	}
}
