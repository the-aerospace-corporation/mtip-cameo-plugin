package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Profile extends CommonElement {

	public Profile(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PROFILE;
		this.xmlConstant = XmlTagConstants.PROFILE;
		this.sysmlElement = f.createProfileInstance();
	}
}
