package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Condition extends CommonElement{
	public Condition (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.CONDITION;
		this.xmlConstant = XmlTagConstants.CONDITION;
		this.element = f.createDataTypeInstance();

	}
}
