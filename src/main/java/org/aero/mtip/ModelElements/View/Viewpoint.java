package org.aero.mtip.ModelElements.View;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class Viewpoint extends CommonElement {

	public Viewpoint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = SysmlConstants.VIEWPOINT;
		this.xmlConstant = XmlTagConstants.VIEWPOINT;
		this.creationStereotype = SysML.getViewpointStereotype();
	}
}
