package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.Slot;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProjectStatus extends Slot{
	
	public ProjectStatus(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_STATUS;
		this.xmlConstant = XmlTagConstants.PROJECT_STATUS;
	}
}
