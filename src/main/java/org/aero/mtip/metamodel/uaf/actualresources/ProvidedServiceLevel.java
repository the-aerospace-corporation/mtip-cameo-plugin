package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;

public class ProvidedServiceLevel extends InstanceSpecification {

	public ProvidedServiceLevel(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROVIDED_SERVICE_LEVEL;
		this.xmlConstant = XmlTagConstants.PROVIDED_SERVICE_LEVEL;
	}
}
