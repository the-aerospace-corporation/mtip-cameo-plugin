package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class AchievedEffect extends Dependency {

	public AchievedEffect(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACHIEVED_EFFECT;
		this.xmlConstant = XmlTagConstants.ACHIEVED_EFFECT;
	}
}
