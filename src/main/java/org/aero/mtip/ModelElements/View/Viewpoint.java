package org.aero.mtip.ModelElements.View;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.SysML;

public class Viewpoint extends CommonElement {

	public Viewpoint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = SysmlConstants.VIEWPOINT;
		this.xmlConstant = XmlTagConstants.VIEWPOINT;
		this.creationStereotype = SysML.getViewpointStereotype();
	}

}
