package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class MilestoneDependency extends Dependency {

	public MilestoneDependency(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.MILESTONE_DEPENDENCY;
		this.xmlConstant = XmlTagConstants.MILESTONE_DEPENDENCY;
	}
}
