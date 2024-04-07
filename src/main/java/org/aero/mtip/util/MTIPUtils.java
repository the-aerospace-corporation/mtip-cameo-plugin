/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.constants.SysmlConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class MTIPUtils {
	public static boolean isSupportedElement(String commonElementType) {
		if (!SysmlConstants.SYSML_ELEMENTS.contains(commonElementType)) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isSupportedRelationship(String commonRelationshipType) {
		if (!SysmlConstants.SYSML_RELATIONSHIPS.contains(commonRelationshipType)) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isSupportedDiagram(String commonElementType) {
		if (!SysmlConstants.SYSML_DIAGRAMS.contains(commonElementType)) {
			return false;
		}
		
		return true;
	}
	
	public static Package createMTIPProfile(Project project) {
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonElement profileClass = cef.createElement(SysmlConstants.PROFILE, "MTIP Stereotypes",  "");
		Profile mtipProfile = (Profile)profileClass.createElement(project, project.getPrimaryModel(), null);
		
		addMTIPImportedStereotype(project, mtipProfile);
		
		return mtipProfile;
	}
	
	private static Element addMTIPImportedStereotype(Project project, Profile huddleProfile) {
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonElement stereotypeClass = cef.createElement(SysmlConstants.STEREOTYPE, "MTIP Imported",  "");
		Element stereotype = stereotypeClass.createElement(project,  huddleProfile,  null);
		
		return stereotype;
	}
}
