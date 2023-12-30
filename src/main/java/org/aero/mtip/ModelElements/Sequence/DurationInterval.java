package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class DurationInterval extends CommonElement {

	public DurationInterval(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DURATION_INTERVAL;
		this.xmlConstant = XmlTagConstants.DURATION_INTERVAL;
		this.element = f.createDurationInstance();
	}
}
