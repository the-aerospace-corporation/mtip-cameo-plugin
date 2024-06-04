package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Requirements.Requirement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class EnterpriseGoal extends Requirement implements UAFElement {
	
	public EnterpriseGoal(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.ENTERPRISE_GOAL;
		this.xmlConstant = XmlTagConstants.ENTERPRISE_GOAL;
	}
}
