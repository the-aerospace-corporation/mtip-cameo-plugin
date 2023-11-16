/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import org.aero.mtip.ModelElements.Activity.AcceptEventAction;
import org.aero.mtip.ModelElements.Activity.Action;
import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.ModelElements.Activity.ActivityFinalNode;
import org.aero.mtip.ModelElements.Activity.ActivityParameterNode;
import org.aero.mtip.ModelElements.Activity.ActivityPartition;
import org.aero.mtip.ModelElements.Activity.CallBehaviorAction;
import org.aero.mtip.ModelElements.Activity.CallOperationAction;
import org.aero.mtip.ModelElements.Activity.CentralBufferNode;
import org.aero.mtip.ModelElements.Activity.ChangeEvent;
import org.aero.mtip.ModelElements.Activity.ConditionalNode;
import org.aero.mtip.ModelElements.Activity.CreateObjectAction;
import org.aero.mtip.ModelElements.Activity.DataStoreNode;
import org.aero.mtip.ModelElements.Activity.DecisionNode;
import org.aero.mtip.ModelElements.Activity.DestroyObjectAction;
import org.aero.mtip.ModelElements.Activity.FlowFinalNode;
import org.aero.mtip.ModelElements.Activity.ForkNode;
import org.aero.mtip.ModelElements.Activity.InitialNode;
import org.aero.mtip.ModelElements.Activity.InputPin;
import org.aero.mtip.ModelElements.Activity.InterruptibleActivityRegion;
import org.aero.mtip.ModelElements.Activity.JoinNode;
import org.aero.mtip.ModelElements.Activity.LoopNode;
import org.aero.mtip.ModelElements.Activity.MergeNode;
import org.aero.mtip.ModelElements.Activity.OpaqueAction;
import org.aero.mtip.ModelElements.Activity.OutputPin;
import org.aero.mtip.ModelElements.Activity.Parameter;
import org.aero.mtip.ModelElements.Activity.SendSignalAction;
import org.aero.mtip.ModelElements.Activity.TimeEvent;
import org.aero.mtip.ModelElements.Activity.TimeExpression;
import org.aero.mtip.ModelElements.Block.AssociationBlock;
import org.aero.mtip.ModelElements.Block.Block;
import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.ModelElements.Block.ConstraintBlock;
import org.aero.mtip.ModelElements.Block.Domain;
import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.ModelElements.Block.External;
import org.aero.mtip.ModelElements.Block.FlowPort;
import org.aero.mtip.ModelElements.Block.FlowSpecification;
import org.aero.mtip.ModelElements.Block.FullPort;
import org.aero.mtip.ModelElements.Block.InformationItem;
import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.ModelElements.Block.Interface;
import org.aero.mtip.ModelElements.Block.InterfaceBlock;
import org.aero.mtip.ModelElements.Block.InterfaceRealization;
import org.aero.mtip.ModelElements.Block.Note;
import org.aero.mtip.ModelElements.Block.Operation;
import org.aero.mtip.ModelElements.Block.PartProperty;
import org.aero.mtip.ModelElements.Block.Port;
import org.aero.mtip.ModelElements.Block.ProxyPort;
import org.aero.mtip.ModelElements.Block.QuantityKind;
import org.aero.mtip.ModelElements.Block.Signal;
import org.aero.mtip.ModelElements.Block.Slot;
import org.aero.mtip.ModelElements.Block.Subsystem;
import org.aero.mtip.ModelElements.Block.System;
import org.aero.mtip.ModelElements.Block.SystemContext;
import org.aero.mtip.ModelElements.Block.Unit;
import org.aero.mtip.ModelElements.Block.ValueProperty;
import org.aero.mtip.ModelElements.Block.ValueType;
import org.aero.mtip.ModelElements.InternalBlock.BoundReference;
import org.aero.mtip.ModelElements.InternalBlock.ClassifierBehaviorProperty;
import org.aero.mtip.ModelElements.InternalBlock.ConstraintParameter;
import org.aero.mtip.ModelElements.InternalBlock.ConstraintProperty;
import org.aero.mtip.ModelElements.InternalBlock.FlowProperty;
import org.aero.mtip.ModelElements.InternalBlock.InternalBlockDiagram;
import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.ModelElements.InternalBlock.ParticipantProperty;
import org.aero.mtip.ModelElements.InternalBlock.ReferenceProperty;
import org.aero.mtip.ModelElements.InternalBlock.RequiredInterface;
import org.aero.mtip.ModelElements.Matrix.AllocationMatrix;
import org.aero.mtip.ModelElements.Matrix.DependencyMatrix;
import org.aero.mtip.ModelElements.Matrix.DeriveRequirementMatrix;
import org.aero.mtip.ModelElements.Matrix.RefineRequirementMatrix;
import org.aero.mtip.ModelElements.Matrix.SatisfyRequirementMatrix;
import org.aero.mtip.ModelElements.Matrix.VerifyRequirementMatrix;
import org.aero.mtip.ModelElements.Profile.Class;
import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.ModelElements.Profile.Constraint;
import org.aero.mtip.ModelElements.Profile.Customization;
import org.aero.mtip.ModelElements.Profile.MetaClass;
import org.aero.mtip.ModelElements.Profile.OpaqueExpression;
import org.aero.mtip.ModelElements.Profile.PackageDiagram;
import org.aero.mtip.ModelElements.Profile.ParametricDiagram;
import org.aero.mtip.ModelElements.Profile.Profile;
import org.aero.mtip.ModelElements.Profile.ProfileDiagram;
import org.aero.mtip.ModelElements.Profile.Stereotype;
import org.aero.mtip.ModelElements.Requirements.DesignConstraint;
import org.aero.mtip.ModelElements.Requirements.ExtendedRequirement;
import org.aero.mtip.ModelElements.Requirements.FunctionalRequirement;
import org.aero.mtip.ModelElements.Requirements.InterfaceRequirement;
import org.aero.mtip.ModelElements.Requirements.PerformanceRequirement;
import org.aero.mtip.ModelElements.Requirements.PhysicalRequirement;
import org.aero.mtip.ModelElements.Requirements.Requirement;
import org.aero.mtip.ModelElements.Requirements.RequirementsDiagram;
import org.aero.mtip.ModelElements.Sequence.Collaboration;
import org.aero.mtip.ModelElements.Sequence.CombinedFragment;
import org.aero.mtip.ModelElements.Sequence.DestructionOccurrenceSpecification;
import org.aero.mtip.ModelElements.Sequence.DurationConstraint;
import org.aero.mtip.ModelElements.Sequence.DurationObservation;
import org.aero.mtip.ModelElements.Sequence.Interaction;
import org.aero.mtip.ModelElements.Sequence.InteractionOperand;
import org.aero.mtip.ModelElements.Sequence.InteractionUse;
import org.aero.mtip.ModelElements.Sequence.Lifeline;
import org.aero.mtip.ModelElements.Sequence.Message;
import org.aero.mtip.ModelElements.Sequence.MessageOccurrenceSpecification;
import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.ModelElements.Sequence.SequenceDiagram;
import org.aero.mtip.ModelElements.Sequence.StateInvariant;
import org.aero.mtip.ModelElements.Sequence.TimeConstraint;
import org.aero.mtip.ModelElements.Sequence.TimeObservation;
import org.aero.mtip.ModelElements.StateMachine.ChoicePseudoState;
import org.aero.mtip.ModelElements.StateMachine.ConnectionPointReference;
import org.aero.mtip.ModelElements.StateMachine.DeepHistory;
import org.aero.mtip.ModelElements.StateMachine.EntryPoint;
import org.aero.mtip.ModelElements.StateMachine.ExitPoint;
import org.aero.mtip.ModelElements.StateMachine.FinalState;
import org.aero.mtip.ModelElements.StateMachine.Fork;
import org.aero.mtip.ModelElements.StateMachine.FunctionBehavior;
import org.aero.mtip.ModelElements.StateMachine.InitialPseudoState;
import org.aero.mtip.ModelElements.StateMachine.Join;
import org.aero.mtip.ModelElements.StateMachine.OpaqueBehavior;
import org.aero.mtip.ModelElements.StateMachine.Region;
import org.aero.mtip.ModelElements.StateMachine.ShallowHistory;
import org.aero.mtip.ModelElements.StateMachine.SignalEvent;
import org.aero.mtip.ModelElements.StateMachine.State;
import org.aero.mtip.ModelElements.StateMachine.StateMachine;
import org.aero.mtip.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.mtip.ModelElements.StateMachine.Terminate;
import org.aero.mtip.ModelElements.StateMachine.Trigger;
import org.aero.mtip.ModelElements.Table.GenericTable;
import org.aero.mtip.ModelElements.Table.GlossaryTable;
import org.aero.mtip.ModelElements.Table.InstanceTable;
import org.aero.mtip.ModelElements.Table.MetricTable;
import org.aero.mtip.ModelElements.Table.RequirementTable;
import org.aero.mtip.ModelElements.UseCase.Actor;
import org.aero.mtip.ModelElements.UseCase.ExtensionPoint;
import org.aero.mtip.ModelElements.UseCase.UseCase;
import org.aero.mtip.ModelElements.UseCase.UseCaseDiagram;
import org.aero.mtip.ModelElements.View.Stakeholder;
import org.aero.mtip.ModelElements.View.View;
import org.aero.mtip.ModelElements.View.Viewpoint;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.dodaf.av.AV1;
import org.aero.mtip.dodaf.av.AV2;
import org.aero.mtip.dodaf.cv.CV1;
import org.aero.mtip.dodaf.cv.CV2;
import org.aero.mtip.dodaf.cv.CV3;
import org.aero.mtip.dodaf.cv.CV4;
import org.aero.mtip.dodaf.cv.CV5;
import org.aero.mtip.dodaf.cv.CV6;
import org.aero.mtip.dodaf.cv.CV7;
import org.aero.mtip.dodaf.div.DIV1;
import org.aero.mtip.dodaf.div.DIV2;
import org.aero.mtip.dodaf.div.DIV3;
import org.aero.mtip.dodaf.ov.OV1;
import org.aero.mtip.dodaf.ov.OV2;
import org.aero.mtip.dodaf.ov.OV3;
import org.aero.mtip.dodaf.ov.OV4;
import org.aero.mtip.dodaf.ov.OV5a;
import org.aero.mtip.dodaf.ov.OV5b;
import org.aero.mtip.dodaf.ov.OV6a;
import org.aero.mtip.dodaf.ov.OV6b;
import org.aero.mtip.dodaf.ov.OV6c;
import org.aero.mtip.dodaf.pv.PV1;
import org.aero.mtip.dodaf.pv.PV2;
import org.aero.mtip.dodaf.pv.PV3;
import org.aero.mtip.dodaf.sv.SV1;
import org.aero.mtip.dodaf.sv.SV10a;
import org.aero.mtip.dodaf.sv.SV10b;
import org.aero.mtip.dodaf.sv.SV10c;
import org.aero.mtip.dodaf.sv.SV2;
import org.aero.mtip.dodaf.sv.SV4;
import org.aero.mtip.uaf.Dictionary.Alias;
import org.aero.mtip.uaf.Dictionary.Definition;
import org.aero.mtip.uaf.Metadata.Information;
import org.aero.mtip.uaf.Metadata.Metadata;
import org.aero.mtip.uaf.Operational.ConceptRole;
import org.aero.mtip.uaf.Operational.HighLevelOperationalConcept;
import org.aero.mtip.uaf.Operational.InformationElement;
import org.aero.mtip.uaf.Operational.KnownResource;
import org.aero.mtip.uaf.Operational.OperationalAction;
import org.aero.mtip.uaf.Operational.OperationalActivity;
import org.aero.mtip.uaf.Operational.OperationalActivityAction;
import org.aero.mtip.uaf.Operational.OperationalAgent;
import org.aero.mtip.uaf.Operational.OperationalArchitecture;
import org.aero.mtip.uaf.Operational.OperationalConnectivity;
import org.aero.mtip.uaf.Operational.OperationalConstraint;
import org.aero.mtip.uaf.Operational.OperationalConstraintsDefinition;
import org.aero.mtip.uaf.Operational.OperationalExchangeKind;
import org.aero.mtip.uaf.Operational.OperationalFreeFormTaxonomy;
import org.aero.mtip.uaf.Operational.OperationalHighLevelTaxonomy;
import org.aero.mtip.uaf.Operational.OperationalInteractionScenarios;
import org.aero.mtip.uaf.Operational.OperationalInterface;
import org.aero.mtip.uaf.Operational.OperationalMethod;
import org.aero.mtip.uaf.Operational.OperationalParameter;
import org.aero.mtip.uaf.Operational.OperationalParametric;
import org.aero.mtip.uaf.Operational.OperationalPerformer;
import org.aero.mtip.uaf.Operational.OperationalPort;
import org.aero.mtip.uaf.Operational.OperationalProcessFlow;
import org.aero.mtip.uaf.Operational.OperationalProcesses;
import org.aero.mtip.uaf.Operational.OperationalRole;
import org.aero.mtip.uaf.Operational.OperationalSignal;
import org.aero.mtip.uaf.Operational.OperationalSignalProperty;
import org.aero.mtip.uaf.Operational.OperationalStateDescription;
import org.aero.mtip.uaf.Operational.OperationalStates;
import org.aero.mtip.uaf.Operational.OperationalStructure;
import org.aero.mtip.uaf.Operational.OperationalTaxonomy;
import org.aero.mtip.uaf.Operational.ProblemDomain;
import org.aero.mtip.uaf.Operational.StandardOperationalActivity;
import org.aero.mtip.uaf.Parameters.ActualCondition;
import org.aero.mtip.uaf.Parameters.ActualEnvironment;
import org.aero.mtip.uaf.Parameters.ActualLocation;
import org.aero.mtip.uaf.Parameters.ActualMeasurementSet;
import org.aero.mtip.uaf.Parameters.ActualPropertySet;
import org.aero.mtip.uaf.Parameters.EnvironmentProperty;
import org.aero.mtip.uaf.Projects.ActualMilestoneKind;
import org.aero.mtip.uaf.Projects.ActualProject;
import org.aero.mtip.uaf.Projects.ActualProjectMilestone;
import org.aero.mtip.uaf.Projects.ActualProjectMilestoneRole;
import org.aero.mtip.uaf.Projects.ActualProjectRole;
import org.aero.mtip.uaf.Projects.Project;
import org.aero.mtip.uaf.Projects.ProjectActivity;
import org.aero.mtip.uaf.Projects.ProjectActivityAction;
import org.aero.mtip.uaf.Projects.ProjectMilestone;
import org.aero.mtip.uaf.Projects.ProjectMilestoneRole;
import org.aero.mtip.uaf.Projects.ProjectRole;
import org.aero.mtip.uaf.Projects.ProjectStatus;
import org.aero.mtip.uaf.Projects.ProjectTheme;
import org.aero.mtip.uaf.Projects.StatusIndicators;
import org.aero.mtip.uaf.Resources.CapabilityConfiguration;
import org.aero.mtip.uaf.Resources.DataElement;
import org.aero.mtip.uaf.Resources.Function;
import org.aero.mtip.uaf.Resources.FunctionAction;
import org.aero.mtip.uaf.Resources.NaturalResource;
import org.aero.mtip.uaf.Resources.ResourceAction;
import org.aero.mtip.uaf.Resources.ResourceArchitecture;
import org.aero.mtip.uaf.Resources.ResourceArtifact;
import org.aero.mtip.uaf.Resources.ResourceConstraint;
import org.aero.mtip.uaf.Resources.ResourceExchangeKind;
import org.aero.mtip.uaf.Resources.ResourceInterface;
import org.aero.mtip.uaf.Resources.ResourceMethod;
import org.aero.mtip.uaf.Resources.ResourceParameter;
import org.aero.mtip.uaf.Resources.ResourcePort;
import org.aero.mtip.uaf.Resources.ResourceRole;
import org.aero.mtip.uaf.Resources.ResourceSignal;
import org.aero.mtip.uaf.Resources.ResourceSignalProperty;
import org.aero.mtip.uaf.Resources.ResourceStateDescription;
import org.aero.mtip.uaf.Resources.ResourcesProcessFlow;
import org.aero.mtip.uaf.Resources.RoleKind;
import org.aero.mtip.uaf.Resources.Software;
import org.aero.mtip.uaf.Resources.Technology;
import org.aero.mtip.uaf.Resources.VersionOfConfiguration;
import org.aero.mtip.uaf.Resources.WholeLifeConfiguration;
import org.aero.mtip.uaf.Resources.WholeLifeConfigurationKind;
import org.aero.mtip.uaf.Strategic.Achiever;
import org.aero.mtip.uaf.Strategic.ActualEnduringTask;
import org.aero.mtip.uaf.Strategic.ActualEnterprisePhase;
import org.aero.mtip.uaf.Strategic.Capability;
import org.aero.mtip.uaf.Strategic.CapabilityProperty;
import org.aero.mtip.uaf.Strategic.Desirer;
import org.aero.mtip.uaf.Strategic.EnduringTask;
import org.aero.mtip.uaf.Strategic.EnterpriseGoal;
import org.aero.mtip.uaf.Strategic.EnterprisePhase;
import org.aero.mtip.uaf.Strategic.EnterpriseVision;
import org.aero.mtip.uaf.Strategic.StrategicConnectivity;
import org.aero.mtip.uaf.Strategic.StrategicConstraints;
import org.aero.mtip.uaf.Strategic.StrategicStates;
import org.aero.mtip.uaf.Strategic.StrategicStructure;
import org.aero.mtip.uaf.Strategic.StrategicTaxonomy;
import org.aero.mtip.uaf.Strategic.StructuralPart;
import org.aero.mtip.uaf.Strategic.TemporalPart;
import org.aero.mtip.uaf.Strategic.VisionStatement;
import org.aero.mtip.uaf.Strategic.WholeLifeEnterprise;
import org.aero.mtip.uaf.actualresources.ActualOrganization;
import org.aero.mtip.uaf.actualresources.ActualPerson;
import org.aero.mtip.uaf.actualresources.ActualPost;
import org.aero.mtip.uaf.actualresources.ActualResource;
import org.aero.mtip.uaf.actualresources.ActualResourcesConnectivity;
import org.aero.mtip.uaf.actualresources.ActualResourcesStructure;
import org.aero.mtip.uaf.actualresources.ActualResponsibility;
import org.aero.mtip.uaf.actualresources.ActualService;
import org.aero.mtip.uaf.actualresources.FieldedCapability;
import org.aero.mtip.uaf.actualresources.ProvidedServiceLevel;
import org.aero.mtip.uaf.actualresources.RequiredServiceLevel;
import org.aero.mtip.uaf.personnel.Competence;
import org.aero.mtip.uaf.personnel.Organization;
import org.aero.mtip.uaf.personnel.Person;
import org.aero.mtip.uaf.personnel.PersonnelConnectivity;
import org.aero.mtip.uaf.personnel.PersonnelProcesses;
import org.aero.mtip.uaf.personnel.PersonnelStates;
import org.aero.mtip.uaf.personnel.PersonnelStructure;
import org.aero.mtip.uaf.personnel.PersonnelTaxonomy;
import org.aero.mtip.uaf.personnel.Post;
import org.aero.mtip.uaf.personnel.Responsibility;
import org.aero.mtip.uaf.security.ActualRisk;
import org.aero.mtip.uaf.security.EnhancedSecurityControl;
import org.aero.mtip.uaf.security.OperationalMitigation;
import org.aero.mtip.uaf.security.ResourceMitigation;
import org.aero.mtip.uaf.security.Risk;
import org.aero.mtip.uaf.security.SecurityConnectivity;
import org.aero.mtip.uaf.security.SecurityConstraint;
import org.aero.mtip.uaf.security.SecurityConstraints;
import org.aero.mtip.uaf.security.SecurityControl;
import org.aero.mtip.uaf.security.SecurityControlFamily;
import org.aero.mtip.uaf.security.SecurityEnclave;
import org.aero.mtip.uaf.security.SecurityProcess;
import org.aero.mtip.uaf.security.SecurityProcessAction;
import org.aero.mtip.uaf.security.SecurityProcessesFlow;
import org.aero.mtip.uaf.security.SecurityStructure;
import org.aero.mtip.uaf.security.SecurityTaxonomy;
import org.aero.mtip.uaf.services.ServiceStateDescription;
import org.aero.mtip.uaf.services.ServicesStates;
import org.aero.mtip.uaf.standards.Protocol;
import org.aero.mtip.uaf.standards.ProtocolLayer;
import org.aero.mtip.uaf.standards.ProtocolStack;
import org.aero.mtip.uaf.standards.Standard;
import org.aero.mtip.uaf.standards.StandardsStructure;
import org.aero.mtip.uaf.standards.StandardsTaxonomy;

import org.aero.mtip.util.CameoUtils;

public class CommonElementsFactory {
	public CommonElement createElement(String type, String name, String EAID) {
		CommonElement element = null;
		switch(type) {
			case SysmlConstants.ACCEPTEVENTACTION:
				element = new AcceptEventAction(name, EAID);
				break;
			case SysmlConstants.ACTION:
				element = new Action(name, EAID);
				break;
			case SysmlConstants.ACTIVITY:
				element = new Activity(name, EAID);
				break;
			case SysmlConstants.ACTIVITYFINALNODE:
				element = new ActivityFinalNode(name, EAID);
				break;
			case SysmlConstants.ACTIVITYPARAMETERNODE:
				element = new ActivityParameterNode(name, EAID);
				break;
			case SysmlConstants.ACTIVITYPARTITION:
				element = new ActivityPartition(name, EAID);
				break;
			case SysmlConstants.ACTOR:
				element = new Actor(name, EAID);
				break;
//			case "Attribute":
//				element = new Attribute(name, EAID);
//				break;
			case SysmlConstants.ASSOCIATIONBLOCK:
				element = new AssociationBlock(name, EAID);
				break;
			case SysmlConstants.BOUNDREFERENCE:
				element = new BoundReference(name, EAID);
				break;
			case SysmlConstants.BLOCK:
				element = new Block(name, EAID);
				break;
			case SysmlConstants.CALLBEHAVIORACTION:
				element = new CallBehaviorAction(name, EAID);
				break;
			case SysmlConstants.CALLOPERATIONACTION:
				element = new CallOperationAction(name, EAID);
				break;
			case SysmlConstants.CENTRALBUFFERNODE:
				element = new CentralBufferNode(name, EAID);
				break;
			case SysmlConstants.CHOICEPSEUDOSTATE:
				element = new ChoicePseudoState(name, EAID);
				break;
			case SysmlConstants.CHANGEEVENT:
				element = new ChangeEvent(name, EAID);
				break;
			case SysmlConstants.CLASS:
				element = new Class(name, EAID);
				break;
			case SysmlConstants.CLASSIFIERBEHAVIORPROPERTY:
				element = new ClassifierBehaviorProperty(name, EAID);
				break;
			case SysmlConstants.COLLABORATION:
				element = new Collaboration(name, EAID);
				break;
			case "CombinedFragment":
				element = new CombinedFragment(name, EAID);
				break;
			case SysmlConstants.COMMENT:
				element = new Comment(name, EAID);
				break;
			case "ConditionalNode":
				element = new ConditionalNode(name, EAID);
				break;
			case SysmlConstants.CONNECTIONPOINTREFERENCE:
				element = new ConnectionPointReference(name, EAID);
				break;
			case SysmlConstants.CONSTRAINT:
				element = new Constraint(name, EAID);
				break;
			case "ConstraintBlock":
				element = new ConstraintBlock(name, EAID);
				break;
			case SysmlConstants.CONSTRAINTPARAMETER:
				element = new ConstraintParameter(name, EAID);
				break;
			case SysmlConstants.CONSTRAINTPROPERTY:
				element = new ConstraintProperty(name, EAID);
				break;
			case "CreateObjectAction":
				element = new CreateObjectAction(name, EAID);
				break;
			case SysmlConstants.CUSTOMIZATION:
				element = new Customization(name, EAID);
				break;
			case "DataStoreNode":
				element = new DataStoreNode(name, EAID);
				break;
			case "DecisionNode":
				element = new DecisionNode(name, EAID);
				break;
			case SysmlConstants.DEEPHISTORY:
				element = new DeepHistory(name, EAID);
				break;
			case "DesignConstraint":
				element = new DesignConstraint(name, EAID);
				break;
			case "DestroyObjectAction":
				element = new DestroyObjectAction(name, EAID);
				break;
			case SysmlConstants.DESTRUCTIONOCCURRENCESPECIFICATION:
				element = new DestructionOccurrenceSpecification(name, EAID);
				break;
			case SysmlConstants.DURATIONCONSTRAINT:
				element = new DurationConstraint(name, EAID);
				break;
			case SysmlConstants.DURATIONOBSERVATION:
				element = new DurationObservation(name, EAID);
				break;
			case SysmlConstants.ENTRYPOINT:
				element = new EntryPoint(name, EAID);
				break;
			case "Enumeration":
				element = new Enumeration(name, EAID);
				break;
			case SysmlConstants.ENUMERATIONLITERAL:
				element = new EnumerationLiteral(name, EAID);
				break;
			case "ExitPoint":
				element = new ExitPoint(name, EAID);
				break;
			case "ExtendedRequirement":
				element = new ExtendedRequirement(name, EAID);
				break;
			case SysmlConstants.EXTENSIONPOINT:
				element = new ExtensionPoint(name, EAID);
				break;
			case "FinalState":
				element = new FinalState(name, EAID);
				break;
			case "FlowFinalNode":
				element = new FlowFinalNode(name, EAID);
				break;
			case SysmlConstants.FORK:
				element = new Fork(name, EAID);
				break;
			case SysmlConstants.FLOWPORT:
				element = new FlowPort(name, EAID);
				break;
			case SysmlConstants.FLOWPROPERTY:
				element = new FlowProperty(name, EAID);
				break;
			case SysmlConstants.FLOWSPECIFICATION:
				element = new FlowSpecification(name, EAID);
				break;
			case "ForkNode":
				element = new ForkNode(name, EAID);
				break;
			case "FullPort":
				element = new FullPort(name, EAID);
				break;
			case "FunctionalRequirement":
				element = new FunctionalRequirement(name, EAID);
				break;
			case SysmlConstants.FUNCTIONBEHAVIOR:
				element = new FunctionBehavior(name, EAID);
				break;
			case SysmlConstants.INFORMATIONITEM:
				element = new InformationItem(name, EAID);
				break;
			case "InitialNode":
				element = new InitialNode(name, EAID);
				break;
			case "InitialPseudoState":
				element = new InitialPseudoState(name, EAID);
				break;
			case "InputPin":
				element = new InputPin(name, EAID);
				break;
			case SysmlConstants.INSTANCESPECIFICATION:
				element = new InstanceSpecification(name, EAID);
				break;
			case SysmlConstants.INTERACTION:
				element = new Interaction(name, EAID);
				break;
			case SysmlConstants.INTERACTIONOPERAND:
				element = new InteractionOperand(name, EAID);
				break;
			case "InteractionUse":
				element = new InteractionUse(name, EAID);
				break;
			case SysmlConstants.INTERFACE:
				element = new Interface(name, EAID);
				break;
			case "InterfaceBlock":
				element = new InterfaceBlock(name, EAID);
				break;
			case SysmlConstants.INTERFACEREALIZATION:
				element = new InterfaceRealization(name, EAID);
				break;
			case "InterfaceRequirement":
				element = new InterfaceRequirement(name, EAID);
				break;
			case SysmlConstants.INTERRUPTIBLEACTIVITYREGION:
				element = new InterruptibleActivityRegion(name, EAID);
				break;
			case SysmlConstants.ITEMFLOW:
				element = new ItemFlow(name, EAID);
				break;
			case SysmlConstants.JOIN:
				element = new Join(name, EAID);
				break;
			case "JoinNode":
				element = new JoinNode(name, EAID);
				break;
			case "Lifeline":
				element = new Lifeline(name, EAID);
				break;
			case SysmlConstants.LINK:
				element = new Link(name, EAID);
				break;
			case "LoopNode":
				element = new LoopNode(name, EAID);
				break;
			case SysmlConstants.METACLASS:
				element = new MetaClass(name, EAID);
				break;
			case "MergeNode":
				element = new MergeNode(name, EAID);
				break;
			case SysmlConstants.MESSAGE:
				element = new Message(name, EAID);
				break;
			case SysmlConstants.MESSAGEOCCURRENCESPECIFICATION:
				element = new MessageOccurrenceSpecification(name, EAID);
				break;
			case "Model":
				element = new Model(name, EAID);
				break;
			case "Note":
				element = new Note(name, EAID);
				break;
			case "OpaqueAction":
				element = new OpaqueAction(name, EAID);
				break;
			case SysmlConstants.OPAQUEBEHAVIOR:
				element = new OpaqueBehavior(name, EAID);
				break;
			case SysmlConstants.OPAQUEEXPRESSION:
				element = new OpaqueExpression(name, EAID);
				break;
			case "Operation":
				element = new Operation(name, EAID);
				break;
			case "OutputPin":
				element = new OutputPin(name, EAID);
				break;
			case "Package":
				element = new SysmlPackage(name, EAID);
				break;
			case SysmlConstants.PARAMETER:
				element = new Parameter(name, EAID);
				break;
			case SysmlConstants.PARTICIPANTPROPERTY:
				element = new ParticipantProperty(name, EAID);
				break;
			case "PartProperty":
				element = new PartProperty(name, EAID);
				break;
			case "PerformanceRequirement":
				element = new PerformanceRequirement(name, EAID);
				break;
			case "PhysicalRequirement":
				element = new PhysicalRequirement(name, EAID);
				break;
			case "Port":
				element = new Port(name, EAID);
				break;
			case "Profile":
				element = new Profile(name, EAID);
				break;
			// Property vs PartProperty - EA treats the same? Check if parent is block?
			case "Property":
				element = new Property(name, EAID);
				break;
			case "ProxyPort":
				element = new ProxyPort(name, EAID);
				break;
			case SysmlConstants.QUANTITYKIND:
				element = new QuantityKind(name, EAID);
				break;
			case SysmlConstants.REFERENCEPROPERTY:
				element = new ReferenceProperty(name, EAID);
				break;
			case SysmlConstants.REGION:
				element = new Region(name, EAID);
				break;
			case "RequiredInterface":
				element = new RequiredInterface(name, EAID);
				break;
			case "Requirement":
				element = new Requirement(name, EAID);
				break;
			case SysmlConstants.SENDSIGNALACTION:
				element = new SendSignalAction(name, EAID);
				break;
			case SysmlConstants.SHALLOWHISTORY:
				element = new ShallowHistory(name, EAID);
				break;
			case "Signal":
				element = new Signal(name, EAID);
				break;
			case SysmlConstants.SIGNALEVENT:
				element = new SignalEvent(name, EAID);
				break;
			case SysmlConstants.SLOT:
				element = new Slot(name, EAID);
				break;
			case SysmlConstants.STAKEHOLDER:
				element = new Stakeholder(name, EAID);
				break;
			case SysmlConstants.STATE:
				element = new State(name, EAID);
				break;
			case SysmlConstants.STATEINVARIANT:
				element = new StateInvariant(name, EAID);
				break;
			case SysmlConstants.STATEMACHINE:
				element = new StateMachine(name, EAID);
				break;
			case SysmlConstants.STEREOTYPE:
				element = new Stereotype(name, EAID);
				break;
			case SysmlConstants.TERMINATE:
				element = new Terminate(name, EAID);
				break;
			case SysmlConstants.TIMECONSTRAINT:
				element = new TimeConstraint(name, EAID);
				break;
			case SysmlConstants.TIMEEVENT:
				element = new TimeEvent(name, EAID);
				break;
			case SysmlConstants.TIMEEXPRESSION:
				element = new TimeExpression(name, EAID);
				break;
			case SysmlConstants.TIMEOBSERVATION:
				element = new TimeObservation(name, EAID);
				break;
			case SysmlConstants.TRIGGER:
				element = new Trigger(name, EAID);
				break;
			case SysmlConstants.UNIT:
				element = new Unit(name, EAID);
				break;
			case "UseCase":
				element = new UseCase(name, EAID);
				break;
			case "ValueProperty":
				element = new ValueProperty(name, EAID);
				break;
			case "ValueType":
				element = new ValueType(name, EAID);
				break;
			case SysmlConstants.VIEW:
				element = new View(name, EAID);
				break;
			case SysmlConstants.VIEWPOINT:
				element = new Viewpoint(name, EAID);
				break;
				
			// Cameo Specific model Elements ************************************************
			case SysmlConstants.DOMAIN:
				element = new Domain(name, EAID);
				break;
			case SysmlConstants.EXTERNAL:
				element = new External(name, EAID);
				break;
			case SysmlConstants.SUBSYSTEM:
				element = new Subsystem(name, EAID);
				break;
			case SysmlConstants.SYSTEM:
				element = new System(name, EAID);
				break;
			case SysmlConstants.SYSTEMCONTEXT:
				element = new SystemContext(name, EAID);
				break;
				
			// DIAGRAMS	*********************************************************************
			case SysmlConstants.ACT:
				element = new ActivityDiagram(name, EAID);
				break;
			case SysmlConstants.BDD:
				element = new BlockDefinitionDiagram(name, EAID);
				break;
			case SysmlConstants.STM:
				element = new StateMachineDiagram(name, EAID);
				break;
			case SysmlConstants.IBD:
				element = new InternalBlockDiagram(name, EAID);
				break;
			case SysmlConstants.UC:
				element = new UseCaseDiagram(name, EAID);
				break;
			case SysmlConstants.REQ:
				element = new RequirementsDiagram(name, EAID);
				break;
			case SysmlConstants.SEQ:
				element = new SequenceDiagram(name, EAID);
				break;
			case SysmlConstants.PAR:
				element = new ParametricDiagram(name, EAID);
				break;
			case SysmlConstants.PKG:
				element = new PackageDiagram(name, EAID);
				break;
			case SysmlConstants.PROFILEDIAGRAM:
				element = new ProfileDiagram(name, EAID);
				break;
			case SysmlConstants.CLASSDIAGRAM:
				element = new ClassDiagram(name, EAID);
				break;
				
			case SysmlConstants.CUSTOM_DIAGRAM:
				element = new CustomDiagram(name, EAID);
				break;
				
			// Tables	*********************************************************************
			case SysmlConstants.GENERIC_TABLE:
				element = new GenericTable(name, EAID);
				break;
			case SysmlConstants.GLOSSARY_TABLE:
				element = new GlossaryTable(name, EAID);
				break;
			case SysmlConstants.INSTANCE_TABLE:
				element = new InstanceTable(name, EAID);
				break;
			case SysmlConstants.METRIC_TABLE:
				element = new MetricTable(name, EAID);
				break;
			case SysmlConstants.REQUIREMENT_TABLE:
				element = new RequirementTable(name, EAID);
				break;
			// Matrices	*********************************************************************	
			case SysmlConstants.ALLOCATION_MATRIX:
				element = new AllocationMatrix(name, EAID);
				break;
			case SysmlConstants.DEPENDENCY_MATRIX:
				element = new DependencyMatrix(name, EAID);
				break;
			case SysmlConstants.DERIVE_REQUIREMENT_MATRIX:
				element = new DeriveRequirementMatrix(name, EAID);
				break;
			case SysmlConstants.REFINE_REQUIREMENT_MATRIX:
				element = new RefineRequirementMatrix(name, EAID);
				break;
			case SysmlConstants.SATISFY_REQUIREMENT_MATRIX:
				element = new SatisfyRequirementMatrix(name, EAID);
				break;
			case SysmlConstants.VERIFY_REQUIREMENT_MATRIX:
				element = new VerifyRequirementMatrix(name, EAID);
				break;
				
			// UAF Elements ******************************************************************
			case UAFConstants.ACHIEVER:
				element = new Achiever(name, EAID);
				break;
			case UAFConstants.ACTUAL_ENDURING_TASK:
				element = new ActualEnduringTask(name, EAID);
				break;
			case UAFConstants.ACTUAL_ENTERPRISE_PHASE:
				element = new ActualEnterprisePhase(name, EAID);
				break;
			case UAFConstants.CAPABILITY:
				element = new Capability(name, EAID);
				break;
			case UAFConstants.CAPABILITY_PROPERTY:
				element = new CapabilityProperty(name, EAID);
				break;
			case UAFConstants.DESIRER:
				element = new Desirer(name, EAID);
				break;
			case UAFConstants.ENDURING_TASK:
				element = new EnduringTask(name, EAID);
				break;
			case UAFConstants.ENTERPRISE_GOAL:
				element = new EnterpriseGoal(name, EAID);
				break;
			case UAFConstants.ENTERPRISE_PHASE:
				element = new EnterprisePhase(name, EAID);
				break;
			case UAFConstants.ENTERPRISE_VISION:
				element = new EnterpriseVision(name, EAID);
				break;
			// Not a part of the UAFP v1.1 Specification
//			case UAFConstants.STRATEGIC_TAXONOMY_PACKAGE:
//				element = new StrategicTaxonomyPackage(name, EAID);
//				break;
			case UAFConstants.STRUCTURAL_PART:
				element = new StructuralPart(name, EAID);
				break;
			case UAFConstants.TEMPORAL_PART:
				element = new TemporalPart(name, EAID);
				break;
			case UAFConstants.VISION_STATEMENT:
				element = new VisionStatement(name, EAID);
				break;
			case UAFConstants.WHOLE_LIFE_ENTERPRISE:
				element = new WholeLifeEnterprise(name, EAID);
				break;
			//Operational
			case UAFConstants.CONCEPT_ROLE:
				element = new ConceptRole(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_EXCHANGE_KIND:
				element = new OperationalExchangeKind(name, EAID);
				break;
			case UAFConstants.HIGH_LEVEL_OPERATIONAL_CONCEPT:
				element = new HighLevelOperationalConcept(name, EAID);
				break;
			case UAFConstants.INFORMATION_ELEMENT:
				element = new InformationElement(name, EAID);
				break;
			case UAFConstants.KNOWN_RESOURCE:
				element = new KnownResource(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_ACTIVITY:
				element = new OperationalActivity(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_ACTIVITY_ACTION:
				element = new OperationalActivityAction(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_AGENT:
				element = new OperationalAgent(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_ARCHITECTURE:
				element = new OperationalArchitecture(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_INTERFACE:
				element = new OperationalInterface(name, EAID);
				break;	
			case UAFConstants.OPERATIONAL_PERFORMER:
				element = new OperationalPerformer(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_ROLE:
				element = new OperationalRole(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_SIGNAL:
				element = new OperationalSignal(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_SIGNAL_PROPERTY:
				element = new OperationalSignalProperty(name, EAID);
				break;
			case UAFConstants.STANDARD_OPERATIONAL_ACTIVITY:
				element = new StandardOperationalActivity(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_METHOD:
				element = new OperationalMethod(name, EAID);
				break;
			case UAFConstants.PROBLEM_DOMAIN:
				element = new ProblemDomain(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_PORT:
				element = new OperationalPort(name, EAID);
				break;				
				
			// UAF Actual Resources
			case UAFConstants.ACTUAL_ORGANIZATION:
				element = new ActualOrganization(name, EAID);
				break;
			case UAFConstants.ACTUAL_PERSON:
				element = new ActualPerson(name, EAID);
				break;
			case UAFConstants.ACTUAL_POST:
				element = new ActualPost(name, EAID);
				break;
			case UAFConstants.ACTUAL_RESOURCE:
				element = new ActualResource(name, EAID);
				break;
			case UAFConstants.ACTUAL_RESPONSIBILITY:
				element = new ActualResponsibility(name, EAID);
				break;
			case UAFConstants.ACTUAL_SERVICE:
				element = new ActualService(name, EAID);
				break;
			case UAFConstants.FIELDED_CAPABILITY:
				element = new FieldedCapability(name, EAID);
				break;
			case UAFConstants.PROVIDED_SERVICE_LEVEL:
				element = new ProvidedServiceLevel(name, EAID);
				break;
			case UAFConstants.REQUIRED_SERVICE_LEVEL:
				element = new RequiredServiceLevel(name, EAID);
				break;
				
			// Personnel
			case UAFConstants.COMPETENCE:
				element = new Competence(name, EAID);
				break;
			case UAFConstants.ORGANIZATION:
				element = new Organization(name, EAID);
				break;
			case UAFConstants.PERSON:
				element = new Person(name, EAID);
				break;
			case UAFConstants.POST:
				element = new Post(name, EAID);
				break;
			case UAFConstants.RESPONSIBILITY:
				element = new Responsibility(name, EAID);
				break;
				
			//Security
			case UAFConstants.ACTUAL_RISK:
				element = new ActualRisk(name, EAID);
				break;
			case UAFConstants.ENHANCED_SECURITY_CONTROL:
				element = new EnhancedSecurityControl(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_MITIGATION:
				element = new OperationalMitigation(name, EAID);
				break;
			case UAFConstants.RESOURCE_MITIGATION:
				element = new ResourceMitigation(name, EAID);
				break;
			case UAFConstants.RISK:
				element = new Risk(name, EAID);
				break;
			case UAFConstants.SECURITY_CONSTRAINT:
				element = new SecurityConstraint(name, EAID);
				break;
			case UAFConstants.SECURITY_CONTROL:
				element = new SecurityControl(name, EAID);
				break;
			case UAFConstants.SECURITY_CONTROL_FAMILY:
				element = new SecurityControlFamily(name, EAID);
				break;
			case UAFConstants.SECURITY_ENCLAVE:
				element = new SecurityEnclave(name, EAID);
				break;
			case UAFConstants.SECURITY_PROCESS:
				element = new SecurityProcess(name, EAID);
				break;
			case UAFConstants.SECURITY_PROCESS_ACTION:
				element = new SecurityProcessAction(name, EAID);
				break;
				
			// Services
			case UAFConstants.SERVICE_STATE_DESCRIPTION:
				element = new ServiceStateDescription(name, EAID);
				break;
			// UAF Diagrams
			// Actual Resources
			case UAFConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM:
				element = new ActualResourcesConnectivity(name, EAID);
				break;
			case UAFConstants.ACTUAL_RESOURCES_STRUCTURE_DIAGRAM:
				element = new ActualResourcesStructure(name, EAID);
				break;
			// Personnel
			case UAFConstants.PERSONNEL_CONNECTIVITY_DIAGRAM:
				element = new PersonnelConnectivity(name, EAID);
				break;
			case UAFConstants.PERSONNEL_INTERACTION_SCENARIO_DIAGRAM:
				element = new PersonnelConnectivity(name, EAID);
				break;
			case UAFConstants.PERSONNEL_PROCESSES_DIAGRAM:
				element = new PersonnelProcesses(name, EAID);
				break;
			case UAFConstants.PERSONNEL_PROCESSES_FLOW_DIAGRAM:
				element = new PersonnelProcesses(name, EAID);
				break;
			case UAFConstants.PERSONNEL_STATES_DIAGRAM:
				element = new PersonnelStates(name, EAID);
				break;
			case UAFConstants.PERSONNEL_STRUCTURE_DIAGRAM:
				element = new PersonnelStructure(name, EAID);
				break;
			case UAFConstants.PERSONNEL_TAXONOMY_DIAGRAM:
				element = new PersonnelTaxonomy(name, EAID);
				break;
				
			// Strategic Diagrams
			case UAFConstants.STRATEGIC_STRUCTURE_DIAGRAM:
				element = new StrategicStructure(name, EAID);
				break;
			case UAFConstants.STRATEGIC_TAXONOMY_DIAGRAM:
				element = new StrategicTaxonomy(name, EAID);
				break;
			case UAFConstants.STRATEGIC_CONNECTIVITY_DIAGRAM:
				element = new StrategicConnectivity(name, EAID);
				break;
			case UAFConstants.STRATEGIC_STATES_DIAGRAM:
				element = new StrategicStates(name, EAID);
				break;
			case UAFConstants.STRATEGIC_CONSTRAINTS_DIAGRAM:
				element = new StrategicConstraints(name, EAID);
				break;
			// Security
			case UAFConstants.SECURITY_TAXONOMY_DIAGRAM:
				element = new SecurityTaxonomy(name, EAID);
				break;
			case UAFConstants.SECURITY_STRUCTURE_DIAGRAM:
				element = new SecurityStructure(name, EAID);
				break;
			case UAFConstants.SECURITY_CONNECTIVITY_DIAGRAM:
				element = new SecurityConnectivity(name, EAID);
				break;
			case UAFConstants.SECURITY_PROCESSES_FLOW_DIAGRAM:
				element = new SecurityProcessesFlow(name, EAID);
				break;
			case UAFConstants.SECURITY_CONSTRAINTS_DIAGRAM:
				element = new SecurityConstraints(name, EAID);
				break;
				
			// Services Diagrams
				
			case UAFConstants.SERVICES_STATES_DIAGRAM:
				element = new ServicesStates(name, EAID);
				break;
				
			// DoDAF Diagram
			case DoDAFConstants.CV1:
				element = new CV1(name, EAID);
				break;
			case DoDAFConstants.CV2:
				element = new CV2(name, EAID);
				break;
			case DoDAFConstants.CV3:
				element = new CV3(name, EAID);
				break;
			case DoDAFConstants.CV4:
				element = new CV4(name, EAID);
				break;
			case DoDAFConstants.CV5:
				element = new CV5(name, EAID);
				break;
			case DoDAFConstants.CV6:
				element = new CV6(name, EAID);
				break;
			case DoDAFConstants.CV7:
				element = new CV7(name, EAID);
				break;
			case DoDAFConstants.SV1:
				element = new SV1(name, EAID);
				break;
			case DoDAFConstants.SV2:
				element = new SV2(name, EAID);
				break;
			case DoDAFConstants.SV4:
				element = new SV4(name, EAID);
				break;
			case DoDAFConstants.SV10A:
				element = new SV10a(name, EAID);
				break;
			case DoDAFConstants.SV10B:
				element = new SV10b(name, EAID);
				break;
			case DoDAFConstants.SV10C:
				element = new SV10c(name, EAID);
				break;
			case DoDAFConstants.OV1:
				element = new OV1(name, EAID);
				break;
			case DoDAFConstants.OV2:
				element = new OV2(name, EAID);
				break;
			case DoDAFConstants.OV3:
				element = new OV3(name, EAID);
				break;
			case DoDAFConstants.OV4:
				element = new OV4(name, EAID);
				break;
			case DoDAFConstants.OV5A:
				element = new OV5a(name, EAID);
				break;
			case DoDAFConstants.OV5B:
				element = new OV5b(name, EAID);
				break;
			case DoDAFConstants.OV6A:
				element = new OV6a(name, EAID);
				break;
			case DoDAFConstants.OV6B:
				element = new OV6b(name, EAID);
				break;
			case DoDAFConstants.OV6C:
				element = new OV6c(name, EAID);
				break;
			case DoDAFConstants.AV1:
				element = new AV1(name, EAID);
				break;
			case DoDAFConstants.AV2:
				element = new AV2(name, EAID);
				break;
			case DoDAFConstants.DIV1:
				element = new DIV1(name, EAID);
				break;
			case DoDAFConstants.DIV2:
				element = new DIV2(name, EAID);
				break;
			case DoDAFConstants.DIV3:
				element = new DIV3(name, EAID);
				break;
			case DoDAFConstants.PV1:
				element = new PV1(name, EAID);
				break;
			case DoDAFConstants.PV2:
				element = new PV2(name, EAID);
				break;
			case DoDAFConstants.PV3:
				element = new PV3(name, EAID);
				break;			
			case UAFConstants.OPERATIONAL_CONSTRAINT:
				element = new OperationalConstraint(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_STATE_DESCRIPTION:
				element = new OperationalStateDescription(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_PARAMETER:
				element = new OperationalParameter(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_ACTION:
				element = new OperationalAction(name, EAID);
				break;
			//OPERATIONAL Diagrams
			case UAFConstants.OPERATIONAL_PROCESS_FLOW:
				element = new OperationalProcessFlow(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_CONNECTIVITY:
				element = new OperationalConnectivity(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_CONSTRAINTS_DEFINITION:
				element = new OperationalConstraintsDefinition(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_FREE_FORM_TAXONOMY:
				element = new OperationalFreeFormTaxonomy(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_PROCESSES:
				element = new OperationalProcesses(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_STRUCTURE:
				element = new OperationalStructure(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_TAXONOMY:
				element = new OperationalTaxonomy(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_HIGH_LEVEL_TAXONOMY:
				element = new OperationalHighLevelTaxonomy(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_INTERACTION_SCENARIOS:
				element = new OperationalInteractionScenarios(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_PARAMETRIC:
				element = new OperationalParametric(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_STATES:
				element = new OperationalStates(name, EAID);
				break;
				
			//Resources
			case UAFConstants.CAPABILITY_CONFIGURATION:
				element = new CapabilityConfiguration(name, EAID);
				break;
			case UAFConstants.NATURAL_RESOURCE:
				element = new NaturalResource(name, EAID);
				break;
			case UAFConstants.RESOURCE_ARCHITECTURE:
				element = new ResourceArchitecture(name, EAID);
				break;
			case UAFConstants.RESOURCE_ARTIFACT:
				element = new ResourceArtifact(name, EAID);
				break;
			case UAFConstants.SOFTWARE:
				element = new Software(name, EAID);
				break;
			case UAFConstants.SYSTEM:
				element = new System(name, EAID);
				break;
			case UAFConstants.RESOURCE_INTERFACE:
				element = new ResourceInterface(name, EAID);
				break;
			case UAFConstants.DATA_ELEMENT:
				element = new DataElement(name, EAID);
				break;
			case UAFConstants.TECHNOLOGY:
				element = new Technology(name, EAID);
				break;
			case UAFConstants.WHOLE_LIFE_CONFIGURATION:
				element = new WholeLifeConfiguration(name, EAID);
				break;
			case UAFConstants.RESOURCE_METHOD:
				element = new ResourceMethod(name, EAID);
				break;
			case UAFConstants.RESOURCE_PARAMETER:
				element = new ResourceParameter(name, EAID);
				break;
			case UAFConstants.RESOURCE_PORT:
				element = new ResourcePort(name, EAID);
				break;
			case UAFConstants.RESOURCE_ROLE:
				element = new ResourceRole(name, EAID);
				break;
			case UAFConstants.ROLE_KIND:
				element = new RoleKind(name, EAID);
				break;
			case UAFConstants.RESOURCE_EXCHANGE_KIND:
				element = new ResourceExchangeKind(name, EAID);
				break;
			case UAFConstants.RESOURCE_SIGNAL:
				element = new ResourceSignal(name, EAID);
				break;
			case UAFConstants.RESOURCE_SIGNAL_PROPERTY:
				element = new ResourceSignalProperty(name, EAID);
				break;
			case UAFConstants.FUNCTION:
				element = new Function(name, EAID);
				break;
			case UAFConstants.FUNCTION_ACTION:
				element = new FunctionAction(name, EAID);
				break;
			case UAFConstants.RESOURCE_STATE_DESCRIPTION:
				element = new ResourceStateDescription(name, EAID);
				break;
			case UAFConstants.RESOURCE_CONSTRAINT:
				element = new ResourceConstraint(name, EAID);
				break;
			case UAFConstants.VERSION_OF_CONFIGURATION:
				element = new VersionOfConfiguration(name, EAID);
				break;
			case UAFConstants.WHOLE_LIFE_CONFIGURATION_KIND:
				element = new WholeLifeConfigurationKind(name, EAID);
				break;
			case UAFConstants.RESOURCE_ACTION:
				element = new ResourceAction(name, EAID);
				break;
			case UAFConstants.RESOURCES_PROCESS_FLOW:
				element = new ResourcesProcessFlow(name, EAID);
				break;
			case UAFConstants.ACTUAL_MILESTONE_KIND:
				element = new ActualMilestoneKind(name, EAID);
				break;
			case UAFConstants.PROJECT:
				element = new Project(name, EAID);
				break;
			case UAFConstants.PROJECT_KIND:
				element = new Project(name, EAID);
				break;
			case UAFConstants.PROJECT_MILESTONE:
				element = new ProjectMilestone(name, EAID);
				break;
			case UAFConstants.PROJECT_MILESTONE_ROLE:
				element = new ProjectMilestoneRole(name, EAID);
				break;
			case UAFConstants.PROJECT_ROLE:
				element = new ProjectRole(name, EAID);
				break;
			case UAFConstants.PROJECT_THEME:
				element = new ProjectTheme(name, EAID);
				break;
			case UAFConstants.PROJECT_ACTIVITY:
				element = new ProjectActivity(name, EAID);
				break;
			case UAFConstants.PROJECT_ACTIVITY_ACTION:
				element = new ProjectActivityAction(name, EAID);
				break;
			case UAFConstants.PROJECT_STATUS:
				element = new ProjectStatus(name, EAID);
				break;
			case UAFConstants.ACTUAL_PROJECT_MILESTONE_ROLE:
				element = new ActualProjectMilestoneRole(name, EAID);
				break;
			case UAFConstants.ACTUAL_PROJECT_ROLE:
				element = new ActualProjectRole(name, EAID);
				break;
			case UAFConstants.STATUS_INDICATORS:
				element = new StatusIndicators(name, EAID);
				break;
			case UAFConstants.ACTUAL_PROJECT:
				element = new ActualProject(name, EAID);
				break;
			case UAFConstants.ACTUAL_PROJECT_MILESTONE:
				element = new ActualProjectMilestone(name, EAID);
				break;
			//Definition
			case UAFConstants.DEFINITION:
				element = new Definition(name, EAID);
				break;
			case UAFConstants.ALIAS:
				element = new Alias(name, EAID);
				break;
			case UAFConstants.INFORMATION:
				element = new Information(name, EAID);
				break;
			//Standards
			case UAFConstants.PROTOCOL:
				element = new Protocol(name, EAID);
				break;
			case UAFConstants.PROTOCOL_STACK:
				element = new ProtocolStack(name, EAID);
				break;
			case UAFConstants.STANDARD:
				element = new Standard(name, EAID);
				break;
			case UAFConstants.PROTOCOL_LAYER:
				element = new ProtocolLayer(name, EAID);
				break;				
			case UAFConstants.STANDARDS_TAXONOMY_DIAGRAM:
				element = new StandardsTaxonomy(name, EAID);
				break;
			case UAFConstants.STANDARDS_STRUCTURE_DIAGRAM:
				element = new StandardsStructure(name, EAID);
				break;

			//Parameters
			case UAFConstants.ACTUAL_CONDITION:
				element= new ActualCondition(name, EAID);
				break;
			case UAFConstants.ACTUAL_ENVIRONMENT:
				element = new ActualEnvironment(name, EAID);
				break;
			case UAFConstants.ACTUAL_LOCATION:
				element  = new ActualLocation(name, EAID);
				break;
			case UAFConstants.ACTUAL_MEASUREMENT_SET:
				element = new ActualMeasurementSet(name, EAID);
				break;
			case UAFConstants.ACTUAL_PROPERTY_SET:
				element = new ActualPropertySet(name, EAID);
				break;
			case UAFConstants.ENVIRONMENT_PROPERTY:
				element = new EnvironmentProperty(name, EAID);
				break;
			//Metadata
			case UAFConstants.METADATA:
				element = new Metadata(name,EAID);
				break;
			default:
				break;
		}
		if(element == null) {
			CameoUtils.logGUI("Element of type " + type + " not supported by CommonElementsFactory.");
		}
		return element;	
	}
}
