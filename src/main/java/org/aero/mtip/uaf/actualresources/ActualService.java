package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ActualService extends InstanceSpecification {

	public ActualService(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ACTUAL_SERVICE;
		this.xmlConstant = XmlTagConstants.ACTUAL_SERVICE;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.ACTUAL_SERVICE_STEREOTYPE);
	}
}
