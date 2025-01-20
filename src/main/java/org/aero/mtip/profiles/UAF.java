package org.aero.mtip.profiles;

import javax.annotation.CheckForNull;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class UAF {
	static UAF instance = null;
	
	static final String NAME = "UAF";
	static final String PROJECT_NAME = "UAF Profile";
	static final String UPDM_NAME = "UPDM Customization";
	
	Project project;
	Profile uafProfile;
	Profile updmProfile;

	UAF() {
		project = Application.getInstance().getProject();
		uafProfile = StereotypesHelper.getProfile(project, NAME);
		updmProfile = StereotypesHelper.getProfile(project, UPDM_NAME);
	}
	
	Project getProject() {
		return project;
	}
	
	Profile getUafProfile() {
		return uafProfile;
	}
	
	Profile getUpdmProfile() {
		return updmProfile;
	}
	
	public static void clearProfile() {
	  instance = null;
	}
	
	public static UAF getInstance() {
		if (instance == null) {
			instance = new UAF();
		}
		
		return instance;
	}
	
	@CheckForNull
	public static Stereotype getStereotype(String stereotypeName) {
		Stereotype stereotype = StereotypesHelper.getStereotype(getInstance().getProject(), stereotypeName, getInstance().getUafProfile());
		
		if (stereotype != null) {
			return stereotype;
		}
		
		return StereotypesHelper.getStereotype(getInstance().getProject(), stereotypeName, getInstance().getUpdmProfile());
	}
	
	public static boolean isUafProfile(Package profile) {
		if (profile.getName().contentEquals(NAME) 
				|| profile.getName().contentEquals(UPDM_NAME)) {
			return true;
		}
		
		return false;
	}
	
	public static String getProfileModelName() {
	  return PROJECT_NAME;
	}
}

