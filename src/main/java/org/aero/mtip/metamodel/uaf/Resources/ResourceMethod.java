package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Operation;

public class ResourceMethod extends Operation {

	public ResourceMethod(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_METHOD;
		this.xmlConstant = XmlTagConstants.RESOURCE_METHOD;
	}
}
