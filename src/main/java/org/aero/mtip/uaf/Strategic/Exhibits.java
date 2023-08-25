package org.aero.mtip.uaf.Strategic;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Exhibits extends Abstraction {
	
	public Exhibits(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.EXHIBITS;
		this.sysmlConstant = UAFConstants.EXHIBITS;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.EXHIBITS_STEREOTYPE);
	}
}
