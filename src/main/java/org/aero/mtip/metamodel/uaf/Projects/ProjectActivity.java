package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.Activity;

public class ProjectActivity extends Activity {
	public ProjectActivity(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_ACTIVITY;
		this.xmlConstant = XmlTagConstants.PROJECT_ACTIVITY;
	}
}