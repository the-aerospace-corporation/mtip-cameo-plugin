/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Profile;

import java.util.Arrays;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Customization extends CommonElement {
	private final static String CUSTOMIZATIONTARGET = "customizationTarget";
	private final static String CUSTOMIZATIONTARGETS = "customizationTargets";
	private final static String ALLOWEDRELATIONSHIPS = "allowedRelationships";
	private final static String DISALLOWEDRELATIONSHIPS = "disallowedRelationships";
	private final static String TYPESFORTARGET = "typesForTarget";
	private final static String TYPESFORSOURCE = "typesForSource";
	private final static String POSSIBLEOWNERS = "possibleOwners";
	
	private Profile dslCustomizationProfile = null;
	private Stereotype customizationStereotype = null;
	
	public Customization(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.xmlConstant = XmlTagConstants.CUSTOMIZATION;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.CUSTOMIZATION); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysmlConstants.CUSTOMIZATION, creationProfile);
		
		this.dslCustomizationProfile = StereotypesHelper.getProfile(project,  "DSL Customization");
		this.customizationStereotype = StereotypesHelper.getStereotype(project, SysmlConstants.CUSTOMIZATION, dslCustomizationProfile);
		
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element customization = super.createElement(project, owner, xmlElement);
				
		return customization;
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		// Create Relationship tags for customization targets
		List<Element> customizationTargets = StereotypesHelper.getStereotypePropertyValue(element, SysmlConstants.CUSTOMIZATION, CUSTOMIZATIONTARGET);
		CameoUtils.logGUI("Found " + Integer.toString(customizationTargets.size()) + " customization targets.");
		if(customizationTargets != null && !customizationTargets.isEmpty()) {
			org.w3c.dom.Element customizationTargetsTag = createListRelationship(xmlDoc, CUSTOMIZATIONTARGETS);
			int customizationTargetCount = 0;
			for(Element customizationTarget : customizationTargets) {
				org.w3c.dom.Element customizationTargetTag = createListRel(xmlDoc, customizationTarget, CUSTOMIZATIONTARGET, Integer.toString(customizationTargetCount));
				customizationTargetsTag.appendChild(customizationTargetTag);
				customizationTargetCount++;
			}
			relationships.appendChild(customizationTargetsTag);
		}
		
		// Create Relationship tags for possible owners

		return data;
	}
}
