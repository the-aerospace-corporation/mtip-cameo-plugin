package org.aero.mtip.profiles;

import org.aero.mtip.util.Logger;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class DslCustomization {
	static DslCustomization instance;
	
	Project project;
	Profile profile;
	
	static final String NAME = "DSL Customization";
	static final String CUSTOMIZATION_NAME = "Customization";
	
	public DslCustomization() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
	}
	
	Stereotype getStereotype(String stereotypeName) {
		return StereotypesHelper.getStereotype(project, stereotypeName, profile);
	}

	boolean hasStereotype(Element element, String stereotypeName) {
		if (profile == null) {
			Logger.log(String.format("Profile not initialized when looking for stereotype name %s", stereotypeName));
			return false;
		}
		
		if (element == null) {
		  return false;
		}
		
		Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);
		
		if (stereotype == null) {
			Logger.log(String.format("Stereotype %s not found in profile %s", stereotypeName, profile.getHumanName()));
			return false;
		}
		
		if (!StereotypesHelper.hasStereotype(element, stereotype)) {
			return false;
		}
		
		return true;
	}
	
	public static DslCustomization getInstance() {
		if (instance == null) {
			instance = new DslCustomization();
		}
		
		return instance;
	}
	
	public static boolean isCustomization(Element element) {
		return getInstance().hasStereotype(element, CUSTOMIZATION_NAME);
	}
	
	public static Stereotype getCustomizationStereotype() {
		return getInstance().getStereotype(CUSTOMIZATION_NAME);
	}
}
