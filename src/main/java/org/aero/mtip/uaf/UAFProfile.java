package org.aero.mtip.uaf;

import javax.annotation.CheckForNull;

import org.aero.mtip.profiles.SysML;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class UAFProfile {
	static UAFProfile instance = null;
	
	static final String NAME = "UAF";
	static final String UPDM_NAME = "UPDM Customization";
	
	Project project;
	Profile uafProfile;
	Profile updmProfile;

	UAFProfile() {
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
	
	public static UAFProfile getInstance() {
		if (instance == null || instance.project.getPrimaryModel().getID() != Application.getInstance().getProject().getPrimaryModel().getID()) {
			instance = new UAFProfile();
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
		if (profile.getID() == getInstance().getUafProfile().getID() 
				|| profile.getID() == getInstance().getUpdmProfile().getID()) {
			return true;
		}
		
		return false;
	}
}

