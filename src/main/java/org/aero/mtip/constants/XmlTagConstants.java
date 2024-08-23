/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

<<<<<<< HEAD
This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.constants;

// Helper class with constants used in formatting the import/export XML.
public class XmlTagConstants {
	// XML Tags
	public static final String DATA = "data";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String CAMEO = "cameo";
	public static final String ATTRIBUTE = "attribute";
	public static final String ATTRIBUTES = "attributes";
	public static final String RELATIONSHIPS = "relationships";
	public static final String CLIENT = "client";
	public static final String SUPPLIER = "supplier";
	public static final String HUDDLE_ID = "huddle";
	public static final String CLASSIFIED_BY = "classifiedBy";
	public static final String TYPED_BY = "typedBy";
	public static final String HAS_PARENT = "hasParent";
	public static final String HAS_REL = "hasRel";
	public static final String STEREOTYPE_TAG = "stereotype";
	public static final String DISPLAY_AS = "displayAs";
	public static final String DISPLAY_AS_DIAGRAM = "Diagram";
	public static final String ELEMENT = "element";
	public static final String RELATIONSHIP = "relationship";
	public static final String RELATIONSHIP_METADATA = "relationship_metadata";
	public static final String TOP = "top";
	public static final String BOTTOM = "bottom";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String DIAGRAM_CONNECTOR = "diagramConnector";
	public static final String DIAGRAM_PARENT = "diagramParent";
	public static final String RELATIONSHIP_PROPERTY = "property";
	public static final String RELATIONSHIP_ENUMERATION = "enumeration";
	public static final String BEHAVIOR = "behavior";
	public static final String STEREOTYPE_TAGGED_VALUE = "stereotypeTaggedValue";
	public static final String TAGGED_VALUE_NAME = "taggedValueName";
	public static final String TAGGED_VALUE_TYPE = "taggedValueType";
	public static final String TAGGED_VALUE_VALUE = "value";
	public static final String TAGGED_VALUE_VALUES = "values";
	public static final String CLIENT_MULTIPLICITY = "clientMultiplicity";
	public static final String SUPPLIER_MULTIPLICITY = "supplierMultiplicity";
	
	// Diagram Connector Path Point Tags
	public static final String SUPPLIER_POINT = "supplierPoint";
	public static final String CLIENT_POINT = "clientPoint";
	public static final String BREAK_POINT = "breakPoint";
	public static final String X_COORDINATE = "xCoordinate";
	public static final String Y_COORDINATE = "yCoordinate";
	
	// XML Tag Attributes
	public static final String ATTRIBUTE_NAME = "name";
	public static final String ATTRIBUTE_KEY = "key";
	public static final String ATTRIBUTE_DATA_TYPE = "_dtype";
	public static final String ATTRIBUTE_TYPE_STRING = "str";
	public static final String ATTRIBUTE_TYPE_DICT = "dict";
	public static final String ATTRIBUTE_TYPE_LIST = "list";
	public static final String ATTRIBUTE_TYPE_INT = "int";
	public static final String ATTRIBUTE_TYPE_FLOAT = "float";
	public static final String ATTRIBUTE_TYPE_BOOL = "boolean";
	public static final String ATTRIBUTE_KEY_VALUE = "value";
	public static final String ATTRIBUTE_KEY_STEREOTYPE = "stereotype";
	public static final String ATTRIBUTE_KEY_STEREOTYPE_NAME = "stereotypeName";
	public static final String ATTRIBUTE_KEY_STEREOTYPE_ID = "stereotypeId";
	public static final String ATTRIBUTE_KEY_PROFILE_NAME = "profileName";
	public static final String ATTRIBUTE_KEY_PROFILE_ID = "profileId";
	public static final String ATTRIBUTE_KEY_DOCUMENTATION = "documentation";
	public static final String ATTRIBUTE_KEY_DEFAULT_VALUE = "defaultValue";
	public static final String ATTRIBUTE_KEY_MULTIPLICITY = "multiplicity";
	public static final String ATTRIBUTE_KEY_BODY = "body";
	public static final String ATTRIBUTE_KEY_TEXT = "text";
	public static final String ATTRIBUTE_KEY_ID = "id";
	public static final String ATTRIBUTE_KEY_DIRECTION = "direction";
	public static final String ATTRIBUTE_KEY_MIN = "min";
	public static final String ATTRIBUTE_KEY_MAX = "max";
	public static final String ATTRIBUTE_INDEX_0 = "0";
	public static final String ATTRIBUTE_INDEX_1 = "1";
	public static final String ATTRIBUTE_INDEX_2 = "2";
	public static final String ATTRIBUTE_KEY_INSTANCE_VALUE = "instanceValue";
	
	public static final String ATTRIBUTE_NAME_INTERACTION_OPERATOR_KIND = "interactionOperatorKind";
	public static final String ATTRIBUTE_NAME_INTERACTION_OPERAND = "interactionOperand";
	public static final String ATTRIBUTE_NAME_MESSAGE_KIND = "messageKind";
	public static final String ATTRIBUTE_NAME_MESSAGE_SORT = "messageSort";
	public static final String ATTRIBUTE_NAME_RECEIVE_EVENT = "receiveEvent";
	public static final String ATTRIBUTE_NAME_SEND_EVENT = "sendEvent";
	public static final String ATTRIBUTE_NAME_COVERED_BY = "coveredBy";
	public static final String ATTRIBUTE_NAME_TYPED_BY = "typedBy";
	public static final String ATTRIBUTE_NAME_REPRESENTS = "represents";
	public static final String ATTRIBUTE_NAME_ACTIVITY = "activity";
	public static final String ATTRIBUTE_NAME_COLUMN_ELEMENT_TYPE = "columnElementType";
	public static final String ATTRIBUTE_NAME_ROW_ELEMENT_TYPE = "rowElementType";
	public static final String ATTRIBUTE_NAME_DECISION_INPUT = "decisionInput";
	public static final String ATTRIBUTE_NAME_INTERRUPTIBLE_ACTIVITY_REGION = "interruptibleActivityRegion";
	public static final String ATTRIBUTE_NAME_WHEN = "when";
	public static final String ATTRIBUTE_NAME_OBSERVATION = "observation";
	public static final String ATTRIBUTE_NAME_EXPR = "expr";
	public static final String ATTRIBUTE_NAME_SPECIFICATION = "specification";
	public static final String ATTRIBUTE_CONSTRAINED_ELEMENT = "constrainedElement";
	
	// Tagged Value types
    public static final String TV_TYPE_BOOLEAN = "boolean";
    public static final String TV_TYPE_INTEGER = "int";
    public static final String TV_TYPE_REAL = "float";
    public static final String TV_TYPE_STRING = "string";
    public static final String TV_TYPE_ELEMENT = "element";
    public static final String TV_TYPE_ENUMERATION_LITERAL = "enumerationLiteral";
	
	public static final String COVERED_BY = "coveredBy";
	
	public static final String RELATIONSHIP_DEFAULT_VALUE= "defaultValueElement";
	public static final String MESSAGE_NUMBER = "messageNumber";
	
	//Matrix Tags
	public static final String ROW_SCOPE = "rowScope";
	public static final String COLUMN_SCOPE = "columnScope";
	public static final String ROW_ELEMENT_TYPE = "rowElementType";
	public static final String COLUMN_ELEMENT_TYPE = "columnElementType";
	
	public static final String DEPENDENCY_CRITERIA = "dependencyCriteria";
	
	//SysML element text for type tag
	public static final String ACCEPT_EVENT_ACTION = "sysml.AcceptEventAction";
	public static final String ACTION = "sysml.Action";
	public static final String ACTIVITY = "sysml.Activity";
	public static final String ACTIVITY_FINAL_NODE = "sysml.ActivityFinalNode";
	public static final String ACTIVITY_PARAMETER_NODE = "sysml.ActivityParameterNode";
	public static final String ACTIVITY_PARTITION = "sysml.ActivityPartition";
	public static final String ACTOR = "sysml.Actor";
	public static final String ANY_RECEIVE_EVENT = "sysml.AnyReceiveEvent";
	public static final String ASSOCIATION_BLOCK = "sysml.AssociationBlock";
	public static final String BLOCK = "sysml.Block";
	public static final String BOUND_REFERENCE = "sysml.BoundReference";
	public static final String BUSINESS_REQUIREMENT = "sysml.BusinessRequirement";
	public static final String CALL_BEHAVIOR_ACTION = "sysml.CallBehaviorAction";
	public static final String CALL_EVENT = "sysml.CallEvent";
	public static final String CALL_OPERATION_ACTION = "sysml.CallOperationAction";
	public static final String CENTRAL_BUFFER_NODE = "sysml.CentralBufferNode";
	public static final String CHANGE_EVENT = "sysml.ChangeEvent";
	public static final String CHOICE_PSEUDO_STATE = "sysml.ChoicePseudoState";
	public static final String CLASS = "sysml.Class";
	public static final String CLASSIFIER_BEHAVIOR_PROPERTY = "sysml.ClassifierBehaviorProperty";
	public static final String COLLABORATION = "sysml.Collaboration";
	public static final String COMBINED_FRAGMENT = "sysml.CombinedFragment";
	public static final String COMMENT = "sysml.Comment";
	public static final String CONDITIONAL_NODE = "sysml.ConditionalNode";
	public static final String CONNECTION_POINT_REFERENCE = "sysml.ConnectionPointReference";
	public static final String CONSTRAINT = "sysml.Constraint";
	public static final String CONSTRAINT_BLOCK = "sysml.ConstraintBlock";
	public static final String CONSTRAINT_PARAMETER = "sysml.ConstraintParameter";
	public static final String CONSTRAINT_PROPERTY = "sysml.ConstraintProperty";
	public static final String CREATE_OBJECT_ACTION = "sysml.CreateObjectAction";
	public static final String CUSTOMIZATION = "cameo.Customization";
	public static final String DATA_STORE_NODE = "sysml.DataStoreNode";
	public static final String DATA_TYPE = "sysml.DataType";
	public static final String DECISION_NODE = "sysml.DecisionNode";
	public static final String DEEP_HISTORY = "sysml.DeepHistory";
	public static final String DESIGN_CONSTRAINT = "sysml.DesignConstraint";
	public static final String DESTROY_OBJECT_ACTION = "sysml.DestroyObjectAction";
	public static final String DESTRUCTION_OCCURRENCE_SPECIFICATION = "sysml.DestructionOccurrenceSpecification";
	public static final String DURATION = "sysml.Duration";
	public static final String DURATION_CONSTRAINT = "sysml.DurationConstraint";
	public static final String DURATION_INTERVAL = "sysml.DurationInterval";
	public static final String DURATION_OBSERVATION = "sysml.DurationObservation";
	public static final String ENTRY_POINT = "sysml.EntryPoint";
	public static final String ENUMERATION = "sysml.Enumeration";
	public static final String ENUMERATION_LITERAL = "sysml.EnumerationLiteral";
	public static final String EXIT_POINT = "sysml.ExitPoint";
	public static final String EXTENDED_REQUIREMENT = "sysml.ExtendedRequirement";
	public static final String EXTENSION_POINT = "sysml.ExtensionPoint";
	public static final String FINAL_STATE = "sysml.FinalState";
	public static final String FLOW_FINAL_NODE = "sysml.FlowFinalNode";
	public static final String FLOW_PORT = "sysml.FlowPort";
	public static final String FLOW_PROPERTY = "sysml.FlowProperty";
	public static final String FLOW_SPECIFICATION = "sysml.FlowSpecification";
	public static final String FORK = "sysml.Fork";
	public static final String FORK_NODE = "sysml.ForkNode";
	public static final String FULL_PORT = "sysml.FullPort";
	public static final String FUNCTIONAL_REQUIREMENT = "sysml.FunctionalRequirement";
	public static final String FUNCTION_BEHAVIOR = "sysml.FunctionBehavior";
	public static final String INCLUDE = "sysml.Include";
	public static final String INFORMATION_ITEM = "sysml.InformationItem";
	public static final String INITIAL_NODE = "sysml.InitialNode";
	public static final String INITIAL_PSEUDO_STATE = "sysml.InitialPseudoState";
	public static final String INPUT_PIN = "sysml.InputPin";
	public static final String INSTANCESPECIFICATION = "sysml.InstanceSpecification";
	public static final String INTERACTION = "sysml.Interaction";
	public static final String INTERACTION_OPERAND = "sysml.InteractionOperand";
	public static final String INTERACTION_USE = "sysml.InteractionUse";
	public static final String INTERFACE = "sysml.Interface";
	public static final String INTERFACE_REALIZATION = "sysml.InterfaceRealization";
	public static final String INTERFACE_BLOCK = "sysml.InterfaceBlock";
	public static final String INTERFACE_REQUIREMENT = "sysml.InterfaceRequirement";
	public static final String INTERRUPTIBLE_ACTIVITY_REGION = "sysml.InterruptibleActivityRegion";
	public static final String JOIN = "sysml.Join";
	public static final String JOIN_NODE = "sysml.JoinNode";
	public static final String LIFELINE = "sysml.Lifeline";
	public static final String LINK = "sysml.Link";
	public static final String LOOP_NODE = "sysml.LoopNode";
//	public static final String OBJECTNODE = "sysml.ObjetNode;
	public static final String OPAQUE_ACTION = "sysml.OpaqueAction";
	public static final String OUTPUT_PIN = "sysml.OutputPin";
	public static final String MERGE_NODE = "sysml.MergeNode";
	public static final String MESSAGE = "sysml.Message";
	public static final String MESSAGE_OCCURRENCE_SPECIFICATION = "sysml.MessageOccurrenceSpecification";
	public static final String METACLASS = "sysml.Metaclass";
	public static final String MODEL = "sysml.Model";
	public static final String OPAQUE_BEHAVIOR = "sysml.OpaqueBehavior";
	public static final String OPAQUE_EXPRESSION = "sysml.OpaqueExpression";
	public static final String OPERATION = "sysml.Operation";
	public static final String PARTICIPANT_PROPERTY = "sysml.ParticipantProperty";
	public static final String PART_PROPERTY = "sysml.PartProperty";
	public static final String PACKAGE = "sysml.Package";
	public static final String PERFORMANCE_REQUIREMENT = "sysml.PerformanceRequirement";
	public static final String PHYSICAL_REQUIREMENT = "sysml.PhysicalRequirement";
	public static final String PORT = "sysml.Port";
	public static final String PROFILE = "sysml.Profile";
	public static final String PROPERTY = "sysml.Property";
	public static final String PROXY_PORT = "sysml.ProxyPort";
	public static final String QUANTITY_KIND = "sysml.QuantityKind";
	public static final String REFERENCE_PROPERTY = "sysml.ReferenceProperty";
	public static final String REGION = "sysml.Region";
	public static final String REQUIREMENT = "sysml.Requirement";
	public static final String SEND_SIGNAL_ACTION = "sysml.SendSignalAction";
	public static final String SHALLOW_HISTORY = "sysml.ShallowHistory";
	public static final String SIGNAL = "sysml.Signal";
	public static final String SIGNAL_EVENT = "sysml.SignalEvent";
	public static final String SLOT = "sysml.Slot";
	public static final String STAKEHOLDER = "sysml.Stakeholder";
	public static final String STATE = "sysml.State";
	public static final String STATEINVARIANT = "sysml.StateInvariant";
	public static final String STATEMACHINE = "sysml.StateMachine";
	public static final String STEREOTYPE = "sysml.Stereotype";
	public static final String STRUCTURED_ACTIVITY_NODE = "sysml.StructuredActivityNode";
	public static final String TERMINATE = "sysml.Terminate";
	public static final String TERM = "sysml.Term";
	public static final String TIMECONSTRAINT = "sysml.TimeConstraint";
	public static final String TIMEEVENT = "sysml.TimeEvent";
	public static final String TIMEEXPRESSION = "sysml.TimeExpression";
	public static final String TIMEOBSERVATION = "sysml.TimeObservation";
	public static final String TRIGGER = "sysml.Trigger";
	public static final String UNIT = "sysml.Unit";
	public static final String USABILITY_REQUIREMENT = "sysml.UsabilityRequirement";
	public static final String USECASE = "sysml.UseCase";
	public static final String VALUEPROPERTY = "sysml.ValueProperty";
	public static final String VALUETYPE = "sysml.ValueType";
	public static final String VIEW = "sysml.View";
	public static final String VIEWPOINT = "sysml.Viewpoint";

	//SysML relationships
	public static final String ABSTRACTION = "sysml.Abstraction";
	public static final String AGGREGATION = "sysml.Aggregation";
	public static final String ALLOCATE = "sysml.Allocate";
	public static final String ASSOCIATION = "sysml.Association";
	public static final String BINDINGCONNECTOR = "sysml.BindingConnector";
	public static final String COMPOSITION = "sysml.Composition";
	public static final String CONNECTOR = "sysml.Connector";
	public static final String CONTROLFLOW = "sysml.ControlFlow";
	public static final String COPY = "sysml.Copy";
	public static final String DEPENDENCY = "sysml.Dependency";
	public static final String DERIVEREQUIREMENT = "sysml.DeriveRequirement";
	public static final String EXTEND = "sysml.Extend";
	public static final String EXTENSION = "sysml.Extension";
	public static final String GENERALIZATION = "sysml.Generalization";
	public static final String INFORMATIONFLOW = "sysml.InformationFlow";
	public static final String ITEMFLOW = "sysml.ItemFlow";
	public static final String OBJECTFLOW = "sysml.ObjectFlow";
	public static final String PACKAGEIMPORT = "sysml.PackageImport";
	public static final String REFINE = "sysml.Refine";
	public static final String SATISFY = "sysml.Satisfy";
	public static final String TRACE = "sysml.Trace";
	public static final String TRANSITION = "sysml.Transition";
	public static final String USAGE = "sysml.Usage";
	public static final String VERIFY = "sysml.Verify";
	public static final String SYSML_PARAMETER = "sysml.Parameter";
	
	public static final String PARAMETER = "parameter";
	
	//Tables
	public static final String GENERIC_TABLE = "sysml.GenericTable";
	public static final String INSTANCE_TABLE = "sysml.InstanceTable";
	public static final String GLOSSARY_TABLE = "sysml.GlossaryTable";
	public static final String METRIC_TABLE = "sysml.MetricTable";
	public static final String REQUIREMENT_TABLE = "sysml.RequirementTable";
	
	//Matrices
	public static final String ALLOCATION_MATRIX = "sysml.AllocationMatrix";
	public static final String DEPENDENCY_MATRIX = "sysml.DependencyMatrix";
	public static final String DERIVE_REQUIREMENT_MATRIX = "sysml.DeriveRequirementMatrix";
	public static final String REFINE_REQUIREMENT_MATRIX = "sysml.RefineRequirementMatrix";
	public static final String SATISFY_REQUIREMENT_MATRIX = "sysml.SatisfyRequirementMatrix";
	public static final String VERIFY_REQUIREMENT_MATRIX = "sysml.VerifyRequirementMatrix";
	
	//Stereotypes
	public static final String DOMAINSTEREOTYPE = "Domain";
	public static final String EXTERNALSTEREOTYPE = "External";
	public static final String SUBSYSTEMSTEREOTYPE = "Subsystem";
	public static final String SYSTEMCONTEXTSTEREOTYPE = "System context";
	public static final String SYSTEMSTEREOTYPE = "System";
	
	//SysML Diagrams
	public static final String BLOCKDEFINITIONDIAGRAM = "sysml.BlockDefinitionDiagram";
	public static final String ACTIVITYDIAGRAM = "sysml.ActivityDiagram";
	public static final String STATEMACHINEDIAGRAM = "sysml.StateMachineDiagram";
	public static final String USECASEDIAGRAM = "sysml.UseCaseDiagram";
	public static final String INTERNALBLOCKDIAGRAM = "sysml.InternalBlockDiagram";
	public static final String REQUIREMENTSDIAGRAM = "sysml.RequirementsDiagram";
	public static final String SEQUENCEDIAGRAM = "sysml.SequenceDiagram";
	public static final String PARAMETRICDIAGRAM = "sysml.ParametricDiagram";
	public static final String PACKAGEDIAGRAM = "sysml.PackageDiagram";
	public static final String PROFILEDIAGRAM = "sysml.ProfileDiagram";
	public static final String CLASSDIAGRAM = "sysml.ClassDiagram";
	
	//UAF Element text for XML type tag
		// Strategic
	public static final String ACHIEVER = "uaf.Achiever";
	public static final String ACTUAL_ENDURING_TASK = "uaf.ActualEnduringTask";
	public static final String ACTUAL_ENTERPRISE_PHASE = "uaf.ActualEnterprisePhase";
	public static final String CAPABILITY = "uaf.Capability";
	public static final String CAPABILITY_PROPERTY = "uaf.CapabilityProperty";
	public static final String DESIRER = "uaf.Desirer";
	public static final String ENDURING_TASK = "uaf.EnduringTask";
	public static final String ENTERPRISE_PHASE = "uaf.EnterprisePhase";
	public static final String ENTERPIRSE_VISION = "uaf.EnterpriseVision";
	public static final String STRUCTURAL_PART = "uaf.StructuralPart";
	public static final String TEMPORAL_PART = "uaf.TemporalPart";
	public static final String VISION_STATEMENT = "uaf.VisionStatement";
	public static final String WHOLE_LIFE_ENTERPRISE = "uaf.WholeLifeEnterprise";
	public static final String ENTERPRISE_GOAL = "uaf.EnterpriseGoal";

	//Operational
	public static final String CONCEPT_ROLE = "uaf.ConceptRole";
	public static final String HIGH_LEVEL_OPERATIONAL_CONCEPT = "uaf.HighLevelOperationalConcept";
	public static final String INFORMATION_ELEMENT = "uaf.InformationElement";
	public static final String KNOWN_RESOURCE = "uaf.KnownResource";
	public static final String OPERATIONAL_ACTIVITY = "uaf.OperationalActivity";
	public static final String OPERATIONAL_ACTIVITY_ACTION = "uaf.OperationalActivityAction";
	public static final String OPERATIONAL_AGENT = "uaf.OperationalAgent";
	public static final String OPERATIONAL_ARCHITECTURE = "uaf.OperationalArchitecture";
	public static final String OPERATIONAL_INTERFACE = "uaf.OperationalInterface";
	public static final String OPERATIONAL_PERFORMER = "uaf.OperationalPerformer";
	public static final String OPERATIONAL_ROLE= "uaf.OperationalRole";
	public static final String OPERATIONAL_SIGNAL = "uaf.OperationalSignal";
	public static final String OPERATIONAL_SIGNAL_PROPERTY = "uaf.OperationalSignalProperty";
	public static final String STANDARD_OPERATIONAL_ACTIVITY = "uaf.StandardOperationalActivity";
	public static final String OPERATIONAL_METHOD = "uaf.OperationalMethod";
	public static final String PROBLEM_DOMAIN = "uaf.ProblemDomain";
	public static final String OPERATIONAL_CONSTRAINT = "uaf.OperationalConstraint";
	public static final String OPERATIONAL_STATE_DESCRIPTION = "uaf.OperationalStateDescription";
	public static final String OPERATIONAL_PARAMETER = "uaf.OperationalParameter";
	public static final String OPERATIONAL_EXCHANGE_KIND = "uaf.OperationalExchangeKind";
	public static final String OPERATIONAL_ACTION = "uaf.OperationalAction";
	public static final String OPERATIONAL_ASSOCIATION = "uaf.OperationalAssociation";
	
	public static final String OPERATIONAL_PROCESS_FLOW = "cameo.OperationalProcessFlow";
	public static final String OPERATIONAL_PROCESSES_DIAGRAM="uaf.OperationalProcesses";
	
	public static final String OPERATIONAL_CONNECTIVITY = "uaf.OperationalConnectivity";
	public static final String OPERATIONAL_CONSTRAINTS_DEFINITION ="uaf.OperationalConstraintsDefinition";
	public static final String OPERATIONAL_FREE_FORM_TAXONOMY="uaf.OperationalFreeFormTaxonomy";
	
	public static final String OPERATIONAL_STRUCTURE="uaf.OperationalStructure";
	public static final String OPERATIONAL_TAXONOMY="uaf.OperationalTaxonomy";
	public static final String OPERATIONAL_HIGH_LEVEL_TAXONOMY = "uaf.OperationalTaxonomy";
	public static final String OPERATIONAL_INTERACTION_SCENARIOS = "uaf.OperationalInteractionScenarios";
	public static final String OPERATIONAL_INTERNAL_CONNECTIVITY = "uaf.OperationalInternalConnectivity";
	public static final String OPERATIONAL_PARAMETRIC = "uaf.OperationalParametric";
	public static final String OPERATIONAL_STATES = "uaf.OperationalStates";
	
	//Resources
	public static final String CAPABILITY_CONFIGURATION = "uaf.CapabilityConfiguration";
	public static final String NATURAL_RESOURCE = "uaf.NaturalResource";
	public static final String RESOURCE_ARCHITECTURE = "uaf.ResourceArchitecture";
	public static final String RESOURCE_ARTIFACT = "uaf.ResourceArtifact";
	public static final String SOFTWARE = "uaf.Software";
	public static final String SYSTEM = "uaf.System";
	public static final String RESOURCE_INTERFACE = "uaf.ResourceInterface";
	public static final String DATA_ELEMENT = "uaf.DataElement";
	public static final String TECHNOLOGY = "uaf.Technology";
	public static final String WHOLE_LIFE_CONFIGURATION = "uaf.WholeLifeConfiguration";
	public static final String RESOURCE_METHOD = "uaf.ResourceMethod";
	public static final String RESOURCE_PARAMETER = "uaf.ResourceParameter";
	public static final String RESOURCE_PORT = "uaf.ResourcePort";
	public static final String RESOURCE_ROLE = "uaf.ResourceRole";
	public static final String ROLE_KIND = "uaf.RoleKind";
	public static final String RESOURCE_EXCHANGE_KIND = "uaf.ResourceExchangeKind";
	public static final String RESOURCE_SIGNAL = "uaf.ResourceSignal";
	public static final String RESOURCE_SIGNAL_PROPERTY = "uaf.ResourceSignalProperty";
	public static final String FUNCTION = "uaf.Function";
	public static final String FUNCTION_ACTION = "uaf.FunctionAction";
	public static final String RESOURCE_STATE_DESCRIPTION = "uaf.ResourceStateDescription";
	public static final String RESOURCE_CONSTRAINT = "uaf.ResourceConstraint";
	public static final String WHOLE_LIFE_CONFIGURATION_KIND = "uaf.WholeLifeConfigurationKind";
	public static final String RESOURCE_ACTION = "uaf.ResourceAction";
	
	public static final String RESOURCES_PROCESS_FLOW = "uaf.ResourcesProcessFlow";
	
	//Projects
	public static final String ACTUAL_MILESTONE_KIND = "uaf.ActualMilestoneKind";
	public static final String PROJECT =" uaf.Project";
	public static final String PROJECT_KIND = "uaf.ProjectKind";
	public static final String PROJECT_MILESTONE = "uaf.ProjectMilestone";
	public static final String PROJECT_MILESTONE_ROLE = "uaf.ProjectMilestoneRole";
	public static final String PROJECT_ROLE = "uaf.ProjectRole";
	public static final String PROJECT_THEME = "uaf.ProjectTheme";
	public static final String PROJECT_ACTIVITY = "uaf.ProjectActivity";
	public static final String PROJECT_ACTIVITY_ACTION = "uaf.ProjectActivityAction";
	public static final String PROJECT_STATUS = "uaf.ProjectStatus";
	public static final String ACTUAL_PROJECT_MILESTONE_ROLE = "uaf.ActualProjectMilestoneRole";
	public static final String ACTUAL_PROJECT_ROLE = "uaf.ActualProjectRole";
	public static final String STATUS_INDICATORS = "uaf.StatusIndicators";
	public static final String ACTUAL_PROJECT = "uaf.ActualProject";
	public static final String ACTUAL_PROJECT_MILESTONE = "uaf.ActualProjectMilestone";
	
	//Dictionary
	public static final String DEFINITION = "uaf.Definition";
	public static final String ALIAS = "uaf.alias";
	public static final String INFORMATION = "uaf.Information";
	
	//Security
	public static final String ACTUAL_RISK = "uaf.ActualRisk";
	public static final String AFFECTS = "uaf.Affects";
	public static final String AFFECTS_IN_CONTEXT = "uaf.AffectsInContext";
	public static final String ENHANCED_SECURITY_CONTROL = "uaf.EnhancedSecurityControl";
	public static final String ENHANCES = "uaf.Enhances";
	public static final String MITIGATES = "uaf.Mitigates";
	public static final String OPERATIONAL_MITIGATION = "uaf.OperationalMitigation";
	public static final String OWNS_RISK = "uaf.OwnsRisk";
	public static final String OWNS_RISK_IN_CONTEXT = "uaf.OwnsRiskInContext";
	public static final String PROTECTS = "uaf.Protects";
	public static final String PROTECTS_IN_CONTEXT = "uaf.ProtectsInContext";
	public static final String RESOURCE_MITIGATION = "uaf.ResourceMitigation";
	public static final String RISK = "uaf.Risk";
	public static final String SECURITY_CONSTRAINT = "uaf.SecurityConstraint";
	public static final String SECURITY_CONTROL = "uaf.SecurityControl";
	public static final String SECURITY_CONTROL_FAMILY = "uaf.SecurityControlFamily";
	public static final String SECURITY_ENCLAVE = "uaf.SecurityEnclave";
	public static final String SECURITY_PROCESS = "uaf.SecurityProcess";
	public static final String SECURITY_PROCESS_ACTION = "uaf.SecurityProcessAction";
	
	//UAF Relationships
	//Strategic
	public static final String CAPABILITY_FOR_TASK = "uaf.CapabilityForTask";
	public static final String DESIRED_EFFECT = "uaf.DesiredEffect";
	public static final String ACHIEVED_EFFECT = "uaf.AchievedEffect";
	public static final String EXHIBITS = "uaf.Exhibits";
	public static final String ORGANIZATION_IN_ENTERPRISE="uaf.OrganizationInEnterprise";
	public static final String MAPS_TO_CAPABILITY ="uaf.MapsToCapability";
	//Operational
	public static final String ARBITRARY_CONNECTOR = "uaf.ArbitraryConnector";
	public static final String OPERATIONAL_CONTROL_FLOW = "uaf.OperationalControlFlow";
	public static final String OPERATIONAL_OBJECT_FLOW = "uaf.OperationalObjectFlow";
	public static final String OPERATIONAL_CONNECTOR = "uaf.OperationalConnector";
	public static final String OPERATIONAL_EXCHANGE = "uaf.OperationalExchange";
	//public static final String OPERATIONAL_MESSAGE = "uaf.OperationalMessage";
	public static final String OPERATIONAL_PORT = "uaf.OperationalPort";
	/*public static final String OPERATIONAL_CONNECTOR = "uaf.OperationalConnector";
	public static final String INFORMATION_FLOW = "uaf.InformationFlow";
	public static final String OBJECT_FLOW = "uaf.ObjectFlow";*/
	
	//Resources
	public static final String RESOURCE_CONNECTOR = "uaf.ResourceConnector";
	public static final String RESOURCE_EXCHANGE = "uaf.ResourceExchange";
	public static final String FUNCTION_CONTROL_FLOW = "uaf.FunctionControlFlow";
	public static final String FUNCTION_OBJECT_FLOW = "uaf.FunctionObjectFlow";
	//public static final String RESOURCE_MESSAGE = "uaf.ResourceMessage";
	
	public static final String FORECAST = "uaf.Forecast";
	public static final String VERSION_SUCCESSION = "uaf.VersionSuccession";
	public static final String VERSION_OF_CONFIGURATION = "uaf.VersionOfConfiguration";
	
	
	//Project
	public static final String MILESTONE_DEPENDENCY = "uaf.MilestoneDependency";
	public static final String PROJECT_SEQUENCE = "uaf.ProjectSequence";
	
	//Services
	public static final String CONSUMES = "uaf.Consumes";
	public static final String SERVICE_CONNECTOR = "uaf.ServiceConnector";
	public static final String SERVICE_FUNCTION = "uaf.ServiceFunction";
	public static final String SERVICE_FUNCTION_ACTION = "uaf.ServiceFunctionAction";
	public static final String SERVICE_INTERFACE = "uaf.ServiceInterface";
	public static final String SERVICE_MESSAGE = "uaf.ServiceMessage";
	public static final String SERVICE_METHOD = "uaf.ServiceMethod";
	public static final String SERVICE_PARAMETER = "uaf.ServiceParamter";
	public static final String SERVICE_POLICY = "uaf.ServicePolicy";
	public static final String SERVICE_PORT = "uaf.ServicePort";
	public static final String SERVICE_SPECIFICATION = "uaf.ServiceSpecification";
	public static final String SERVICE_SPECIFICATION_ROLE = "uaf.ServiceSpecificationRole";
	public static final String SERVICE_STATE_DESCRIPTION = "uaf.ServiceStateDescription";
	
	//Standards
	public static final String PROTOCOL = "uaf.Protocol";
	public static final String PROTOCOL_STACK = "uaf.ProtocolStack";
	public static final String STANDARD = "uaf.Standard";
	public static final String PROTOCOL_LAYER = "uaf.ProtocolLayer";
	

	
	//Dictionary
	public static final String SAME_AS = "uaf.SameAs";

	// Actual Resources
	public static final String ACTUAL_ORGANIZATION = "uaf.ActualOrganization";
	public static final String ACTUAL_PERSON = "uaf.ActualPerson";
	public static final String ACTUAL_POST = "uaf.ActualPost";
	public static final String ACTUAL_RESOURCE = "uaf.ActualResource";
	public static final String ACTUAL_RESOURCE_RELATIONSHIP = "uaf.ActualResourceRelationship";
	public static final String ACTUAL_RESPONSIBILITY = "uaf.ActualResponsibility";
	public static final String ACTUAL_SERVICE = "uaf.ActualService";
	public static final String FIELDED_CAPABILITY = "uaf.FieldedCapability";
	public static final String FILLS_POST = "uaf.FillsPost";
	public static final String OWNS_PROCESS = "uaf.OwnsProcess";
	public static final String PROVIDED_SERVICE_LEVEL = "uaf.ProvidedServiceLevel";
	public static final String PROVIDES_COMPETENCE = "uaf.ProvidesCompetence";
	public static final String REQUIRED_SERVICE_LEVEL = "uaf.RequiredServiceLevel";
	
	//Personnel
	public static final String COMMAND = "uaf.Command";
	public static final String COMPETENCE = "uaf.Competence";
	public static final String COMPETENCE_FOR_ROLE = "uaf.CompetenceForRole";
	public static final String COMPETENCE_TO_CONDUCT = "uaf.CompetenceToConduct";
	public static final String CONTROL = "uaf.Control";
	public static final String ORGANIZATION = "uaf.Organization";
	public static final String PERSON = "uaf.Person";
	public static final String POST = "uaf.Post";
	public static final String REQUIRES_COMPETENCE = "uaf.RequiresCompetence";
	public static final String RESPONSIBILITY = "uaf.Responsibility";

	//Parameters //TODO
	public static final String ACTUAL_CONDITION = "uaf.ActualCondition"; 
	public static final String ACTUAL_ENVIRONMENT = "uaf.ActualEnvironment";
	public static final String ACTUAL_LOCATION = "uaf.ActualLocation";
	public static final String ACTUAL_MEASUREMENT_SET = "uaf.ActualMeasurementSet";
	public static final String ACTUAL_PROPERTY_SET = "uaf.ActualPropertySet";
	public static final String ENVIRONMENT_PROPERTY = "uaf.EnvironmentProperty";
	public static final String ACTUAL_MEASUREMENT = "uaf.ActualMeasurement";
	public static final String ENVIRONMENT = "uaf.Environment";
	public static final String ENVIRONMENT_KIND = "uaf.EnvironmentKind";
	public static final String CONDITION = "uaf.Condition";
	public static final String GEO_POLITICAL_EXTENT_TYPE = "uaf.GeoPoliticalExtentType";
	public static final String LOCATION = "uaf.Location";
	public static final String MEASUREMENT_SET = "uaf.MeasurementSet";
	public static final String MEASUREMENT = "uaf.Measurement";
	
	//Metadata
	public static final String METADATA = "uaf.Metadata";
	public static final String IMPLEMENTS = "uaf.Implements";
	public static final String PERFORMS_IN_CONTEXT ="uaf.PerformsInContext";
	public static final String DATA_MODEL_KIND = "uaf.DataModelKind";

	
	public static final String STRATEGIC_TAXONOMY_PACKAGE = "uaf.StrategicTaxonomyPackage";
	
	// UAF Diagrams
	
	// Actual Resources Diagrams
	public static final String ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM = "uaf.ActualResourcesConnectivity";
	public static final String ACTUAL_RESOURCES_STRUCTURE_DIAGRAM = "uaf.ActualResourcesStructure";
	
	// Personnel Diagrams
	public static final String PERSONNEL_CONNECTIVITY_DIAGRAM = "uaf.PersonnelConnectivity";
	public static final String PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM = "uaf.PersonnelInteractionScenarios";
	public static final String PERSONNEL_INTERNAL_CONNECTIVITY = "cameo.PersonnelInternalConnectivity";
	public static final String PERSONNEL_PROCESSES_DIAGRAM = "uaf.PersonnelProcesses";
	public static final String PERSONNEL_PROCESSES_FLOW_DIAGRAM = "uaf.PersonnelProcessesFlow";
	public static final String PERSONNEL_STATES_DIAGRAM = "uaf.PersonnelStates";
	public static final String PERSONNEL_STRUCTURE_DIAGRAM = "uaf.PersonnelStructure";
	public static final String PERSONNEL_TAXONOMY_DIAGRAM = "uaf.PersonnelTaxonomy";

	//Projects Diagrams
	public static final String PROJECTS_TAXONOMY_DIAGRAM = "uaf.ProjectsTaxonomy";
	public static final String PROJECTS_STRUCTURE_DIAGRAM = "uaf.ProjectsStructure";
	public static final String PROJECTS_CONNECTIVITY_DIAGRAM = "uaf.ProjectsConnectivity";
	public static final String PROJECTS_PROCESSES_DIAGRAM = "uaf.ProjectsProcesses";
	
	//Resources Diagrams
	public static final String RESOURCES_CONNECTIVITY_DIAGRAM = "uaf.ResourcesConnectivity";
	public static final String RESOURCES_INTERACTION_SCENARIOS_DIAGRAM = "uaf.ResourcesInteractionScenarios";
	public static final String RESOURCES_PROCESSES_DIAGRAM = "uaf.ResourcesProcesses";
	public static final String RESOURCES_STATES_DIAGRAM = "uaf.ResourcesStates";
	public static final String RESOURCES_STRUCTURE_DIAGRAM = "uaf.ResourcesStructure";
	public static final String RESOURCES_TAXONOMY_DIAGRAM = "uaf.ResourcesTaxonomy";
	
	// Standards Diagrams
	public static final String STANDARDS_TAXONOMY_DIAGRAM = "uaf.StandardsTaxonomy";
	public static final String STANDARDS_STRUCTURE_DIAGRAM = "uaf.StandardsStructure";

	// Security Diagrams
	public static final String SECURITY_TAXONOMY_DIAGRAM = "uaf.SecurityTaxonomy";
	public static final String SECURITY_STRUCTURE_DIAGRAM = "uaf.SecurityStructure";
	public static final String SECURITY_CONNECTIVITY_DIAGRAM = "uaf.SecurityConnectivity";
	public static final String SECURITY_PROCESSES_DIAGRAM ="uaf.SecurityProcesses";
	public static final String SECURITY_PROCESSES_FLOW_DIAGRAM = "uaf.SecurityProcesses";
	public static final String SECURITY_CONSTRAINTS_DIAGRAM = "uaf.SecurityConstraints";
	
	// Services
	public static final String SERVICES_CONNECTIVITY_DIAGRAM = "uaf.ServicesConnectivity";
	public static final String SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM = "uaf.ServicesConstraintsDefinition";
	public static final String SERVICES_INTERACTION_SCENARIOS_DIAGRAM = "uaf.ServicesInteractionScenarios";
	public static final String SERVICES_PROCESSES_DIAGRAM = "uaf.ServicesProcesses";
	public static final String SERVICES_STATES_DIAGRAM = "uaf.ServicesStates";
	public static final String SERVICES_STRUCTURE_DIAGRAM = "uaf.ServicesStructure";
	public static final String SERVICES_TAXONOMY_DIAGRAM = "uaf.ServicesTaxonomy";
	
	// Strategic Diagrams
	public static final String STRATEGIC_TAXONOMY_DIAGRAM = "uaf.StrategicTaxonomy";
	public static final String STRATEGIC_STRUCTURE_DIAGRAM = "uaf.StrategicStructure";
	public static final String STRATEGIC_CONNECTIVITY_DIAGRAM = "uaf.StrategicConnectivity";
	public static final String STRATEGIC_STATES_DIAGRAM = "uaf.StrategicStates";
	public static final String STRATEGIC_CONSTRAINTS_DIAGRAM = "uaf.StrategicConstraints";
	
	//Environment
	public static final String ENVIRONMENT_DIAGRAM="uaf.ParametersEnvironment";
	
	//Summary & Overview
	public static final String SUMMARY_AND_OVERVIEW_DIAGRAM = "uaf.SummaryandOverview";
	
	// Uncategorized UAF
	public static final String IS_CAPABLE_TO_PERFORM = "uaf.IsCapableToPerform";
	
	// DoDAF Diagram Types
	// Capability Views

	public static final String CV1 = "dodaf.CV-1";
	public static final String CV2 = "dodaf.CV-2";
	public static final String CV3 = "dodaf.CV-3";
	public static final String CV4 = "dodaf.CV-4";
	public static final String CV5 = "dodaf.CV-5";
	public static final String CV6 = "dodaf.CV-6";
	public static final String CV7 = "dodaf.CV-7";
	
	// Systems Views
	public static final String SV1 = "dodaf.SV-1";
	public static final String SV2 = "dodaf.SV-2";
	public static final String SV3 = "dodaf.SV-3";
	public static final String SV4 = "dodaf.SV-4";
	public static final String SV5A = "dodaf.SV-5a";
	public static final String SV5B = "dodaf.SV-5b";
	public static final String SV5C = "dodaf.SV-5c";
	public static final String SV6 = "dodaf.SV-6";
	public static final String SV7 = "dodaf.SV-7";
	public static final String SV8 = "dodaf.SV-8";
	public static final String SV9 = "dodaf.SV-9";
	public static final String SV10A = "dodaf.SV-10a";
	public static final String SV10B = "dodaf.SV-10b";
	public static final String SV10C = "dodaf.SV-10c";
	
	// Operational Views
	public static final String OV1 = "dodaf.OV-1";
	public static final String OV2 = "dodaf.OV-2";
	public static final String OV3 = "dodaf.OV-3";
	public static final String OV4 = "dodaf.OV-4";
	public static final String OV5A = "dodaf.OV-5a";
	public static final String OV5B = "dodaf.OV-5b";
	public static final String OV6A = "dodaf.OV-6a";
	public static final String OV6B = "dodaf.OV-6b";
	public static final String OV6C = "dodaf.OV-6c";
	
	// All Views
	public static final String AV1 = "dodaf.AV-1";
	public static final String AV2 = "dodaf.AV-2";
	
	// Data and Information Viewpoints
	public static final String DIV1 = "dodaf.DIV-1";
	public static final String DIV2 = "dodaf.DIV-2";
	public static final String DIV3 = "dodaf.DIV-3";
	
	// Project Viewpoints
	public static final String PV1 = "dodaf.PV-1";
	public static final String PV2 = "dodaf.PV-2";
	public static final String PV3 = "dodaf.PV-3";
	
	// Standards Viewpoints
	public static final String STDV1 = "dodaf.StdV-1";
	public static final String STDV2 = "dodaf.StdV-2";
	
	//XML Tags
	public static final String CLIENT_ID = "client_id";
	public static final String SUPPLIER_ID = "supplier_id";
	public static final String RELATIONSHIP_STEREOTYPE = "relationshipStereotype";
	public static final String BODY = "body";
	public static final String LANGUAGE = "language";
	public static final String SIGNATURE_TAG = "signature";
	public static final String SIGNAL_TAG = "signal";
	public static final String EVENT_TAG = "event";
	public static final String TRIGGER_TAG = "trigger";
	public static final String ACCEPT_EVENT_ACTION_TAG = "acceptEventAction";
	public static final String OPERATION_TAG = "operation";
	public static final String INVARIANT_TAG = "invariant";
	public static final String PROFILE_TAG = "profile";
	public static final String ID_TAG = "id";
	public static final String ASSOCIATION_TAG = "association";
	public static final String ASSOCIATION_PART_PROPERTY_ID = "associationForPartProperty";
	public static final String SUPPLIER_CONNECTOR_END_TAG = "supplierConnectorEnd";
	public static final String CLIENT_CONNECTOR_END_TAG = "clientConnectorEnd";
	public static final String SUPPLIER_PART_WITH_PORT = "supplierPartWithPort";
	public static final String CLIENT_PART_WITH_PORT = "clientPartWithPort";
	public static final String CLASSIFIER_TYPE = "classifierType";
	public static final String SUBMACHINE = "submachine";
	public static final String DO_ACTIVITY = "doActivity";
	public static final String ENTRY = "entry";
	public static final String EXIT = "exit";
	public static final String GUARD = "guard";
	public static final String PRIMITIVE_VALUE_TYPE = "PrimitiveValueType";
	public static final String SWIMLANE = "swimlane";
	
	//Model Creation Type Constants
	public static final String ELEMENTS_FACTORY = "ElementsFactory";
	public static final String CLASS_WITH_STEREOTYPE = "ClassWithStereotype";
	public static final String SOURCE_TARGET = "SourceTarget";
	
	//SysML nomenclature, but not elements
	public static final String APPLIEDSTEREOTYPE = "appliedStereotype";
	public static final String TAG = "tag";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String ELEMENT_ID = "elementID";
	public static final String PARENT_ID = "parentID";
	public static final String SYNC_ELEMENT = "syncElement";
	
	//Type Prefixes
	public static final String CUSTOM = "custom.Diagram";
	
	//List of sysmlTags
	public static final String[] sysmlElementList = {
			ACTIVITY,
			ACTIVITY_PARAMETER_NODE,
			BLOCK,
			CLASS,
			PACKAGE,
			PROFILE,
			STEREOTYPE,
			PROPERTY,
			PARAMETER
	};
}
