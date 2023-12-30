/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class SysmlPackage extends CommonElement{
	public SysmlPackage(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.PACKAGE;
		this.sysmlConstant = SysmlConstants.PACKAGE;
		this.element = f.createPackageInstance();
	}
}
