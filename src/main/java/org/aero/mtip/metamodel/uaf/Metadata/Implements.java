package org.aero.mtip.metamodel.uaf.Metadata;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class Implements extends Abstraction {
	
	public Implements(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.IMPLEMENTS;
		this.metamodelConstant = UAFConstants.IMPLEMENTS;
	}
}