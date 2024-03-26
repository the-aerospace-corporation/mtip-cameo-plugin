/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.profiles.MDCustomizationForSysMLProfile;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.uml.Finder;

public class QuantityKind extends InstanceSpecification {

	public QuantityKind(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.QUANTITY_KIND;
		this.xmlConstant = XmlTagConstants.QUANTITY_KIND;
		this.creationStereotype = MDCustomizationForSysMLProfile.getQuantityKindStereotype();
	}

	protected void setClassifier() {
		this.classifier = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element)Finder.byQualifiedName().find(ImportXmlSysml.getProject(), "SysML::Libraries::UnitAndQuantityKind::QuantityKind");
	}
}
