package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Environment extends CommonElement{
	public Environment (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = UAFConstants.ENVIRONMENT;
		this.xmlConstant = XmlTagConstants.ENVIRONMENT;
		this.element = f.createDataTypeInstance();

	}
}
