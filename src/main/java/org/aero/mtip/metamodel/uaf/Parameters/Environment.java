package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class Environment extends CommonElement{
	public Environment (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.ENVIRONMENT;
		this.xmlConstant = XmlTagConstants.ENVIRONMENT;
		this.element = f.createDataTypeInstance();

	}
}
