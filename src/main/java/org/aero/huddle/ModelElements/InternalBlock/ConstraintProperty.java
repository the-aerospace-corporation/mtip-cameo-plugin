/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.InternalBlock;

import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ConstraintProperty extends CommonElement {

	public ConstraintProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CONSTRAINTPROPERTY;
		this.sysmlConstant = SysmlConstants.CONSTRAINTPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype partPropertyStereotype = StereotypesHelper.getStereotype(project, "ConstraintProperty", mdCustomizationProfile);
		StereotypesHelper.addStereotype(sysmlElement, partPropertyStereotype);
		
//		if(xmlElement.hasAttribute(XmlTagConstants.CLASSIFIER_TYPE)) {
//			Type classifierType = (Type) project.getElementByID(xmlElement.getAttribute(XmlTagConstants.CLASSIFIER_TYPE));
//			((TypedElement)sysmlElement).setType(classifierType);
//		}
		
		return sysmlElement;
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for PartProperty with id: " + modelElement.getEAID());
		
		if(modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			String classifierID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
			try {
				Element type = ImportXmlSysml.getOrBuildElement(project, parsedXML, classifierID);
				modelElement.addAttribute(XmlTagConstants.CLASSIFIER_TYPE, type.getLocalID());
			} catch (NullPointerException npe) {
				CameoUtils.logGUI("Failed to create/get typed by element for element with id" + this.EAID);
				ImportLog.log("Failed to create/get typed by element for element with id" + this.EAID);
			}
			
		}		
	}
}
