package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class FieldedCapability extends InstanceSpecification {
	
	public FieldedCapability(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.FIELDED_CAPABILITY;
		this.xmlConstant = XmlTagConstants.FIELDED_CAPABILITY;
		this.sysmlElement = f.createInstanceSpecificationInstance();
	}
}
