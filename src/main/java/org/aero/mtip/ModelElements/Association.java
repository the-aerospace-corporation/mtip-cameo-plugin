/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;

public class Association extends CommonRelationship{
	public Association(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.ASSOCIATION;
		this.xmlConstant = XmlTagConstants.ASSOCIATION;
		this.element = f.createAssociationInstance();
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
		
		if(xmlElement.hasAttribute(XmlTagConstants.SUPPLIER_MULTIPLICITY)) {
			ModelHelper.setMultiplicity(xmlElement.getAttribute(XmlTagConstants.SUPPLIER_MULTIPLICITY), ModelHelper.getFirstMemberEnd(association));
		}
		if(xmlElement.hasAttribute(XmlTagConstants.CLIENT_MULTIPLICITY)) {
			ModelHelper.setMultiplicity(xmlElement.getAttribute(XmlTagConstants.CLIENT_MULTIPLICITY), ModelHelper.getSecondMemberEnd(association));
		}
		
		return association;
	}
	
	@Override
	public String getSupplierMultiplicity(Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association association = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association) element;
		return ModelHelper.getMultiplicity((MultiplicityElement) ModelHelper.getFirstMemberEnd(association));
	}
	
	@Override
	public String getClientMultiplicity(Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association association = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association) element;
		return ModelHelper.getMultiplicity((MultiplicityElement) ModelHelper.getSecondMemberEnd(association));
	}
}
