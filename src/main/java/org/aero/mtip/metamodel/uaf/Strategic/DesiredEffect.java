package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class DesiredEffect extends Dependency {

	public DesiredEffect(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.DESIRED_EFFECT;
		this.xmlConstant = XmlTagConstants.DESIRED_EFFECT;
	}
}
