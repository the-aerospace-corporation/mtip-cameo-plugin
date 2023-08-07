package org.aero.mtip.util;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.constants.SysmlConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class HuddleUtils {
	public static Package createHUDDLEProfile(Project project) {
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonElement profileClass = cef.createElement(SysmlConstants.PROFILE, "HUDDLE Stereotypes",  "");
		Profile huddleProfile = (Profile)profileClass.createElement(project, project.getPrimaryModel(), null);
		
		addHuddleImportedStereotype(project, huddleProfile);
		
		return huddleProfile;
	}
	
	private static Element addHuddleImportedStereotype(Project project, Profile huddleProfile) {
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonElement stereotypeClass = cef.createElement(SysmlConstants.STEREOTYPE, "HUDDLE Imported",  "");
		Element stereotype = stereotypeClass.createElement(project,  huddleProfile,  null);
		
		return stereotype;
	}
}
