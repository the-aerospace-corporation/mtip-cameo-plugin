/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Association extends CommonRelationship{
	public Association(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ASSOCIATION;
		this.xmlConstant = XmlTagConstants.ASSOCIATION;
		this.sysmlElement = f.createAssociationInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association association = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association) super.createElement(project, owner, client, supplier, xmlElement);
		
		if(association != null) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(association);
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(association);
			ModelHelper.setNavigable(firstMemberEnd, true);
			ModelHelper.setNavigable(secondMemberEnd, true);
		}
		return association;
	}
}
