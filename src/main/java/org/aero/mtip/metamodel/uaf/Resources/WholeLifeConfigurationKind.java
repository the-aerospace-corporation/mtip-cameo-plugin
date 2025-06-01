package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.Enumeration;

public class WholeLifeConfigurationKind extends Enumeration{
	public WholeLifeConfigurationKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.WHOLE_LIFE_CONFIGURATION_KIND;
		this.xmlConstant = XmlTagConstants.WHOLE_LIFE_CONFIGURATION_KIND;
	}
}