/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ConnectionPointReference extends CommonElement {

	public ConnectionPointReference(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CONNECTIONPOINTREFERENCE;
		this.xmlConstant = XmlTagConstants.CONNECTIONPOINTREFERENCE;
		this.sysmlElement = f.createConnectionPointReferenceInstance();
	}
}
