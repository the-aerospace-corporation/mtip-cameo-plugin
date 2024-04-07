package org.aero.mtip.ModelElements.View;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class View extends CommonElement {

	public View(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.VIEW;
		this.xmlConstant = XmlTagConstants.VIEW;
		this.creationStereotype = SysML.getViewStereotype();
	}
}
