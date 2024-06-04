package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Exhibits extends Abstraction {
	
	public Exhibits(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.EXHIBITS;
		this.metamodelConstant = UAFConstants.EXHIBITS;
	}
}
