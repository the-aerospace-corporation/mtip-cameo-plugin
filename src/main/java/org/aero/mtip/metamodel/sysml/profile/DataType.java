package org.aero.mtip.metamodel.sysml.profile;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class DataType extends CommonElement {
	public DataType(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTS_FACTORY;
		metamodelConstant = SysmlConstants.DATA_TYPE;
		xmlConstant = XmlTagConstants.DATA_TYPE;
		element = f.createDataTypeInstance();
	}
}
