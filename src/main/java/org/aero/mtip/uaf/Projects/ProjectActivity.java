package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectActivity extends Activity{
	public ProjectActivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_ACTIVITY;
		this.xmlConstant = XmlTagConstants.PROJECT_ACTIVITY;
	}
}
