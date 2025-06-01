package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class RequiredServiceLevel extends InstanceSpecification {

	public RequiredServiceLevel(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.REQUIRED_SERVICE_LEVEL;
		this.xmlConstant = XmlTagConstants.REQUIRED_SERVICE_LEVEL;
	}
}
