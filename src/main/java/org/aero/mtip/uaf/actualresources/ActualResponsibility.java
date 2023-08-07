package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ActualResponsibility extends InstanceSpecification {

	public ActualResponsibility(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_RESPONSIBILITY;
		this.xmlConstant = XmlTagConstants.ACTUAL_RESPONSIBILITY;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.ACTUAL_RESPONSIBILITY_STEREOTYPE);
	}
}
