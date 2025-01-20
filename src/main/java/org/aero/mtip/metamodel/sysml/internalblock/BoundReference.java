/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.internalblock;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class BoundReference extends CommonElement {

	public BoundReference(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.xmlConstant = XmlTagConstants.BOUND_REFERENCE;
		this.metamodelConstant = SysmlConstants.BOUND_REFERENCE;
		this.element = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype boundReferenceStereotype = StereotypesHelper.getStereotype(project, "BoundReference", sysmlProfile);
		StereotypesHelper.addStereotype(element, boundReferenceStereotype);

		return element;
	}
}
