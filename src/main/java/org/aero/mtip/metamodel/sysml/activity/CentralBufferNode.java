/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.activity;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CentralBufferNode extends ActivityNode {

	public CentralBufferNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.CENTRAL_BUFFER_NODE;
		this.xmlConstant = XmlTagConstants.CENTRAL_BUFFER_NODE;
		this.element = f.createCentralBufferNodeInstance();
	}
}
