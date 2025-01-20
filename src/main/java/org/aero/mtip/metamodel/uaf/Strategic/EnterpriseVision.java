package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.uaf.UAFElement;

public class EnterpriseVision extends CommonElement implements UAFElement{

	public EnterpriseVision(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.ENTERPRISE_VISION;
		this.xmlConstant = XmlTagConstants.ENTERPIRSE_VISION;
	}
	
}
