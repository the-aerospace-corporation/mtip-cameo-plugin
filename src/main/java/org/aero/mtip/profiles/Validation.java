package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Validation extends Profile {
	private static final String NAME = "Validation Profile";
	
	private static final String VALIDATION_RULE_NAME = "validationRule";
	
	public static final String ERROR_MESSAGE_PROPERTY_NAME = "errorMessage";
	
	public Validation(Project project) {
		this.project = project;
		this.PROFILE = StereotypesHelper.getProfile(project, NAME);
	}
	
	public static void initialize(Project project) {
		instance = new Validation(project);
	}
	
	public static boolean hasValidationRuleStereotype(Element element) {
		if (!hasStereotype(element, VALIDATION_RULE_NAME)) {
			return false;
		}
		
		return true;
	}
	
	public static Stereotype getValidationRuleStereotype() {
		return StereotypesHelper.getStereotype(instance.project, VALIDATION_RULE_NAME, instance.PROFILE);
	}
}
