package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Stereotype extends CommonElement {	
	public Stereotype(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STEREOTYPE;
		this.xmlConstant = XmlTagConstants.STEREOTYPE;
		this.sysmlElement = f.createStereotypeInstance();
	}
}