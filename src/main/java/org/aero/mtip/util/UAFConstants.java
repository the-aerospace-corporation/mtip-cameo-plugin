package org.aero.mtip.util;

public class UAFConstants {
	public static final String UAF = "UAF";
	public static final String XML_METAMODEL_UAF = "uaf";
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
	/*public static final String INFORMATION_FLOW = "InformationFlow";
	public static final String OBJECT_FLOW = "ObjectFlow";*/
	
	// Diagrams
	public static final String STRATEGIC_TAXONOMY = "StrategicTaxonomy";
	
	
	// Diagram Stereotypes String in UAF/UPDM Profiles
	public static final String STRATEGIC_TAXONOMY_STEREOTYPE = "St-Tx Strategic Taxonomy"; // Contained in "UPDM Customization" Profile
	
	
	public static final String[] UAF_ELEMENTS = {
			ACHIEVER,
			ACTUAL_ENDURING_TASK,
			ACTUAL_ENTERPRISE_PHASE,
			CAPABILITY,
			CAPABILITY_PROPERTY,
			DESIRER,
			ENDURING_TASK,
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
			

	};
	
	public static final String[] UAF_RELATIONSHIPS = {
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
			OPERATIONAL_MESSAGE
	};
	
	public static final String[] UAF_DIAGRAMS = {
			STRATEGIC_TAXONOMY
	};
}
