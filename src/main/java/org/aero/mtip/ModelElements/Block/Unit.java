/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.MDCustomizationForSysML;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.uml.Finder;

public class Unit extends InstanceSpecification {

	public Unit(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = SysmlConstants.UNIT;
		this.xmlConstant = XmlTagConstants.UNIT;
		this.creationStereotype = MDCustomizationForSysML.getUnitStereotype();
	}
	
	@Override
	protected void setClassifier() {
		this.classifier = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element)Finder.byQualifiedName().find(Application.getInstance().getProject(), "SysML::Libraries::UnitAndQuantityKind::Unit");
	}
}
