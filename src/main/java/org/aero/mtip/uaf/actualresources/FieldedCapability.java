package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XmlTagConstants;

public class FieldedCapability extends InstanceSpecification {
	
	public FieldedCapability(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.FIELDED_CAPABILITY;
		this.xmlConstant = XmlTagConstants.FIELDED_CAPABILITY;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.FIELDED_CAPABILITY_STEREOTYPE);
	}
}
