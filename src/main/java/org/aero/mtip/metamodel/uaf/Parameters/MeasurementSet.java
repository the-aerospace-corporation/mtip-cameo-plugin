                 package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class MeasurementSet extends CommonElement{
	public MeasurementSet (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.MEASUREMENT_SET;
		this.xmlConstant = XmlTagConstants.MEASUREMENT_SET;
		this.element = f.createDataTypeInstance();
	}
	
}
