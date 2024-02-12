package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class GeoPoliticalExtentType extends CommonElement{
	public GeoPoliticalExtentType (String name, String EAID) 
	{
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = UAFConstants.GEO_POLITICAL_EXTENT_TYPE;
		this.xmlConstant = XmlTagConstants.GEO_POLITICAL_EXTENT_TYPE;
		this.element = f.createDataTypeInstance();

	}
}
