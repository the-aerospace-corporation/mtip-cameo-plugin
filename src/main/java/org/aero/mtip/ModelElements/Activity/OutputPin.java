/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class OutputPin extends CommonElement {

	public OutputPin(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OUTPUTPIN;
		this.xmlConstant = XmlTagConstants.OUTPUTPIN;
		this.sysmlElement = f.createOutputPinInstance();
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		if(!(owner instanceof com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.Action)) {
			owner = CameoUtils.findNearestActivity(project, owner);
		}
		sysmlElement.setOwner(owner);
	}
}
