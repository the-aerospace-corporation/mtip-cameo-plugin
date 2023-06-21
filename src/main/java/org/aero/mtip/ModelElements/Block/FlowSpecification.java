/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.Arrays;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class FlowSpecification extends CommonElement {
	public FlowSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FLOW_SPECFICATION;
		this.xmlConstant = XmlTagConstants.FLOW_SPECIFICATION;
		this.element = f.createInterfaceInstance();
		this.initialStereotypes = Arrays.asList(SysML.getFlowSpecificationStereotype());
	}
}
