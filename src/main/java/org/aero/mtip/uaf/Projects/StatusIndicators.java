package org.aero.mtip.uaf.Projects;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StatusIndicators extends Enumeration{
	public StatusIndicators(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.STATUS_INDICATORS;
		this.xmlConstant = XmlTagConstants.STATUS_INDICATORS;
	}
}