package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.Operation;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceMethod extends Operation {

	public ResourceMethod(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_METHOD;
		this.xmlConstant = XmlTagConstants.RESOURCE_METHOD;
	}
}
