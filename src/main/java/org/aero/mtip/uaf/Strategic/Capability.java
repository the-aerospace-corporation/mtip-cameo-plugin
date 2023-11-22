package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class Capability extends CommonElement implements UAFElement {
	
	public Capability(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.CAPABILITY;
		this.xmlConstant = XmlTagConstants.CAPABILITY;
	}
}
