package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class Exhibits extends Abstraction {
	
	public Exhibits(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.EXHIBITS;
		this.metamodelConstant = UAFConstants.EXHIBITS;
	}
}
