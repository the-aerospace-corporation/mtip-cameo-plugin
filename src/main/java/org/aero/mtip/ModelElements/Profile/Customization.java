/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Profile;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.profiles.DslCustomization;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Customization extends CommonElement {
	private final static String CUSTOMIZATION_TARGET = "customizationTarget";
	private final static String CUSTOMIZATION_TARGETS = "customizationTargets";
	private final static String ALLOWED_RELATIONSHIPS = "allowedRelationships";
	private final static String DISALLOWED_RELATIONSHIPS = "disallowedRelationships";
	private final static String TYPES_FOR_TARGET = "typesForTarget";
	private final static String TYPES_FOR_SOURCE = "typesForSource";
	private final static String POSSIBLE_OWNERS = "possibleOwners";
	
	private Profile dslCustomizationProfile = null;
	private Stereotype customizationStereotype = null;
	
	public Customization(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.xmlConstant = XmlTagConstants.CUSTOMIZATION; 
		this.creationStereotype = DslCustomization.getCustomizationStereotype();
		
		this.dslCustomizationProfile = StereotypesHelper.getProfile(project,  "DSL Customization");
		this.customizationStereotype = StereotypesHelper.getStereotype(project, SysmlConstants.CUSTOMIZATION, dslCustomizationProfile);
		this.creationStereotype = this.customizationStereotype;
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element customization = super.createElement(project, owner, xmlElement);
				
		return customization;
	}
	
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());

		writeCustomizationTargets(relationships, element);
		
		// TODO: Create relationship tags for possible owners
		// TODO: Create relationship tags for allowed and disallowed relationships
		// TODO: Create relationship tags for types for source and types for target
		
		return data;
	}
	
	private void writeCustomizationTargets(org.w3c.dom.Element relationships, Element element) {
		List<Element> customizationTargets = StereotypesHelper.getStereotypePropertyValue(element, SysmlConstants.CUSTOMIZATION, CUSTOMIZATION_TARGET);

		if(customizationTargets == null || customizationTargets.isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element customizationTargetsTag = XmlWriter.createMtipListAttribute(CUSTOMIZATION_TARGETS);
		
		int customizationTargetCount = 0;
		for(Element customizationTarget : customizationTargets) {
			org.w3c.dom.Element customizationTargetTag = XmlWriter.createMtipListItem(customizationTarget, CUSTOMIZATION_TARGET, Integer.toString(customizationTargetCount));
			XmlWriter.add(relationships, customizationTargetTag);
			
			customizationTargetCount++;
		}
		
		XmlWriter.add(relationships, customizationTargetsTag);
	}
}
