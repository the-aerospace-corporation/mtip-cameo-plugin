package org.aero.mtip.profiles;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class MagicDraw {
	private static MagicDraw instance;
	
	private Project project;
	private Profile profile;
	
	public static final String NAME = "MagicDraw Profile";
	
	static final String AUXILIARY_RESOURCE = "auxiliaryResource";
	static final String TERM_NAME = "Term";
	
	private MagicDraw() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
	}
	
	public static MagicDraw getInstance() {
		if (instance == null || !instance.isCurrentProject(Application.getInstance().getProject())) {
			instance = new MagicDraw();
		}
		
		return instance;
	}
	
	public static Stereotype getAuxiliaryResourceStereotype() {
		return getInstance().getStereotype(AUXILIARY_RESOURCE);
	}
	
	public Stereotype getStereotype(String stereotypeName) {
		return StereotypesHelper.getStereotype(project, stereotypeName, profile);
	}
	
	public boolean isCurrentProject(Project activeProject) {
		if (project != activeProject) {
			return false;
		}
		
		return true;
	}
	
	public static Stereotype getTermStereotype() {
		return getInstance().getStereotype(TERM_NAME);
	}
}
