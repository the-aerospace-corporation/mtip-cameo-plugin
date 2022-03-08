/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Abstraction extends CommonDirectedRelationship {

	public Abstraction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.ABSTRACTION;
		this.sysmlConstant = SysmlConstants.ABSTRACTION;
		this.sysmlElement = f.createAbstractionInstance();
	}
}
