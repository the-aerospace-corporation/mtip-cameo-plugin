package org.aero.huddle.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// Helper class with constants used in formatting the import/export XML.
public class XmlTagConstants
{
	//SysML elements
	public static final String ACCEPTEVENTACTION = "sysml.AcceptEventAction";
	public static final String ACTION = "sysml.Action";
	public static final String ACTIVITY = "sysml.Activity";
	public static final String ACTIVITYFINALNODE = "sysml.ActivityFinalNode";
	public static final String ACTIVITYPARAMETERNODE = "sysml.ActivityParameterNode";
	public static final String ACTIVITYPARTITION = "sysml.ActivityPartition";
	public static final String ACTOR = "sysml.Actor";
	public static final String ASSOCIATIONBLOCK = "sysml.AssociationBlock";
	public static final String BLOCK = "sysml.Block";
	public static final String BOUNDREFERENCE = "sysml.BoundReference";
	public static final String CALLBEHAVIORACTION = "sysml.CallBehaviorAction";
	public static final String CALLOPERATIONACTION = "sysml.CallOperationAction";
	public static final String CENTRALBUFFERNODE = "sysml.CentralBufferNode";
	public static final String CHANGEEVENT = "sysml.ChangeEvent";
	public static final String CHOICEPSEUDOSTATE = "sysml.ChoicePseudoState";
	public static final String CLASS = "sysml.Class";
	public static final String CLASSIFIERBEHAVIORPROPERTY = "sysml.ClassifierBehaviorProperty";
	public static final String COLLABORATION = "sysml.Collaboration";
	public static final String COMBINEDFRAGMENT = "sysml.CombinedFragment";
	public static final String CONDITIONALNODE = "sysml.ConditionalNode";
	public static final String CONNECTIONPOINTREFERENCE = "sysml.ConnectionPointReference";
	public static final String CONSTRAINTBLOCK = "sysml.ConstraintBlock";
	public static final String CONSTRAINTPARAMETER = "sysml.ConstraintParameter";
	public static final String CONSTRAINTPROPERTY = "sysml.ConstraintProperty";
	public static final String CREATEOBJECTACTION = "sysml.CreateObjectAction";
	public static final String DATASTORENODE = "sysml.DataStoreNode";
	public static final String DECISIONNODE = "sysml.DecisionNode";
	public static final String DEEPHISTORY = "sysml.DeepHistory";
	public static final String DESIGNCONSTRAINT = "sysml.DesignConstraint";
	public static final String DESTROYOBJECTACTION = "sysml.DestroyObjectAction";
	public static final String ENTRYPOINT = "sysml.EntryPoint";
	public static final String ENUMERATION = "sysml.Enumeration";
	public static final String EXITPOINT = "sysml.ExitPoint";
	public static final String EXTENDEDREQUIREMENT = "sysml.ExtendedRequirement";
	public static final String FINALSTATE = "sysml.FinalState";
	public static final String FLOWFINALNODE = "sysml.FlowFinalNode";
	public static final String FLOWPORT = "sysml.FlowPort";
	public static final String FORK = "sysml.Fork";
	public static final String FORKNODE = "sysml.ForkNode";
	public static final String FULLPORT = "sysml.FullPort";
	public static final String FUNCTIONALREQUIREMENT = "sysml.FunctionalRequirement";
	public static final String INITIALNODE = "sysml.InitialNode";
	public static final String INITIALPSEUDOSTATE = "sysml.InitialPseudoState";
	public static final String INPUTPIN = "sysml.InputPin";
	public static final String INSTANCESPECIFICATION = "sysml.InstanceSpecification";
	public static final String INTERACTION = "sysml.Interaction";
	public static final String INTERACTIONUSE = "sysml.InteractionUse";
	public static final String INTERFACE = "sysml.Interface";
	public static final String INTERFACEBLOCK = "sysml.InterfaceBlock";
	public static final String INTERFACEREQUIREMENT = "sysml.InterfaceRequirement";
	public static final String JOIN = "sysml.Join";
	public static final String JOINNODE = "sysml.JoinNode";
	public static final String LIFELINE = "sysml.Lifeline";
	public static final String LOOPNODE = "sysml.LoopNode";
//	public static final String OBJECTNODE = "sysml.ObjetNode;
	public static final String OPAQUEACTION = "sysml.OpaqueAction";
	public static final String OUTPUTPIN = "sysml.OutputPin";
	public static final String MERGENODE = "sysml.MergeNode";
	public static final String MODEL = "sysml.Model";
	public static final String OPERATION = "sysml.Operation";
	public static final String PARTICIPANTPROPERTY = "sysml.ParticipantProperty";
	public static final String PARTPROPERTY = "sysml.PartProperty";
	public static final String PACKAGE = "sysml.Package";
	public static final String PERFORMANCEREQUIREMENT = "sysml.PerformanceRequirement";
	public static final String PHYSICALREQUIREMENT = "sysml.PhysicalRequirement";
	public static final String PORT = "sysml.Port";
	public static final String PROFILE = "sysml.Profile";
	public static final String PROPERTY = "sysml.Property";
	public static final String PROXYPORT = "sysml.ProxyPort";
	public static final String QUANTITYKIND = "sysml.QuantityKind";
	public static final String REFERENCEPROPERTY = "sysml.ReferneceProperty";
	public static final String REQUIREMENT = "sysml.Requirement";
	public static final String SENDSIGNALACTION = "sysml.SendSignalAction";
	public static final String SHALLOWHISTORY = "sysml.ShallowHistory";
	public static final String SIGNAL = "sysml.Signal";
	public static final String STATE = "sysml.State";
	public static final String STATEMACHINE = "sysml.StateMachine";
	public static final String STEREOTYPE = "sysml.Stereotype";
	public static final String TERMINATE = "sysml.Terminate";
	public static final String TIMEEVENT = "sysml.TimeEvent";
	public static final String TRIGGER = "sysml.Trigger";
	public static final String UNIT = "sysml.Unit";
	public static final String USECASE = "sysml.UseCase";
	public static final String VALUEPROPERTY = "sysml.ValueProperty";
	public static final String VALUETYPE = "sysml.ValueType";

	//SysML relationships
	public static final String AGGREGATION = "sysml.Aggregation";
	public static final String ASSOCIATION = "sysml.Association";
	public static final String BINDINGCONNECTOR = "sysml.BindingConnector";
	public static final String CONNECTOR = "sysml.Connector";
	public static final String CONTROLFLOW = "sysml.ControlFlow";
	public static final String COPY = "sysml.Copy";
	public static final String DEPENDENCY = "sysml.Dependency";
	public static final String DERIVEREQUIREMENT = "sysml.DeriveRequirement";
	public static final String EXTENSION = "sysml.Extension";
	public static final String GENERALIZATION = "sysml.Generalization";
	public static final String ITEMFLOW = "sysml.ItemFlow";
	public static final String OBJECTFLOW = "sysml.ObjectFlow";
	public static final String REFINE = "sysml.Refine";
	public static final String SATISFY = "sysml.Satisfy";
	public static final String TRACE = "sysml.Trace";
	public static final String TRANSITION = "sysml.Transition";
	public static final String USAGE = "sysml.Usage";
	public static final String VERIFY = "sysml.Verify";
	
	public static final String PARAMETER = "parameter";
	
	//Stereotypes
	public static final String DOMAINSTEREOTYPE = "Domain";
	public static final String EXTERNALSTEREOTYPE = "External";
	public static final String SUBSYSTEMSTEREOTYPE = "Subsystem";
	public static final String SYSTEMCONTEXTSTEREOTYPE = "System context";
	public static final String SYSTEMSTEREOTYPE = "System";
	
	//SysML Diagrams
	public static final String BLOCKDEFINITIONDIAGRAM = "sysml.BlockDefinitionDiagram";
	
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
