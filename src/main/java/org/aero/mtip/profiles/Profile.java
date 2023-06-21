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
}
