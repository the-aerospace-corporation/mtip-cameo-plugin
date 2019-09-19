package org.aero.huddle.util;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class CameoUtils {
	public static void logGUI(String text) {
		Application.getInstance().getGUILog().log(text);
	}
	
	public static Element findNearestPackage(Project project, Element element) {
		Element topPackage = project.getPrimaryModel();
		Element owner = element.getOwner();
		if(owner.equals(topPackage)) {
			return topPackage;
		}
		if(owner.getHumanType().equals("Package")) {
			return owner;
		} else {
			return findNearestPackage(project, owner);
		}
		
	}
	
	public static Stereotype getAuxiliaryStereotype() {
		
		return null;
	}
	
	public static Stereotype getModelLibraryStereotype() {
		
		return null;
	}
	
}
