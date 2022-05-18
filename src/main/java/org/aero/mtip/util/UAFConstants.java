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
	
	// Diagrams
	public static final String STRATEGIC_TAXONOMY = "StrategicTaxonomy";
	
	
	// Diagram Stereotypes String in UAF/UPDM Profiles
	public static final String STRATEGIC_TAXONOMY_STEREOTYPE = "St-Tx Strategic Taxonomy"; // Contained in "UPDM Customization" Profile
	
	
	public static final String[] UAF_ELEMENTS = {
			ACTUAL_ENTERPRISE_PHASE,
			CAPABILITY,
			CAPABILITY_PROPERTY,
			ENTERPRISE_PHASE,
			STRATEGIC_TAXONOMY_PACKAGE	
	};
	
	public static final String[] UAF_RELATIONSHIPS = {
			
	};
	
	public static final String[] UAF_DIAGRAMS = {
			STRATEGIC_TAXONOMY
	};
}
