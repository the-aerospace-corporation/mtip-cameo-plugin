package org.aero.mtip.uaf.standards;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class ProtocolStack extends CommonElement implements UAFElement {
	
	public ProtocolStack(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.PROTOCOL_STACK;
		this.xmlConstant = XmlTagConstants.PROTOCOL_STACK;
	}

}
