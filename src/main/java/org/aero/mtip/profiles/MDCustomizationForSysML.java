package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class MDCustomizationForSysML extends Profile {
	private static final String MD_CUSTOMIZATION_FOR_SYSML_PROFILE_NAME = "MD Customization for SysML";
	
	private static final String CONSTRAINT_PARAMETER_NAME = "ConstraintParameter";
	private static final String CONSTRAINT_PROPERTY_NAME = "ConstraintProperty";
	private static final String PART_PROPERTY_NAME = "PartProperty";
	private static final String QUANTITY_KIND_NAME = "QuantityKind";
	private static final String REFERENCE_PROPERTY_NAME = "ReferenceProperty";
	private static final String UNIT_NAME = "Unit";
	private static final String VALUE_PROPERTY_NAME = "ValueProperty";
	
	public MDCustomizationForSysML(Project project) {
		this.project = project;
		this.PROFILE = StereotypesHelper.getProfile(project, MD_CUSTOMIZATION_FOR_SYSML_PROFILE_NAME);
	}
	
	public static void initialize(Project project) {		
		if (instance != null & instance.project == project) {
			return;
		}
		
		instance = new MDCustomizationForSysML(project);
	}
	
	public static boolean isConstraintParameter(Element element) {
		return hasStereotype(element, CONSTRAINT_PARAMETER_NAME);
	}
	
	public static boolean isConstraintProperty(Element element) {
		return hasStereotype(element, CONSTRAINT_PROPERTY_NAME);
	}
	
	public static boolean isPartProperty(Element element) {
		return hasStereotype(element, PART_PROPERTY_NAME);
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
		return hasStereotype(element, QUANTITY_KIND_NAME);
	}
	
	public static boolean isReferenceProperty(Element element) {
		return hasStereotype(element, REFERENCE_PROPERTY_NAME);
	}
	
	public static boolean isUnit(Element element) {
		return hasStereotype(element, UNIT_NAME);
	}
	
	public static boolean isValueProperty(Element element) {
		return hasStereotype(element, VALUE_PROPERTY_NAME);
	}
}