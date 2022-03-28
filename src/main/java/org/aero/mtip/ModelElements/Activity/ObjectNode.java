/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ObjectNode extends ActivityNode {

	public ObjectNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CENTRALBUFFERNODE;
		this.xmlConstant = XmlTagConstants.CENTRALBUFFERNODE;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}
}
