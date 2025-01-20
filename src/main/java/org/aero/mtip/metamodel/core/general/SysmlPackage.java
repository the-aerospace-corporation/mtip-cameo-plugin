/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.core.general;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class SysmlPackage extends CommonElement{
	public SysmlPackage(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.xmlConstant = XmlTagConstants.PACKAGE;
		this.metamodelConstant = SysmlConstants.PACKAGE;
		this.element = f.createPackageInstance();
	}
}
