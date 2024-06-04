package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DesiredEffect extends Dependency {

	public DesiredEffect(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.DESIRED_EFFECT;
		this.xmlConstant = XmlTagConstants.DESIRED_EFFECT;
	}
}
