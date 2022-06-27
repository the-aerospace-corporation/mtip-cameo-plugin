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
	public static final String HIGH_LEVEL_OPERATIONAL_CONCEPT ="HighLevelOperationalConcept";
	public static final String KNOWN_RESOURCE="KnownResource";
	public static final String OPERATIONAL_AGENT = "OperationalAgent";
	public static final String OPERATIONAL_ARCHITECTURE = "OperationalArchitecture";
	public static final String OPERATIONAL_PERFORMER = "OperationalPerformer";
	public static final String OPERATIONAL_INTERFACE = "OperationalInterface";
	public static final String INFORMATIONAL_ELEMENT = "InformationalElement";
	public static final String OPERATIONAL_CONTROL_FLOW = "OperationalControlFlow";
	public static final String OPERATIONAL_CONNECTOR = "OperationalConnector";
	public static final String OPERATIONAL_ROLE = "OperationalRole";
	public static final String OPERATIONAL_ACTIVITY = "OperationalActivity";
	public static final String OPERATIONAL_ACTIVITY_ACTION = "OperationalActivityAction";
	/*public static final String INFORMATION_FLOW = "InformationFlow";
	public static final String OBJECT_FLOW = "ObjectFlow";*/
	
	// Diagrams
	public static final String STRATEGIC_TAXONOMY = "StrategicTaxonomy";
	
	
	// Diagram Stereotypes String in UAF/UPDM Profiles
	public static final String STRATEGIC_TAXONOMY_STEREOTYPE = "St-Tx Strategic Taxonomy"; // Contained in "UPDM Customization" Profile
	
	
	public static final String[] UAF_ELEMENTS = {
			ACTUAL_ENTERPRISE_PHASE,
			CAPABILITY,
			CAPABILITY_PROPERTY,
			ENTERPRISE_PHASE,
			ENTERPRISE_VISION,
			WHOLE_LIFE_ENTERPRISE,
			STRATEGIC_TAXONOMY_PACKAGE,
			ENDURING_TASK,
			DESIRER,
			VISION_STATEMENT,
			STRUCTURAL_PART,
			TEMPORAL_PART,
			ACTUAL_ENDURING_TASK,
			ACHIEVER,
			//Operational
			HIGH_LEVEL_OPERATIONAL_CONCEPT,
			KNOWN_RESOURCE,
			OPERATIONAL_AGENT,
			OPERATIONAL_ARCHITECTURE,
			OPERATIONAL_PERFORMER,
			OPERATIONAL_INTERFACE,
			INFORMATIONAL_ELEMENT,
			OPERATIONAL_ROLE,
			OPERATIONAL_ACTIVITY,
			OPERATIONAL_ACTIVITY_ACTION
			
			

	};
	
	public static final String[] UAF_RELATIONSHIPS = {
			CAPABILITY_FOR_TASK,
			DESIRED_EFFECT,
			ACHIEVED_EFFECT,
			EXHIBITS,
			ORGANIZATION_IN_ENTERPRISE,
			MAPS_TO_CAPABILITY,
			//OPERATIONAL
			/*OPERATIONAL_CONTROL_FLOW,
			OPERATIONAL_CONNECTOR,
			INFORMATION_FLOW,
			OBJECT_FLOW*/
	};
	
	public static final String[] UAF_DIAGRAMS = {
			STRATEGIC_TAXONOMY
	};
}
