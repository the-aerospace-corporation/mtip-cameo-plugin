/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class FullPort extends Port {

	public FullPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FULLPORT;
		this.xmlConstant = XmlTagConstants.FULLPORT;
		this.sysmlElement = f.createPortInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element port = super.createElement(project, owner, xmlElement);
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype fullPortStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.FULLPORT_STEREOTYPE, sysmlProfile);
		StereotypesHelper.addStereotype(port, fullPortStereotype);
		
		return port;
	}
}
