package org.aero.mtip.constants;

import org.apache.commons.lang.ArrayUtils;

public class DoDAFConstants {
	// Capability Viewpoints
	public static final String CV1 = "CV-1";
	public static final String CV2 = "CV-2";
	public static final String CV3 = "CV-3";
	public static final String CV4 = "CV-4";
	public static final String CV5 = "CV-5";
	public static final String CV6 = "CV-6";
	public static final String CV7 = "CV-7";
	
	// Systems Viewpoints
	public static final String SV1 = "SV-1";
	public static final String SV2 = "SV-2";
	public static final String SV3 = "SV-3";
	public static final String SV4 = "SV-4";
	public static final String SV5A = "SV-5a";
	public static final String SV5B = "SV-5b";
	public static final String SV5C = "SV-5c";
	public static final String SV6 = "SV-6";
	public static final String SV7 = "SV-7";
	public static final String SV8 = "SV-8";
	public static final String SV9 = "SV-9";
	public static final String SV10A = "SV-10a";
	public static final String SV10B = "SV-10b";
	public static final String SV10C = "SV-10c";
	
	// Operational Viewpoints
	public static final String OV1 = "OV-1";
	public static final String OV2 = "OV-2";
	public static final String OV3 = "OV-3";
	public static final String OV4 = "OV-4";
	public static final String OV5A = "OV-5a";
	public static final String OV5B = "OV-5b";
	public static final String OV6A = "OV-6a";
	public static final String OV6B = "OV-6b";
	public static final String OV6C = "OV-6c";
	
	// All Viewpoints
	public static final String AV1 = "AV-1";
	public static final String AV2 = "AV-2";
	
	// Data and Information Viewpoints
	public static final String DIV1 = "DIV-1";
	public static final String DIV2 = "DIV-2";
	public static final String DIV3 = "DIV-3";
	
	// Project Viewpoints
	public static final String PV1 = "PV-1";
	public static final String PV2 = "PV-2";
	public static final String PV3 = "PV-3";
	
	// Standards Viewpoints
	public static final String STDV1 = "StdV-1";
	public static final String STDV2 = "StdV-2";
	
	public static final String[] DODAF_DIAGRAMS = {
			CV1,
			CV2,
			CV3,
			CV4,
			CV5,
			CV6,
			CV7,
			SV1,
			SV2,
			SV3,
			SV4,
			SV5A,
			SV5B,
			SV5C,
			SV6,
			SV7,
			SV8,
			SV9,
			SV10A,
			SV10B,
			SV10C,
			OV1,
			OV2,
			OV3,
			OV4,
			OV5A,
			OV5B,
			OV6A,
			OV6B,
			OV6C,
			AV1,
			AV2,
			DIV1,
			DIV2,
			DIV3
	};
	
	public static final String[] CV1_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
			// Dictionary
			UAFConstants.DEFINITION,
			UAFConstants.ALIAS,
			UAFConstants.INFORMATION,
			UAFConstants.SAME_AS,
			
			// CV-1
			UAFConstants.ACTUAL_ENTERPRISE_PHASE,
			UAFConstants.WHOLE_LIFE_ENTERPRISE,
			UAFConstants.ENTERPRISE_PHASE,
			UAFConstants.CAPABILITY,
			UAFConstants.EXHIBITS,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENTERPRISE_GOAL,
			UAFConstants.ENTERPRISE_VISION,
			UAFConstants.VISION_STATEMENT,
			UAFConstants.ORGANIZATION_IN_ENTERPRISE,
			UAFConstants.DESIRED_EFFECT,
			UAFConstants.ACHIEVED_EFFECT,
			UAFConstants.STRUCTURAL_PART,
			UAFConstants.TEMPORAL_PART,
			UAFConstants.ENDURING_TASK,
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY_FOR_TASK,
			
			// Parametric Elements
			//UAFConstants.ACTUAL_CONDITION,
			//UAFConstants.ACTUAL_ENVIRONMENT,
			//UAFConstants.ACTUAL_LOCATION,
			//UAFConstants.CONDITION,
			//UAFConstants.ENVIRONMENT,
			//UAFConstants.LOCATION,
			
			UAFConstants.OPERATIONAL_PERFORMER,
			UAFConstants.OPERATIONAL_ACTIVITY,
			UAFConstants.MAPS_TO_CAPABILITY,
			
			
			
			// Operational
			UAFConstants.OPERATIONAL_PERFORMER,
			UAFConstants.KNOWN_RESOURCE,
			UAFConstants.OPERATIONAL_ARCHITECTURE,
			UAFConstants.OPERATIONAL_ACTIVITY,
			UAFConstants.MAPS_TO_CAPABILITY,
			
			//Resources
			UAFConstants.CAPABILITY_CONFIGURATION,
			UAFConstants.NATURAL_RESOURCE,
			UAFConstants.RESOURCE_ARCHITECTURE,
			UAFConstants.RESOURCE_ARTIFACT,
			UAFConstants.SOFTWARE,
			UAFConstants.SYSTEM,
			UAFConstants.TECHNOLOGY,
//			UAFConstants.ORGANIZATION,
//			UAFConstants.POST,
//			UAFConstants.PERSON,
//			UAFConstants.FUNCTION
			UAFConstants.PROJECT,
//			UAFConstants.ACTUAL_ORGANIZATION,
//			UAFConstants.ACTUAL_PERSON,
//			UAFConstants.ACTUAL_POST,
//			UAFConstants.ACTUAL_RESOURCE,
//			UAFConstants.FIELDED_CAPABILITY,
						
			// Services
//			UAFConstants.SERVICE_SPECIFICATION,
//			UAFConstants.SERVICE_FUNCTION,
			
			// Measurements
//			UAFConstants.MEASUREMENT_SET,
//			UAFConstants.ACTUAL_MEASUREMENT_SET,
//			UAFConstants.ACTUAL_PROPERTY_SET,
			
			// General Types
			SysmlConstants.GENERALIZATION,
			SysmlConstants.COMPOSITION,
			SysmlConstants.AGGREGATION,
			SysmlConstants.DEPENDENCY
	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] CV2_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
			// Dictionary
			UAFConstants.DEFINITION,
			UAFConstants.ALIAS,
			UAFConstants.INFORMATION,
			UAFConstants.SAME_AS,
			
			// CV-2
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK,
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY_FOR_TASK,
			
			// Parametric Elements
			//UAFConstants.ACTUAL_CONDITION,
			//UAFConstants.ACTUAL_ENVIRONMENT,
			//UAFConstants.ACTUAL_LOCATION,
			//UAFConstants.CONDITION,
			//UAFConstants.ENVIRONMENT,
			//UAFConstants.LOCATION,
			
			// Operational
			UAFConstants.OPERATIONAL_PERFORMER,
			UAFConstants.KNOWN_RESOURCE,
			UAFConstants.OPERATIONAL_ARCHITECTURE, // ORGANIZATIONAL Architecture?
			UAFConstants.OPERATIONAL_ACTIVITY,
			UAFConstants.MAPS_TO_CAPABILITY,
			
			//Resources
			UAFConstants.CAPABILITY_CONFIGURATION,
			UAFConstants.NATURAL_RESOURCE,
			UAFConstants.RESOURCE_ARCHITECTURE,
			UAFConstants.RESOURCE_ARTIFACT,
			UAFConstants.SOFTWARE,
			UAFConstants.SYSTEM,
			UAFConstants.TECHNOLOGY,
//			UAFConstants.ORGANIZATION,
//			UAFConstants.POST,
//			UAFConstants.PERSON,
//			UAFConstants.FUNCTION
			UAFConstants.PROJECT,
//			UAFConstants.ACTUAL_ORGANIZATION,
//			UAFConstants.ACTUAL_PERSON,
//			UAFConstants.ACTUAL_POST,
//			UAFConstants.ACTUAL_RESOURCE,
//			UAFConstants.FIELDED_CAPABILITY,
						
			// Services
//			UAFConstants.SERVICE_SPECIFICATION,
//			UAFConstants.SERVICE_FUNCTION,
			
			// Measurements
//			UAFConstants.MEASUREMENT_SET,
//			UAFConstants.ACTUAL_MEASUREMENT_SET,
//			UAFConstants.ACTUAL_PROPERTY_SET,
			
			// General Types
			SysmlConstants.GENERALIZATION,
			SysmlConstants.COMPOSITION,
			SysmlConstants.AGGREGATION,
			SysmlConstants.DEPENDENCY

	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] CV3_TYPES = {
			UAFConstants.CAPABILITY
			// Table
	};
	
	public static final String[] CV4_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			
			// Operational
			UAFConstants.OPERATIONAL_PERFORMER,
			UAFConstants.KNOWN_RESOURCE,
			UAFConstants.OPERATIONAL_ARCHITECTURE,
			UAFConstants.EXHIBITS,
			
			// Resources
			UAFConstants.CAPABILITY_CONFIGURATION,
			UAFConstants.NATURAL_RESOURCE,
			UAFConstants.RESOURCE_ARCHITECTURE,
			UAFConstants.RESOURCE_ARTIFACT,
			UAFConstants.SOFTWARE,
			UAFConstants.SYSTEM,
			UAFConstants.TECHNOLOGY
			
			// Services
//			UAFConstants.SERVICE_SPECIFICATION
			
			// Measurements
//			UAFConstants.MEASUREMENT_SET,
//			UAFConstants.ACTUAL_MEASUREMENT_SET,
//			UAFConstants.ACTUAL_PROPERTY_SET,
	},
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] CV5_TYPES = {
			UAFConstants.CAPABILITY
			// Table
	};
	
	public static final String[] CV6_TYPES = {
			UAFConstants.CAPABILITY
			// Matrix
	};
	
	public static final String[] CV7_TYPES = {
			UAFConstants.CAPABILITY
			// Matrix
	};
	
	public static final String[] DODAF_ELEMENTS = {
	};
	
	public static final String[] DODAF_RELATIONSHIPS = {
	};
	
	
	public static final String[] SV1_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// SV-1
		UAFConstants.CAPABILITY_CONFIGURATION,
		UAFConstants.NATURAL_RESOURCE,
		UAFConstants.RESOURCE_ARCHITECTURE,
		UAFConstants.RESOURCE_ARTIFACT,
		UAFConstants.SOFTWARE,
		UAFConstants.SYSTEM,
		UAFConstants.TECHNOLOGY,
//		UAFConstants.IMPLEMENTS,
		UAFConstants.RESOURCE_INTERFACE,
		UAFConstants.FUNCTION,
		UAFConstants.RESOURCE_ROLE,
		UAFConstants.RESOURCE_CONNECTOR,
		UAFConstants.RESOURCE_PORT,
//		UAFConstants.MEASUREMENT,
		
		//Resource Exchanges
		UAFConstants.RESOURCE_EXCHANGE,
//		UAFConstants.CONTROL,
//		UAFConstants.COMMAND,
		UAFConstants.RESOURCE_SIGNAL,
//		UAFConstants.GEO_POLITICAL_EXTENT_TYPE,
				
		// Parametric Elements
		//UAFConstants.ACTUAL_CONDITION,
		//UAFConstants.ACTUAL_ENVIRONMENT,
		//UAFConstants.ACTUAL_LOCATION,
		//UAFConstants.CONDITION,
		//UAFConstants.ENVIRONMENT,
		//UAFConstants.LOCATION,
		
		// Organizational Resources
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
		UAFConstants.PROJECT,
//		UAFConstants.RESPONSIBILITY,
		
		// Actual Resources
//		UAFConstants.ACTUAL_ORGANIZATION,
//		UAFConstants.ACTUAL_PERSON,
//		UAFConstants.ACTUAL_POST,
//		UAFConstants.ACTUAL_RESOURCE,
//		UAFConstants.FIELDED_CAPABILITY,
//		UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP,
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.MAPS_TO_CAPABILITY,
		UAFConstants.EXHIBITS,
				
		// Operational
		UAFConstants.OPERATIONAL_PERFORMER,
		UAFConstants.KNOWN_RESOURCE,
		UAFConstants.OPERATIONAL_ARCHITECTURE,
//		UAFConstants.IMPLEMENTS,
		UAFConstants.OPERATIONAL_ACTIVITY,
		UAFConstants.OPERATIONAL_INTERFACE,
		
		// Services
//		UAFConstants.SERVICE_SPECIFICATION,
//		UAFConstants.SERVICE_INTERFACE,
//		UAFConstants.SERVICE_FUNCTION,
		
		// Security
//		UAFConstants.RESOURCE_MITIGATION,
//		UAFConstants.SECURITY_ENCLAVE,
//		UAFConstants.SECURITY_CONTROL,
//		UAFConstants.ENHANCED_SECURITY_CONTROL,
//		UAFConstants.PROTECTS,
//		UAFConstants.RISK,
//		UAFConstants.OWNS_RISK,
//		UAFConstants.OWNS_RISK_IN_CONTEXT,
//		UAFConstants.AFFECTS,
//		UAFConstants.AFFECTS_IN_CONTEXT,
//		UAFConstants.SECURITY_PROCESS,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET,
		
		// General
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION
	},
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] SV2_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// SV-2
		UAFConstants.CAPABILITY_CONFIGURATION,
		UAFConstants.NATURAL_RESOURCE,
		UAFConstants.RESOURCE_ARCHITECTURE,
		UAFConstants.RESOURCE_ARTIFACT,
		UAFConstants.SOFTWARE,
		UAFConstants.SYSTEM,
		UAFConstants.TECHNOLOGY,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
//		UAFConstants.IMPLEMENTS,
		UAFConstants.RESOURCE_INTERFACE,
		UAFConstants.FUNCTION,
//		UAFConstants.IS_CAPABLE_TO_PERFORM,
		UAFConstants.RESOURCE_ROLE,
		UAFConstants.RESOURCE_CONNECTOR,
		UAFConstants.RESOURCE_PORT,
//		UAFConstants.MEASUREMENT,
		
		// Resource Exchanges
		UAFConstants.RESOURCE_EXCHANGE,
//		UAFConstants.COMMAND,
//		UAFConstants.CONTROL,
		UAFConstants.DATA_ELEMENT,
		UAFConstants.RESOURCE_SIGNAL,
//		UAFConstants.GEO_POLITICAL_EXTENT_TYPE,
		
		// Parametric Elements
		//UAFConstants.ACTUAL_CONDITION,
		//UAFConstants.ACTUAL_ENVIRONMENT,
		//UAFConstants.ACTUAL_LOCATION,
		//UAFConstants.CONDITION,
		//UAFConstants.ENVIRONMENT,
		//UAFConstants.LOCATION,
		
		// Organizational Resources
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
		UAFConstants.PROJECT,
//		UAFConstants.RESPONSIBILITY,
		
		// Actual Resources
//		UAFConstants.ACTUAL_ORGANIZATION,
//		UAFConstants.ACTUAL_PERSON,
//		UAFConstants.ACTUAL_POST,
//		UAFConstants.ACTUAL_RESOURCE,
//		UAFConstants.FIELDED_CAPABILITY,
//		UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP,
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.MAPS_TO_CAPABILITY,
		UAFConstants.EXHIBITS,
		
		// Operational
		UAFConstants.OPERATIONAL_PERFORMER,
		UAFConstants.KNOWN_RESOURCE,
		UAFConstants.OPERATIONAL_ARCHITECTURE,
		UAFConstants.OPERATIONAL_ACTIVITY,
		UAFConstants.OPERATIONAL_INTERFACE,
		
		// Services
//		UAFConstants.SERVICE_SPECIFCIATION,
//		UAFConstants.SERVICE_INTERFACE,
//		UAFConstants.SERVICE_FUNCTION
		
		// Security
//		UAFConstants.RESOURCE_MITIGATION,
//		UAFConstants.SECURITY_ENCLAVE,
//		UAFConstants.SECURITY_CONTROL,
//		UAFConstants.ENHANCED_SECURITY_CONTROL,
//		UAFConstants.PROTECTS,
//		UAFConstants.RISK,
//		UAFConstants.OWNS_RISK,
//		UAFConstants.OWNS_RISK_IN_CONTEXT,
//		UAFConstants.AFFECTS,
//		UAFConstants.AFFECTS_IN_CONTEXT,
//		UAFConstants.SECURITY_PROCESS,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET
		
	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] SV3_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Matrix
	};
	public static final String[] SV4_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// SV-4
		UAFConstants.FUNCTION,
		UAFConstants.PROJECT_ACTIVITY,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
		UAFConstants.CAPABILITY_CONFIGURATION,
		UAFConstants.NATURAL_RESOURCE,
		UAFConstants.RESOURCE_ARCHITECTURE,
		UAFConstants.RESOURCE_ARTIFACT,
		UAFConstants.SOFTWARE,
		UAFConstants.SYSTEM,
		UAFConstants.TECHNOLOGY,
		UAFConstants.PROJECT,
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
//		UAFConstants.IS_CAPABLE_TO_PERFORM,
//		UAFConstants.CONDITION,
		
		// Resource Exchanges
		UAFConstants.RESOURCE_EXCHANGE,
//		UAFConstants.CONTROL,
//		UAFConstants.COMMAND,
		UAFConstants.DATA_ELEMENT,
//		UAFConstants.GEO_POLITICAL_EXTENT_TYPE
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.MAPS_TO_CAPABILITY,
		
		// Operational
		UAFConstants.OPERATIONAL_PERFORMER,
		UAFConstants.KNOWN_RESOURCE,
		UAFConstants.OPERATIONAL_ACTIVITY
		
		// Services
//		UAFConstants.SERVICE_FUNCTION,
		
		// Security
//		UAFConstants.SECURITY_PROCESS,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET
		
	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] SV5A_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Matrix
	};
	public static final String[] SV5B_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Matrix
	};
	public static final String[] SV6_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Table
	};
	public static final String[] SV7_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Table
	};
	public static final String[] SV8_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Non-Diagram
	};
	public static final String[] SV9_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Table
	};
	public static final String[] SV10A_TYPES = {
		UAFConstants.CAPABILITY_CONFIGURATION
		// Table
	};
	public static final String[] SV10B_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
	}, 
	SysmlConstants.STM_TYPES);
	
	public static final String[] SV10C_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// SV-10c
		UAFConstants.RESOURCE_MESSAGE,
//		UAFConstants.PART,
		// Review this for UAF and DoDAF Types
	}, 
	SysmlConstants.SEQ_TYPES);
	
	public static final String[] OV1_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
			
		// OV-1
		UAFConstants.CONCEPT_ROLE,
//		UAFConstants.CAPABILITY_CONFIGURATION_CONCEPT_ROLE,
//		UAFConstants.CONDITION_CONCEPT_ROLE,
//		UAFConstants.ENVIRONMENT_CONCEPT_ROLE,
//		UAFConstants.ENVIRONEMENT_CONCEPT_ROLE,
//		UAFConstants.LCOATION_CONCEPT_ROLE,
//		UAFConstants.NATURAL_RESOURCE_CONCEPT_ROLE,
//		UAFConstants.PERSON_CONCEPT_ROLE,
//		UAFConstants.RESOURCE_ARTIFACT_CONCEPT_ROLE,
//		UAFConstants.SOFTWARE_CONCEPT_ROLE,
//		UAFConstants.TECNOLOGY_CONCEPT_ROLE,
		UAFConstants.ARBITRARY_CONNECTOR,
	},
	SysmlConstants.IBD_TYPES);
	
	public static final String[] OV2_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// Operational
		UAFConstants.OPERATIONAL_PERFORMER,
		UAFConstants.OPERATIONAL_ARCHITECTURE,
		UAFConstants.KNOWN_RESOURCE,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
		UAFConstants.OPERATIONAL_INTERFACE,
		UAFConstants.OPERATIONAL_PORT,
		UAFConstants.OPERATIONAL_ACTIVITY,
//		UAFConstants.IS_CAPABLE_TO_PERFORM,
		UAFConstants.HIGH_LEVEL_OPERATIONAL_CONCEPT,
		UAFConstants.OPERATIONAL_ROLE,
		UAFConstants.OPERATIONAL_CONNECTOR,
		UAFConstants.PROBLEM_DOMAIN,
//		UAFContants.MEASUREMENT,
		
		// Operational Exchanges
		UAFConstants.OPERATIONAL_EXCHANGE,
		UAFConstants.INFORMATION_ELEMENT,
//		UAFConstants.OEPRATIONAL_SIGNAL,
//		UAFConstants.GEO_POLITICAL_EXTENT_TYPE,
		UAFConstants.CAPABILITY_CONFIGURATION,
		UAFConstants.NATURAL_RESOURCE,
		UAFConstants.RESOURCE_ARCHITECTURE,
		UAFConstants.RESOURCE_ARTIFACT,
		UAFConstants.SOFTWARE,
		UAFConstants.SYSTEM,
		UAFConstants.TECHNOLOGY,
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
		UAFConstants.PROJECT,
		
		// Parametric Elements
		//UAFConstants.ACTUAL_CONDITION,
		//UAFConstants.ACTUAL_ENVIRONMENT,
		//UAFConstants.ACTUAL_LOCATION,
		//UAFConstants.CONDITION,
		//UAFConstants.ENVIRONMENT,
		//UAFConstants.LOCATION,
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.EXHIBITS,
		UAFConstants.MAPS_TO_CAPABILITY,
		
		//
//		UAFConstants.IMPLEMENTS,
		UAFConstants.RESOURCE_EXCHANGE,
//		UAFConstants.RESOURCE_ASSOCIATION,
		UAFConstants.FUNCTION
		
		// Services
//		UAFConstants.SERVICE_SPECIFICATION,
//		UAFConstants.CONSUMES,
//		UAFConstants.SERVICE_FUNCTION,
		
		// Security
//		UAFConstants.OPERATIONAL_MITIGATION,
//		UAFConstants.SECURITY_ENCLAVE,
//		UAFConstants.SECURITL_CONTROL,
//		UAFConstants.ENHANCED_SECURITY_CONTROL,
//		UAFConstants.PROTECTS,
//		UAFConstants.RISK,
//		UAFConstants.OWNS_RISK,
//		UAFConstants.AFFECTS,
//		UAFConstants.OWNS_RISK_IN_CONTEXT,
//		UAFConstants.AFFECTS_IN_CONTEXT,
//		UAFConstants.SECURITY_PROCESS,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET,
	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);

	public static final String[] OV3_TYPES = {
		UAFConstants.OPERATIONAL_PERFORMER
		// Table
	};
	public static final String[] OV4_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// OV-4
//		UAFConstants.COMPETENCE,
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
//		UAFConstants.RESPONSIBILITY,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
//		UAFConstants.COMMAND,
//		UAFConstants.CONTROL,
		UAFConstants.RESOURCE_EXCHANGE,
		UAFConstants.DATA_ELEMENT,
//		UAFConstants.REQUIRES_COMPETENCE,
		UAFConstants.FUNCTION,
//		UAFConstants.IS_CAPABLE_TO_PERFORM,
//		UAFConstants.IMPLEMENTS,
		
		// SV-1
		UAFConstants.RESOURCE_ROLE,
		UAFConstants.RESOURCE_PORT,
		UAFConstants.RESOURCE_CONNECTOR,
//		UAFConstants.MEASUREMENT,
		
		// Actual Organizational Resources
//		UAFConstants.ACTUAL_ORGANIATION,
//		UAFConstants.ACTUAL_PERSON,
//		UAFConstants.ACTUAL_POST,
//		UAFConstants.ACTUAL_RESPONSIBILITY,
//		UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP,
//		UAFConstants.COMPETENCE_TO_CONDUCT,
//		UAFConstants.FILLS_POST,
		SysmlConstants.LINK,
//		UAFConstants.OWNS_PROCESS,
//		UAFConstants.PROVIDES_COMPETENCE,
//		UAFConstants.RESPONSIBLE_FOR,
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.EXHIBITS,
		UAFConstants.MAPS_TO_CAPABILITY,
		
		// Operational
		UAFConstants.OPERATIONAL_PERFORMER,
		UAFConstants.KNOWN_RESOURCE,
		UAFConstants.OPERATIONAL_ACTIVITY,
		UAFConstants.STANDARD_OPERATIONAL_ACTIVITY,
		
		// Resources
		UAFConstants.CAPABILITY_CONFIGURATION,
		UAFConstants.NATURAL_RESOURCE,
		UAFConstants.RESOURCE_ARCHITECTURE,
		UAFConstants.RESOURCE_ARTIFACT,
		UAFConstants.SOFTWARE,
		UAFConstants.SYSTEM,
		UAFConstants.TECHNOLOGY,
		
		// Services
//		UAFConstants.SERVICE_SPECIFICATION,
//		UAFConstants.SERVICE_FUNCTION,
		
		// Security
//		UAFConstants.RESOURCE_MITIGATION,
//		UAFConstants.SECURITY_ENCLAVE,
//		UAFConstants.ENHANCED_SECURITY_CONTROL,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET,
		
	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] OV5A_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// OV-5a
		UAFConstants.OPERATIONAL_ACTIVITY,
		UAFConstants.STANDARD_OPERATIONAL_ACTIVITY,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
		UAFConstants.OPERATIONAL_PERFORMER,
		UAFConstants.KNOWN_RESOURCE,
		UAFConstants.OPERATIONAL_ARCHITECTURE,
//		UAFConstants.IS_CAPABLE_TO_PERFORM,
//		UAFConstants.CONDITION,
		
		// Actual Organizational Resources
//		UAFConstants.ACTUAL_ORGANIZATION,
//		UAFConstants.ACTUAL_PERSON,
//		UAFConstants.ACTUAL_POST,
//		UAFContants.OWNS_PROCESS,
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.MAPS_TO_CAPABILITY,
		UAFConstants.ENDURING_TASK,
//		UAFConstatns.IMPLEMENTS,
		
		// Resources
		UAFConstants.FUNCTION,
		UAFConstants.PROJECT_ACTIVITY,
		
		// Services
//		UAFConstants.SERVICE_SPECIFICATION,
//		UAFConstants.CONSUMES,
		
		// Security
//		UAFConstants.SECURITY_PROCESS,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET,
		
	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	public static final String[] OV5B_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// OV-5b
		UAFConstants.OPERATIONAL_ACTIVITY_ACTION,
		UAFConstants.OPERATIONAL_ACTION,
		UAFConstants.OPERATIONAL_CONTROL_FLOW,
		UAFConstants.OPERATIONAL_OBJECT_FLOW,
		SysmlConstants.INPUTPIN,
		SysmlConstants.OUTPUTPIN,
		UAFConstants.OPERATIONAL_PARAMETER,
		
		// Operational Exchange
		UAFConstants.OPERATIONAL_EXCHANGE,
		
		// Security
//		UAFConstants.SECURITY_PROCESS_ACTION
	}, 
	SysmlConstants.ACT_TYPES);
	public static final String[] OV6A_TYPES = {
		UAFConstants.OPERATIONAL_PERFORMER
		// Table
	};
	public static final String[] OV6B_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
	}, 
	SysmlConstants.STM_TYPES);
	
	public static final String[] OV6C_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// OV-6c
		UAFConstants.OPERATIONAL_MESSAGE,
		UAFConstants.OPERATIONAL_ROLE,
		
		// OPerational Exchange
		UAFConstants.OPERATIONAL_EXCHANGE
		
	}, 
	SysmlConstants.SEQ_TYPES);
	
	public static final String[] AV1_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// AV-1
//		UAFConstants.ARCHITECTURAL_DESCRIPTION,
//		UAFConstants.ARCHITECTURE_REFERENCE,
//		UAFConstants.ARCHITECTURE_METADATA,
//		UAFConstants.METADATA,
		UAFConstants.ENTERPRISE_PHASE,
		UAFConstants.ACTUAL_ENTERPRISE_PHASE,
		UAFConstants.OPERATIONAL_ARCHITECTURE,
		UAFConstants.RESOURCE_ARCHITECTURE,
//		UAFConstants.CONCERN,
//		SysmlConstants.VIEW,
//		SysmlConstants.VIEWPOINT,
		UAFConstants.ARBITRARY_CONNECTOR,
		
		// Stakeholders
//		UAFConstants.ACTUAL_ORGANIZATION,
//		UAFConstants.ACTUAL_PERSON,
//		UAFConstants.ACTUAL_POST,
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
		
		// Conditions
		//UAFConstants.ACTUAL_CONDITION,
		//UAFConstants.ACTUAL_ENVIRONMENT,
		//UAFConstants.ACTUAL_LOCATION,
		//UAFConstants.CONDITION,
		//UAFConstants.ENVIRONMENT,
		//UAFConstants.GEO_POLITICAL_EXTENT_TYPE,
		//UAFConstants.LOCATION,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET
	},
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] AV2_TYPES = {
		UAFConstants.ENTERPRISE_PHASE
		// Table
	};
	
	public static final String[] DIV1_TYPES = (String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// DIV-1
//		UAFConstants.DATA_MODEL,
		UAFConstants.DATA_ELEMENT,
		UAFConstants.INFORMATION_ELEMENT,
		
		// Class Diagram
		SysmlConstants.CLASS,
//		SysmlConstants.STRUCTURED_CLASS,
		SysmlConstants.ENUMERATION,
		SysmlConstants.SIGNAL,
//		SysmlConstants.DATA_TYPE,
//		SysmlConstants.PRIMITIVE_TYPE,
		SysmlConstants.INTERFACE,
		SysmlConstants.PACKAGE,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
		SysmlConstants.INTERFACEREALIZATION,
		SysmlConstants.USAGE,
		SysmlConstants.ABSTRACTION,
		SysmlConstants.COLLABORATION,
		SysmlConstants.INSTANCESPECIFICATION,
		SysmlConstants.LINK
	},
	SysmlConstants.BDD_TYPES);
	
	public static final String[] DIV2_TYPES = DIV1_TYPES;

	public static final String[] DIV3_TYPES = DIV1_TYPES;
	
	public static final String[] PV1_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
		// Dictionary
		UAFConstants.DEFINITION,
		UAFConstants.ALIAS,
		UAFConstants.INFORMATION,
		UAFConstants.SAME_AS,
		
		// PV-1
		UAFConstants.PROJECT,
		UAFConstants.PROJECT_MILESTONE,
		UAFConstants.ACTUAL_PROJECT,
		UAFConstants.PROJECT_SEQUENCE,
		UAFConstants.ACTUAL_PROJECT_MILESTONE,
		UAFConstants.MILESTONE_DEPENDENCY,
		UAFConstants.PROJECT_ACTIVITY,
		UAFConstants.STATUS_INDICATORS,
		SysmlConstants.AGGREGATION,
		SysmlConstants.ASSOCIATION,
		SysmlConstants.COMPOSITION,
		SysmlConstants.GENERALIZATION,
		
		// Project Portfolio
		UAFConstants.PROJECT_ROLE,
		UAFConstants.PROJECT_MILESTONE_ROLE,
		
		// Actual Organizational Resources
//		UAFConstants.ACTUAL_ORGANIZATION,
//		UAFConstants.ACTUAL_PERSON,
//		UAFConstants.ACTUAL_POST,
//		UAFConstants.RESPONSIBLE_FOR,
		
		// Strategy
		UAFConstants.CAPABILITY,
		UAFConstants.MAPS_TO_CAPABILITY,
		UAFConstants.EXHIBITS,
		
		// Resources
		UAFConstants.CAPABILITY_CONFIGURATION,
		UAFConstants.NATURAL_RESOURCE,
		UAFConstants.RESOURCE_ARCHITECTURE,
		UAFConstants.RESOURCE_ARTIFACT,
		UAFConstants.SOFTWARE,
		UAFConstants.SYSTEM,
		UAFConstants.TECHNOLOGY,
//		UAFConstants.ORGANIZATION,
//		UAFConstants.PERSON,
//		UAFConstants.POST,
		UAFConstants.FUNCTION
//		UAFConstants.IS_CAPABLE_TO_PERFORM,
		
		// Measurements
//		UAFConstants.MEASUREMENT_SET,
//		UAFConstants.ACTUAL_MEASUREMENT_SET,
//		UAFConstants.ACTUAL_PROPERTY_SET,
		
	},
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] PV2_TYPES = {
		UAFConstants.PROJECT
		// Gantt Chart
	};
	
	public static final String[] PV3_TYPES = {
		UAFConstants.PROJECT
		// Matrix
	};
	
	public static final String[] STDV1_TYPES = {
//			UAFConstants.STANDARD
			// Table
	};
	
	public static final String[] STDV2_TYPES = {
//			UAFConstants.STANDARD
			// Table
	};
}
