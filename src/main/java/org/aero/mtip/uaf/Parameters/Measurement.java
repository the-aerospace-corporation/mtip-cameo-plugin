package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Measurement extends Property{

	public Measurement(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.MEASUREMENT;
		this.xmlConstant = XmlTagConstants.MEASUREMENT;
	
	}
}
