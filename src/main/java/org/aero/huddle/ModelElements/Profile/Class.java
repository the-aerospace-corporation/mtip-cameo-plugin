package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Class extends CommonElement {
	public Class(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CLASS;
		this.xmlConstant = XmlTagConstants.CLASS;
		this.sysmlElement = f.createClassInstance();
	}
}