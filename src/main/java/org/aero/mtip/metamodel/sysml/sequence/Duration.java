package org.aero.mtip.metamodel.sysml.sequence;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class Duration extends CommonElement {

	public Duration(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.DURATION;
		this.xmlConstant = XmlTagConstants.DURATION;
		this.element = f.createDurationInstance();
	}
}
