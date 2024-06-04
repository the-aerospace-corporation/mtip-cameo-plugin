package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ProvidedServiceLevel extends InstanceSpecification {

	public ProvidedServiceLevel(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.PROVIDED_SERVICE_LEVEL;
		this.xmlConstant = XmlTagConstants.PROVIDED_SERVICE_LEVEL;
	}
}
