package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Slot;

public class ProjectStatus extends Slot {
	
	public ProjectStatus(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROJECT_STATUS;
		this.xmlConstant = XmlTagConstants.PROJECT_STATUS;
	}
}