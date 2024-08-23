package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class Measurement extends Property{

	public Measurement(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.MEASUREMENT;
		this.xmlConstant = XmlTagConstants.MEASUREMENT;
	
	}
}
