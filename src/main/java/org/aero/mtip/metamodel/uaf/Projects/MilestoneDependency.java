package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class MilestoneDependency extends Dependency {

	public MilestoneDependency(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.MILESTONE_DEPENDENCY;
		this.xmlConstant = XmlTagConstants.MILESTONE_DEPENDENCY;
	}
}
