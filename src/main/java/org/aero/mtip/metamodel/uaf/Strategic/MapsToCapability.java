package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class MapsToCapability extends Abstraction {
	
	public MapsToCapability(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.MAPS_TO_CAPABILITY;
		this.metamodelConstant = UAFConstants.MAPS_TO_CAPABILITY;
	}
}
