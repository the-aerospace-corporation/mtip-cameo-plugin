/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import java.util.Collections;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ConstraintParameter extends CommonElement {

	public ConstraintParameter(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CONSTRAINT_PARAMETER;
		this.sysmlConstant = SysmlConstants.CONSTRAINT_PARAMETER;
		this.element = f.createPortInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, SysmlConstants.MD_CUSTOMIZATION_PROFILE_NAME); 
		Stereotype constraintParameterStereotype = StereotypesHelper.getStereotype(project, SysmlConstants.CONSTRAINT_PARAMETER, mdCustomizationProfile);
		StereotypesHelper.addStereotype(element, constraintParameterStereotype);
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, SysmlConstants.SYSML_PROFILE_NAME);
		Stereotype directedFeatureStereotype = StereotypesHelper.getStereotype(project, SysmlConstants.DIRECTED_FEATURE, sysmlProfile);
		StereotypesHelper.addStereotype(element, directedFeatureStereotype);
		
		setDirectedFeatureValue(xmlElement);
		
		return element;
	}
	
	private void setDirectedFeatureValue(XMLItem xmlElement) {
		TaggedValue directedFeatureTaggedValue = getDirectedFeatureTaggedValue(xmlElement);
		
		if (directedFeatureTaggedValue == null) {
			return;
		}
		
		Profile profile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), directedFeatureTaggedValue.getProfileName());
		Stereotype stereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), directedFeatureTaggedValue.getStereotypeName(), profile);
		Property prop = StereotypesHelper.getPropertyByName(stereotype, directedFeatureTaggedValue.getValueName());
		Slot slot = StereotypesHelper.getSlot(element, prop, true, false);
		
		EnumerationLiteral directedFeatureValue = getDirectionFeatureValue(prop, directedFeatureTaggedValue);
		
		if (directedFeatureValue == null) {
			return;
		}
		
		ValueSpecification instanceValue 
			= ModelHelper.createValueSpecification(
				InstanceValue.class, 
				prop.getType(), 
				directedFeatureValue, 
				f, 
				Collections.<ValueSpecification>emptySet());
		
		slot.getValue().add(instanceValue);
	}
	
	private EnumerationLiteral getDirectionFeatureValue(Property prop, TaggedValue tv) {
		if (prop.getType() instanceof Enumeration) {
			Enumeration directedFeatureChoices = (Enumeration)prop.getType();
			
			for (EnumerationLiteral choice: directedFeatureChoices.getOwnedLiteral()) {
				if (choice.getName().contentEquals(tv.getValue())) {
					return choice;
				}
			}
		}
		
		return null;
	}
	
	private TaggedValue getDirectedFeatureTaggedValue(XMLItem xmlElement) {
		for(TaggedValue tv : xmlElement.getTaggedValues()) {
			CameoUtils.logGUI(String.format("Checking %s for comparison to %s", tv.getStereotypeName(), SysMLProfile.DIRECTEDFEATURE_STEREOTYPE));
			if (tv.getStereotypeName().contentEquals(SysMLProfile.DIRECTEDFEATURE_STEREOTYPE)) {
				return tv;
			}
		}
		
		return null;
	}
}
