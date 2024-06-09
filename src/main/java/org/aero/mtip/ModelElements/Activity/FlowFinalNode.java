/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class FlowFinalNode extends ActivityNode {

	public FlowFinalNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.FLOW_FINAL_NODE;
		this.xmlConstant = XmlTagConstants.FLOW_FINAL_NODE;
		this.element = f.createFlowFinalNodeInstance();
	}
}
