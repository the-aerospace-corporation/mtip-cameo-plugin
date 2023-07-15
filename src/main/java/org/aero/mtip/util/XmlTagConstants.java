/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

// Helper class with constants used in formatting the import/export XML.
public class XmlTagConstants
{
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
	public static final String CLIENT_MULTIPLICITY = "clientMultiplicity";
	public static final String SUPPLIER_MULTIPLICITY = "supplierMultiplicity";
	
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
	public static final String ASSOCIATION_BLOCK = "sysml.AssociationBlock";
	public static final String BLOCK = "sysml.Block";
	public static final String BOUND_REFERENCE = "sysml.BoundReference";
	public static final String BUSINESS_REQUIREMENT = "sysml.BusinessRequirement";
	public static final String CALL_BEHAVIOR_ACTION = "sysml.CallBehaviorAction";
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
	public static final String INTERFACE_BLOCK = "sysml.InterfaceBlock";
	public static final String INTERFACE_REQUIREMENT = "sysml.InterfaceRequirement";
	public static final String INTERRUPTIBLE_ACTIVITY_REGION = "sysml.InterruptibleActivityRegion";
	public static final String INTERFACE_REALIZATION = "sysml.InterfaceRealization";
	public static final String INTERRUPTIBLEACTIVITYREGION = "sysml.InterruptibleActivityRegion";
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
	public static final String TERMINATE = "sysml.Terminate";
	public static final String TERM = "sysml.Term";
	public static final String TIMECONSTRAINT = "sysml.TimeConstraint";
	public static final String TIMEEVENT = "sysml.TimeEvent";
	public static final String TIMEEXPRESSION = "sysml.TimeExpression";
	public static final String TIMEOBSERVATION = "sysml.TimeObservation";
	public static final String TRIGGER = "sysml.Trigger";
	public static final String UNIT = "sysml.Unit";
	public static final String USABILITYREQUIREMENT = "sysml.UsabilityRequirement";
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
	public static final String ELEMENTSFACTORY = "ElementsFactory";
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
}
