package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.DataType;

public class Location extends DataType {
	public Location (String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.LOCATION;
		this.xmlConstant = XmlTagConstants.LOCATION;
	}
}
