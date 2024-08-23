package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Dependency;

public class AchievedEffect extends Dependency {

	public AchievedEffect(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ACHIEVED_EFFECT;
		this.xmlConstant = XmlTagConstants.ACHIEVED_EFFECT;
	}
}
