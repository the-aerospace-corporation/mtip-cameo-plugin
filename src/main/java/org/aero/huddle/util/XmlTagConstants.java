package org.aero.huddle.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// Helper class with constants used in formatting the import/export XML.
public class XmlTagConstants
{
	//SysML elements
	public static final String ACTIVITY = "sysml.Activity";
	public static final String ACTIVITYPARAMETERNODE = "sysml.ActivityParameterNode";
	public static final String ACTOR = "sysml.Actor";
	public static final String ASSOCIATIONBLOCK = "sysml.AssociationBlock";
	public static final String BLOCK = "sysml.Block";
	public static final String CLASS = "sysml.Class";
	public static final String COLLABORATION = "sysml.Collaboration";
	public static final String COMBINEDFRAGMENT = "sysml.CombinedFragment";
	public static final String CONSTRAINTBLOCK = "sysml.ConstraintBlock";
	public static final String DESIGNCONSTRAINT = "sysml.DesignConstraint";
	public static final String EXTENDEDREQUIREMENT = "sysml.ExtendedRequirement";
	public static final String FINALSTATE = "sysml.FinalState";
	public static final String FLOWPORT = "sysml.FlowPort";
	public static final String FULLPORT = "sysml.FullPort";
	public static final String FUNCTIONALREQUIREMENT = "sysml.FunctionalRequirement";
	public static final String INTERACTION = "sysml.Interaction";
	public static final String INTERACTIONUSE = "sysml.InteractionUse";
	public static final String INITIALPSEUDOSTATE = "sysml.InitialPseudoState";
	public static final String INTERFACEBLOCK = "sysml.InterfaceBlock";
	public static final String INTERFACEREQUIREMENT = "sysml.InterfaceRequirement";
	public static final String LIFELINE = "sysml.Lifeline";
	public static final String MODEL = "sysml.Model";
	public static final String OPERATION = "sysml.Operation";
	public static final String PARTPROPERTY = "sysml.PartProperty";
	public static final String PACKAGE = "sysml.Package";
	public static final String PERFORMANCEREQUIREMENT = "sysml.PerformanceRequirement";
	public static final String PHYSICALREQUIREMENT = "sysml.PhysicalRequirement";
	public static final String PORT = "sysml.Port";
	public static final String PROFILE = "sysml.Profile";
	public static final String PROPERTY = "sysml.Property";
	public static final String PROXYPORT = "sysml.ProxyPort";
	public static final String REQUIREMENT = "sysml.Requirement";
	public static final String SIGNAL = "sysml.Signal";
	public static final String STATE = "sysml.State";
	public static final String STATEMACHINE = "sysml.StateMachine";
	public static final String STEREOTYPE = "sysml.Stereotype";
	public static final String USECASE = "sysml.UseCase";
	public static final String VALUEPROPERTY = "sysml.ValueProperty";
	public static final String VALUETYPE = "sysml.ValueType";

	//SysML relationships
	public static final String ASSOCIATION = "sysml.Association";
	public static final String COPY = "sysml.Copy";
	public static final String DERIVEREQUIREMENT = "sysml.DeriveRequirement";
	public static final String REFINE = "sysml.Refine";
	public static final String SATISFY = "sysml.Satisfy";
	public static final String TRACE = "sysml.Trace";
	public static final String VERIFY = "sysml.Verify";
	
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
	public static final String[] sysmlElementList = {
			ACTIVITY,
			ACTIVITYPARAMETERNODE,
			BLOCK,
			CLASS,
			PACKAGE,
			PROFILE,
			STEREOTYPE,
			PROPERTY,
			PARAMETER
	};
	
	//Puddle nomenclature
	public static final String SPLASH = "splash";
	public static final String SYSTEM = "system";
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
