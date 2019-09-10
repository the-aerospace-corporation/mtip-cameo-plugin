package org.aero.huddle.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// Helper class with constants used in formatting the import/export XML.
public class XmlTagConstants
{
	//SysML elements
	public static final String PKG = "package";
	public static final String PROFILE = "profile";
	public static final String STEREOTYPE = "stereotype";
	public static final String PROPERTY = "property";
	public static final String CLS = "class";
	public static final String BLOCK = "block";
	public static final String ASSOCIATION = "association";
	public static final String ACTIVITY = "activity";
	public static final String PARAMETER = "parameter";
	
	//SysML nomenclature, but not elements
	public static final String APPLIEDSTEREOTYPE = "appliedStereotype";
	public static final String TAG = "tag";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String CLIENT = "client";
	public static final String SUPPLIER = "supplier";
	public static final String ELEMENT_ID = "elementID";
	public static final String PARENT_ID = "parentID";
	
	//List of sysmlTags
	public static final String[] sysmlTagList = {
			PKG,
			PROFILE,
			STEREOTYPE,
			PROPERTY,
			CLS,
			BLOCK,
			ASSOCIATION,
			ACTIVITY,
			PARAMETER,
			APPLIEDSTEREOTYPE,
			TAG,
			NAME,
			VALUE,
			CLIENT,
			SUPPLIER,
			ELEMENT_ID,
			PARENT_ID};
	
	//Puddle nomenclature
	public static final String SPLASH = "splash";
	public static final String SYSTEM = "system";
	public static final String INTERACTION = "interaction";
	public static final String FUNCTION = "function";
	public static final String EVENT = "event";
	public static final String PROCESS = "process";
	public static final String OUTPORT = "outport";
	public static final String EVENT_TRACE = "eventTrace";
	public static final String CHARACTERIZATIONS = "characterizations";
	public static final String CHARACTERIZATION = "characterization";
	public static final String HUDDLE_ID = "_huddle_id";
	public static final String JOINS = "joins";
	public static final String EXECUTES = "executes";
	public static final String PERFORMED_BY = "performedBy";
	public static final String PERFORMS = "performs";
	public static final String EXECUTED_BY = "executedBy";
	public static final String EXPOSES = "exposes";
	public static final String POINTS_TO = "pointsTo";
	public static final String PRODUCED_BY = "producedBy";
	public static final String PRODUCES = "produces";
	public static final String EXPOSED_BY = "exposedBy";
	public static final String CONSUMED_BY = "consumedBy";
	
	public static final String LOCAL_ID = "_local_id";
	
	public static enum Type {SYSTEM, INTERACTION, FUNCTION, EVENT, PROCESS, OUTPORT, EVENT_TRACE};
	
	//XML namespace utilities
	public static final String NS_URL = "http://www.w3.org/2000/xmlns/";
	
	//Huddle namespaces
	private static HashMap<String, String> huddleNS = new HashMap<String, String>();
	
	static {
		huddleNS.put("xmlns:acquisition", "http://archia.aero.org/ontologies/acquisition#");
		huddleNS.put("xmlns:analysis", "http://archia.aero.org/ontologies/analysis#");
		huddleNS.put("xmlns:huddle", "http://huddle.aero.org#");
		huddleNS.put("xmlns:orbital", "http://archia.aero.org/ontologies/orbital#");
		huddleNS.put("xmlns:sos", "http://archia.aero.org/ontologies/sos#");
		huddleNS.put("xmlns:time", "http://archia.aero.org/ontologies/time#");
		huddleNS.put("xmlns:behavior", "http://archia.aero.org/ontologies/behavior#");
	}
	
	//Method to get the namespace map
	public static Map<String, String> getHuddleNS() {
		Map<String, String> duplicate = new HashMap<String, String>();
		for(Entry<String, String> entry : huddleNS.entrySet()) {
			duplicate.put(entry.getKey(), entry.getValue());
		}
		return duplicate;
	}
}
