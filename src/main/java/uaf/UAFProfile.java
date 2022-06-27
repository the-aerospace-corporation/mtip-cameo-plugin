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
	
	public static Stereotype ACTUAL_ENTERPRISE_PHASE_STEREOTYPE = null;
	public static Stereotype CAPABILITY_STEREOTYPE = null;
	public static Stereotype CAPABILITY_PROPERTY_STEREOTYPE = null;
	public static Stereotype ENTERPRISE_PHASE_STEREOTYPE = null;
	public static Stereotype ENTERPRISE_VISION_STEREOTYPE = null;
	public static Stereotype STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE = null;
	public static Stereotype WHOLE_LIFE_ENTERPRISE_STEREOTYPE = null;
	public static Stereotype ENDURING_TASK_STEREOTYPE = null;
	public static Stereotype DESIRER_STEREOTYPE = null;
	public static Stereotype VISION_STATEMENT_STEREOTYPE = null;
	public static Stereotype STRUCTURAL_PART_STEREOTYPE = null;
	public static Stereotype TEMPORAL_PART_STEREOTYPE = null;
	public static Stereotype ACTUAL_ENDURING_TASK_STEREOTYPE = null;
	public static Stereotype ACHIEVER_STEREOTYPE = null;
	public static Stereotype CAPABILITY_FOR_TASK_STEREOTYPE = null;
	public static Stereotype DESIRED_EFFECT_STEREOTYPE = null;
	public static Stereotype ACHIEVED_EFFECT_STEREOTYPE = null;
	public static Stereotype EXHIBITS_STEREOTYPE = null;
	public static Stereotype ORGANIZATION_IN_ENTERPRISE_STEREOTYPE = null;
	public static Stereotype MAPS_TO_CAPABILITY_STEREOTYPE = null;
	
	//Operational
	public static Stereotype HIGH_LEVEL_OPERATIONAL_CONCEPT_STEREOTYPE = null;
	public static Stereotype KNOWN_RESOURCE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_AGENT_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ARCHITECTURE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_PERFORMER_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_INTERFACE_STEREOTYPE = null;
	public static Stereotype INFORMATIONAL_ELEMENT_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_CONTROL_FLOW_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_CONNECTOR_STEREOTYPE = null;
	/*public static Stereotype INFORMATION_FLOW_STEREOTYPE = null;
	public static Stereotype OBJECT_FLOW_STEREOTYPE = null;*/
	public static Stereotype OPERATIONAL_ROLE_STEREOTYPE = null;
	
	public UAFProfile(Project project) {
		UAF_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UAF);
		UPDM_CUSTOMIZATION_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UPDM_CUSTOMIZATION_PROFILE);
		
		ACTUAL_ENTERPRISE_PHASE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_ENTERPRISE_PHASE, UAF_PROFILE);
		CAPABILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY, UAF_PROFILE);
		CAPABILITY_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY_PROPERTY, UAF_PROFILE);
		ENTERPRISE_PHASE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ENTERPRISE_PHASE, UAF_PROFILE);
		ENTERPRISE_VISION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ENTERPRISE_VISION, UAF_PROFILE);
		STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STRATEGIC_TAXONOMY_PACKAGE, UPDM_CUSTOMIZATION_PROFILE);
		WHOLE_LIFE_ENTERPRISE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.WHOLE_LIFE_ENTERPRISE, UAF_PROFILE);
	    ENDURING_TASK_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ENDURING_TASK, UAF_PROFILE);
	    DESIRER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.DESIRER, UAF_PROFILE);
	    VISION_STATEMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.VISION_STATEMENT, UAF_PROFILE);
	    STRUCTURAL_PART_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STRUCTURAL_PART, UAF_PROFILE);
	    TEMPORAL_PART_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.TEMPORAL_PART, UAF_PROFILE);
	    ACTUAL_ENDURING_TASK_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.ACTUAL_ENDURING_TASK, UAF_PROFILE);
	    ACHIEVER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACHIEVER, UAF_PROFILE);
	    CAPABILITY_FOR_TASK_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY_FOR_TASK, UAF_PROFILE);
	    DESIRED_EFFECT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.DESIRED_EFFECT, UAF_PROFILE);
	    ACHIEVED_EFFECT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACHIEVED_EFFECT, UAF_PROFILE);
	    EXHIBITS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.EXHIBITS, UAF_PROFILE);
	    ORGANIZATION_IN_ENTERPRISE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ORGANIZATION_IN_ENTERPRISE, UAF_PROFILE);
	    MAPS_TO_CAPABILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.MAPS_TO_CAPABILITY, UAF_PROFILE);
	
	    //Operational
	    HIGH_LEVEL_OPERATIONAL_CONCEPT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.HIGH_LEVEL_OPERATIONAL_CONCEPT, UAF_PROFILE);
	    KNOWN_RESOURCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.KNOWN_RESOURCE, UAF_PROFILE);
	    OPERATIONAL_AGENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_AGENT, UAF_PROFILE);
	    OPERATIONAL_ARCHITECTURE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ARCHITECTURE, UAF_PROFILE);
	    OPERATIONAL_PERFORMER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_PERFORMER, UAF_PROFILE);
	    OPERATIONAL_INTERFACE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_INTERFACE, UAF_PROFILE);
	    INFORMATIONAL_ELEMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.INFORMATIONAL_ELEMENT, UAF_PROFILE);
	
	    /*OPERATIONAL_CONTROL_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_CONTROL_FLOW, UAF_PROFILE);
	    OPERATIONAL_CONNECTOR_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.OPERATIONAL_CONNECTOR, UAF_PROFILE);
	    INFORMATION_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.INFORMATION_FLOW, UAF_PROFILE);
	    OBJECT_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OBJECT_FLOW, UAF_PROFILE);*/
	    OPERATIONAL_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ROLE, UAF_PROFILE);
	}
}
