package org.aero.mtip.metamodel.uaf.standards;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class Standard extends CommonElement implements UAFElement{
	
	public Standard (String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.STANDARD;
		this.xmlConstant = XmlTagConstants.STANDARD;
	}

}
