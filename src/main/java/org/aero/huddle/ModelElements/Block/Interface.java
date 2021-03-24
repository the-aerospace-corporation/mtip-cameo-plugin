package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Interface extends CommonElement {

	public Interface(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INTERFACE;
		this.xmlConstant = XmlTagConstants.INTERFACE;
		this.sysmlElement = f.createInterfaceInstance();
	}
}
