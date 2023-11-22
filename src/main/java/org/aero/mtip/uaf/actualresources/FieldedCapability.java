package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class FieldedCapability extends InstanceSpecification {
	
	public FieldedCapability(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.FIELDED_CAPABILITY;
		this.xmlConstant = XmlTagConstants.FIELDED_CAPABILITY;
	}
}
