/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class InterfaceRealization extends CommonDirectedRelationship {

	public InterfaceRealization(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTSFACTORY;
		sysmlConstant = SysmlConstants.INTERFACE_REALIZATION;
		xmlConstant = XmlTagConstants.INTERFACE_REALIZATION;
		element = f.createInterfaceRealizationInstance();
	}
}
