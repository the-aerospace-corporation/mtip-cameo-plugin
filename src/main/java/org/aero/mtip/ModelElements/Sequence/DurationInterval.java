package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DurationInterval extends CommonElement {

	public DurationInterval(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.DURATION_INTERVAL;
		this.xmlConstant = XmlTagConstants.DURATION_INTERVAL;
		this.element = f.createDurationInstance();
	}
}
