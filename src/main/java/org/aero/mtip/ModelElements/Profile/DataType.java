package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class DataType extends CommonElement {

	public DataType(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTSFACTORY;
		sysmlConstant = SysmlConstants.DATA_TYPE;
		xmlConstant = XmlTagConstants.DATA_TYPE;
		element = f.createDataTypeInstance();
	}

}
