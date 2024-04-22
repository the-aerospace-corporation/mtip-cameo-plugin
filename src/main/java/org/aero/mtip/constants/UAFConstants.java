package org.aero.mtip.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UAFConstants {
	public static final String UAF = "UAF";
	public static final String METAMODEL_PREFIX = "uaf";
	public static final String UAF_PROFILE_NAME = "UAF";
	public static final String UPDM_CUSTOMIZATION_PROFILE = "UPDM Customization";
	// Strategic
	public static final String ACTUAL_ENTERPRISE_PHASE = "ActualEnterprisePhase";
	public static final String CAPABILITY = "Capability";
	public static final String CAPABILITY_PROPERTY = "CapabilityProperty";
	public static final String STRATEGIC_TAXONOMY_PACKAGE = "Strategic Taxonomy Package";
	public static final String ENTERPRISE_PHASE = "EnterprisePhase";
	public static final String ENTERPRISE_VISION = "EnterpriseVision";
	public static final String WHOLE_LIFE_ENTERPRISE = "WholeLifeEnterprise";
	public static final String ENDURING_TASK = "EnduringTask";
	public static final String DESIRER = "Desirer";
	public static final String VISION_STATEMENT = "VisionStatement";
	public static final String STRUCTURAL_PART = "StructuralPart";
	public static final String TEMPORAL_PART = "TemporalPart";
	public static final String ACTUAL_ENDURING_TASK = "ActualEnduringTask";
	public static final String ACHIEVER = "Achiever";
	public static final String CAPABILITY_FOR_TASK = "CapabilityForTask";
	public static final String DESIRED_EFFECT = "DesiredEffect";
	public static final String ACHIEVED_EFFECT = "AchievedEffect";
	public static final String EXHIBITS = "Exhibits";
	public static final String ORGANIZATION_IN_ENTERPRISE = "OrganizationInEnterprise";
	public static final String MAPS_TO_CAPABILITY = "MapsToCapability";
	public static final String ENTERPRISE_GOAL = "EnterpriseGoal";
	
	//Operational
	public static final String CONCEPT_ROLE = "ConceptRole";
	public static final String HIGH_LEVEL_OPERATIONAL_CONCEPT ="HighLevelOperationalConcept";
	public static final String KNOWN_RESOURCE="KnownResource";
	public static final String OPERATIONAL_AGENT = "OperationalAgent";
	public static final String OPERATIONAL_ARCHITECTURE = "OperationalArchitecture";
	public static final String OPERATIONAL_PERFORMER = "OperationalPerformer";
	public static final String OPERATIONAL_INTERFACE = "OperationalInterface";
	public static final String INFORMATION_ELEMENT = "InformationElement";
	public static final String OPERATIONAL_CONTROL_FLOW = "OperationalControlFlow";
	public static final String OPERATIONAL_CONNECTOR = "OperationalConnector";
	public static final String OPERATIONAL_ROLE = "OperationalRole";
	public static final String OPERATIONAL_ACTIVITY = "OperationalActivity";
	public static final String OPERATIONAL_ACTIVITY_ACTION = "OperationalActivityAction";
	public static final String STANDARD_OPERATIONAL_ACTIVITY = "StandardOperationalActivity";
	public static final String OPERATIONAL_SIGNAL = "OperationalSignal";
	public static final String OPERATIONAL_SIGNAL_PROPERTY = "OperationalSignalProperty";
	public static final String ARBITRARY_CONNECTOR = "ArbitraryConnector";
	public static final String OPERATIONAL_METHOD = "OperationalMethod";
	public static final String PROBLEM_DOMAIN = "ProblemDomain";
	public static final String OPERATIONAL_OBJECT_FLOW = "OperationalObjectFlow";
	public static final String OPERATIONAL_EXCHANGE = "OperationalExchange";
	public static final String OPERATIONAL_MESSAGE = "OperationalMessage";
	public static final String OPERATIONAL_PORT = "OperationalPort";
	public static final String OPERATIONAL_CONSTRAINT = "OperationalConstraint";
	public static final String OPERATIONAL_STATE_DESCRIPTION = "OperationalStateDescription";
	public static final String OPERATIONAL_PARAMETER = "OperationalParameter";
	public static final String OPERATIONAL_EXCHANGE_KIND = "OperationalExchangeKind";
	public static final String OPERATIONAL_ACTION = "OperationalAction";
	public static final String OPERATIONAL_ASSOCIATION = "OperationalAssociation";
	public static final String OPERATIONAL_CONNECTIVITY = "OperationalConnectivity";
	public static final String OPERATIONAL_CONSTRAINTS_DEFINITION = "OperationalConstraintsDefinition";
	public static final String OPERATIONAL_FREE_FORM_TAXONOMY = "OperationalFreeFormTaxonomy";
	
	public static final String OPERATIONAL_STRUCTURE = "OperationalStructure";
	public static final String OPERATIONAL_TAXONOMY = "OperationalTaxonomy";
	public static final String OPERATIONAL_HIGH_LEVEL_TAXONOMY = "OperationalHighLevelTaxonomy";
	
	public static final String OPERATIONAL_INTERACTION_SCENARIOS = "OperationalInteractionScenarios";
	public static final String OPERATIONAL_INTERNAL_CONNECTIVITY = "OperationalInternalConnectivity";
	public static final String OPERATIONAL_PARAMETRIC = "OperationalParametric";
	public static final String OPERATIONAL_STATES = "OperationalStates";

	/*public static final String INFORMATION_FLOW = "InformationFlow";
	public static final String OBJECT_FLOW = "ObjectFlow";*/
	
	//Resources
	public static final String CAPABILITY_CONFIGURATION = "CapabilityConfiguration";
	public static final String NATURAL_RESOURCE = "NaturalResource";
	public static final String RESOURCE_ARCHITECTURE = "ResourceArchitecture";
	public static final String RESOURCE_ARTIFACT = "ResourceArtifact";
	public static final String SOFTWARE = "Software";
	public static final String SYSTEM = "System";
	public static final String RESOURCE_INTERFACE = "ResourceInterface";
	public static final String DATA_ELEMENT = "DataElement";
	public static final String TECHNOLOGY = "Technology";
	public static final String WHOLE_LIFE_CONFIGURATION = "WholeLifeConfiguration";
	public static final String RESOURCE_METHOD = "ResourceMethod";
	public static final String RESOURCE_PARAMETER = "ResourceParameter";
	public static final String RESOURCE_PORT = "ResourcePort";
	public static final String RESOURCE_ROLE = "ResourceRole";
	public static final String ROLE_KIND = "RoleKind";
	public static final String RESOURCE_CONNECTOR = "ResourceConnector";
	public static final String RESOURCE_EXCHANGE = "ResourceExchange";
	public static final String RESOURCE_EXCHANGE_KIND = "ReourceExchangeKind";
	public static final String RESOURCE_SIGNAL = "ResourceSignal";
	public static final String RESOURCE_SIGNAL_PROPERTY = "ResourceSignalProperty";
	public static final String FUNCTION = "Function";
	public static final String FUNCTION_ACTION = "FunctionAction";
	public static final String FUNCTION_CONTROL_FLOW = "FunctionControlFlow";
	public static final String FUNCTION_OBJECT_FLOW = "FunctionObjectFlow";
	public static final String RESOURCE_STATE_DESCRIPTION = "ResourceStateDescription";
	public static final String RESOURCE_MESSAGE = "ResourceMessage";
	public static final String RESOURCE_CONSTRAINT = "ResourceConstraint";
	public static final String FORECAST = "Forecast";
	public static final String VERSION_SUCCESSION = "VersionSuccession";
	public static final String VERSION_OF_CONFIGURATION = "VersionOfConfiguration";
	public static final String WHOLE_LIFE_CONFIGURATION_KIND = "WholeLifeConfigurationKind";
	public static final String RESOURCE_ACTION = "ResourceAction";
	
	//Projects
	public static final String ACTUAL_MILESTONE_KIND = "ActualMilestoneKind";
	public static final String PROJECT ="Project";
	public static final String PROJECT_KIND = "ProjectKind";
	public static final String PROJECT_MILESTONE = "ProjectMilestone";
	public static final String PROJECT_MILESTONE_ROLE = "ProjectMilestoneRole";
	public static final String PROJECT_ROLE = "ProjectRole";
	public static final String PROJECT_THEME = "ProjectTheme";
	public static final String PROJECT_ACTIVITY = "ProjectActivity";
	public static final String PROJECT_ACTIVITY_ACTION = "ProjectActivityAction";
	public static final String PROJECT_STATUS = "ProjectStatus";
	public static final String ACTUAL_PROJECT_MILESTONE_ROLE = "ActualProjectMileStone";
	public static final String ACTUAL_PROJECT_ROLE = "ActualProjectRole";
	public static final String MILESTONE_DEPENDENCY = "MilestoneDependency";
	public static final String PROJECT_SEQUENCE = "ProjectSequence";
	public static final String STATUS_INDICATORS = "StatusIndicators";
	public static final String ACTUAL_PROJECT = "ActualProject";
	public static final String ACTUAL_PROJECT_MILESTONE = "ActualProjectMilestone";
	
	
	//Dictionary
	public static final String DEFINITION = "Definition";
	public static final String ALIAS = "Alias";
		//this is a meta data
	public static final String INFORMATION = "Information";
	public static final String SAME_AS = "SameAs";
	
	//Security
	public static final String ACTUAL_RISK = "ActualRisk";
	public static final String AFFECTS = "Affects";
	public static final String AFFECTS_IN_CONTEXT = "AffectsInContext";
	public static final String ENHANCES = "Enhances";
	public static final String ENHANCED_SECURITY_CONTROL = "EnhancedSecurityControl";
	public static final String MITIGATES = "Mitigates";
	public static final String OPERATIONAL_MITIGATION = "OperationalMitigation";
	public static final String OWNS_RISK = "OwnsRisk";
	public static final String OWNS_RISK_IN_CONTEXT = "OwnsRiskInContext";
	public static final String PROTECTS = "Protects";
	public static final String PROTECTS_IN_CONTEXT = "ProtectsInContext";
	public static final String RESOURCE_MITIGATION = "ResourceMitigation";
	public static final String RISK = "Risk";
	public static final String SECURITY_CONSTRAINT = "SecurityConstraint";
	public static final String SECURITY_CONTROL = "SecurityControl";
	public static final String SECURITY_CONTROL_FAMILY = "SecurityControlFamily";
	public static final String SECURITY_ENCLAVE = "SecurityEnclave";
	public static final String SECURITY_PROCESS = "SecurityProcess";
	public static final String SECURITY_PROCESS_ACTION = "SecurityProcessAction";

	//Services
	public static final String CONSUMES = "Consumes";
	public static final String SERVICE_CONNECTOR = "ServiceConnector";
	public static final String SERVICE_FUNCTION = "ServiceFunction";
	public static final String SERVICE_FUNCTION_ACTION = "ServiceFunctionAction";
	public static final String SERVICE_INTERFACE = "ServiceInterface";
	public static final String SERVICE_MESSAGE = "ServiceMessage";
	public static final String SERVICE_METHOD = "ServiceMethod";
	public static final String SERVICE_PARAMETER = "ServiceParameter";
	public static final String SERVICE_POLICY = "ServicePolicy";
	public static final String SERVICE_PORT = "ServicePort";
	public static final String SERVICE_SPECIFICATION = "ServiceSpecification";
	public static final String SERVICE_SPECIFICATION_ROLE = "ServiceSpecificationRole";
	public static final String SERVICE_STATE_DESCRIPTION = "ServiceStateDescription";
	
	//Standards
	public static final String PROTOCOL = "Protocol";
	public static final String PROTOCOL_STACK = "ProtocolStack";
	public static final String STANDARD = "Standard";
	public static final String PROTOCOL_LAYER = "ProtocolLayer";
	
	//Parameters
	public static final String ACTUAL_CONDITION = "ActualCondition";
	public static final String ACTUAL_ENVIRONMENT = "ActualEnvironment";
	public static final String ACTUAL_LOCATION = "ActualLocation";
	public static final String ACTUAL_PROPERTY_SET = "ActualPropertySet";
	public static final String ACTUAL_MEASUREMENT_SET = "ActualMeasurementSet";
	public static final String ENVIRONMENT_PROPERTY = "EnvironmentProperty";
	public static final String ACTUAL_MEASUREMENT = "ActualMeasurement";
	public static final String ENVIRONMENT = "Environment";
	public static final String ENVIRONMENT_KIND = "EnvironmentKind";
	public static final String CONDITION = "Condition";
	public static final String GEO_POLITICAL_EXTENT_TYPE = "GeoPoliticalExtentType";
	public static final String LOCATION = "Location";
	public static final String MEASUREMENT_SET = "MeasurementSet";
	public static final String MEASUREMENT = "Measurement";
	
	//Metadata
	public static final String METADATA="Metadata";
	public static final String IMPLEMENTS = "Implements";
	public static final String PERFORMS_IN_CONTEXT = "PerformsInContext";
	public static final String DATA_MODEL_KIND = "DataModelKind";
	
	/*public static final String INFORMATION_FLOW = "InformationFlow";
	public static final String OBJECT_FLOW = "ObjectFlow";*/
	
	// Actual Resources
	public static final String ACTUAL_ORGANIZATION = "ActualOrganization";
	public static final String ACTUAL_PERSON = "ActualPerson";
	public static final String ACTUAL_POST = "ActualPost";
	public static final String ACTUAL_RESOURCE = "ActualResource";
	public static final String ACTUAL_RESOURCE_RELATIONSHIP = "ActualResourceRelationship";
	public static final String ACTUAL_RESPONSIBILITY = "ActualResponsibility";
	public static final String ACTUAL_SERVICE = "ActualService";
	public static final String FIELDED_CAPABILITY = "FieldedCapability";
	public static final String FILLS_POST = "FillsPost";
	public static final String OWNS_PROCESS = "OwnsProcess";
	public static final String PROVIDED_SERVICE_LEVEL = "ProvidedServiceLevel";
	public static final String PROVIDES_COMPETENCE = "ProvidesCompetence";
	public static final String REQUIRED_SERVICE_LEVEL = "RequiredServiceLevel";
	
	// Personnel
	public static final String COMMAND = "Command";
	public static final String COMPETENCE = "Competence";
	public static final String COMPETENCE_FOR_ROLE = "CompetenceForRole";
	public static final String COMPETENCE_TO_CONDUCT = "CompetenceToConduct";
	public static final String CONTROL = "Control";
	public static final String ORGANIZATION = "Organization";
	public static final String PERSON = "Person";
	public static final String POST = "Post";
	public static final String REQUIRES_COMPETENCE = "RequiresCompetence";
	public static final String RESPONSIBILITY = "Responsibility";

	// Diagrams
	// Actual Resources Diagrams
	public static final String ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM = "ActualResourcesConnectivity";
	public static final String ACTUAL_RESOURCES_STRUCTURE_DIAGRAM = "ActualResourcesStructure";
	
	// Operational Diagrams
	public static final String OPERATIONAL_PROCESS_FLOW = "OperationalProcessFlow";
	public static final String OPERATIONAL_PROCESSES_DIAGRAM = "OperationalProcesses";
	
	// Personnel Diagrams
	public static final String PERSONNEL_CONNECTIVITY_DIAGRAM = "PersonnelConnectivity";
	public static final String PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM = "PersonnelInteractionScenarios";
	public static final String PERSONNEL_PROCESSES_DIAGRAM = "PersonnelProcesses";
	public static final String PERSONNEL_PROCESSES_FLOW_DIAGRAM = "PersonnelProcessesFlow";
	public static final String PERSONNEL_STATES_DIAGRAM = "PersonnelStates";
	public static final String PERSONNEL_STRUCTURE_DIAGRAM = "PersonnelStructure";
	public static final String PERSONNEL_TAXONOMY_DIAGRAM = "PersonnelTaxonomy";
	
	//Projects Diagrams
	public static final String PROJECTS_TAXONOMY_DIAGRAM = "ProjectsTaxonomy";
	public static final String PROJECTS_STRUCTURE_DIAGRAM = "ProjectsStructure";
	public static final String PROJECTS_CONNECTIVITY_DIAGRAM = "ProjectsConnectivity";
	public static final String PROJECTS_PROCESSES_DIAGRAM = "ProjectsProcesses";

	// Resource Diagrams
	public static final String RESOURCES_CONNECTIVITY_DIAGRAM = "ResourcesConnectivity";
	public static final String RESOURCES_INTERACTION_SCENARIOS_DIAGRAM ="ResourcesInteractionScenarios";
	public static final String RESOURCES_PROCESSES_DIAGRAM = "ResourcesProcesses";
	public static final String RESOURCES_PROCESS_FLOW = "ResourcesProcessFlow";
	public static final String RESOURCES_STATES_DIAGRAM = "ResourcesStates";
	public static final String RESOURCES_STRUCTURE_DIAGRAM = "ResourcesStructure";
	public static final String RESOURCES_TAXONOMY_DIAGRAM = "ResourcesTaxonomy";
	
	// Security Diagrams
	public static final String SECURITY_TAXONOMY_DIAGRAM = "SecurityTaxonomy";
	public static final String SECURITY_STRUCTURE_DIAGRAM = "SecurityStructure";
	public static final String SECURITY_CONNECTIVITY_DIAGRAM = "SecurityConnectivity";
	public static final String SECURITY_PROCESSES_DIAGRAM ="SecurityProcesses";
	public static final String SECURITY_PROCESSES_FLOW_DIAGRAM = "SecurityProcessesFlow";
	public static final String SECURITY_CONSTRAINTS_DIAGRAM = "SecurityConstraints";
	
	// Services Diagrams
	public static final String SERVICES_CONNECTIVITY_DIAGRAM = "ServicesConnectivity";
	public static final String SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM = "ServicesConstraintsDefinition";
	public static final String SERVICES_INTERACTION_SCENARIOS_DIAGRAM = "ServicesInteractionScenarios";
	public static final String SERVICES_PROCESSES_DIAGRAM = "ServicesProcesses";
	public static final String SERVICES_STATES_DIAGRAM = "ServicesStates";
	public static final String SERVICES_STRUCTURE_DIAGRAM = "ServicesStructure";
	public static final String SERVICES_TAXONOMY_DIAGRAM = "ServicesTaxonomy";
	
	// Strategic Diagrams
	public static final String STRATEGIC_TAXONOMY_DIAGRAM = "StrategicTaxonomy";
	public static final String STRATEGIC_STRUCTURE_DIAGRAM = "StrategicStructure";
	public static final String STRATEGIC_CONNECTIVITY_DIAGRAM = "StrategicConnectivity";
	public static final String STRATEGIC_STATES_DIAGRAM = "StrategicStates";
	public static final String STRATEGIC_CONSTRAINTS_DIAGRAM = "StrategicConstraints";
	
	//Standards Diagrams
	public static final String STANDARDS_TAXONOMY_DIAGRAM = "StandardsTaxonomy";
	public static final String STANDARDS_STRUCTURE_DIAGRAM = "StandardsStructure";
	
	public static final String ENVIRONMENT_DIAGRAM = "ParametersEnvironment";
	
	//Summary and Overview
	public static final String SUMMARY_AND_OVERVIEW_DIAGRAM ="SummaryandOverview";
	
	
	// Diagram Stereotypes String in UAF/UPDM Profiles
	public static final String STRATEGIC_TAXONOMY_STEREOTYPE = "St-Tx Strategic Taxonomy"; // Contained in "UPDM Customization" Profile
	
	// Uncategorized
	public static final String IS_CAPABLE_TO_PERFORM = "IsCapableToPerform";
	
	public static final String[] UAF_ELEMENT_VALUES = {
			ACHIEVER,
			ACTUAL_ENDURING_TASK,
			ACTUAL_ENTERPRISE_PHASE,
			CAPABILITY,
			CAPABILITY_PROPERTY,
			DESIRER,
			ENDURING_TASK,
			ENTERPRISE_GOAL,
			ENTERPRISE_PHASE,
			ENTERPRISE_VISION,
			WHOLE_LIFE_ENTERPRISE,
			STRATEGIC_TAXONOMY_PACKAGE,
			STRUCTURAL_PART,
			TEMPORAL_PART,
			VISION_STATEMENT,
			
			//Operational
			CONCEPT_ROLE,
			HIGH_LEVEL_OPERATIONAL_CONCEPT,
			INFORMATION_ELEMENT,
			KNOWN_RESOURCE,
			OPERATIONAL_ACTIVITY,
			OPERATIONAL_ACTIVITY_ACTION,
			OPERATIONAL_AGENT,
			OPERATIONAL_ARCHITECTURE,
			OPERATIONAL_INTERFACE,
			STANDARD_OPERATIONAL_ACTIVITY,
			OPERATIONAL_PERFORMER,
			OPERATIONAL_ROLE,
			OPERATIONAL_SIGNAL,
			OPERATIONAL_SIGNAL_PROPERTY,
			OPERATIONAL_METHOD,
			PROBLEM_DOMAIN,
			OPERATIONAL_PORT,
			OPERATIONAL_CONSTRAINT,
			OPERATIONAL_STATE_DESCRIPTION,
			OPERATIONAL_PARAMETER,
			OPERATIONAL_EXCHANGE_KIND,
			OPERATIONAL_ACTION,
			
			//Resources
			CAPABILITY_CONFIGURATION,
			NATURAL_RESOURCE,
			RESOURCE_ARCHITECTURE,
			RESOURCE_ARTIFACT,
			SOFTWARE,
			SYSTEM,
			RESOURCE_INTERFACE,
			DATA_ELEMENT,
			TECHNOLOGY,
			WHOLE_LIFE_CONFIGURATION,
			RESOURCE_METHOD,
			RESOURCE_PARAMETER,
			RESOURCE_PORT,
			RESOURCE_ROLE,
			ROLE_KIND,
			RESOURCE_EXCHANGE_KIND,
			RESOURCE_SIGNAL,
			RESOURCE_SIGNAL_PROPERTY,
			FUNCTION,
			FUNCTION_ACTION,
			RESOURCE_STATE_DESCRIPTION,
			RESOURCE_CONSTRAINT,
			VERSION_OF_CONFIGURATION,
			WHOLE_LIFE_CONFIGURATION_KIND,
			RESOURCE_ACTION,
			
			//Projects
			ACTUAL_MILESTONE_KIND,
			PROJECT,
			PROJECT_KIND,
			PROJECT_MILESTONE,
			PROJECT_MILESTONE_ROLE,
			PROJECT_ROLE,
			PROJECT_THEME,
			PROJECT_ACTIVITY,
			PROJECT_ACTIVITY_ACTION,
			PROJECT_STATUS,
			ACTUAL_PROJECT_MILESTONE_ROLE,
			ACTUAL_PROJECT_ROLE,
			STATUS_INDICATORS,
			ACTUAL_PROJECT,
			ACTUAL_PROJECT_MILESTONE,
			
			//Dictionary
			DEFINITION,
			ALIAS,
			INFORMATION,
			OPERATIONAL_PORT,
			
			// Actual Resources
			ACTUAL_ORGANIZATION,
			ACTUAL_PERSON,
			ACTUAL_POST,
			ACTUAL_RESOURCE,
			ACTUAL_RESPONSIBILITY,
			ACTUAL_SERVICE,
			FIELDED_CAPABILITY,
			PROVIDED_SERVICE_LEVEL,
			REQUIRED_SERVICE_LEVEL,
			
			// Personnel
			COMPETENCE,
			ORGANIZATION,
			PERSON,
			POST,
			RESPONSIBILITY,
			
			//Standards
			PROTOCOL,
			PROTOCOL_STACK,
			STANDARD,
			PROTOCOL_LAYER,
			
			// Personnel
			COMPETENCE,
			ORGANIZATION,
			PERSON,
			POST,
			RESPONSIBILITY,
			
			//Parameters
			ACTUAL_CONDITION,
			ACTUAL_ENVIRONMENT,
			ACTUAL_LOCATION,
			ACTUAL_MEASUREMENT_SET,
			ACTUAL_PROPERTY_SET,
			ENVIRONMENT_PROPERTY,
			ACTUAL_MEASUREMENT,
			ENVIRONMENT,
			
			//Metadata
			METADATA,
			DATA_MODEL_KIND,
			
			// Security
			ACTUAL_RISK,
			ENHANCED_SECURITY_CONTROL,
			OPERATIONAL_MITIGATION,
			RESOURCE_MITIGATION,
			RISK,
			SECURITY_CONSTRAINT,
			SECURITY_CONTROL,
			SECURITY_CONTROL_FAMILY,
			SECURITY_ENCLAVE,
			SECURITY_PROCESS,
			SECURITY_PROCESS_ACTION,	
			
			// Services
			SERVICE_STATE_DESCRIPTION,
			SERVICE_FUNCTION,
			SERVICE_INTERFACE,
			SERVICE_METHOD,
			SERVICE_PARAMETER,
			SERVICE_POLICY,
			SERVICE_PORT,
			SERVICE_SPECIFICATION,
			SERVICE_SPECIFICATION_ROLE,
			
			
			OPERATIONAL_PORT

	};
	
	public static final String[] UAF_RELATIONSHIP_VALUES = {
			//STRATEGIC
			ACHIEVED_EFFECT,
			CAPABILITY_FOR_TASK,
			DESIRED_EFFECT,
			EXHIBITS,
			ORGANIZATION_IN_ENTERPRISE,
			MAPS_TO_CAPABILITY,
			
			//OPERATIONAL
			/*
			OPERATIONAL_CONNECTOR,
			INFORMATION_FLOW,
			OBJECT_FLOW*/
			OPERATIONAL_CONNECTOR,
			OPERATIONAL_CONTROL_FLOW,
			OPERATIONAL_OBJECT_FLOW,
			ARBITRARY_CONNECTOR,
			OPERATIONAL_EXCHANGE,
			OPERATIONAL_ASSOCIATION,

			//OPERATIONAL_MESSAGE,
			//RESOURCES
			RESOURCE_CONNECTOR,
			RESOURCE_EXCHANGE,
			FUNCTION_CONTROL_FLOW,
			FUNCTION_OBJECT_FLOW,
			//RESOURCE_MESSAGE,
			FORECAST,
			VERSION_SUCCESSION,
			
			//PROJECT
			MILESTONE_DEPENDENCY,
			PROJECT_SEQUENCE,
			
			// Actual Resources
			ACTUAL_RESOURCE_RELATIONSHIP,
			FILLS_POST,
			OWNS_PROCESS,
			PROVIDES_COMPETENCE,
			
			// Personnel
			COMMAND,
			COMPETENCE_FOR_ROLE,
			COMPETENCE_TO_CONDUCT,
			CONTROL,
			REQUIRES_COMPETENCE,
			
			//Dictionary
			SAME_AS,
			
			// Security
			AFFECTS,
			AFFECTS_IN_CONTEXT,
			ENHANCES,
			MITIGATES,
			OWNS_RISK,
			OWNS_RISK_IN_CONTEXT,
			PROTECTS,
			PROTECTS_IN_CONTEXT,
			
			//Services
			CONSUMES,
			SERVICE_CONNECTOR,
			SERVICE_MESSAGE, 
			
			//MetaData
			PERFORMS_IN_CONTEXT,
			
			IMPLEMENTS,
			
			// Uncategorized
			IS_CAPABLE_TO_PERFORM
	};
	
	public static final String[] UAF_DIAGRAM_VALUES = {
			// Actual Resources
			ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM,
			ACTUAL_RESOURCES_STRUCTURE_DIAGRAM,
			
			// Personnel
			PERSONNEL_CONNECTIVITY_DIAGRAM,
			PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM,
			PERSONNEL_PROCESSES_DIAGRAM,
			PERSONNEL_PROCESSES_FLOW_DIAGRAM,
			PERSONNEL_STATES_DIAGRAM,
			PERSONNEL_STRUCTURE_DIAGRAM,
			PERSONNEL_TAXONOMY_DIAGRAM,
			
			//Resources
			RESOURCES_CONNECTIVITY_DIAGRAM,
			RESOURCES_INTERACTION_SCENARIOS_DIAGRAM,
			RESOURCES_PROCESS_FLOW,
			RESOURCES_PROCESSES_DIAGRAM,
			RESOURCES_STATES_DIAGRAM,
			RESOURCES_STRUCTURE_DIAGRAM,
			RESOURCES_TAXONOMY_DIAGRAM,
			
			
			// Security
			SECURITY_TAXONOMY_DIAGRAM,
			SECURITY_STRUCTURE_DIAGRAM,
			SECURITY_CONNECTIVITY_DIAGRAM,
			SECURITY_PROCESSES_DIAGRAM,
			SECURITY_PROCESSES_FLOW_DIAGRAM,
			SECURITY_CONSTRAINTS_DIAGRAM,
			
			// Services
			SERVICES_CONNECTIVITY_DIAGRAM,
			SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM,
			SERVICES_INTERACTION_SCENARIOS_DIAGRAM,
			SERVICES_PROCESSES_DIAGRAM,
			SERVICES_STATES_DIAGRAM,
			SERVICES_STRUCTURE_DIAGRAM,
			SERVICES_TAXONOMY_DIAGRAM,
			
			// Strategic
			STRATEGIC_TAXONOMY_DIAGRAM,
			STRATEGIC_STRUCTURE_DIAGRAM,
			STRATEGIC_CONNECTIVITY_DIAGRAM,
			STRATEGIC_STATES_DIAGRAM,
			STRATEGIC_CONSTRAINTS_DIAGRAM,
			
			// Operational
			OPERATIONAL_PROCESS_FLOW,
			OPERATIONAL_CONNECTIVITY,
			OPERATIONAL_CONSTRAINTS_DEFINITION,
			OPERATIONAL_FREE_FORM_TAXONOMY,
			OPERATIONAL_PROCESSES_DIAGRAM,
			OPERATIONAL_STRUCTURE,
			OPERATIONAL_TAXONOMY,
			OPERATIONAL_HIGH_LEVEL_TAXONOMY,
			OPERATIONAL_INTERACTION_SCENARIOS,
			OPERATIONAL_INTERNAL_CONNECTIVITY,
			OPERATIONAL_PARAMETRIC,
			OPERATIONAL_STATES,
			
			//Projects
			PROJECTS_TAXONOMY_DIAGRAM,
			PROJECTS_STRUCTURE_DIAGRAM,
			PROJECTS_CONNECTIVITY_DIAGRAM,
			PROJECTS_PROCESSES_DIAGRAM,
			

			SUMMARY_AND_OVERVIEW_DIAGRAM,
			
			STANDARDS_TAXONOMY_DIAGRAM,
			STANDARDS_STRUCTURE_DIAGRAM,
	};
	
	public static final Set<String> UAF_ELEMENTS = new HashSet<String>(Arrays.asList(UAF_ELEMENT_VALUES));
	public static final Set<String> UAF_RELATIONSHIPS = new HashSet<String>(Arrays.asList(UAF_RELATIONSHIP_VALUES));
	public static final Set<String> UAF_DIAGRAMS = new HashSet<String>(Arrays.asList(UAF_DIAGRAM_VALUES));
	
	public static boolean isUafElement(String metamodelConstant) {
		if (UAF_ELEMENTS.contains(metamodelConstant)
				|| UAF_RELATIONSHIPS.contains(metamodelConstant)
				|| UAF_DIAGRAMS.contains(metamodelConstant)) {
			
			return true;
		}
		
		return false;
	}
}
