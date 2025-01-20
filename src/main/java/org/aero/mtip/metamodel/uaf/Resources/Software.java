package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class Software extends CommonElement implements UAFElement {
	
	public Software(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.SOFTWARE;
		this.xmlConstant = XmlTagConstants.SOFTWARE;
	}
}
