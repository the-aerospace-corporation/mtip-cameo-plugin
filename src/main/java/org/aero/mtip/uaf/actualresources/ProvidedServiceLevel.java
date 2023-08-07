package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class ProvidedServiceLevel extends InstanceSpecification {

	public ProvidedServiceLevel(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.PROVIDED_SERVICE_LEVEL;
		this.xmlConstant = XmlTagConstants.PROVIDED_SERVICE_LEVEL;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.PROVIDED_SERVICE_LEVEL_STEREOTYPE);
	}
}
