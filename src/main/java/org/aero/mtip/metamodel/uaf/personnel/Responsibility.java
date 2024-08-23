package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class Responsibility extends CommonElement implements UAFElement{
	public Responsibility(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESPONSIBILITY;
		this.xmlConstant = XmlTagConstants.RESPONSIBILITY;
	}

}
