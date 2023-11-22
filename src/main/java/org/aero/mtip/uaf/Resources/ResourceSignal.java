package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Block.Signal;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceSignal extends Signal {

	public ResourceSignal(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCE_SIGNAL;
		this.xmlConstant = XmlTagConstants.RESOURCE_SIGNAL;
	}
}
