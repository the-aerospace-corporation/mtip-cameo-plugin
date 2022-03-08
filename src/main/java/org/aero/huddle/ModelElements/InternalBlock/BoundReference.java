/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class BoundReference extends CommonElement {

	public BoundReference(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.BOUNDREFERENCE;
		this.sysmlConstant = SysmlConstants.BOUNDREFERENCE;
		this.sysmlElement = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype boundReferenceStereotype = StereotypesHelper.getStereotype(project, "BoundReference", sysmlProfile);
		StereotypesHelper.addStereotype(sysmlElement, boundReferenceStereotype);

		return sysmlElement;
	}
}
