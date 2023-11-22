package org.aero.mtip.uaf;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.util.CameoUtils;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class UAFProfile {
	private static UAFProfile instance = null;
	
	private Map<String, Stereotype> stereotypesByName = new HashMap<String, Stereotype> ();
	private Map<Stereotype, String> nameFromStereotype = new HashMap<Stereotype, String> ();
	private Project project;
	public Profile UAF_PROFILE = null;
	public Profile UPDM_CUSTOMIZATION_PROFILE = null;

	public UAFProfile(Project project) {
		this.project = project;
		UAF_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UAF);
		UPDM_CUSTOMIZATION_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UPDM_CUSTOMIZATION_PROFILE);
		
		initializeStereotypes();
	}
	
	public static void initialize(Project project) {
		instance = new UAFProfile(project);
	}
	
	public static Stereotype getStereotype(String elementName) {
		return instance.stereotypesByName.get(elementName);
	}
	
	public static String getName(Stereotype stereotype) {
		return instance.nameFromStereotype.get(stereotype);
	}
	
	public static boolean isUafStereotype(Stereotype stereotype) {
		if (instance.nameFromStereotype.keySet().contains(stereotype)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isInitialized(Project project) {
		if (instance == null || !instance.project.equals(project)) {
			return false;
		}
	
		return true;
	}
	
	private void initializeStereotypes() {
		initializeElementStereotypes();
		initializeRelationshipStereotypes();
		
		nameFromStereotype = 
				stereotypesByName.entrySet()
			       .stream()
			       .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
	}
	
	private void initializeElementStereotypes() {
		for (String uafElement : UAFConstants.UAF_ELEMENTS) {
			Stereotype stereotype = StereotypesHelper.getStereotype(project, uafElement, UAF_PROFILE);
			
			if (stereotype != null) {
				stereotypesByName.put(uafElement, stereotype);
				continue;
			}
			
			CameoUtils.logGUI(String.format("Failed to initialize stereotype for element %s.", uafElement));
		}
	}
	
	private void initializeRelationshipStereotypes() {
		for (String uafElement : UAFConstants.UAF_RELATIONSHIPS) {
			Stereotype stereotype = StereotypesHelper.getStereotype(project, uafElement, UAF_PROFILE);
			
			if (stereotype != null) {
				stereotypesByName.put(uafElement, stereotype);
				continue;
			}
			
			CameoUtils.logGUI(String.format("Failed to initialize stereotype for relationship %s.", uafElement));
		}
	}
}
