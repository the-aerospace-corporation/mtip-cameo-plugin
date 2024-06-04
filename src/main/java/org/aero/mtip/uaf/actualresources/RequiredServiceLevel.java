package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class RequiredServiceLevel extends InstanceSpecification {

	public RequiredServiceLevel(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.REQUIRED_SERVICE_LEVEL;
		this.xmlConstant = XmlTagConstants.REQUIRED_SERVICE_LEVEL;
	}
}
