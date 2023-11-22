package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class EnduringTask extends CommonElement implements UAFElement {
	
	public EnduringTask(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.ENDURING_TASK;
		this.xmlConstant = XmlTagConstants.ENDURING_TASK;
	}
}
