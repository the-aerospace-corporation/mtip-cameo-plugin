package org.aero.mtip.metamodel.sysml.view;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.profiles.SysML;

public class View extends CommonElement {

	public View(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = SysmlConstants.VIEW;
		this.xmlConstant = XmlTagConstants.VIEW;
		this.creationStereotype = SysML.getViewStereotype();
	}
}
