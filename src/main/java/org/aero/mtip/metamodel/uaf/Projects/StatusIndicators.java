package org.aero.mtip.metamodel.uaf.Projects;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Enumeration;
// TODO: Determine if StatusIndicators is necessary as first class element as an enumeration.
public class StatusIndicators extends Enumeration {
	public StatusIndicators(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.STATUS_INDICATORS;
		this.xmlConstant = XmlTagConstants.STATUS_INDICATORS;
	}
}