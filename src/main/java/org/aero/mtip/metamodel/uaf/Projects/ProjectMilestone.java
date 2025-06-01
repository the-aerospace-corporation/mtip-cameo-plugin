package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class ProjectMilestone extends CommonElement implements UAFElement {
	
	public ProjectMilestone(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.PROJECT_MILESTONE;
		this.xmlConstant = XmlTagConstants.PROJECT_MILESTONE;
	}
}
