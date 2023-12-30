/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.UseCase;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class Actor extends CommonElement {

	public Actor(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTOR;
		this.xmlConstant = XmlTagConstants.ACTOR;
		this.element = f.createActorInstance();
	}
}
