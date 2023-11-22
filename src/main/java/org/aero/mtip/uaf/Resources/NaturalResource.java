package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class NaturalResource extends CommonElement implements UAFElement {
	
	public NaturalResource(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.NATURAL_RESOURCE;
		this.xmlConstant = XmlTagConstants.NATURAL_RESOURCE;
	}
}
