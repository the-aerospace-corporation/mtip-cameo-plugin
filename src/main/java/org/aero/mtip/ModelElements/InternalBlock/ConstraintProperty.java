/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ConstraintProperty extends CommonElement {

	public ConstraintProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CONSTRAINT_PROPERTY;
		this.metamodelConstant = SysmlConstants.CONSTRAINT_PROPERTY;
		this.element = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype partPropertyStereotype = StereotypesHelper.getStereotype(project, "ConstraintProperty", mdCustomizationProfile);
		StereotypesHelper.addStereotype(element, partPropertyStereotype);
		
//		if(xmlElement.hasAttribute(XmlTagConstants.CLASSIFIER_TYPE)) {
//			Type classifierType = (Type) project.getElementByID(xmlElement.getAttribute(XmlTagConstants.CLASSIFIER_TYPE));
//			((TypedElement)sysmlElement).setType(classifierType);
//		}
		
		return element;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for PartProperty with id: " + modelElement.getEAID());
		
		if(modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			String classifierID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
			try {
				Element type = ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(classifierID));
				modelElement.addAttribute(XmlTagConstants.CLASSIFIER_TYPE, type.getID());
			} catch (NullPointerException npe) {
				CameoUtils.logGUI("Failed to create/get typed by element for element with id" + this.EAID);
				ImportLog.log("Failed to create/get typed by element for element with id" + this.EAID);
			}
			
		}		
	}
}
