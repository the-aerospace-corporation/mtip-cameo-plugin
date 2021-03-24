package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class SysmlPackage extends CommonElement{
	public SysmlPackage(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.PACKAGE;
		this.sysmlConstant = SysmlConstants.PACKAGE;
		this.sysmlElement = f.createPackageInstance();
	}
}
