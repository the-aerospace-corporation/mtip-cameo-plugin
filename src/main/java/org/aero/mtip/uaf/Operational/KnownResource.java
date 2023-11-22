package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class KnownResource extends CommonElement implements UAFElement {
	
	public KnownResource(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.KNOWN_RESOURCE;
		this.xmlConstant = XmlTagConstants.KNOWN_RESOURCE;
	}
}
