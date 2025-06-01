/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.block;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.profiles.SysML;

public class FlowSpecification extends CommonElement {
	public FlowSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.FLOW_SPECFICATION;
		this.xmlConstant = XmlTagConstants.FLOW_SPECIFICATION;
		this.element = f.createInterfaceInstance();
		this.creationStereotype = SysML.getFlowSpecificationStereotype();
	}
}
