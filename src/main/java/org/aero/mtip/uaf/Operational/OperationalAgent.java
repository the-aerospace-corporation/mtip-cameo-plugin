package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class OperationalAgent extends CommonElement implements UAFElement {
	
	public OperationalAgent(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.OPERATIONAL_AGENT;
		this.xmlConstant = XmlTagConstants.CLASS_WITH_STEREOTYPE;
	}
}
