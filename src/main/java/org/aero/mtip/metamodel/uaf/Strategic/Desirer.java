package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class Desirer extends CommonElement implements UAFElement {
	
	public Desirer(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.DESIRER;
		this.xmlConstant = XmlTagConstants.DESIRER;
	}
}
