/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.TaggedValue;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.jmi.helpers.TagsHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ConstraintParameter extends CommonElement {

	public ConstraintParameter(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CONSTRAINT_PARAMETER;
		this.sysmlConstant = SysmlConstants.CONSTRAINT_PARAMETER;
		this.element = f.createPortInstance();
		this.creationStereotype = MDCustomizationForSysML.getConstraintParameterStereotype();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		StereotypesHelper.addStereotype(element, SysML.getDirectedFeatureStereotype());
		setDirectedFeatureValue(xmlElement);
		
		return element;
	}
	
	private void setDirectedFeatureValue(XMLItem xmlElement) {
		TaggedValue directedFeatureTaggedValue = getDirectedFeatureTaggedValue(xmlElement);
		
		if (directedFeatureTaggedValue == null) {
			return;
		}
		
		Profile profile = StereotypesHelper.getProfile(Importer.getProject(), directedFeatureTaggedValue.getProfileName());
		Stereotype stereotype = StereotypesHelper.getStereotype(Importer.getProject(), directedFeatureTaggedValue.getStereotypeName(), profile);
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
			if (tv.getStereotypeName().contentEquals(SysMLProfile.DIRECTEDFEATURE_STEREOTYPE)) {
				return tv;
			}
		}
		
		return null;
	}
}
