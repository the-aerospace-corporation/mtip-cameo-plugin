/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.UseCase;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class Extend extends CommonDirectedRelationship {

	public Extend(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.EXTEND;
		this.xmlConstant = XmlTagConstants.EXTEND;
		this.sysmlElement = f.createExtendInstance();
	}
}
