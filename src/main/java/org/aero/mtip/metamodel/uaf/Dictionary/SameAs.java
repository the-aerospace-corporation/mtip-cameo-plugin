package org.aero.mtip.metamodel.uaf.Dictionary;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class SameAs extends Dependency {

	public SameAs(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.SAME_AS;
		this.xmlConstant = XmlTagConstants.SAME_AS;
	}
}
