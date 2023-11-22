package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualProject extends InstanceSpecification {

	public ActualProject(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACTUAL_PROJECT;
		this.xmlConstant = XmlTagConstants.ACTUAL_PROJECT;
	}
}
