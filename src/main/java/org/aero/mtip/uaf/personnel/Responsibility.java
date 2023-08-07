package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Responsibility extends CommonElement {

	public Responsibility(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.RESPONSIBILITY;
		this.xmlConstant = XmlTagConstants.RESPONSIBILITY;
		this.creationProfile = UAFProfile.UAF_PROFILE;
		this.creationStereotype = UAFProfile.RESPONSIBILITY_STEREOTYPE;
	}
}
