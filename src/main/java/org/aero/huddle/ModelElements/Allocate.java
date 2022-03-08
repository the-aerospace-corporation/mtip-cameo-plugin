/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Allocate extends CommonRelationship{
	public Allocate(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ALLOCATE;
		this.xmlConstant = XmlTagConstants.ALLOCATE;
		this.sysmlElement = f.createAbstractionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		Element allocate = super.createElement(project, owner, client, supplier, xmlElement);
		
		Profile mdCustomSysml = StereotypesHelper.getProfile(project, "SysML");
		Stereotype allocateStereotype = StereotypesHelper.getStereotype(project,  "Allocate", mdCustomSysml);
		StereotypesHelper.addStereotype(allocate, allocateStereotype);

		return allocate;
	}
}
