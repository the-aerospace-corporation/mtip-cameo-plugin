package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualMilestoneKind extends Enumeration{
	public ActualMilestoneKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.ACTUAL_MILESTONE_KIND;
		this.xmlConstant = XmlTagConstants.ACTUAL_MILESTONE_KIND;
	}
}