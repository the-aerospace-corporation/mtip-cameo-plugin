package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class OperationalArchitecture extends CommonElement implements UAFElement {
	
	public OperationalArchitecture(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.OPERATIONAL_ARCHITECTURE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ARCHITECTURE;
	}
}
