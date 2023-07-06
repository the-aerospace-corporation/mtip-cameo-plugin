package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ActualOrganization extends InstanceSpecification {

	public ActualOrganization(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_ORGANIZATION;
		this.xmlConstant = XmlTagConstants.ACTUAL_ORGANIZATION;
		this.sysmlElement = f.createInstanceSpecificationInstance();
	}
}
