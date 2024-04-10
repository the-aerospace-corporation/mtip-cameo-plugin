package org.aero.mtip.profiles;

import org.aero.mtip.util.ExportLog;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class MDForSysMLExtensions {
	private static MDForSysMLExtensions instance;
	
	private Project project;
	private Profile profile;
	
	public static final String NAME = "additional_stereotypes";
	
	public MDForSysMLExtensions() {
		project = Application.getInstance().getProject();
		profile = StereotypesHelper.getProfile(project, NAME);
		
		if (profile == null) {
			ExportLog.log("Failed to find MD Customization for SysML Profile.");
		}
	}
	
	public static MDForSysMLExtensions getInstance() {
		if (instance == null || instance.project.getPrimaryModel().getID() != Application.getInstance().getProject().getPrimaryModel().getID()) {
			instance = new MDForSysMLExtensions();
		}
		
		return instance;
	}
	
	public static boolean isProperty(Element element) {
		if(element instanceof Property) {
			if(MDCustomizationForSysMLProfile.isPartProperty(element) 
					|| MDCustomizationForSysMLProfile.isReferenceProperty(element) 
					|| MDCustomizationForSysMLProfile.isValueProperty(element)) {
				return false;
			}
			return true;
		}
		return false;
	}
}