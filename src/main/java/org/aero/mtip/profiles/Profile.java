package org.aero.mtip.profiles;

import java.util.Collection;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Profile {
	public static boolean hasStereotype(Element element, Stereotype stereotype) {
		Collection<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		
		if (stereotypes.contains(stereotype)) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * Clears all projects and profiles so that exports or imports initialize profiles properly
	 * when executed on different projects without restarting the application.
	 */
	public static void clearAllProfiles() {
	  DependencyMatrixProfile.clearProfile();
	  DslCustomization.clearProfile();
	  MagicDraw.clearProfile();
	  MDCustomizationForSysML.clearProfile();
	  SysML.clearProfile();
	  UAF.clearProfile();
	  Validation.clearProfile();
	}
}
