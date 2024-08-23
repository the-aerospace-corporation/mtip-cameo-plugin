package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Slot;

public class ActualProjectRole extends Slot{
	
	public ActualProjectRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROJECT_ROLE;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT_ROLE;
	}
}
