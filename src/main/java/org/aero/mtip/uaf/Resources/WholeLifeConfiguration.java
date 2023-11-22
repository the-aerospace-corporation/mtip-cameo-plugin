package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class WholeLifeConfiguration extends CommonElement implements UAFElement {
	
	public WholeLifeConfiguration(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.WHOLE_LIFE_CONFIGURATION;
		this.xmlConstant = XmlTagConstants.WHOLE_LIFE_CONFIGURATION;
	}
}
