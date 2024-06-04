package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Competence extends CommonElement {
	
	public Competence(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.COMPETENCE;
		this.xmlConstant = XmlTagConstants.COMPETENCE;
	}
}
