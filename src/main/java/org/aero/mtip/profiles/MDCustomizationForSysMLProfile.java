package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class MDCustomizationForSysMLProfile extends org.aero.mtip.profiles.Profile {
	private static boolean isInitialized = false;
	
	private static final String MD_CUSTOMIZATION_FOR_SYSML_PROFILE_NAME = "MD Customization for SysML";
	private static final String PART_PROPERTY_NAME = "PartProperty";
	private static final String QUANTITY_KIND_NAME = "QuantityKind";
	private static final String REFERENCE_PROPERTY_NAME = "ReferenceProperty";
	private static final String UNIT_NAME = "Unit";
	private static final String VALUE_PROPERTY_NAME = "ValueProperty";

	public static Profile MD_CUSTOMIZATION_FOR_SYSML_PROFILE;
	public static Stereotype PART_PROPERTY_STEREOTYPE;
	public static Stereotype QUANTITY_KIND_STEREOTYPE;
	public static Stereotype REFERENCE_PROPERTY_STEREOTYPE;
	public static Stereotype UNIT_STEREOTYPE;
	public static Stereotype VALUE_PROPERTY_STEREOTYPE;
	
	public static void initializeStereotypes() {		
		Project project = Application.getInstance().getProject();
		MD_CUSTOMIZATION_FOR_SYSML_PROFILE = StereotypesHelper.getProfile(project, MD_CUSTOMIZATION_FOR_SYSML_PROFILE_NAME);
		
		PART_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, PART_PROPERTY_NAME, MD_CUSTOMIZATION_FOR_SYSML_PROFILE);
		QUANTITY_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, QUANTITY_KIND_NAME, MD_CUSTOMIZATION_FOR_SYSML_PROFILE);
		REFERENCE_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, REFERENCE_PROPERTY_NAME, MD_CUSTOMIZATION_FOR_SYSML_PROFILE);
		UNIT_STEREOTYPE = StereotypesHelper.getStereotype(project, UNIT_NAME, MD_CUSTOMIZATION_FOR_SYSML_PROFILE);
		VALUE_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, VALUE_PROPERTY_NAME, MD_CUSTOMIZATION_FOR_SYSML_PROFILE);
		
	
		isInitialized = true;
	}
	
	public static boolean isPartProperty(Element element) {
		return hasStereotype(element, PART_PROPERTY_STEREOTYPE);
	}
	
	public static boolean isProperty(Element element) {
		if(element instanceof Property) {
			if(isPartProperty(element) || isReferenceProperty(element) || isValueProperty(element)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean isQuantityKind(Element element) {
		return hasStereotype(element, QUANTITY_KIND_STEREOTYPE);
	}
	
	public static boolean isReferenceProperty(Element element) {
		return hasStereotype(element, REFERENCE_PROPERTY_STEREOTYPE);
	}
	
	public static boolean isUnit(Element element) {
		return hasStereotype(element, UNIT_STEREOTYPE);
	}
	
	public static boolean isValueProperty(Element element) {
		return hasStereotype(element, VALUE_PROPERTY_STEREOTYPE);
	}
}
