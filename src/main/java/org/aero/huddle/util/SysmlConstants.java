package org.aero.huddle.util;

public class SysmlConstants {
	//Categorization constants
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
	public static final String CONDITIONALNODE = "ConditionalNode";
	public static final String CONNECTIONPOINTREFERENCE = "ConnectionPointReference";
	public static final String CONSTRAINT = "Constraint";
	public static final String CONSTRAINTBLOCK = "ConstraintBlock";
	public static final String CONSTRAINTPARAMETER = "ConstraintParameter";
	public static final String CONSTRAINTPROPERTY = "ConstraintProperty";
	public static final String CREATEOBJECTACTION = "CreateObjectAction";
	public static final String CUSTOMIZATION = "RelationshipConstraint";
	public static final String DATASTORENODE = "DataStoreNode";
	public static final String DECISIONNODE = "DecisionNode";
	public static final String DEEPHISTORY = "DeepHistory";
	public static final String DESIGNCONSTRAINT = "DesignConstraint";
	public static final String DESTROYOBJECTACTION = "DestroyObjectAction";
	public static final String ENTRYPOINT = "EntryPoint";
	public static final String ENUMERATION = "Enumeration";
	public static final String EXITPOINT = "ExitPoint";
	public static final String EXTENDEDREQUIREMENT = "ExtendedRequirement";
	public static final String FINALSTATE = "FinalState";
	public static final String FLOWFINALNODE = "FlowFinalNode";
	public static final String FLOWPORT = "FlowPort";
	public static final String FLOWSPECIFICATION = "FlowSpecification";
	public static final String FORK = "Fork";
	public static final String FORKNODE = "ForkNode";
	public static final String FULLPORT = "FullPort";
	public static final String FUNCTIONALREQUIREMENT = "FunctionalRequirement";
	public static final String FUNCTIONBEHAVIOR = "FunctionBehavior";
	public static final String INCLUDE = "Include";
	public static final String INITIALNODE = "InitialNode";
	public static final String INITIALPSEUDOSTATE = "InitialPseudoState";
	public static final String INPUTPIN = "InputPin";
	public static final String INSTANCESPECIFICATION = "InstanceSpecification";
	public static final String INTERACTION = "Interaction";
	public static final String INTERACTIONUSE = "InteractionUse";
	public static final String INTERFACE = "Interface";
	public static final String INTERFACEBLOCK = "InterfaceBlock";
	public static final String INTERFACEREQUIREMENT = "InterfaceRequirement";
	public static final String JOIN = "Join";
	public static final String JOINNODE = "JoinNode";
	public static final String LIFELINE = "Lifeline";
	public static final String LOOPNODE = "LoopNode";
	public static final String MERGENODE = "MergeNode";
	public static final String METACLASS = "Metaclass";
	public static final String MODEL = "Model";
	public static final String OPAQUEACTION = "OpaqueAction";
	public static final String OPAQUEEXPRESSION = "OpaqueExpression";
	public static final String OPERATION = "Operation";
	public static final String OUTPUTPIN = "OutputPin";
	public static final String PACKAGE = "Package";
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
	public static final String REQUIREMENT = "Requirement";
	public static final String SENDSIGNALACTION = "SendSignalAction";
	public static final String SHALLOWHISTORY = "ShallowHistory";
	public static final String SIGNAL = "Signal";
	public static final String STATE = "State";
	public static final String STATEMACHINE = "StateMachine";
	public static final String STEREOTYPE = "Stereotype";
	public static final String TERMINATE = "Terminate";
	public static final String TIMEEVENT = "TimeEvent";
	public static final String TRIGGER = "Trigger";
	public static final String UNIT = "Unit";
	public static final String USECASE = "UseCase";
	public static final String VALUEPROPERTY = "ValueProperty";
	public static final String VALUETYPE = "ValueType";
	
	// Cameo specific "non-normative" block extension
	public static final String DOMAIN = "Domain";
	public static final String EXTERNAL = "External";
	public static final String SUBSYSTEM = "Subsystem";
	public static final String SYSTEM = "System";
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
	public static final String CONNECTOR = "Connector";
	public static final String CONTROLFLOW = "ControlFlow";
	public static final String COPY = "Copy";
	public static final String DEPENDENCY = "Dependency";
	public static final String DERIVEREQUIREMENT = "DeriveRequirement";
	public static final String EXTENSION = "Extension";
	public static final String GENERALIZATION = "Generalization";
	public static final String ITEMFLOW = "ItemFlow";
	public static final String OBJECTFLOW = "ObjectFlow";
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
	
	public static final String SUBMACHINE = "submachine";
	
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
			ENTRYPOINT,
			ENUMERATION,
			EXITPOINT,
			EXTENDEDREQUIREMENT,
			FINALSTATE,
			FLOWFINALNODE,
			FLOWPORT,
			FLOWSPECIFICATION,
			FORK,
			FORKNODE,
			FULLPORT,
			FUNCTIONALREQUIREMENT,
			FUNCTIONBEHAVIOR,
			INITIALNODE,
			INITIALPSEUDOSTATE,
			INPUTPIN,
			INSTANCESPECIFICATION,
			INTERACTION,
			INTERFACE,
			INTERACTIONUSE,
			INTERFACEBLOCK,
			INTERFACEREQUIREMENT,
			JOIN,
			JOINNODE,
			LIFELINE,
			LOOPNODE,
			MERGENODE,
			METACLASS,
			MODEL,
			OPAQUEACTION,
			OPAQUEEXPRESSION,
			OPERATION,
			OUTPUTPIN,
			PACKAGE,
			PARTPROPERTY,
			PARTICIPANTPROPERTY,
			PERFORMANCEREQUIREMENT,
			PHYSICALREQUIREMENT,
			PORT,
			PROFILE,
			PROPERTY,
			PROXYPORT,
			QUANTITYKIND,
			REFERENCEPROPERTY,
			REQUIREMENT,
			SHALLOWHISTORY,
			SENDSIGNALACTION,
			SIGNAL,
			STATE,
			STATEMACHINE,
			STEREOTYPE,
			TERMINATE,
			TIMEEVENT,
			TRIGGER,
			UNIT,
			USECASE,
			VALUEPROPERTY,
			VALUETYPE,			
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
			CONNECTOR,
			CONTROLFLOW,
			COPY,
			DEPENDENCY,
			DERIVEREQUIREMENT,
			EXTENSION,
			GENERALIZATION,
			INCLUDE,
			ITEMFLOW,
			OBJECTFLOW,
			REFINE,
			SATISFY,
			TRACE,
			TRANSITION,
			USAGE,
			VERIFY
	};

	public static final String[] SYSMLDIAGRAMS = {
			BDD,
			STM,
			ACT,
	};
}
