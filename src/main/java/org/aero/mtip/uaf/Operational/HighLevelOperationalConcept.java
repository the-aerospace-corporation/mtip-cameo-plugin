package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class HighLevelOperationalConcept extends CommonElement implements UAFElement {
	
	public HighLevelOperationalConcept(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.HIGH_LEVEL_OPERATIONAL_CONCEPT;
		this.xmlConstant = XmlTagConstants.HIGH_LEVEL_OPERATIONAL_CONCEPT;
	}
}
