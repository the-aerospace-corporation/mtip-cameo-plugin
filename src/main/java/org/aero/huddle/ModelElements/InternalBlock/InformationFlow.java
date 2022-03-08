/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InformationFlow extends CommonRelationship {

	public InformationFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INFORMATIONFLOW;
		this.xmlConstant = XmlTagConstants.INFORMATIONFLOW;
		this.sysmlElement = f.createInformationFlowInstance();
	}
}
