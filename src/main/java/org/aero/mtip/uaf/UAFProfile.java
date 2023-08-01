package org.aero.mtip.uaf;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class UAFProfile {

	//UAF Profile Stereotypes for Element Identification
	public static Profile UAF_PROFILE = null;
	public static Profile UPDM_CUSTOMIZATION_PROFILE = null;
	
	// Strategic
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
	public static Stereotype ENTERPRISE_GOAL = null;
	
	//Operational
	public static Stereotype CONCEPT_ROLE_STEREOTYPE = null;
	public static Stereotype HIGH_LEVEL_OPERATIONAL_CONCEPT_STEREOTYPE = null;
	public static Stereotype KNOWN_RESOURCE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_AGENT_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ARCHITECTURE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_PERFORMER_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_INTERFACE_STEREOTYPE = null;
	public static Stereotype INFORMATION_ELEMENT_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_CONTROL_FLOW_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_CONNECTOR_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_METHOD_STEREOTYPE = null;
	public static Stereotype PROBLEM_DOMAIN_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_OBJECT_FLOW_STEREOTYPE = null;
	/*public static Stereotype INFORMATION_FLOW_STEREOTYPE = null;
	public static Stereotype OBJECT_FLOW_STEREOTYPE = null;*/
	public static Stereotype ARBITRARY_CONNECTOR_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ROLE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ACTIVITY_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ACTIVITY_ACTION_STEREOTYPE = null;
	public static Stereotype STANDARD_OPERATIONAL_ACTIVITY_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_SIGNAL_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_SIGNAL_PROPERTY_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_EXCHANGE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_MESSAGE_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_PORT_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_CONSTRAINT_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_STATE_DESCRIPTION_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_PARAMETER_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_EXCHANGE_KIND_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ACTION_STEREOTYPE = null;
	public static Stereotype OPERATIONAL_ASSOCIATION_STEREOTYPE = null;
	
	//Resources
	public static Stereotype CAPABILITY_CONFIGURATION_STEREOTYPE = null;
	public static Stereotype NATURAL_RESOURCE_STEREOTYPE = null;
	public static Stereotype RESOURCE_ARCHITECTURE_STEREOTYPE = null;
	public static Stereotype RESOURCE_ARTIFACT_STEREOTYPE = null;
	public static Stereotype SOFTWARE_STEREOTYPE = null;
	public static Stereotype SYSTEM_STEREOTYPE = null;
	public static Stereotype RESOURCE_INTERFACE_STEREOTYPE = null;
	public static Stereotype DATA_ELEMENT_STEREOTYPE = null;
	public static Stereotype TECHNOLOGY_STEREOTYPE = null;
	public static Stereotype WHOLE_LIFE_CONFIGURATION_STEREOTYPE = null;
	public static Stereotype RESOURCE_METHOD_STEREOTYPE = null;
	public static Stereotype RESOURCE_PARAMETER_STEREOTYPE = null;
	public static Stereotype RESOURCE_PORT_STEREOTYPE = null;
	public static Stereotype RESOURCE_ROLE_STEREOTYPE = null;
	public static Stereotype ROLE_KIND_STEREOTYPE = null;
	public static Stereotype RESOURCE_CONNECTOR_STEREOTYPE = null;
	public static Stereotype RESOURCE_EXCHANGE_STEREOTYPE = null;
	public static Stereotype RESOURCE_EXCHANGE_KIND_STEREOTYPE = null;
	public static Stereotype RESOURCE_SIGNAL_STEREOTYPE = null;
	public static Stereotype RESOURCE_SIGNAL_PROPERTY_STEREOTYPE = null;
	public static Stereotype FUNCTION_STEREOTYPE = null;
	public static Stereotype FUNCTION_ACTION_STEREOTYPE = null;
	public static Stereotype FUNCTION_CONTROL_FLOW_STEREOTYPE = null;
	public static Stereotype FUNCTION_OBJECT_FLOW_STEREOTYPE = null;
	public static Stereotype RESOURCE_STATE_DESCRIPTION_STEREOTYPE = null;
	public static Stereotype RESOURCE_MESSAGE_STEREOTYPE = null;
	public static Stereotype RESOURCE_CONSTRAINT_STEREOTYPE = null;
	public static Stereotype FORECAST_STEREOTYPE = null;
	public static Stereotype VERSION_SUCCESSION_STEREOTYPE = null;
	public static Stereotype VERSION_OF_CONFIGURATION_STEREOTYPE = null;
	public static Stereotype WHOLE_LIFE_CONFIGURATION_KIND_STEREOTYPE = null;
	public static Stereotype RESOURCE_ACTION_STEREOTYPE = null;
	
	public static Stereotype ACTUAL_MILESTONE_KIND_STEREOTYPE = null;
	public static Stereotype PROJECT_STEREOTYPE = null;
	public static Stereotype PROJECT_KIND_STEREOTYPE = null;
	public static Stereotype PROJECT_MILESTONE_STEREOTYPE = null;
	public static Stereotype PROJECT_MILESTONE_ROLE_STEREOTYPE = null;
	public static Stereotype PROJECT_ROLE_STEREOTYPE = null;
	public static Stereotype PROJECT_THEME_STEREOTYPE = null;
	public static Stereotype PROJECT_ACTIVITY_STEREOTYPE = null;
	public static Stereotype PROJECT_ACTIVITY_ACTION_STEREOTYPE = null;
	public static Stereotype PROJECT_STATUS_STEREOTYPE = null;
	public static Stereotype ACTUAL_PROJECT_MILESTONE_ROLE_STEREOTYPE = null;
	public static Stereotype ACTUAL_PROJECT_ROLE_STEREOTYPE = null;
	public static Stereotype MILESTONE_DEPENDENCY_STEREOTYPE = null;
	public static Stereotype PROJECT_SEQUENCE_STEREOTYPE = null;
	public static Stereotype STATUS_INDICATORS_STEREOTYPE = null;
	public static Stereotype ACTUAL_PROJECT_STEREOTYPE = null;
	public static Stereotype ACTUAL_PROJECT_MILESTONE_STEREOTYPE = null;
	
	//Dictionary
	public static Stereotype DEFINITION_STEREOTYPE = null;
	public static Stereotype ALIAS_STEREOTYPE = null;
	public static Stereotype INFORMATION_STEREOTYPE = null;
	public static Stereotype SAME_AS_STEREOTYPE = null;
	
	// Actual Resources
	public static Stereotype ACTUAL_ORGANIZATION_STEREOTYPE = null;
	public static Stereotype ACTUAL_PERSON_STEREOTYPE = null;
	public static Stereotype ACTUAL_POST_STEREOTYPE = null;
	public static Stereotype ACTUAL_RESOURCE_STEREOTYPE = null;
	public static Stereotype ACTUAL_RESOURCE_RELATIONSHIP_STEREOTYPE = null;
	public static Stereotype ACTUAL_RESPONSIBILITY_STEREOTYPE = null;
	public static Stereotype ACTUAL_SERVICE_STEREOTYPE = null;
	public static Stereotype FIELDED_CAPABILITY_STEREOTYPE = null;
	public static Stereotype FILLS_POST_STEREOTYPE = null;
	public static Stereotype OWNS_PROCESS_STEREOTYPE = null;
	public static Stereotype PROVIDED_SERVICE_LEVEL_STEREOTYPE = null;
	public static Stereotype PROVIDES_COMPETENCE_STEREOTYPE = null;
	public static Stereotype REQUIRED_SERVICE_LEVEL_STEREOTYPE = null;
	
	// Personnel
	public static Stereotype COMPETENCE_STEREOTYPE = null;
	
	public static Stereotype SECURITY_PROCESS_ACTION_STEREOTYPE = null;
	
	public UAFProfile(Project project) {
		UAF_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UAF);
		UPDM_CUSTOMIZATION_PROFILE = StereotypesHelper.getProfile(project, UAFConstants.UPDM_CUSTOMIZATION_PROFILE);
		STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STRATEGIC_TAXONOMY_PACKAGE, UPDM_CUSTOMIZATION_PROFILE);
		
		ACHIEVED_EFFECT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACHIEVED_EFFECT, UAF_PROFILE);
	    CAPABILITY_FOR_TASK_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY_FOR_TASK, UAF_PROFILE);
	    DESIRED_EFFECT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.DESIRED_EFFECT, UAF_PROFILE);
	    EXHIBITS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.EXHIBITS, UAF_PROFILE);
	    MAPS_TO_CAPABILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.MAPS_TO_CAPABILITY, UAF_PROFILE);
	    ORGANIZATION_IN_ENTERPRISE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ORGANIZATION_IN_ENTERPRISE, UAF_PROFILE);
	
	    //Strategic
	    ACHIEVER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACHIEVER, UAF_PROFILE);
	    ACTUAL_ENDURING_TASK_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.ACTUAL_ENDURING_TASK, UAF_PROFILE);
	    ACTUAL_ENTERPRISE_PHASE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_ENTERPRISE_PHASE, UAF_PROFILE);
	    CAPABILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY, UAF_PROFILE);
	    CAPABILITY_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY_PROPERTY, UAF_PROFILE);
	    DESIRER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.DESIRER, UAF_PROFILE);
	    ENDURING_TASK_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ENDURING_TASK, UAF_PROFILE);
	    ENTERPRISE_PHASE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ENTERPRISE_PHASE, UAF_PROFILE);
	    ENTERPRISE_VISION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ENTERPRISE_VISION, UAF_PROFILE);
	    STRUCTURAL_PART_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STRUCTURAL_PART, UAF_PROFILE);
	    TEMPORAL_PART_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.TEMPORAL_PART, UAF_PROFILE);
	    VISION_STATEMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.VISION_STATEMENT, UAF_PROFILE);
	    WHOLE_LIFE_ENTERPRISE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.WHOLE_LIFE_ENTERPRISE, UAF_PROFILE); 
	    ENTERPRISE_GOAL = StereotypesHelper.getStereotype(project, UAFConstants.ENTERPRISE_GOAL, UAF_PROFILE); 
	    
	    //Operational
	    ARBITRARY_CONNECTOR_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ARBITRARY_CONNECTOR, UAF_PROFILE);
	    CONCEPT_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CONCEPT_ROLE, UAF_PROFILE);
	    HIGH_LEVEL_OPERATIONAL_CONCEPT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.HIGH_LEVEL_OPERATIONAL_CONCEPT, UAF_PROFILE);
	    INFORMATION_ELEMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.INFORMATION_ELEMENT, UAF_PROFILE);
	    KNOWN_RESOURCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.KNOWN_RESOURCE, UAF_PROFILE);
	    OPERATIONAL_ACTIVITY_ACTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ACTIVITY_ACTION, UAF_PROFILE);
	    OPERATIONAL_ACTIVITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ACTIVITY, UAF_PROFILE);
	    OPERATIONAL_AGENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_AGENT, UAF_PROFILE);
	    OPERATIONAL_ARCHITECTURE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ARCHITECTURE, UAF_PROFILE);
	    OPERATIONAL_INTERFACE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_INTERFACE, UAF_PROFILE);
	    OPERATIONAL_METHOD_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_METHOD, UAF_PROFILE);
	    OPERATIONAL_PERFORMER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_PERFORMER, UAF_PROFILE);
	    OPERATIONAL_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ROLE, UAF_PROFILE);
	    OPERATIONAL_SIGNAL_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_SIGNAL_PROPERTY, UAF_PROFILE);
	    OPERATIONAL_SIGNAL_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_SIGNAL, UAF_PROFILE);
	    STANDARD_OPERATIONAL_ACTIVITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STANDARD_OPERATIONAL_ACTIVITY, UAF_PROFILE);
	    PROBLEM_DOMAIN_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROBLEM_DOMAIN, UAF_PROFILE);
	    OPERATIONAL_CONTROL_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_CONTROL_FLOW, UAF_PROFILE);
	    OPERATIONAL_OBJECT_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_OBJECT_FLOW, UAF_PROFILE);
	    OPERATIONAL_CONNECTOR_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.OPERATIONAL_CONNECTOR, UAF_PROFILE);
	    OPERATIONAL_EXCHANGE_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.OPERATIONAL_EXCHANGE, UAF_PROFILE);
	    //OPERATIONAL_MESSAGE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_MESSAGE, UAF_PROFILE);
	    OPERATIONAL_PORT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_PORT, UAF_PROFILE);
	    OPERATIONAL_CONSTRAINT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_CONSTRAINT, UAF_PROFILE);
	    OPERATIONAL_STATE_DESCRIPTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_STATE_DESCRIPTION, UAF_PROFILE);
	    OPERATIONAL_PARAMETER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_PARAMETER, UAF_PROFILE);
	    OPERATIONAL_EXCHANGE_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_EXCHANGE_KIND, UAF_PROFILE);
	    OPERATIONAL_ACTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ACTION, UPDM_CUSTOMIZATION_PROFILE);
	    OPERATIONAL_ASSOCIATION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OPERATIONAL_ASSOCIATION, UPDM_CUSTOMIZATION_PROFILE);
	    
	    /*INFORMATION_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.INFORMATION_FLOW, UAF_PROFILE);
	    OBJECT_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OBJECT_FLOW, UAF_PROFILE);*/
	    	   
	    //Resources
	    CAPABILITY_CONFIGURATION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.CAPABILITY_CONFIGURATION, UAF_PROFILE);
	    NATURAL_RESOURCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.NATURAL_RESOURCE, UAF_PROFILE);
	    RESOURCE_ARCHITECTURE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_ARCHITECTURE, UAF_PROFILE);	    
	    RESOURCE_ARTIFACT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_ARTIFACT, UAF_PROFILE);
	    SOFTWARE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.SOFTWARE, UAF_PROFILE);
	    SYSTEM_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.SYSTEM, UAF_PROFILE);
	    RESOURCE_INTERFACE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_INTERFACE, UAF_PROFILE);
	    DATA_ELEMENT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.DATA_ELEMENT, UAF_PROFILE);
	    TECHNOLOGY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.TECHNOLOGY, UAF_PROFILE);
	    WHOLE_LIFE_CONFIGURATION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.WHOLE_LIFE_CONFIGURATION, UAF_PROFILE);
	    RESOURCE_METHOD_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_METHOD, UAF_PROFILE);
	    RESOURCE_PARAMETER_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_PARAMETER, UAF_PROFILE);
	    RESOURCE_PORT_STEREOTYPE =  StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_PORT, UAF_PROFILE);
	    RESOURCE_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.RESOURCE_ROLE, UAF_PROFILE);
	    ROLE_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ROLE_KIND, UAF_PROFILE);
	    RESOURCE_ACTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_ACTION, UPDM_CUSTOMIZATION_PROFILE);
	    //8/3/2022
	    RESOURCE_CONNECTOR_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_CONNECTOR, UAF_PROFILE);
	    RESOURCE_EXCHANGE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_EXCHANGE, UAF_PROFILE);
	    RESOURCE_EXCHANGE_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_EXCHANGE_KIND, UAF_PROFILE);
	    RESOURCE_SIGNAL_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_SIGNAL, UAF_PROFILE);
	    RESOURCE_SIGNAL_PROPERTY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_SIGNAL_PROPERTY, UAF_PROFILE);
	    //8/15/2022
	    FUNCTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FUNCTION, UAF_PROFILE);
	    FUNCTION_ACTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FUNCTION_ACTION, UAF_PROFILE);
	    FUNCTION_CONTROL_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FUNCTION_CONTROL_FLOW, UAF_PROFILE);
	    FUNCTION_OBJECT_FLOW_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FUNCTION_OBJECT_FLOW, UAF_PROFILE);
	    RESOURCE_STATE_DESCRIPTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_STATE_DESCRIPTION, UAF_PROFILE);
	    //RESOURCE_MESSAGE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_MESSAGE, UAF_PROFILE);
	
	    RESOURCE_CONSTRAINT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.RESOURCE_CONSTRAINT, UAF_PROFILE);
	    FORECAST_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FORECAST, UAF_PROFILE);
	    VERSION_SUCCESSION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.VERSION_SUCCESSION, UAF_PROFILE);
	    VERSION_OF_CONFIGURATION_STEREOTYPE = StereotypesHelper.getStereotype(project,  UAFConstants.VERSION_OF_CONFIGURATION, UAF_PROFILE);
	    WHOLE_LIFE_CONFIGURATION_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.WHOLE_LIFE_CONFIGURATION_KIND, UAF_PROFILE);
	
	    //Projects
	    ACTUAL_MILESTONE_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_MILESTONE_KIND, UAF_PROFILE);
	    PROJECT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT, UAF_PROFILE);
	    PROJECT_KIND_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_KIND, UAF_PROFILE);
	    PROJECT_MILESTONE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_MILESTONE, UAF_PROFILE);
	    PROJECT_MILESTONE_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_MILESTONE_ROLE, UAF_PROFILE);
	    PROJECT_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_ROLE, UAF_PROFILE);
	    PROJECT_THEME_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_THEME, UAF_PROFILE);
	    PROJECT_ACTIVITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_ACTIVITY, UAF_PROFILE);
	    PROJECT_ACTIVITY_ACTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_ACTIVITY_ACTION, UAF_PROFILE);
	    PROJECT_STATUS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_STATUS, UAF_PROFILE);
	    ACTUAL_PROJECT_MILESTONE_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_PROJECT_MILESTONE_ROLE, UAF_PROFILE);
	    ACTUAL_PROJECT_ROLE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_PROJECT_ROLE, UAF_PROFILE);
	    MILESTONE_DEPENDENCY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.MILESTONE_DEPENDENCY, UAF_PROFILE);
	    PROJECT_SEQUENCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROJECT_SEQUENCE, UAF_PROFILE);
	    STATUS_INDICATORS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.STATUS_INDICATORS, UAF_PROFILE);
	    ACTUAL_PROJECT_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_PROJECT, UAF_PROFILE);
	    ACTUAL_PROJECT_MILESTONE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_PROJECT_MILESTONE, UAF_PROFILE);
	    
	    //Dictionary
	    DEFINITION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.DEFINITION, UAF_PROFILE);
	    ALIAS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ALIAS, UAF_PROFILE);
	    INFORMATION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.INFORMATION, UAF_PROFILE);
	    SAME_AS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.SAME_AS, UAF_PROFILE);
	    
	    // Actual Resources
	    ACTUAL_ORGANIZATION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_ORGANIZATION, UAF_PROFILE);
	    ACTUAL_PERSON_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_PERSON, UAF_PROFILE);
	    ACTUAL_POST_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_PERSON, UAF_PROFILE);
	    ACTUAL_RESOURCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_RESOURCE, UAF_PROFILE);
	    ACTUAL_RESOURCE_RELATIONSHIP_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP, UAF_PROFILE);
	    ACTUAL_RESPONSIBILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_RESPONSIBILITY, UAF_PROFILE);
	    ACTUAL_SERVICE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.ACTUAL_SERVICE, UAF_PROFILE);
	    FIELDED_CAPABILITY_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FIELDED_CAPABILITY, UAF_PROFILE);
	    FILLS_POST_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.FILLS_POST, UAF_PROFILE);
	    OWNS_PROCESS_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.OWNS_PROCESS, UAF_PROFILE);
	    PROVIDED_SERVICE_LEVEL_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROVIDED_SERVICE_LEVEL, UAF_PROFILE);
	    PROVIDES_COMPETENCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.PROVIDES_COMPETENCE, UAF_PROFILE);
	    REQUIRED_SERVICE_LEVEL_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.REQUIRED_SERVICE_LEVEL, UAF_PROFILE);
	    
	    // Personnel
	    COMPETENCE_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.COMPETENCE, UAF_PROFILE);
	    
	    //Security
	    SECURITY_PROCESS_ACTION_STEREOTYPE = StereotypesHelper.getStereotype(project, UAFConstants.SECURITY_PROCESS_ACTION, UAF_PROFILE);
	}
}
