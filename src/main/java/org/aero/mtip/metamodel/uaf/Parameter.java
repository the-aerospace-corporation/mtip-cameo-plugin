package org.aero.mtip.metamodel.uaf;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public abstract class Parameter extends CommonElement {

	public Parameter(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.PARAMETER;
		this.xmlConstant = XmlTagConstants.PARAMETER;
		this.element = f.createParameterInstance();
	}
}
