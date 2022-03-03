package uaf;

import org.aero.mtip.util.UAFConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class UAFProfile {

	//UAF Profile Stereotypes for Element Identification
	public static Profile UAF_PROFILE = null;
	public static Profile UPDM_CUSTOMIZATION_PROFILE = null;
	public static Stereotype CAPABILITY_STEREOTYPE = null;
	public static Stereotype STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE = null;
	
	public UAFProfile(Project project) {
		UAF_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UAF);
		UPDM_CUSTOMIZATION_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UPDM_CUSTOMIZATION_PROFILE);
		CAPABILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY, UAF_PROFILE);
		STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STRATEGIC_TAXONOMY_PACKAGE, UPDM_CUSTOMIZATION_PROFILE);
	}
}
