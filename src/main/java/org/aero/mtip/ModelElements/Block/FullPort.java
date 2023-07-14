/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.Arrays;

import org.aero.mtip.profiles.SysML;
import org.aero.mtip.profiles.SysMLProfile;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class FullPort extends Port {

	public FullPort(String name, String EAID) {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTSFACTORY;
		sysmlConstant = SysmlConstants.FULL_PORT;
		xmlConstant = XmlTagConstants.FULL_PORT;
		element = f.createPortInstance();
		initialStereotypes = Arrays.asList(SysML.getFullPortStereotype());
	}
}
