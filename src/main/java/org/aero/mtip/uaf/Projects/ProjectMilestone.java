package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class ProjectMilestone extends CommonElement implements UAFElement {
	
	public ProjectMilestone(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.PROJECT_MILESTONE;
		this.xmlConstant = XmlTagConstants.PROJECT_MILESTONE;
	}
}
