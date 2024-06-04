package org.aero.mtip.uaf.Dictionary;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SameAs extends Dependency {

	public SameAs(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SAME_AS;
		this.xmlConstant = XmlTagConstants.SAME_AS;
	}
}
