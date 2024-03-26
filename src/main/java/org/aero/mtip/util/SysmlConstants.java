/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SysmlConstants {
	//Categorization constants
	public static final String ELEMENT = "Element";
	public static final String RELATIONSHIP = "Relationship";
	public static final String DIAGRAM = "Diagram";
	
	//Sysml Element constants
	public static final String ACCEPT_EVENT_ACTION = "AcceptEventAction";
	public static final String ACTION = "Action";
	public static final String ACTIVITY = "Activity";
	public static final String ACTIVITY_FINAL_NODE = "ActivityFinalNode";
	public static final String ACTIVITY_PARAMETER_NODE = "ActivityParameterNode"; // Sysml specifies as ActivityParameterNode
	public static final String ACTIVITY_PARTITION = "ActivityPartition";
	public static final String ACTOR = "Actor";
	public static final String ASSOCIATION_BLOCK = "AssociationBlock";
	public static final String BOUND_REFERENCE = "BoundReference";
	public static final String BLOCK = "Block";
	public static final String BUSINESS_REQUIREMENT = "BusinessRequirement";
	public static final String CALL_BEHAVIOR_ACTION = "CallBehaviorAction";
	public static final String CALL_OPERATION_ACTION = "CallOperationAction";
	public static final String CENTRAL_BUFFER_NODE = "CentralBufferNode";
	public static final String CHANGE_EVENT = "ChangeEvent";
	public static final String CHOICE_PSEUDO_STATE = "ChoicePseudoState";
	public static final String CLASS = "Class";
	public static final String CLASSIFIER_BEHAVIOR_PROPERTY = "ClassifierBehaviorProperty";
	public static final String COLLABORATION = "Collaboration";
	public static final String COMBINED_FRAGMENT = "CombinedFragment";
	public static final String COMMENT = "Comment";
	public static final String CONDITIONAL_NODE = "ConditionalNode";
	public static final String CONNECTION_POINT_REFERENCE = "ConnectionPointReference";
	public static final String CONSTRAINT = "Constraint";
	public static final String CONSTRAINT_BLOCK = "ConstraintBlock";
	public static final String CONSTRAINT_PARAMETER = "ConstraintParameter";
	public static final String CONSTRAINT_PROPERTY = "ConstraintProperty";
	public static final String CREATE_OBJECT_ACTION = "CreateObjectAction";
	public static final String CUSTOMIZATION = "Customization";
	public static final String DATA_STORE_NODE = "DataStoreNode";
	public static final String DATA_TYPE = "DataType";
	public static final String DECISION_NODE = "DecisionNode";
	public static final String DEEP_HISTORY = "DeepHistory";
	public static final String DESIGN_CONSTRAINT = "DesignConstraint";
	public static final String DESTROY_OBJECT_ACTION = "DestroyObjectAction";
	public static final String DESTRUCTION_OCCURRENCE_SPECIFICATION = "DestructionOccurrenceSpecification";
	public static final String DIRECTED_FEATURE = "DirectedFeature";
	public static final String DURATION = "Duration";
	public static final String DURATION_CONSTRAINT = "DurationConstraint";
	public static final String DURATION_INTERVAL = "DurationInterval";
	public static final String DURATION_OBSERVATION = "DurationObservation";
	public static final String ENTRY_POINT = "EntryPoint";
	public static final String ENUMERATION = "Enumeration";
	public static final String ENUMERATION_LITERAL = "EnumerationLiteral";
	public static final String EXIT_POINT = "ExitPoint";
	public static final String EXTENDED_REQUIREMENT = "ExtendedRequirement";
	public static final String EXTENSION_POINT = "ExtensionPoint";
	public static final String FINAL_STATE = "FinalState";
	public static final String FLOW_FINAL_NODE = "FlowFinalNode";
	public static final String FLOW_PORT = "FlowPort";
	public static final String FLOW_PROPERTY = "FlowProperty";
	public static final String FLOW_SPECFICATION = "FlowSpecification";
	public static final String FORK = "Fork";
	public static final String FORK_NODE = "ForkNode";
	public static final String FULL_PORT = "FullPort";
	public static final String FUNCTIONAL_REQUIREMENT = "FunctionalRequirement";
	public static final String FUNCTION_BEHAVIOR = "FunctionBehavior";
	public static final String INCLUDE = "Include";
	public static final String INFORMATION_ITEM = "InformationItem";
	public static final String INITIAL_NODE = "InitialNode";
	public static final String INITIAL_PSEUDO_STATE = "InitialPseudoState";
	public static final String INPUT_PIN = "InputPin";
	public static final String INSTANCE_SPECIFICATION = "InstanceSpecification";
	public static final String INSTANCE_VALUE = "InstanceValue";
	public static final String INTERACTION = "Interaction";
	public static final String INTERACTION_OPERAND = "InteractionOperand";
	public static final String INTERACTION_USE = "InteractionUse";
	public static final String INTERFACE = "Interface";
	public static final String INTERFACE_BLOCK = "InterfaceBlock";
	public static final String INTERFACE_REALIZATION = "InterfaceRealization";
	public static final String INTERFACE_REQUIREMENT = "InterfaceRequirement";
	public static final String INTERRUPTIBLE_ACTIVITY_REGION = "InterruptibleActivityRegion";
	public static final String JOIN = "Join";
	public static final String JOIN_NODE = "JoinNode";
	public static final String LIFELINE = "Lifeline";
	public static final String LINK = "Link";
	public static final String LOOP_NODE = "LoopNode";
	public static final String MERGE_NODE = "MergeNode";
	public static final String MESSAGE = "Message";
	public static final String MESSAGE_OCCURRENCE_SPECIFICATION = "MessageOccurrenceSpecification";
	public static final String METACLASS = "Metaclass";
	public static final String MODEL = "Model";
	public static final String OPAQUE_ACTION = "OpaqueAction";
	public static final String OPAQUE_BEHAVIOR = "OpaqueBehavior";
	public static final String OPAQUE_EXPRESSION = "OpaqueExpression";
	public static final String OPERATION = "Operation";
	public static final String OUTPUT_PIN = "OutputPin";
	public static final String PACKAGE = "Package";
	public static final String PARAMETER = "Parameter";
	public static final String PARTICIPANT_PROPERTY = "ParticipantProperty";
	public static final String PART_PROPERTY = "PartProperty";
	public static final String PERFORMANCE_REQUIREMENT = "PerformanceRequirement";
	public static final String PHYSICAL_REQUIREMENT = "PhysicalRequirement";
	public static final String PORT = "Port";
	public static final String PROFILE = "Profile";
	public static final String PROPERTY = "Property";
	public static final String PROXY_PORT = "ProxyPort";
	public static final String QUANTITY_KIND = "QuantityKind";
	public static final String REFERENCE_POINT = "ReferenceProperty";
	public static final String REGION = "Region";
	public static final String REQUIREMENT = "Requirement";
	public static final String SEND_SIGNAL_ACTION = "SendSignalAction";
	public static final String SIGNAL_EVENT = "SignalEvent";
	public static final String SHALLOW_HISTORY = "ShallowHistory";
	public static final String SIGNAL = "Signal";
	public static final String SLOT = "Slot";
	public static final String STAKEHOLDER = "Stakeholder";
	public static final String STATE = "State";
	public static final String STATE_INVARIANT = "StateInvariant";
	public static final String STATE_MACHINE = "StateMachine";
	public static final String STEREOTYPE = "Stereotype";
	public static final String TERMINATE = "Terminate";
	public static final String TERM = "Term";
	public static final String TIME_CONSTRAINT = "TimeConstraint";
	public static final String TIME_EVENT = "TimeEvent";
	public static final String TIME_EXPRESSION = "TimeExpression";
	public static final String TIME_OBSERVATION = "TimeObservation";
	public static final String TRIGGER = "Trigger";
	public static final String UNIT = "Unit";
	public static final String USABILITY_REQUIREMENT = "UsabilityRequirement";
	public static final String USE_CASE = "UseCase";
	public static final String VALUE_PROPERTY = "ValueProperty";
	public static final String VALUE_TYPE = "ValueType";
	public static final String VIEW = "View";
	public static final String VIEWPOINT = "Viewpoint";
	
	// Tables
	public static final String GENERIC_TABLE = "GenericTable";
	public static final String INSTANCE_TABLE = "InstanceTable";
	public static final String GLOSSARY_TABLE = "GlossaryTable";
	public static final String METRIC_TABLE = "MetricTable";
	public static final String REQUIREMENT_TABLE = "RequirementTable";
	
	//Matrices
	public static final String ALLOCATION_MATRIX = "AllocationMatrix";
	public static final String DEPENDENCY_MATRIX = "DependencyMatrix";
	public static final String DERIVE_REQUIREMENT_MATRIX = "DeriveRequirementMatrix";
	public static final String REFINE_REQUIREMENT_MATRIX = "RefineRequirementMatrix";
	public static final String SATISFY_REQUIREMENT_MATRIX = "SatisfyRequirementMatrix";
	public static final String VERIFY_REQUIREMENT_MATRIX = "VerifyRequirementMatrix";
	
	// Matrix and Table constants as known by Cameo
	public static final String CAMEO_ALLOCATION_MATRIX = "SysML Allocation Matrix";
	public static final String CAMEO_DERIVE_REQUIREMENT_MATRIX = "Derive Requirement Matrix";
	public static final String CAMEO_REFINE_REQUIREMENT_MATRIX = "Refine Requirement Matrix";
	public static final String CAMEO_SATISFY_REQUIREMENT_MATRIX = "Satisfy Requirement Matrix";
	public static final String CAMEO_VERIFY_REQUIREMENT_MATRIX = "Verify Requirement Matrix";
	
	public static final String CAMEO_GENERIC_TABLE = "Generic Table";
	public static final String CAMEO_GLOSSARY_TABLE = "Glossary Table";
	public static final String CAMEO_REQUIREMENT_TABLE = "Requirement Table";
	public static final String CAMEO_INSTANCE_TABLE = "Instance Table";
	public static final String CAMEO_METRIC_TABLE = "Metric Table";
	
	// Cameo specific "non-normative" block extension
	public static final String DOMAIN = "Domain";
	public static final String EXTERNAL = "External";
	public static final String SUBSYSTEM = "Subsystem";
	public static final String SYSTEM = "System";
	public static final String SYSTEM_CONTEXT = "SystemContext";

//	public static final String TRIGGER = "Trigger";
//	public static final String NOTE = "Note";
//	public static final String PROFILE = "Profile";
//	public static final String PARAMETER = "Parameter";
	
	//Sysml Relationship Constants
	public static final String ABSTRACTION = "Abstraction";
	public static final String AGGREGATION = "Aggregation";
	public static final String ALLOCATE = "Allocate";
	public static final String ASSOCIATION = "Association";
	public static final String BINDING_CONNECTOR = "BindingConnector";
	public static final String COMPOSITION = "Composition";
	public static final String CONNECTOR = "Connector";
	public static final String CONTROL_FLOW = "ControlFlow";
	public static final String COPY = "Copy";
	public static final String DEPENDENCY = "Dependency";
	public static final String DERIVE_REQUIREMENT = "DeriveRequirement";
	public static final String EXTEND = "Extend";
	public static final String EXTENSION = "Extension";
	public static final String GENERALIZATION = "Generalization";
	public static final String INFORMATION_FLOW = "InformationFlow";
	public static final String ITEM_FLOW = "ItemFlow";
	public static final String OBJECT_FLOW = "ObjectFlow";
	public static final String PACKAGE_IMPORT = "PackageImport";
	public static final String REFINE = "Refine";
	public static final String SATISFY = "Satisfy";
	public static final String TRACE = "Trace";
	public static final String TRANSITION = "Transition";
	public static final String USAGE = "Usage";
	public static final String VERIFY = "Verify";
	
	//Sysml Diagram constants
	public static final String BDD = "BlockDefinitionDiagram";
	public static final String IBD = "InternalBlockDiagram";
	public static final String PKG = "PackageDiagram";
	public static final String REQ = "RequirementsDiagram";
	public static final String PAR = "ParametricDiagram";
	public static final String STM = "StateMachineDiagram";
	public static final String ACT = "ActivityDiagram";
	public static final String SEQ = "SequenceDiagram";
	public static final String UC = "UseCaseDiagram";
	public static final String PROFILEDIAGRAM = "ProfileDiagram";
	public static final String CLASSDIAGRAM = "ClassDiagram";
	public static final String CUSTOM_DIAGRAM = "CustomDiagram";
	
	public static final String SUBMACHINE = "submachine";
	
	// Profile Names
	public static final String DEPENDENCY_MATRIX_PROFILE = "Dependency Matrix Profile";
	
	//Reserved words for instances to keep from generating SysML library instance specifications
	public static final String RESERVE_LINK = "Link";
	public static final String RESERVE_QUANTITY_KIND = "Quantity Kind";
	public static final String RESERVE_UNIT = "Unit";
	
	public static final String[] SYSML_ELEMENTS_VALUES = {
			ACCEPT_EVENT_ACTION,
			ACTION,
			ACTIVITY,
			ACTIVITY_FINAL_NODE,
			ACTIVITY_PARAMETER_NODE,
			ACTIVITY_PARTITION,
			ACTOR,
			ASSOCIATION_BLOCK,
			BLOCK,
			BOUND_REFERENCE,
			BUSINESS_REQUIREMENT,
			CALL_BEHAVIOR_ACTION,
			CALL_OPERATION_ACTION,
			CENTRAL_BUFFER_NODE,
			CHANGE_EVENT,
			CHOICE_PSEUDO_STATE,
			CLASS,
			COLLABORATION,
			COMBINED_FRAGMENT,
//			COMMENT, Filtered out for correct import of Terms
			CONDITIONAL_NODE,
			CONNECTION_POINT_REFERENCE,
			CONSTRAINT,
			CONSTRAINT_BLOCK,
			CONSTRAINT_PARAMETER,
			CONSTRAINT_PROPERTY,
			CLASSIFIER_BEHAVIOR_PROPERTY,
			CREATE_OBJECT_ACTION,
			CUSTOMIZATION,
			DATA_STORE_NODE,
			DATA_TYPE,
			DECISION_NODE,
			DEEP_HISTORY,
			DESIGN_CONSTRAINT,
			DESTROY_OBJECT_ACTION,
			DESTRUCTION_OCCURRENCE_SPECIFICATION,
			DOMAIN,
			DURATION,
			DURATION_INTERVAL,
			DURATION_CONSTRAINT,
			DURATION_OBSERVATION,
			ENTRY_POINT,
			ENUMERATION,
			ENUMERATION_LITERAL,
			EXIT_POINT,
			EXTENDED_REQUIREMENT,
			EXTENSION_POINT,
			EXTERNAL,
			FINAL_STATE,
			FLOW_FINAL_NODE,
			FLOW_PORT,
			FLOW_PROPERTY,
			FLOW_SPECFICATION,
			FORK,
			FORK_NODE,
			FULL_PORT,
			FUNCTIONAL_REQUIREMENT,
			FUNCTION_BEHAVIOR,
			INFORMATION_ITEM,
			INITIAL_NODE,
			INITIAL_PSEUDO_STATE,
			INPUT_PIN,
			INSTANCE_SPECIFICATION,
			INTERACTION,
			INTERACTION_OPERAND,
			INTERFACE,
			INTERACTION_USE,
			INTERFACE_BLOCK,
			INTERFACE_REQUIREMENT,
			INTERRUPTIBLE_ACTIVITY_REGION,
			JOIN,
			JOIN_NODE,
			LIFELINE,
			LINK,
			LOOP_NODE,
			MERGE_NODE,
			MESSAGE_OCCURRENCE_SPECIFICATION,
			METACLASS,
			MODEL,
			OPAQUE_ACTION,
			OPAQUE_BEHAVIOR,
			OPAQUE_EXPRESSION,
			OPERATION,
			OUTPUT_PIN,
			PACKAGE,
			PARAMETER,
			PART_PROPERTY,
			PARTICIPANT_PROPERTY,
			PERFORMANCE_REQUIREMENT,
			PHYSICAL_REQUIREMENT,
			PORT,
			PROFILE,
			PROPERTY,
			PROXY_PORT,
			QUANTITY_KIND,
			REGION,
			REFERENCE_POINT,
			REQUIREMENT,
			SHALLOW_HISTORY,
			SEND_SIGNAL_ACTION,
			SIGNAL,
			SIGNAL_EVENT,
			SLOT,
			STAKEHOLDER,
			STATE,
			STATE_INVARIANT,
			STATE_MACHINE,
			STEREOTYPE,
			SUBSYSTEM,
			SYSTEM,
			SYSTEM_CONTEXT,
			TERMINATE,
			TERM,
			TIME_CONSTRAINT,
			TIME_EVENT,
			TIME_EXPRESSION,
			TIME_OBSERVATION,
			TRIGGER,
			UNIT,
			USE_CASE,
			VALUE_PROPERTY,
			VALUE_TYPE,	
			VIEW,
			VIEWPOINT,
			
			
			// Table treated as Element - cannot be cast to Diagram
			GENERIC_TABLE,
			GLOSSARY_TABLE,
			INSTANCE_TABLE,
			METRIC_TABLE,
			REQUIREMENT_TABLE,
	};
	

	
	public static final String[] RESERVE_INSTANCE_SPECIFICATION = {
			RESERVE_LINK,
			RESERVE_QUANTITY_KIND,
			RESERVE_UNIT,
	};
	
	public static final String[] SYSML_RELATIONSHIP_VALUES = {
			ABSTRACTION,
			AGGREGATION,
			ALLOCATE,
			ASSOCIATION,
			BINDING_CONNECTOR,
			COMPOSITION,
			CONNECTOR,
			CONTROL_FLOW,
			COPY,
			DEPENDENCY,
			DERIVE_REQUIREMENT,
			EXTEND,
			EXTENSION,
			GENERALIZATION,
			INCLUDE,
			INFORMATION_FLOW,
			ITEM_FLOW,
			MESSAGE,
			OBJECT_FLOW,
			PACKAGE_IMPORT,
			REFINE,
			SATISFY,
			TRACE,
			TRANSITION,
			USAGE,
			VERIFY
	};

	public static final String[] SYSML_DIAGRAM_VALUES = {
			BDD, 
			IBD,
			PKG,
			REQ,
			PAR,
			STM,
			ACT,
			SEQ,
			UC, 
			PROFILEDIAGRAM,
			CLASSDIAGRAM, 
			CUSTOM_DIAGRAM,
			
			// Matrix types - requires different method for GetImportedElementsOnDiagram
//			ALLOCATION_MATRIX,
//			DEPENDENCY_MATRIX,
//			DERIVE_REQUIREMENT_MATRIX,
//			REFINE_REQUIREMENT_MATRIX,
//			SATISFY_REQUIREMENT_MATRIX,
//			VERIFY_REQUIREMENT_MATRIX,
	};
	
	public static Set<String> SYSML_ELEMENTS = new HashSet<String>(Arrays.asList(SYSML_ELEMENTS_VALUES));
	public static Set<String> SYSML_RELATIONSHIPS = new HashSet<String>(Arrays.asList(SYSML_RELATIONSHIP_VALUES));
	public static Set<String> SYSML_DIAGRAMS = new HashSet<String>(Arrays.asList(SYSML_DIAGRAM_VALUES));
	
	public static final String[] BDD_TYPES = {
			ASSOCIATION_BLOCK,
			BLOCK,
			CLASS,
			CONSTRAINT_BLOCK,
			DATA_TYPE,
			DOMAIN,
			ENUMERATION,
			EXTERNAL,
			FLOW_PORT,
			FLOW_SPECFICATION,
			FULL_PORT,
			INFORMATION_ITEM,
			INSTANCE_SPECIFICATION,
			INTERACTION,
			INTERFACE,
			INTERFACE_BLOCK,
			//NOTE, // not supported?
			OPERATION,
			PACKAGE,
			PACKAGE_IMPORT,
			PART_PROPERTY,
			PORT,
			PROPERTY,
			PROXY_PORT,
			QUANTITY_KIND,
			REQUIREMENT,
			SIGNAL,
			STAKEHOLDER,
			SUBSYSTEM,
			SYSTEM,
			SYSTEM_CONTEXT,
			UNIT,
			USE_CASE,
			VALUE_PROPERTY,
			VALUE_TYPE,
			VIEW,
			VIEWPOINT
	};


	public static final String[] IBD_TYPES = {
			ASSOCIATION_BLOCK,
			CONSTRAINT_BLOCK,
			DOMAIN,
			ENUMERATION,
			EXTERNAL,
			FLOW_PORT,
			FLOW_SPECFICATION,
			FULL_PORT,
			INSTANCE_SPECIFICATION,
			INTERFACE,
			INTERFACE_BLOCK,
			//NOTE, // not supported?
			OPERATION,
			PACKAGE,
			PART_PROPERTY,
			PORT,
			PROXY_PORT,
			QUANTITY_KIND,
			SIGNAL,
			SUBSYSTEM,
			SYSTEM,
			SYSTEM_CONTEXT,
			UNIT,
			VALUE_PROPERTY,
			VALUE_TYPE
	};

	public static final String[] REQ_TYPES = {
			ACTIVITY,
			BLOCK,
			BUSINESS_REQUIREMENT,
			COPY,
			DERIVE_REQUIREMENT,
			DESIGN_CONSTRAINT,
			EXTENDED_REQUIREMENT,
			FUNCTIONAL_REQUIREMENT,
			INTERACTION,
			INTERFACE_REQUIREMENT,
			PACKAGE,
			PERFORMANCE_REQUIREMENT,
			PHYSICAL_REQUIREMENT,
			REFINE,
			REQUIREMENT,
			SATISFY,
			STATE_MACHINE,
			TRACE,
			USABILITY_REQUIREMENT,
			VERIFY
	};
	
	public static final String[] STM_TYPES = {
			CHOICE_PSEUDO_STATE,
			CONNECTION_POINT_REFERENCE,
			DEEP_HISTORY,
			ENTRY_POINT,
			EXIT_POINT,
			FINAL_STATE,
			FORK,
			FUNCTION_BEHAVIOR,
			INITIAL_PSEUDO_STATE,
			JOIN,
			"PseudoState", // verify with Trent
			REGION,
			SHALLOW_HISTORY,
			STATE,
			STATE_MACHINE,
			TERMINATE,
			TRANSITION,
			TRIGGER
	};
	
	public static final String[] ACT_TYPES = {
			ACCEPT_EVENT_ACTION,
			ACTION,
			ACTIVITY,
			ACTIVITY_FINAL_NODE,
			ACTIVITY_PARAMETER_NODE,
			ACTIVITY_PARTITION,
			CALL_BEHAVIOR_ACTION,
			CALL_OPERATION_ACTION,
			CENTRAL_BUFFER_NODE,
			CHANGE_EVENT,
			CONDITIONAL_NODE,
			CONTROL_FLOW,
			CREATE_OBJECT_ACTION,
			DATA_STORE_NODE,
			DECISION_NODE,
			DESTROY_OBJECT_ACTION,
			FLOW_FINAL_NODE,
			FORK_NODE,
			INITIAL_NODE,
			INPUT_PIN,
			JOIN_NODE,
			LOOP_NODE,
			MERGE_NODE,
			OBJECT_FLOW,
			"ObjectNode", //OBJECTNODE,
			OPAQUE_ACTION,
			OUTPUT_PIN,
			SEND_SIGNAL_ACTION,
			TIME_EVENT
	};
	
	public static final String[] SEQ_TYPES = {
			COMBINED_FRAGMENT,
			COLLABORATION,
			DESTRUCTION_OCCURRENCE_SPECIFICATION,
			DURATION_CONSTRAINT,
			INTERACTION,
			INTERACTION_OPERAND,
			INTERACTION_USE,
			LIFELINE,
			MESSAGE,
			PROPERTY,
			STATE_INVARIANT,
			TIME_CONSTRAINT
	};
	
	public static final String[] UC_TYPES = {
			ACTOR,
			EXTEND,
			INCLUDE,
			USE_CASE
	};
	
	public static final String[] PAR_TYPES = {
			CONSTRAINT_PARAMETER,
			CONSTRAINT_PROPERTY,
			PART_PROPERTY,
			PORT,
			PROPERTY,
			PROXY_PORT			
	};
	
	public static final String[] PKG_TYPES = {
			//From Block Definition Diagram
			ASSOCIATION_BLOCK,
			BLOCK,
			CLASS,
			CONSTRAINT_BLOCK,
			DOMAIN,
			ENUMERATION,
			EXTERNAL,
			FLOW_PORT,
			FLOW_SPECFICATION,
			FULL_PORT,
			INSTANCE_SPECIFICATION,
			INTERFACE,
			INTERFACE_BLOCK,
			//NOTE, // not supported?
			OPERATION,
			PACKAGE,
			PACKAGE_IMPORT,
			PART_PROPERTY,
			PORT,
			PROXY_PORT,
			QUANTITY_KIND,
			SIGNAL,
			STAKEHOLDER,
			SUBSYSTEM,
			SYSTEM,
			SYSTEM_CONTEXT,
			UNIT,
			VALUE_PROPERTY,
			VALUE_TYPE,
			VIEW,
			VIEWPOINT,
			
			//From Requirements Diagram
			COPY,
			DERIVE_REQUIREMENT,
			DESIGN_CONSTRAINT,
			EXTENDED_REQUIREMENT,
			FUNCTIONAL_REQUIREMENT,
			INTERFACE_REQUIREMENT,
			PERFORMANCE_REQUIREMENT,
			PHYSICAL_REQUIREMENT,
			REFINE,
			REQUIREMENT,
			SATISFY,
			TRACE,
			VERIFY,
			
			//From Use Case Diagram
			ACTOR,
			EXTEND,
			INCLUDE,
			USE_CASE
	};
	
	public static final String[] PROFILEDIAGRAM_TYPES = {
			CLASS,
			CONSTRAINT,
			CUSTOMIZATION,
			EXTENSION,
			METACLASS,
			OPAQUE_EXPRESSION,
			PROFILE,
			STEREOTYPE
	};
	
	public static final String[] CLASSDIAGRAM_TYPES = {
			ACTIVITY,
			BLOCK,
			CLASS,
			CONSTRAINT,
			CUSTOMIZATION,
			EXTENSION,
			INTERFACE,
			METACLASS,
			OPAQUE_EXPRESSION,
			PORT,
			PROFILE,
			REQUIREMENT,
			SIGNAL,
	};
	
	public static Map<String, String[]> diagramTypeMap;
	static {
		diagramTypeMap = new HashMap<>();
		diagramTypeMap.put(SysmlConstants.BDD, BDD_TYPES);
		diagramTypeMap.put(SysmlConstants.IBD, IBD_TYPES);
		diagramTypeMap.put(SysmlConstants.PKG, PKG_TYPES);
		diagramTypeMap.put(SysmlConstants.REQ, REQ_TYPES);
		diagramTypeMap.put(SysmlConstants.PAR, PAR_TYPES);
		diagramTypeMap.put(SysmlConstants.STM, STM_TYPES);
		diagramTypeMap.put(SysmlConstants.ACT, ACT_TYPES);
		diagramTypeMap.put(SysmlConstants.SEQ, SEQ_TYPES);
		diagramTypeMap.put(SysmlConstants.UC, UC_TYPES);
		diagramTypeMap.put(SysmlConstants.PROFILEDIAGRAM, PROFILEDIAGRAM_TYPES);
//		diagramTypeMap.put(SysmlConstants.DEPENDENCY_MATRIX, ?);
//		diagramTypeMap.put(SysmlConstants.CLASSDIAGRAM, CLASSDIAGRAM_TYPES);

	}
	
	public static final String MD_CUSTOMIZATION_PROFILE_NAME = "MD Customization for SysML";
	public static final String SYSML_PROFILE_NAME = "SysML";
	public static final String UML_PROFILE_NAME = "UML Standard Profile";
	public static final String MAGICDRAW_PROFILE_NAME = "MagicDraw Profile";
	
	public static final String[] defaultClassifiers = {
			QUANTITY_KIND,
			UNIT
	};
	
	// Primitive Value Types
	public static final String BOOLEAN = "Boolean";
	public static final String COMPLEX = "Complex";
	public static final String INTEGER = "Integer";
	public static final String NUMBER = "Number";
	public static final String REAL = "Real";
	public static final String STRING = "String";
	public static final String ELEMENT_VALUE = "ElementValue";
	
	public static final Map<String, String> primitiveValueTypeIdsByName = new HashMap<String, String>(Map.of(
			BOOLEAN, "_16_5_1_12c903cb_1245415335546_39033_4086",
			COMPLEX, "_11_5EAPbeta_be00301_1147431846238_895928_1691",
			INTEGER, "_16_5_1_12c903cb_1245415335546_8641_4088",
			REAL, "_16_5_1_12c903cb_1245415335546_535327_4089",
			NUMBER, "_11_5EAPbeta_be00301_1147431819399_50461_1671",
			STRING, "_16_5_1_12c903cb_1245415335546_479030_4092"
		));
	
	public static final Set<String> primitiveValueTypeNames = new HashSet<String> (Arrays.asList(
			BOOLEAN,
			COMPLEX,
			INTEGER,
			REAL,
			NUMBER,
			STRING
	));
	
	public static final Set<String> primitiveValueTypeIDs = new HashSet<String> (Arrays.asList(
			"_16_5_1_12c903cb_1245415335546_39033_4086",		// Boolean
			"_11_5EAPbeta_be00301_1147431846238_895928_1691",	// Complex
		    "_16_5_1_12c903cb_1245415335546_8641_4088",			// Integer
			"_16_5_1_12c903cb_1245415335546_535327_4089",		// Number
		    "_11_5EAPbeta_be00301_1147431819399_50461_1671",	// Real
		    "_16_5_1_12c903cb_1245415335546_479030_4092"		// String
	));
	
	public static boolean isElementOrDiagram(String elementType) {
		if(SYSML_ELEMENTS.contains(elementType) || SYSML_DIAGRAMS.contains(elementType)) {
			return true;
		}
		return false;
	}
}
