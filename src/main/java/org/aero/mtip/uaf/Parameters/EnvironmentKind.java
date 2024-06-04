package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class EnvironmentKind extends Enumeration{
	public EnvironmentKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.ENVIRONMENT_KIND;
		this.xmlConstant = XmlTagConstants.ENVIRONMENT_KIND;
	}
}
