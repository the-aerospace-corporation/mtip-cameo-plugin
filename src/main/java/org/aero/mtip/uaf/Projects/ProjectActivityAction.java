package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectActivityAction extends CallBehaviorAction{
	
	public ProjectActivityAction(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_ACTIVITY_ACTION;
		this.xmlConstant = XmlTagConstants.PROJECT_ACTIVITY_ACTION;
	}
}
