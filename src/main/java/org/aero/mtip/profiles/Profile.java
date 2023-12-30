package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Profile {
	public static Profile instance;
	
	protected Project project;
	protected com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile PROFILE;
	
	protected static boolean hasStereotype(Element element, String stereotypeName) {		
		Stereotype stereotype = StereotypesHelper.getStereotype(instance.project, stereotypeName, instance.PROFILE);
		
		if (!StereotypesHelper.hasStereotype(element, stereotype)) {
			return false;
		}
		
		return true;
	}
}
