/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.profiles.MDCustomizationForSysMLProfile;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.jmi.helpers.TagsHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ConstraintParameter extends CommonElement {
	static final String DIRECTED_FEATURE = "DirectedFeature";
	
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
		
		StereotypesHelper.addStereotype(element, MDCustomizationForSysMLProfile.getConstraintParameterStereotype());
		StereotypesHelper.addStereotype(element, SysML.getDirectedFeatureStereotype());
		
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
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TaggedValue tv = TagsHelper.getTaggedValueOrCreate(profile, stereotype, prop, true);
		EnumerationLiteral directedFeatureValue = getDirectionFeatureValue(prop, directedFeatureTaggedValue);
		
		if (directedFeatureValue == null) {
			return;
		}

		tv.addConvertedValue(directedFeatureValue);
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
			if (tv.getStereotypeName().contentEquals(DIRECTED_FEATURE)) {
				return tv;
			}
		}
		
		return null;
	}
}
