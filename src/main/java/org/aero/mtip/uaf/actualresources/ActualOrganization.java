package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ActualOrganization extends InstanceSpecification {

	public ActualOrganization(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_ORGANIZATION;
		this.xmlConstant = XmlTagConstants.ACTUAL_ORGANIZATION;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.ACTUAL_ORGANIZATION_STEREOTYPE);
	}
}
