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
	public static final String COMMENT = "sysml.Comment";
	public static final String CONDITIONALNODE = "sysml.ConditionalNode";
	public static final String CONNECTIONPOINTREFERENCE = "sysml.ConnectionPointReference";
	public static final String CONSTRAINT = "sysml.Constraint";
	public static final String CONSTRAINTBLOCK = "sysml.ConstraintBlock";
	public static final String CONSTRAINTPARAMETER = "sysml.ConstraintParameter";
	public static final String CONSTRAINTPROPERTY = "sysml.ConstraintProperty";
	public static final String CREATEOBJECTACTION = "sysml.CreateObjectAction";
	public static final String CUSTOMIZATION = "cameo.Customization";
	public static final String DATASTORENODE = "sysml.DataStoreNode";
	public static final String DECISIONNODE = "sysml.DecisionNode";
	public static final String DEEPHISTORY = "sysml.DeepHistory";
	public static final String DESIGNCONSTRAINT = "sysml.DesignConstraint";
	public static final String DESTROYOBJECTACTION = "sysml.DestroyObjectAction";
	public static final String DESTRUCTIONOCCURRENCESPECIFICATION = "sysml.DestructionOccurrenceSpecification";
	public static final String DURATIONCONSTRAINT = "sysml.DurationConstraint";
	public static final String DURATIONOBSERVATION = "sysml.DurationObservation";
	public static final String ENTRYPOINT = "sysml.EntryPoint";
	public static final String ENUMERATION = "sysml.Enumeration";
	public static final String ENUMERATIONLITERAL = "sysml.EnumerationLiteral";
	public static final String EXITPOINT = "sysml.ExitPoint";
	public static final String EXTENDEDREQUIREMENT = "sysml.ExtendedRequirement";
	public static final String EXTENSIONPOINT = "sysml.ExtensionPoint";
	public static final String FINALSTATE = "sysml.FinalState";
	public static final String FLOWFINALNODE = "sysml.FlowFinalNode";
	public static final String FLOWPORT = "sysml.FlowPort";
	public static final String FLOWPROPERTY = "sysml.FlowProperty";
	public static final String FLOWSPECIFICATION = "sysml.FlowSpecification";
	public static final String FORK = "sysml.Fork";
	public static final String FORKNODE = "sysml.ForkNode";
	public static final String FULLPORT = "sysml.FullPort";
	public static final String FUNCTIONALREQUIREMENT = "sysml.FunctionalRequirement";
	public static final String FUNCTIONBEHAVIOR = "sysml.FunctionBehavior";
	public static final String INCLUDE = "sysml.Include";
	public static final String INFORMATIONITEM = "sysml.InformationItem";
	public static final String INITIALNODE = "sysml.InitialNode";
	public static final String INITIALPSEUDOSTATE = "sysml.InitialPseudoState";
	public static final String INPUTPIN = "sysml.InputPin";
	public static final String INSTANCESPECIFICATION = "sysml.InstanceSpecification";
	public static final String INTERACTION = "sysml.Interaction";
	public static final String INTERACTIONOPERAND = "sysml.InteractionOperand";
	public static final String INTERACTIONUSE = "sysml.InteractionUse";
	public static final String INTERFACE = "sysml.Interface";
	public static final String INTERFACEBLOCK = "sysml.InterfaceBlock";
	public static final String INTERFACEREQUIREMENT = "sysml.InterfaceRequirement";
	public static final String INTERRUPTIBLEACTIVITYREGION = "sysml.InterruptibleActivityRegion";
	public static final String JOIN = "sysml.Join";
	public static final String JOINNODE = "sysml.JoinNode";
	public static final String LIFELINE = "sysml.Lifeline";
	public static final String LINK = "sysml.Link";
	public static final String LOOPNODE = "sysml.LoopNode";
//	public static final String OBJECTNODE = "sysml.ObjetNode;
	public static final String OPAQUEACTION = "sysml.OpaqueAction";
	public static final String OUTPUTPIN = "sysml.OutputPin";
	public static final String MERGENODE = "sysml.MergeNode";
	public static final String MESSAGE = "sysml.Message";
	public static final String MESSAGEOCCURRENCESPECIFICATION = "sysml.MessageOccurrenceSpecification";
	public static final String METACLASS = "sysml.Metaclass";
	public static final String MODEL = "sysml.Model";
	public static final String OPAQUEBEHAVIOR = "sysml.OpaqueBehavior";
	public static final String OPAQUEEXPRESSION = "sysml.OpaqueExpression";
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
	public static final String REFERENCEPROPERTY = "sysml.ReferenceProperty";
	public static final String REGION = "sysml.Region";
	public static final String REQUIREMENT = "sysml.Requirement";
	public static final String SENDSIGNALACTION = "sysml.SendSignalAction";
	public static final String SHALLOWHISTORY = "sysml.ShallowHistory";
	public static final String SIGNAL = "sysml.Signal";
	public static final String SIGNALEVENT = "sysml.SignalEvent";
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
	public static final String ACTUAL_ENTERPRISE_PHASE = "uaf.ActualEnterprisePhase";
	public static final String CAPABILITY = "uaf.Capability";
	public static final String CAPABILITY_PROPERTY = "uaf.CapabilityProperty";
	public static final String ENTERPRISE_PHASE = "uaf.EnterprisePhase";
	public static final String ENTERPIRSE_VISION = "uaf.EnterpriseVision";
	public static final String WHOLE_LIFE_ENTERPRISE = "uaf.WholeLifeEnterprise";
	public static final String ENDURING_TASK = "uaf.EnduringTask";
	public static final String DESIRER = "uaf.Desirer";
	public static final String ENTERPRISE_VISION = "uaf.EnterpriseVision";
	public static final String VISION_STATEMENT = "uaf.VisionStatement";
	public static final String STRUCTURAL_PART = "uaf.StructuralPart";
	public static final String TEMPORAL_PART = "uaf.TemporalPart";
	public static final String ACTUAL_ENDURING_TASK = "uaf.ActualEnduringTask";
	public static final String ACHIEVER = "uaf.Achiever";
		//Operational
	public static final String HIGH_LEVEL_OPERATIONAL_CONCEPT = "uaf.HighLevelOperationalConcept";
	public static final String KNOWN_RESOURCE = "uaf.KnownResource";
	public static final String OPERATIONAL_AGENT = "uaf.OperationalAgent";
	public static final String OPERATIONAL_ARCHITECTURE = "uaf.OperationalArchitecture";
	public static final String OPERATIONAL_PERFORMER = "uaf.OperationalPerformer";
	public static final String OPERATIONAL_INTERFACE = "uaf.OperationalInterface";
	public static final String INFORMATIONAL_ELEMENT = "uaf.InformationalElement";
	
	public static final String OPERATIONAL_ROLE= "uaf.OperationalRole";
	public static final String OPERATIONAL_ACTIVITY = "uaf.OperationalActivity";
	public static final String OPERATIONAL_ACTIVITY_ACTION = "uaf.OperationalActivityAction";
	
	//UAF Relationships
	public static final String CAPABILITY_FOR_TASK = "uaf.CapabilityForTask";
	public static final String DESIRED_EFFECT = "uaf.DesiredEffect";
	public static final String ACHIEVED_EFFECT = "uaf.AchievedEffect";
	public static final String EXHIBITS = "uaf.Exhibits";
	public static final String ORGANIZATION_IN_ENTERPRISE="uaf.OrganizationInEnterprise";
	public static final String MAPS_TO_CAPABILITY ="uaf.MapsToCapability";
	//Operational
	/*public static final String OPERATIONAL_CONTROL_FLOW = "uaf.OperationalControlFlow";
	public static final String OPERATIONAL_CONNECTOR = "uaf.OperationalConnector";
	public static final String INFORMATION_FLOW = "uaf.InformationFlow";
	public static final String OBJECT_FLOW = "uaf.ObjectFlow";*/
			
	
	
	public static final String STRATEGIC_TAXONOMY_PACKAGE = "uaf.StrategicTaxonomyPackage";
	
	//UAF Diagram text for XML type tag
	public static final String STRATEGIC_TAXONOMY = "uaf.StrategicTaxonomy";
	
	
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
