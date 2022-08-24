/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.util;

import java.util.HashMap;
import java.util.Map;

public class SysmlConstants {
	//Categorization constants
	public static final String SYSML = "SysML";
	public static final String ELEMENT = "Element";
	public static final String RELATIONSHIP = "Relationship";
	public static final String DIAGRAM = "Diagram";
	
	//Sysml Element constants
	public static final String ACCEPTEVENTACTION = "AcceptEventAction";
	public static final String ACTION = "Action";
	public static final String ACTIVITY = "Activity";
	public static final String ACTIVITYFINALNODE = "ActivityFinalNode";
	public static final String ACTIVITYPARAMETERNODE = "ActivityParameterNode"; // Sysml specifies as ActivityParameterNode
	public static final String ACTIVITYPARTITION = "ActivityPartition";
	public static final String ACTOR = "Actor";
	public static final String ASSOCIATIONBLOCK = "AssociationBlock";
	public static final String BOUNDREFERENCE = "BoundReference";
	public static final String BLOCK = "Block";
	public static final String CALLBEHAVIORACTION = "CallBehaviorAction";
	public static final String CALLOPERATIONACTION = "CallOperationAction";
	public static final String CENTRALBUFFERNODE = "CentralBufferNode";
	public static final String CHANGEEVENT = "ChangeEvent";
	public static final String CHOICEPSEUDOSTATE = "ChoicePseudoState";
	public static final String CLASS = "Class";
	public static final String CLASSIFIERBEHAVIORPROPERTY = "ClassifierBehaviorProperty";
	public static final String COLLABORATION = "Collaboration";
	public static final String COMBINEDFRAGMENT = "CombinedFragment";
	public static final String COMMENT = "Comment";
	public static final String CONDITIONALNODE = "ConditionalNode";
	public static final String CONNECTIONPOINTREFERENCE = "ConnectionPointReference";
	public static final String CONSTRAINT = "Constraint";
	public static final String CONSTRAINTBLOCK = "ConstraintBlock";
	public static final String CONSTRAINTPARAMETER = "ConstraintParameter";
	public static final String CONSTRAINTPROPERTY = "ConstraintProperty";
	public static final String CREATEOBJECTACTION = "CreateObjectAction";
	public static final String CUSTOMIZATION = "Customization";
	public static final String DATASTORENODE = "DataStoreNode";
	public static final String DECISIONNODE = "DecisionNode";
	public static final String DEEPHISTORY = "DeepHistory";
	public static final String DESIGNCONSTRAINT = "DesignConstraint";
	public static final String DESTROYOBJECTACTION = "DestroyObjectAction";
	public static final String DESTRUCTIONOCCURRENCESPECIFICATION = "DestructionOccurrenceSpecification";
	public static final String DIRECTED_FEATURE = "DirectedFeature";
	public static final String DURATIONCONSTRAINT = "DurationConstraint";
	public static final String DURATIONOBSERVATION = "DurationObservation";
	public static final String ENTRYPOINT = "EntryPoint";
	public static final String ENUMERATION = "Enumeration";
	public static final String ENUMERATIONLITERAL = "EnumerationLiteral";
	public static final String EXITPOINT = "ExitPoint";
	public static final String EXTENDEDREQUIREMENT = "ExtendedRequirement";
	public static final String EXTENSIONPOINT = "ExtensionPoint";
	public static final String FINALSTATE = "FinalState";
	public static final String FLOWFINALNODE = "FlowFinalNode";
	public static final String FLOWPORT = "FlowPort";
	public static final String FLOWPROPERTY = "FlowProperty";
	public static final String FLOWSPECIFICATION = "FlowSpecification";
	public static final String FORK = "Fork";
	public static final String FORKNODE = "ForkNode";
	public static final String FULLPORT = "FullPort";
	public static final String FUNCTIONALREQUIREMENT = "FunctionalRequirement";
	public static final String FUNCTIONBEHAVIOR = "FunctionBehavior";
	public static final String INCLUDE = "Include";
	public static final String INFORMATIONITEM = "InformationItem";
	public static final String INITIALNODE = "InitialNode";
	public static final String INITIALPSEUDOSTATE = "InitialPseudoState";
	public static final String INPUTPIN = "InputPin";
	public static final String INSTANCESPECIFICATION = "InstanceSpecification";
	public static final String INSTANCEVALUE = "InstanceValue";
	public static final String INTERACTION = "Interaction";
	public static final String INTERACTIONOPERAND = "InteractionOperand";
	public static final String INTERACTIONUSE = "InteractionUse";
	public static final String INTERFACE = "Interface";
	public static final String INTERFACEBLOCK = "InterfaceBlock";
	public static final String INTERFACEREALIZATION = "InterfaceRealization";
	public static final String INTERFACEREQUIREMENT = "InterfaceRequirement";
	public static final String INTERRUPTIBLEACTIVITYREGION = "InterruptibleActivityRegion";
	public static final String JOIN = "Join";
	public static final String JOINNODE = "JoinNode";
	public static final String LIFELINE = "Lifeline";
	public static final String LINK = "Link";
	public static final String LOOPNODE = "LoopNode";
	public static final String MERGENODE = "MergeNode";
	public static final String MESSAGE = "Message";
	public static final String MESSAGEOCCURRENCESPECIFICATION = "MessageOccurrenceSpecification";
	public static final String METACLASS = "Metaclass";
	public static final String MODEL = "Model";
	public static final String OPAQUEACTION = "OpaqueAction";
	public static final String OPAQUEBEHAVIOR = "OpaqueBehavior";
	public static final String OPAQUEEXPRESSION = "OpaqueExpression";
	public static final String OPERATION = "Operation";
	public static final String OUTPUTPIN = "OutputPin";
	public static final String PACKAGE = "Package";
	public static final String PARAMETER = "Parameter";
	public static final String PARTICIPANTPROPERTY = "ParticipantProperty";
	public static final String PARTPROPERTY = "PartProperty";
	public static final String PERFORMANCEREQUIREMENT = "PerformanceRequirement";
	public static final String PHYSICALREQUIREMENT = "PhysicalRequirement";
	public static final String PORT = "Port";
	public static final String PROFILE = "Profile";
	public static final String PROPERTY = "Property";
	public static final String PROXYPORT = "ProxyPort";
	public static final String QUANTITYKIND = "QuantityKind";
	public static final String REFERENCEPROPERTY = "ReferenceProperty";
	public static final String REGION = "Region";
	public static final String REQUIREMENT = "Requirement";
	public static final String SENDSIGNALACTION = "SendSignalAction";
	public static final String SIGNALEVENT = "SignalEvent";
	public static final String SHALLOWHISTORY = "ShallowHistory";
	public static final String SIGNAL = "Signal";
	public static final String SLOT = "Slot";
	public static final String STAKEHOLDER = "Stakeholder";
	public static final String STATE = "State";
	public static final String STATEINVARIANT = "StateInvariant";
	public static final String STATEMACHINE = "StateMachine";
	public static final String STEREOTYPE = "Stereotype";
	public static final String TERMINATE = "Terminate";
	public static final String TERM = "Term";
	public static final String TIMECONSTRAINT = "TimeConstraint";
	public static final String TIMEEVENT = "TimeEvent";
	public static final String TIMEEXPRESSION = "TimeExpression";
	public static final String TIMEOBSERVATION = "TimeObservation";
	public static final String TRIGGER = "Trigger";
	public static final String UNIT = "Unit";
	public static final String USECASE = "UseCase";
	public static final String VALUEPROPERTY = "ValueProperty";
	public static final String VALUETYPE = "ValueType";
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
	public static final String SYSTEM = "CameoSystem";
	public static final String SYSTEMCONTEXT = "SystemContext";

//	public static final String TRIGGER = "Trigger";
//	public static final String NOTE = "Note";
//	public static final String PROFILE = "Profile";
//	public static final String PARAMETER = "Parameter";
	
	//Sysml Relationship Constants
	public static final String ABSTRACTION = "Abstraction";
	public static final String AGGREGATION = "Aggregation";
	public static final String ALLOCATE = "Allocate";
	public static final String ASSOCIATION = "Association";
	public static final String BINDINGCONNECTOR = "BindingConnector";
	public static final String COMPOSITION = "Composition";
	public static final String CONNECTOR = "Connector";
	public static final String CONTROLFLOW = "ControlFlow";
	public static final String COPY = "Copy";
	public static final String DEPENDENCY = "Dependency";
	public static final String DERIVEREQUIREMENT = "DeriveRequirement";
	public static final String EXTEND = "Extend";
	public static final String EXTENSION = "Extension";
	public static final String GENERALIZATION = "Generalization";
	public static final String INFORMATIONFLOW = "InformationFlow";
	public static final String ITEMFLOW = "ItemFlow";
	public static final String OBJECTFLOW = "ObjectFlow";
	public static final String PACKAGEIMPORT = "PackageImport";
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
	public static final String RESERVELINK = "Link";
	public static final String RESERVEQUANTITYKIND = "Quantity Kind";
	public static final String RESERVEUNIT = "Unit";
	
	public static final String[] SYSMLELEMENTS = {
			ACCEPTEVENTACTION,
			ACTION,
			ACTIVITY,
			ACTIVITYFINALNODE,
			ACTIVITYPARAMETERNODE,
			ACTIVITYPARTITION,
			ACTOR,
			ASSOCIATIONBLOCK,
			BLOCK,
			BOUNDREFERENCE,
			CALLBEHAVIORACTION,
			CALLOPERATIONACTION,
			CENTRALBUFFERNODE,
			CHANGEEVENT,
			CHOICEPSEUDOSTATE,
			CLASS,
			COLLABORATION,
			COMBINEDFRAGMENT,
//			COMMENT, Filtered out for correct import of Terms
			CONDITIONALNODE,
			CONNECTIONPOINTREFERENCE,
			CONSTRAINT,
			CONSTRAINTBLOCK,
			CONSTRAINTPARAMETER,
			CONSTRAINTPROPERTY,
			CLASSIFIERBEHAVIORPROPERTY,
			CREATEOBJECTACTION,
			CUSTOMIZATION,
			DATASTORENODE,
			DECISIONNODE,
			DEEPHISTORY,
			DESIGNCONSTRAINT,
			DESTROYOBJECTACTION,
			DESTRUCTIONOCCURRENCESPECIFICATION,
			DURATIONCONSTRAINT,
			DURATIONOBSERVATION,
			ENTRYPOINT,
			ENUMERATION,
			ENUMERATIONLITERAL,
			EXITPOINT,
			EXTENDEDREQUIREMENT,
			EXTENSIONPOINT,
			FINALSTATE,
			FLOWFINALNODE,
			FLOWPORT,
			FLOWPROPERTY,
			FLOWSPECIFICATION,
			FORK,
			FORKNODE,
			FULLPORT,
			FUNCTIONALREQUIREMENT,
			FUNCTIONBEHAVIOR,
			INFORMATIONITEM,
			INITIALNODE,
			INITIALPSEUDOSTATE,
			INPUTPIN,
			INSTANCESPECIFICATION,
			INTERACTION,
			INTERACTIONOPERAND,
			INTERFACE,
			INTERACTIONUSE,
			INTERFACEBLOCK,
			INTERFACEREQUIREMENT,
			INTERRUPTIBLEACTIVITYREGION,
			JOIN,
			JOINNODE,
			LIFELINE,
			LINK,
			LOOPNODE,
			MERGENODE,
			MESSAGEOCCURRENCESPECIFICATION,
			METACLASS,
			MODEL,
			MESSAGE,
			OPAQUEACTION,
			OPAQUEBEHAVIOR,
			OPAQUEEXPRESSION,
			OPERATION,
			OUTPUTPIN,
			PACKAGE,
			PARAMETER,
			PARTPROPERTY,
			PARTICIPANTPROPERTY,
			PERFORMANCEREQUIREMENT,
			PHYSICALREQUIREMENT,
			PORT,
			PROFILE,
			PROPERTY,
			PROXYPORT,
			QUANTITYKIND,
			REGION,
			REFERENCEPROPERTY,
			REQUIREMENT,
			SHALLOWHISTORY,
			SENDSIGNALACTION,
			SIGNAL,
			SIGNALEVENT,
			SLOT,
			STAKEHOLDER,
			STATE,
			STATEINVARIANT,
			STATEMACHINE,
			STEREOTYPE,
			TERMINATE,
			TERM,
			TIMECONSTRAINT,
			TIMEEVENT,
			TIMEEXPRESSION,
			TIMEOBSERVATION,
			TRIGGER,
			UNIT,
			USECASE,
			VALUEPROPERTY,
			VALUETYPE,	
			VIEW,
			VIEWPOINT,
			
			
			// Table treated as Element - cannot be cast to Diagram
			GENERIC_TABLE,
			GLOSSARY_TABLE,
			INSTANCE_TABLE,
			METRIC_TABLE,
			REQUIREMENT_TABLE,
	};
	
	public static final String[] RESERVEINSTANCESPECIFICATION = {
			RESERVELINK,
			RESERVEQUANTITYKIND,
			RESERVEUNIT,
	};
	
	public static final String[] SYSMLRELATIONSHIPS = {
			ABSTRACTION,
			AGGREGATION,
			ALLOCATE,
			ASSOCIATION,
			BINDINGCONNECTOR,
			COMPOSITION,
			CONNECTOR,
			CONTROLFLOW,
			COPY,
			DEPENDENCY,
			DERIVEREQUIREMENT,
			EXTEND,
			EXTENSION,
			GENERALIZATION,
			INCLUDE,
			INFORMATIONFLOW,
			ITEMFLOW,
			OBJECTFLOW,
			PACKAGEIMPORT,
			REFINE,
			SATISFY,
			TRACE,
			TRANSITION,
			USAGE,
			VERIFY
	};

	public static final String[] SYSMLDIAGRAMS = {
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
	
	public static final String[] BDD_TYPES = {
			ASSOCIATIONBLOCK,
			BLOCK,
			CLASS,
			CONSTRAINTBLOCK,
			DOMAIN,
			ENUMERATION,
			EXTERNAL,
			FLOWPORT,
			FLOWSPECIFICATION,
			FULLPORT,
			INFORMATIONITEM,
			INSTANCESPECIFICATION,
			INTERACTION,
			INTERFACE,
			INTERFACEBLOCK,
			//NOTE, // not supported?
			OPERATION,
			PACKAGE,
			PACKAGEIMPORT,
			PARTPROPERTY,
			PORT,
			PROPERTY,
			PROXYPORT,
			QUANTITYKIND,
			REQUIREMENT,
			SIGNAL,
			STAKEHOLDER,
			SUBSYSTEM,
			SYSTEM,
			SYSTEMCONTEXT,
			UNIT,
			USECASE,
			VALUEPROPERTY,
			VALUETYPE,
			VIEW,
			VIEWPOINT
	};


	public static final String[] IBD_TYPES = {
			ASSOCIATIONBLOCK,
			CONSTRAINTBLOCK,
			DOMAIN,
			ENUMERATION,
			EXTERNAL,
			FLOWPORT,
			FLOWSPECIFICATION,
			FULLPORT,
			INSTANCESPECIFICATION,
			INTERFACE,
			INTERFACEBLOCK,
			//NOTE, // not supported?
			OPERATION,
			PACKAGE,
			PARTPROPERTY,
			PORT,
			PROXYPORT,
			QUANTITYKIND,
			SIGNAL,
			SUBSYSTEM,
			SYSTEM,
			SYSTEMCONTEXT,
			UNIT,
			VALUEPROPERTY,
			VALUETYPE
	};

	public static final String[] REQ_TYPES = {
			ACTIVITY,
			BLOCK,
			COPY,
			DERIVEREQUIREMENT,
			DESIGNCONSTRAINT,
			EXTENDEDREQUIREMENT,
			FUNCTIONALREQUIREMENT,
			INTERFACEREQUIREMENT,
			PACKAGE,
			PERFORMANCEREQUIREMENT,
			PHYSICALREQUIREMENT,
			REFINE,
			REQUIREMENT,
			SATISFY,
			TRACE,
			VERIFY
	};
	
	public static final String[] STM_TYPES = {
			CHOICEPSEUDOSTATE,
			CONNECTIONPOINTREFERENCE,
			DEEPHISTORY,
			ENTRYPOINT,
			EXITPOINT,
			FINALSTATE,
			FORK,
			FUNCTIONBEHAVIOR,
			INITIALPSEUDOSTATE,
			JOIN,
			"PseudoState", // verify with Trent
			REGION,
			SHALLOWHISTORY,
			STATE,
			STATEMACHINE,
			TERMINATE,
			TRANSITION,
			TRIGGER
	};
	
	public static final String[] ACT_TYPES = {
			ACCEPTEVENTACTION,
			ACTION,
			ACTIVITY,
			ACTIVITYFINALNODE,
			ACTIVITYPARAMETERNODE,
			ACTIVITYPARTITION,
			CALLBEHAVIORACTION,
			CALLOPERATIONACTION,
			CENTRALBUFFERNODE,
			CHANGEEVENT,
			CONDITIONALNODE,
			CONTROLFLOW,
			CREATEOBJECTACTION,
			DATASTORENODE,
			DECISIONNODE,
			DESTROYOBJECTACTION,
			FLOWFINALNODE,
			FORKNODE,
			INITIALNODE,
			INPUTPIN,
			JOINNODE,
			LOOPNODE,
			MERGENODE,
			OBJECTFLOW,
			"ObjectNode", //OBJECTNODE,
			OPAQUEACTION,
			OUTPUTPIN,
			SENDSIGNALACTION,
			TIMEEVENT
	};
	
	public static final String[] SEQ_TYPES = {
			COMBINEDFRAGMENT,
			COLLABORATION,
			DESTRUCTIONOCCURRENCESPECIFICATION,
			DURATIONCONSTRAINT,
			INTERACTION,
			INTERACTIONOPERAND,
			INTERACTIONUSE,
			LIFELINE,
			MESSAGE,
			PROPERTY,
			STATEINVARIANT,
			TIMECONSTRAINT
	};
	
	public static final String[] UC_TYPES = {
			ACTOR,
			EXTEND,
			INCLUDE,
			USECASE
	};
	
	public static final String[] PAR_TYPES = {
			CONSTRAINTPARAMETER,
			CONSTRAINTPROPERTY,
			PARTPROPERTY,
			PORT,
			PROPERTY,
			PROXYPORT			
	};
	
	public static final String[] PKG_TYPES = {
			//From Block Definition Diagram
			ASSOCIATIONBLOCK,
			BLOCK,
			CLASS,
			CONSTRAINTBLOCK,
			DOMAIN,
			ENUMERATION,
			EXTERNAL,
			FLOWPORT,
			FLOWSPECIFICATION,
			FULLPORT,
			INSTANCESPECIFICATION,
			INTERFACE,
			INTERFACEBLOCK,
			//NOTE, // not supported?
			OPERATION,
			PACKAGE,
			PACKAGEIMPORT,
			PARTPROPERTY,
			PORT,
			PROXYPORT,
			QUANTITYKIND,
			SIGNAL,
			STAKEHOLDER,
			SUBSYSTEM,
			SYSTEM,
			SYSTEMCONTEXT,
			UNIT,
			VALUEPROPERTY,
			VALUETYPE,
			VIEW,
			VIEWPOINT,
			
			//From Requirements Diagram
			COPY,
			DERIVEREQUIREMENT,
			DESIGNCONSTRAINT,
			EXTENDEDREQUIREMENT,
			FUNCTIONALREQUIREMENT,
			INTERFACEREQUIREMENT,
			PERFORMANCEREQUIREMENT,
			PHYSICALREQUIREMENT,
			REFINE,
			REQUIREMENT,
			SATISFY,
			TRACE,
			VERIFY,
			
			//From Use Case Diagram
			ACTOR,
			EXTEND,
			INCLUDE,
			USECASE
	};
	
	public static final String[] PROFILEDIAGRAM_TYPES = {
			CLASS,
			CONSTRAINT,
			CUSTOMIZATION,
			EXTENSION,
			METACLASS,
			OPAQUEEXPRESSION,
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
			OPAQUEEXPRESSION,
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
			QUANTITYKIND,
			UNIT
	};
	
	// Primitive Value Types
	public static final String BOOLEAN = "Boolean";
	public static final String INTEGER = "Integer";
	public static final String REAL = "Real";
	public static final String STRING = "String";
	public static final String ELEMENT_VALUE = "ElementValue";
	
	public static final String[] primitiveValueTypes = {
			BOOLEAN,
			INTEGER,
			REAL,
			STRING
	};
	
	public static final String[] primitiveValueTypeIDs = {
			"16_5_1_12c903cb_1245415335546_39033_4086",
		    "_16_5_1_12c903cb_1245415335546_8641_4088",
		    "_11_5EAPbeta_be00301_1147431819399_50461_1671",
		    "_16_5_1_12c903cb_1245415335546_479030_4092"
	};
}
