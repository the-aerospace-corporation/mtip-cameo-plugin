package org.aero.mtip.profiles;

import org.aero.mtip.util.ExportLog;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class MDCustomizationForSysMLProfile extends org.aero.mtip.profiles.Profile {
	static MDCustomizationForSysMLProfile instance;
	
	Project project;
	Profile profile;
	
	static final String NAME = "additional_stereotypes";
	static final String CONSTRAINT_PARAMETER_NAME = "ConstraintParameter";
	static final String CONSTRAINT_PROPERTY_NAME = "ConstraintProperty";
	static final String PART_PROPERTY_NAME = "PartProperty";
	static final String QUANTITY_KIND_NAME = "QuantityKind";
	static final String REFERENCE_PROPERTY_NAME = "ReferenceProperty";
	static final String UNIT_NAME = "Unit";
	static final String VALUE_PROPERTY_NAME = "ValueProperty";
	
	private MDCustomizationForSysMLProfile() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
		
		if (profile == null) {
			ExportLog.log("Failed to find MD Customization for SysML Profile.");
		}
	}
	
	public static MDCustomizationForSysMLProfile getInstance() {
		if (instance == null || instance.project != Application.getInstance().getProject()) {
			instance = new MDCustomizationForSysMLProfile();
		}
		
		return instance;
	}
	
	public static boolean isPartProperty(Element element) {
		return getInstance().hasStereotype(element, PART_PROPERTY_NAME);
	}
	
	protected boolean hasStereotype(Element element, String stereotypeName) {
		if (profile == null) {
			ExportLog.log(String.format("Profile not initialized when looking for stereotype name %s", stereotypeName));
			return false;
		}
		
		Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
		
		if (stereotype == null) {
			ExportLog.log(String.format("Stereotype %s not found in profile %s", stereotypeName, profile.getHumanName()));
			return false;
		}
		
		if (!StereotypesHelper.hasStereotype(element, stereotype)) {
			return false;
		}
		
		return true;
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
	
	Stereotype getStereotype(String stereotypeName) {
		return StereotypesHelper.getStereotype(project, stereotypeName, profile);
	}
	
	public static Stereotype getConstraintParameterStereotype() {
		return getInstance().getStereotype(CONSTRAINT_PARAMETER_NAME);
	}
	
	public static Stereotype getConstraintPropertyStereotype() {
		return getInstance().getStereotype(CONSTRAINT_PROPERTY_NAME);
	}
	
	public static Stereotype getPartPropertyStereotype() {
		return getInstance().getStereotype(PART_PROPERTY_NAME);
	}
	
	public static Stereotype getQuantityKindStereotype() {
		return getInstance().getStereotype(QUANTITY_KIND_NAME);
	}
	
	public static Stereotype getUnitStereotype() {
		return getInstance().getStereotype(UNIT_NAME);
	}
	
	public static boolean isConstraintParameter(Element element) {
		return getInstance().hasStereotype(element, CONSTRAINT_PARAMETER_NAME);
	}
	
	public static boolean isConstraintProperty(Element element) {
		return getInstance().hasStereotype(element, CONSTRAINT_PROPERTY_NAME);
	}
	
	public static boolean isQuantityKind(Element element) {
		return getInstance().hasStereotype(element, QUANTITY_KIND_NAME);
	}
	
	public static boolean isReferenceProperty(Element element) {
		return getInstance().hasStereotype(element, REFERENCE_PROPERTY_NAME);
	}
	
	public static boolean isUnit(Element element) {
		return getInstance().hasStereotype(element, UNIT_NAME);
	}
	
	public static boolean isValueProperty(Element element) {
		return getInstance().hasStereotype(element, VALUE_PROPERTY_NAME);
	}
}
