package org.aero.mtip.metamodel.uaf.Metadata;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class PerformsInContext extends Abstraction{
	public PerformsInContext(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.PERFORMS_IN_CONTEXT;
		this.metamodelConstant = UAFConstants.PERFORMS_IN_CONTEXT;
	}
}
