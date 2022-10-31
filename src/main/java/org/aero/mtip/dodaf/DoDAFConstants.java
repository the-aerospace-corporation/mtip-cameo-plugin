package org.aero.mtip.dodaf;

import org.aero.mtip.util.SysmlConstants;
import org.apache.commons.lang.ArrayUtils;

import uaf.UAFConstants;

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
			OV6C
	};
	
	public static final String[] CV1_TYPES = (String[]) ArrayUtils.addAll((String[]) ArrayUtils.addAll(new String[] {
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
			
			// UAFConstants.FUNCTION
			

	}, 
	SysmlConstants.BDD_TYPES), 
	SysmlConstants.REQ_TYPES);
	
	public static final String[] CV2_TYPES = {
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK
	};
	
	public static final String[] CV3_TYPES = {
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK
	};
	
	public static final String[] CV4_TYPES = {
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK
	};
	
	public static final String[] CV5_TYPES = {
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK
	};
	
	public static final String[] CV6_TYPES = {
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK
	};
	
	public static final String[] CV7_TYPES = {
			UAFConstants.ACTUAL_ENDURING_TASK,
			UAFConstants.CAPABILITY,
			UAFConstants.CAPABILITY_PROPERTY,
			UAFConstants.ENDURING_TASK
	};
	
	public static final String[] DODAF_ELEMENTS = {
	};
	
	public static final String[] DODAF_RELATIONSHIPS = {
	};
	
	
	public static final String[] SV1_TYPES = {
	
	};
	public static final String[] SV2_TYPES = {
		
	};
	public static final String[] SV3_TYPES = {
			
	};
	public static final String[] SV4_TYPES = {
			
	};
	public static final String[] SV5A_TYPES = {
			
	};
	public static final String[] SV5B_TYPES = {
			
	};
	public static final String[] SV5C_TYPES = {
			
	};
	public static final String[] SV6_TYPES = {
			
	};
	public static final String[] SV7_TYPES = {
			
	};
	public static final String[] SV8_TYPES = {
			
	};
	public static final String[] SV9_TYPES = {
			
	};
	public static final String[] SV10A_TYPES = {
			
	};
	public static final String[] SV10B_TYPES = {
			
	};
	public static final String[] SV10C_TYPES = {
			
	};
	
	public static final String[] OV1_TYPES = {
			
	};
	public static final String[] OV2_TYPES = {
			
	};
	public static final String[] OV3_TYPES = {
			
	};
	public static final String[] OV4_TYPES = {
			
	};
	public static final String[] OV5A_TYPES = {
			
	};
	public static final String[] OV5B_TYPES = {
			
	};
	public static final String[] OV6A_TYPES = {
			
	};
	public static final String[] OV6B_TYPES = {
			
	};
	public static final String[] OV6C_TYPES = {
			
	};
}
