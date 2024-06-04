package org.aero.mtip.uaf.Metadata;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PerformsInContext extends Abstraction{
	public PerformsInContext(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.PERFORMS_IN_CONTEXT;
		this.metamodelConstant = UAFConstants.PERFORMS_IN_CONTEXT;
	}
}
