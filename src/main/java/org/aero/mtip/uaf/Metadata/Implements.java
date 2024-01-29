package org.aero.mtip.uaf.Metadata;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Implements extends Abstraction {
	
	public Implements(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.IMPLEMENTS;
		this.metamodelConstant = UAFConstants.IMPLEMENTS;
	}
}