package org.aero.mtip.profiles;

import org.aero.mtip.util.Logger;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Validation {
	public static Validation instance;
	
	public Project project;
	public Profile profile;
	
	public static final String NAME = "Validation Profile";
	
	private static final String VALIDATION_RULE_NAME = "validationRule";
	
	public static final String ERROR_MESSAGE_PROPERTY_NAME = "errorMessage";
	
	public Validation() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
	}
	
	public static Validation getInstance() {
		if (instance == null) {
			instance = new Validation();
		}
		
		return instance;
	}
	
	public boolean hasStereotype(Element element, String stereotypeName) {
		if (profile == null) {
			Logger.log(String.format("Profile not initialized when looking for stereotype name %s", stereotypeName));
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
	
	public static boolean hasValidationRuleStereotype(Element element) {
		if (!getInstance().hasStereotype(element, VALIDATION_RULE_NAME)) {
			return false;
		}
		
		return true;
	}
	
	public static Stereotype getValidationRuleStereotype() {
		return StereotypesHelper.getStereotype(getInstance().project, VALIDATION_RULE_NAME, getInstance().profile);
	}
}
