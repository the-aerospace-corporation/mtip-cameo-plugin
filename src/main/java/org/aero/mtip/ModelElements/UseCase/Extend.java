/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.UseCase;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Extend extends CommonDirectedRelationship {

	public Extend(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.EXTEND;
		this.xmlConstant = XmlTagConstants.EXTEND;
		this.element = f.createExtendInstance();
	}
}
