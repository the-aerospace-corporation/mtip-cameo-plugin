package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class FieldedCapability extends InstanceSpecification {
	
	public FieldedCapability(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FIELDED_CAPABILITY;
		this.xmlConstant = XmlTagConstants.FIELDED_CAPABILITY;
	}
}
