package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class VersionSuccession extends Dependency {

	public VersionSuccession(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.VERSION_SUCCESSION;
		this.xmlConstant = XmlTagConstants.VERSION_SUCCESSION;
	}
}
