package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class Duration extends CommonElement {

	public Duration(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DURATION;
		this.xmlConstant = XmlTagConstants.DURATION;
		this.element = f.createDurationInstance();
	}
}
