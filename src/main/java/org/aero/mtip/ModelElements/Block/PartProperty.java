/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.HashMap;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class PartProperty extends org.aero.mtip.ModelElements.Sequence.Property {
	public PartProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PARTPROPERTY;
		this.xmlConstant = XmlTagConstants.PARTPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {	
		Element partProperty = super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype partPropertyStereotype = StereotypesHelper.getStereotype(project, "PartProperty", mdCustomizationProfile);
		StereotypesHelper.addStereotype(partProperty, partPropertyStereotype);
		
//		if(xmlElement.hasAttribute(XmlTagConstants.CLASSIFIER_TYPE)) {
//			Type classifierType = (Type) project.getElementByID(xmlElement.getAttribute(XmlTagConstants.CLASSIFIER_TYPE));
//			((TypedElement)partProperty).setType(classifierType);
//		}
		return partProperty;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for PartProperty with id: " + modelElement.getEAID());
		
		if(modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			String classifierID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
			try {
				Element type = ImportXmlSysml.getOrBuildElement(project, parsedXML, classifierID);
				modelElement.addAttribute(XmlTagConstants.CLASSIFIER_TYPE, type.getID());
			} catch (NullPointerException npe) {
				CameoUtils.logGUI("Failed to create/get typed by element for element with id" + this.EAID);
				ImportLog.log("Failed to create/get typed by element for element with id" + this.EAID);
			}
			
		}		
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		if(owner == null) {
			String logMessage = "Owner is null. Could not add connector with id: " + this.EAID + " to the model.";
			ImportLog.log(logMessage);
		}
		try {
			if(!(SysMLProfile.isBlock(owner))) {
				owner = CameoUtils.findNearestBlock(project, owner);
				if(owner == null) {
					String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". No parents found in ancestors. Element could not be placed in model.";
					ImportLog.log(logMessage);

				}
				sysmlElement.setOwner(owner);
			} else {
				sysmlElement.setOwner(owner);
			}
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". Element could not be placed in model.";
			ImportLog.log(logMessage);
		}
	}
}