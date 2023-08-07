package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Person extends CommonElement {
	
	public Person(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.PERSON;
		this.xmlConstant = XmlTagConstants.PERSON;
		this.creationProfile = UAFProfile.UAF_PROFILE;
		this.creationStereotype = UAFProfile.PERSON_STEREOTYPE;
	}
}
