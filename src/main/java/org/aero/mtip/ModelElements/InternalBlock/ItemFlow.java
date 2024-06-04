/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.XmlTagConstants;

public class ItemFlow extends CommonDirectedRelationship {

	public ItemFlow(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTSFACTORY;
		metamodelConstant = SysmlConstants.ITEM_FLOW;
		xmlConstant = XmlTagConstants.ITEMFLOW;
		element = f.createInformationFlowInstance();
		creationStereotype = SysML.getItemFlowStereotype();
	}
}
