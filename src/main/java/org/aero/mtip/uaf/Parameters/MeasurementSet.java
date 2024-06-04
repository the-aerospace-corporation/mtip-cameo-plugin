                 package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class MeasurementSet extends CommonElement{
	public MeasurementSet (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = UAFConstants.MEASUREMENT_SET;
		this.xmlConstant = XmlTagConstants.MEASUREMENT_SET;
		this.element = f.createDataTypeInstance();
	}
	
}
