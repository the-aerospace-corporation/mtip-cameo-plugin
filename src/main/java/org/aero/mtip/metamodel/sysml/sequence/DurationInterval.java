package org.aero.mtip.metamodel.sysml.sequence;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class DurationInterval extends CommonElement {

	public DurationInterval(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.DURATION_INTERVAL;
		this.xmlConstant = XmlTagConstants.DURATION_INTERVAL;
		this.element = f.createDurationInstance();
	}
}
