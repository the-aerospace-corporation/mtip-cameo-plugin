package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Enumeration extends CommonElement {

	public Enumeration(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ENUMERATION;
		this.xmlConstant = XmlTagConstants.ENUMERATION;
		this.sysmlElement = f.createEnumerationInstance();
	}
}
