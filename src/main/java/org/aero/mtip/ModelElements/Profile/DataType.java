package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DataType extends CommonElement {
	public DataType(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTS_FACTORY;
		metamodelConstant = SysmlConstants.DATA_TYPE;
		xmlConstant = XmlTagConstants.DATA_TYPE;
		element = f.createDataTypeInstance();
	}
}
