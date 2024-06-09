package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Duration extends CommonElement {

	public Duration(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.DURATION;
		this.xmlConstant = XmlTagConstants.DURATION;
		this.element = f.createDurationInstance();
	}
}
