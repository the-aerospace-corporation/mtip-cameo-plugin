package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Consumes extends Abstraction {

	public Consumes(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.CONSUMES;
		this.xmlConstant = XmlTagConstants.CONSUMES;
	}
}
