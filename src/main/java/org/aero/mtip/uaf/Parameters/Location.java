package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Location extends CommonElement{
	public Location (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.LOCATION;
		this.xmlConstant = XmlTagConstants.LOCATION;
		this.element = f.createDataTypeInstance();

	}
}
