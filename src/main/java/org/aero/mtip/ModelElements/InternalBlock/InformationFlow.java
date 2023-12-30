/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class InformationFlow extends CommonRelationship {

	public InformationFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INFORMATION_FLOW;
		this.xmlConstant = XmlTagConstants.INFORMATIONFLOW;
		this.element = f.createInformationFlowInstance();
	}
}
