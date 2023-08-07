package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class Post extends CommonElement {
	
	public Post(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.POST;
		this.xmlConstant = XmlTagConstants.POST;
		this.creationProfile = UAFProfile.UAF_PROFILE;
		this.creationStereotype = UAFProfile.POST_STEREOTYPE;
	}
}
