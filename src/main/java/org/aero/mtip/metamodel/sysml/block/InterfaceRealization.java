/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.block;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonDirectedRelationship;

public class InterfaceRealization extends CommonDirectedRelationship {

	public InterfaceRealization(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTS_FACTORY;
		metamodelConstant = SysmlConstants.INTERFACE_REALIZATION;
		xmlConstant = XmlTagConstants.INTERFACE_REALIZATION;
		element = f.createInterfaceRealizationInstance();
	}
}
