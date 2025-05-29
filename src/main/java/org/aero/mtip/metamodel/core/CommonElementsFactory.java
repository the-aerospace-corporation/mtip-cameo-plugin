/*
 * The Aerospace Corporation MTIP_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.metamodel.core;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.UmlConstants;
import org.aero.mtip.metamodel.core.general.Comment;
import org.aero.mtip.metamodel.core.general.Link;
import org.aero.mtip.metamodel.core.general.Model;
import org.aero.mtip.metamodel.core.general.SysmlPackage;
import org.aero.mtip.metamodel.core.matrix.AllocationMatrix;
import org.aero.mtip.metamodel.core.matrix.DependencyMatrix;
import org.aero.mtip.metamodel.core.matrix.DeriveRequirementMatrix;
import org.aero.mtip.metamodel.core.matrix.RefineRequirementMatrix;
import org.aero.mtip.metamodel.core.matrix.SatisfyRequirementMatrix;
import org.aero.mtip.metamodel.core.matrix.VerifyRequirementMatrix;
import org.aero.mtip.metamodel.core.table.GenericTable;
import org.aero.mtip.metamodel.core.table.GlossaryTable;
import org.aero.mtip.metamodel.core.table.InstanceTable;
import org.aero.mtip.metamodel.core.table.MetricTable;
import org.aero.mtip.metamodel.core.table.RequirementTable;
import org.aero.mtip.metamodel.dodaf.av.AV1;
import org.aero.mtip.metamodel.dodaf.av.AV2;
import org.aero.mtip.metamodel.dodaf.cv.CV1;
import org.aero.mtip.metamodel.dodaf.cv.CV2;
import org.aero.mtip.metamodel.dodaf.cv.CV3;
import org.aero.mtip.metamodel.dodaf.cv.CV4;
import org.aero.mtip.metamodel.dodaf.cv.CV5;
import org.aero.mtip.metamodel.dodaf.cv.CV6;
import org.aero.mtip.metamodel.dodaf.cv.CV7;
import org.aero.mtip.metamodel.dodaf.div.DIV1;
import org.aero.mtip.metamodel.dodaf.div.DIV2;
import org.aero.mtip.metamodel.dodaf.div.DIV3;
import org.aero.mtip.metamodel.dodaf.ov.OV1;
import org.aero.mtip.metamodel.dodaf.ov.OV2;
import org.aero.mtip.metamodel.dodaf.ov.OV3;
import org.aero.mtip.metamodel.dodaf.ov.OV4;
import org.aero.mtip.metamodel.dodaf.ov.OV5a;
import org.aero.mtip.metamodel.dodaf.ov.OV5b;
import org.aero.mtip.metamodel.dodaf.ov.OV6a;
import org.aero.mtip.metamodel.dodaf.ov.OV6b;
import org.aero.mtip.metamodel.dodaf.ov.OV6c;
import org.aero.mtip.metamodel.dodaf.pv.PV1;
import org.aero.mtip.metamodel.dodaf.pv.PV2;
import org.aero.mtip.metamodel.dodaf.pv.PV3;
import org.aero.mtip.metamodel.dodaf.sv.SV1;
import org.aero.mtip.metamodel.dodaf.sv.SV10a;
import org.aero.mtip.metamodel.dodaf.sv.SV10b;
import org.aero.mtip.metamodel.dodaf.sv.SV10c;
import org.aero.mtip.metamodel.dodaf.sv.SV2;
import org.aero.mtip.metamodel.dodaf.sv.SV4;
import org.aero.mtip.metamodel.sysml.activity.AcceptEventAction;
import org.aero.mtip.metamodel.sysml.activity.Action;
import org.aero.mtip.metamodel.sysml.activity.Activity;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;
import org.aero.mtip.metamodel.sysml.activity.ActivityFinalNode;
import org.aero.mtip.metamodel.sysml.activity.ActivityParameterNode;
import org.aero.mtip.metamodel.sysml.activity.ActivityPartition;
import org.aero.mtip.metamodel.sysml.activity.CallBehaviorAction;
import org.aero.mtip.metamodel.sysml.activity.CallOperationAction;
import org.aero.mtip.metamodel.sysml.activity.CentralBufferNode;
import org.aero.mtip.metamodel.sysml.activity.ConditionalNode;
import org.aero.mtip.metamodel.sysml.activity.CreateObjectAction;
import org.aero.mtip.metamodel.sysml.activity.DataStoreNode;
import org.aero.mtip.metamodel.sysml.activity.DecisionNode;
import org.aero.mtip.metamodel.sysml.activity.DestroyObjectAction;
import org.aero.mtip.metamodel.sysml.activity.FlowFinalNode;
import org.aero.mtip.metamodel.sysml.activity.ForkNode;
import org.aero.mtip.metamodel.sysml.activity.InitialNode;
import org.aero.mtip.metamodel.sysml.activity.InputPin;
import org.aero.mtip.metamodel.sysml.activity.InterruptibleActivityRegion;
import org.aero.mtip.metamodel.sysml.activity.JoinNode;
import org.aero.mtip.metamodel.sysml.activity.LoopNode;
import org.aero.mtip.metamodel.sysml.activity.MergeNode;
import org.aero.mtip.metamodel.sysml.activity.OpaqueAction;
import org.aero.mtip.metamodel.sysml.activity.OutputPin;
import org.aero.mtip.metamodel.sysml.activity.Parameter;
import org.aero.mtip.metamodel.sysml.activity.SendSignalAction;
import org.aero.mtip.metamodel.sysml.activity.StructuredActivityNode;
import org.aero.mtip.metamodel.sysml.activity.TimeExpression;
import org.aero.mtip.metamodel.sysml.block.AssociationBlock;
import org.aero.mtip.metamodel.sysml.block.Block;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;
import org.aero.mtip.metamodel.sysml.block.ConstraintBlock;
import org.aero.mtip.metamodel.sysml.block.Domain;
import org.aero.mtip.metamodel.sysml.block.Enumeration;
import org.aero.mtip.metamodel.sysml.block.External;
import org.aero.mtip.metamodel.sysml.block.FlowPort;
import org.aero.mtip.metamodel.sysml.block.FlowSpecification;
import org.aero.mtip.metamodel.sysml.block.FullPort;
import org.aero.mtip.metamodel.sysml.block.InformationItem;
import org.aero.mtip.metamodel.sysml.block.InstanceSpecification;
import org.aero.mtip.metamodel.sysml.block.Interface;
import org.aero.mtip.metamodel.sysml.block.InterfaceBlock;
import org.aero.mtip.metamodel.sysml.block.InterfaceRealization;
import org.aero.mtip.metamodel.sysml.block.Operation;
import org.aero.mtip.metamodel.sysml.block.PartProperty;
import org.aero.mtip.metamodel.sysml.block.Port;
import org.aero.mtip.metamodel.sysml.block.ProxyPort;
import org.aero.mtip.metamodel.sysml.block.QuantityKind;
import org.aero.mtip.metamodel.sysml.block.ReferenceProperty;
import org.aero.mtip.metamodel.sysml.block.Signal;
import org.aero.mtip.metamodel.sysml.block.Slot;
import org.aero.mtip.metamodel.sysml.block.Subsystem;
import org.aero.mtip.metamodel.sysml.block.System;
import org.aero.mtip.metamodel.sysml.block.SystemContext;
import org.aero.mtip.metamodel.sysml.block.Unit;
import org.aero.mtip.metamodel.sysml.block.ValueProperty;
import org.aero.mtip.metamodel.sysml.block.ValueType;
import org.aero.mtip.metamodel.sysml.internalblock.BoundReference;
import org.aero.mtip.metamodel.sysml.internalblock.ClassifierBehaviorProperty;
import org.aero.mtip.metamodel.sysml.internalblock.ConstraintParameter;
import org.aero.mtip.metamodel.sysml.internalblock.ConstraintProperty;
import org.aero.mtip.metamodel.sysml.internalblock.FlowProperty;
import org.aero.mtip.metamodel.sysml.internalblock.InternalBlockDiagram;
import org.aero.mtip.metamodel.sysml.internalblock.ItemFlow;
import org.aero.mtip.metamodel.sysml.internalblock.ParticipantProperty;
import org.aero.mtip.metamodel.sysml.profile.Class;
import org.aero.mtip.metamodel.sysml.profile.ClassDiagram;
import org.aero.mtip.metamodel.sysml.profile.Constraint;
import org.aero.mtip.metamodel.sysml.profile.Customization;
import org.aero.mtip.metamodel.sysml.profile.DataType;
import org.aero.mtip.metamodel.sysml.profile.EnumerationLiteral;
import org.aero.mtip.metamodel.sysml.profile.MetaClass;
import org.aero.mtip.metamodel.sysml.profile.OpaqueExpression;
import org.aero.mtip.metamodel.sysml.profile.PackageDiagram;
import org.aero.mtip.metamodel.sysml.profile.ParametricDiagram;
import org.aero.mtip.metamodel.sysml.profile.Profile;
import org.aero.mtip.metamodel.sysml.profile.ProfileDiagram;
import org.aero.mtip.metamodel.sysml.profile.Stereotype;
import org.aero.mtip.metamodel.sysml.requirements.BusinessRequirement;
import org.aero.mtip.metamodel.sysml.requirements.DesignConstraint;
import org.aero.mtip.metamodel.sysml.requirements.ExtendedRequirement;
import org.aero.mtip.metamodel.sysml.requirements.FunctionalRequirement;
import org.aero.mtip.metamodel.sysml.requirements.InterfaceRequirement;
import org.aero.mtip.metamodel.sysml.requirements.PerformanceRequirement;
import org.aero.mtip.metamodel.sysml.requirements.PhysicalRequirement;
import org.aero.mtip.metamodel.sysml.requirements.Requirement;
import org.aero.mtip.metamodel.sysml.requirements.RequirementsDiagram;
import org.aero.mtip.metamodel.sysml.requirements.UsabilityRequirement;
import org.aero.mtip.metamodel.sysml.sequence.Collaboration;
import org.aero.mtip.metamodel.sysml.sequence.CombinedFragment;
import org.aero.mtip.metamodel.sysml.sequence.DestructionOccurrenceSpecification;
import org.aero.mtip.metamodel.sysml.sequence.DurationConstraint;
import org.aero.mtip.metamodel.sysml.sequence.DurationInterval;
import org.aero.mtip.metamodel.sysml.sequence.DurationObservation;
import org.aero.mtip.metamodel.sysml.sequence.Interaction;
import org.aero.mtip.metamodel.sysml.sequence.InteractionOperand;
import org.aero.mtip.metamodel.sysml.sequence.InteractionUse;
import org.aero.mtip.metamodel.sysml.sequence.Lifeline;
import org.aero.mtip.metamodel.sysml.sequence.Message;
import org.aero.mtip.metamodel.sysml.sequence.MessageOccurrenceSpecification;
import org.aero.mtip.metamodel.sysml.sequence.Property;
import org.aero.mtip.metamodel.sysml.sequence.SequenceDiagram;
import org.aero.mtip.metamodel.sysml.sequence.StateInvariant;
import org.aero.mtip.metamodel.sysml.sequence.TimeConstraint;
import org.aero.mtip.metamodel.sysml.sequence.TimeObservation;
import org.aero.mtip.metamodel.sysml.statemachine.AnyReceiveEvent;
import org.aero.mtip.metamodel.sysml.statemachine.CallEvent;
import org.aero.mtip.metamodel.sysml.statemachine.ChangeEvent;
import org.aero.mtip.metamodel.sysml.statemachine.ChoicePseudoState;
import org.aero.mtip.metamodel.sysml.statemachine.ConnectionPointReference;
import org.aero.mtip.metamodel.sysml.statemachine.DeepHistory;
import org.aero.mtip.metamodel.sysml.statemachine.EntryPoint;
import org.aero.mtip.metamodel.sysml.statemachine.ExitPoint;
import org.aero.mtip.metamodel.sysml.statemachine.FinalState;
import org.aero.mtip.metamodel.sysml.statemachine.Fork;
import org.aero.mtip.metamodel.sysml.statemachine.FunctionBehavior;
import org.aero.mtip.metamodel.sysml.statemachine.InitialPseudoState;
import org.aero.mtip.metamodel.sysml.statemachine.Join;
import org.aero.mtip.metamodel.sysml.statemachine.OpaqueBehavior;
import org.aero.mtip.metamodel.sysml.statemachine.Region;
import org.aero.mtip.metamodel.sysml.statemachine.ShallowHistory;
import org.aero.mtip.metamodel.sysml.statemachine.SignalEvent;
import org.aero.mtip.metamodel.sysml.statemachine.State;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachine;
import org.aero.mtip.metamodel.sysml.statemachine.StateMachineDiagram;
import org.aero.mtip.metamodel.sysml.statemachine.Terminate;
import org.aero.mtip.metamodel.sysml.statemachine.TimeEvent;
import org.aero.mtip.metamodel.sysml.statemachine.Trigger;
import org.aero.mtip.metamodel.sysml.usecase.Actor;
import org.aero.mtip.metamodel.sysml.usecase.ExtensionPoint;
import org.aero.mtip.metamodel.sysml.usecase.UseCase;
import org.aero.mtip.metamodel.sysml.usecase.UseCaseDiagram;
import org.aero.mtip.metamodel.sysml.view.Stakeholder;
import org.aero.mtip.metamodel.sysml.view.View;
import org.aero.mtip.metamodel.sysml.view.Viewpoint;
import org.aero.mtip.metamodel.uaf.Dictionary.Alias;
import org.aero.mtip.metamodel.uaf.Dictionary.Definition;
import org.aero.mtip.metamodel.uaf.Metadata.DataModelKind;
import org.aero.mtip.metamodel.uaf.Metadata.Information;
import org.aero.mtip.metamodel.uaf.Metadata.Metadata;
import org.aero.mtip.metamodel.uaf.Operational.ConceptRole;
import org.aero.mtip.metamodel.uaf.Operational.HighLevelOperationalConcept;
import org.aero.mtip.metamodel.uaf.Operational.InformationElement;
import org.aero.mtip.metamodel.uaf.Operational.KnownResource;
import org.aero.mtip.metamodel.uaf.Operational.OperationalAction;
import org.aero.mtip.metamodel.uaf.Operational.OperationalActivity;
import org.aero.mtip.metamodel.uaf.Operational.OperationalActivityAction;
import org.aero.mtip.metamodel.uaf.Operational.OperationalAgent;
import org.aero.mtip.metamodel.uaf.Operational.OperationalArchitecture;
import org.aero.mtip.metamodel.uaf.Operational.OperationalConnectivity;
import org.aero.mtip.metamodel.uaf.Operational.OperationalConstraint;
import org.aero.mtip.metamodel.uaf.Operational.OperationalConstraintsDefinition;
import org.aero.mtip.metamodel.uaf.Operational.OperationalExchangeKind;
import org.aero.mtip.metamodel.uaf.Operational.OperationalFreeFormTaxonomy;
import org.aero.mtip.metamodel.uaf.Operational.OperationalHighLevelTaxonomy;
import org.aero.mtip.metamodel.uaf.Operational.OperationalInteractionScenarios;
import org.aero.mtip.metamodel.uaf.Operational.OperationalInterface;
import org.aero.mtip.metamodel.uaf.Operational.OperationalInternalConnectivity;
import org.aero.mtip.metamodel.uaf.Operational.OperationalMethod;
import org.aero.mtip.metamodel.uaf.Operational.OperationalParameter;
import org.aero.mtip.metamodel.uaf.Operational.OperationalParametric;
import org.aero.mtip.metamodel.uaf.Operational.OperationalPerformer;
import org.aero.mtip.metamodel.uaf.Operational.OperationalPort;
import org.aero.mtip.metamodel.uaf.Operational.OperationalProcessFlow;
import org.aero.mtip.metamodel.uaf.Operational.OperationalProcesses;
import org.aero.mtip.metamodel.uaf.Operational.OperationalRole;
import org.aero.mtip.metamodel.uaf.Operational.OperationalSignal;
import org.aero.mtip.metamodel.uaf.Operational.OperationalSignalProperty;
import org.aero.mtip.metamodel.uaf.Operational.OperationalStateDescription;
import org.aero.mtip.metamodel.uaf.Operational.OperationalStates;
import org.aero.mtip.metamodel.uaf.Operational.OperationalStructure;
import org.aero.mtip.metamodel.uaf.Operational.OperationalTaxonomy;
import org.aero.mtip.metamodel.uaf.Operational.ProblemDomain;
import org.aero.mtip.metamodel.uaf.Operational.StandardOperationalActivity;
import org.aero.mtip.metamodel.uaf.Parameters.ActualCondition;
import org.aero.mtip.metamodel.uaf.Parameters.ActualEnvironment;
import org.aero.mtip.metamodel.uaf.Parameters.ActualLocation;
import org.aero.mtip.metamodel.uaf.Parameters.ActualMeasurement;
import org.aero.mtip.metamodel.uaf.Parameters.ActualMeasurementSet;
import org.aero.mtip.metamodel.uaf.Parameters.ActualPropertySet;
import org.aero.mtip.metamodel.uaf.Parameters.Condition;
import org.aero.mtip.metamodel.uaf.Parameters.Environment;
import org.aero.mtip.metamodel.uaf.Parameters.EnvironmentDiagram;
import org.aero.mtip.metamodel.uaf.Parameters.EnvironmentKind;
import org.aero.mtip.metamodel.uaf.Parameters.EnvironmentProperty;
import org.aero.mtip.metamodel.uaf.Parameters.GeoPoliticalExtentType;
import org.aero.mtip.metamodel.uaf.Parameters.Location;
import org.aero.mtip.metamodel.uaf.Parameters.Measurement;
import org.aero.mtip.metamodel.uaf.Parameters.MeasurementSet;
import org.aero.mtip.metamodel.uaf.Projects.ActualMilestoneKind;
import org.aero.mtip.metamodel.uaf.Projects.ActualProject;
import org.aero.mtip.metamodel.uaf.Projects.ActualProjectMilestone;
import org.aero.mtip.metamodel.uaf.Projects.ActualProjectMilestoneRole;
import org.aero.mtip.metamodel.uaf.Projects.ActualProjectRole;
import org.aero.mtip.metamodel.uaf.Projects.Project;
import org.aero.mtip.metamodel.uaf.Projects.ProjectActivity;
import org.aero.mtip.metamodel.uaf.Projects.ProjectActivityAction;
import org.aero.mtip.metamodel.uaf.Projects.ProjectMilestone;
import org.aero.mtip.metamodel.uaf.Projects.ProjectMilestoneRole;
import org.aero.mtip.metamodel.uaf.Projects.ProjectRole;
import org.aero.mtip.metamodel.uaf.Projects.ProjectStatus;
import org.aero.mtip.metamodel.uaf.Projects.ProjectTheme;
import org.aero.mtip.metamodel.uaf.Projects.StatusIndicators;
import org.aero.mtip.metamodel.uaf.Resources.CapabilityConfiguration;
import org.aero.mtip.metamodel.uaf.Resources.DataElement;
import org.aero.mtip.metamodel.uaf.Resources.Function;
import org.aero.mtip.metamodel.uaf.Resources.FunctionAction;
import org.aero.mtip.metamodel.uaf.Resources.NaturalResource;
import org.aero.mtip.metamodel.uaf.Resources.ResourceAction;
import org.aero.mtip.metamodel.uaf.Resources.ResourceArchitecture;
import org.aero.mtip.metamodel.uaf.Resources.ResourceArtifact;
import org.aero.mtip.metamodel.uaf.Resources.ResourceConstraint;
import org.aero.mtip.metamodel.uaf.Resources.ResourceExchangeKind;
import org.aero.mtip.metamodel.uaf.Resources.ResourceInterface;
import org.aero.mtip.metamodel.uaf.Resources.ResourceMethod;
import org.aero.mtip.metamodel.uaf.Resources.ResourceParameter;
import org.aero.mtip.metamodel.uaf.Resources.ResourcePort;
import org.aero.mtip.metamodel.uaf.Resources.ResourceRole;
import org.aero.mtip.metamodel.uaf.Resources.ResourceSignal;
import org.aero.mtip.metamodel.uaf.Resources.ResourceSignalProperty;
import org.aero.mtip.metamodel.uaf.Resources.ResourceStateDescription;
import org.aero.mtip.metamodel.uaf.Resources.ResourcesProcessFlow;
import org.aero.mtip.metamodel.uaf.Resources.RoleKind;
import org.aero.mtip.metamodel.uaf.Resources.Software;
import org.aero.mtip.metamodel.uaf.Resources.Technology;
import org.aero.mtip.metamodel.uaf.Resources.VersionOfConfiguration;
import org.aero.mtip.metamodel.uaf.Resources.WholeLifeConfiguration;
import org.aero.mtip.metamodel.uaf.Resources.WholeLifeConfigurationKind;
import org.aero.mtip.metamodel.uaf.Strategic.Achiever;
import org.aero.mtip.metamodel.uaf.Strategic.ActualEnduringTask;
import org.aero.mtip.metamodel.uaf.Strategic.ActualEnterprisePhase;
import org.aero.mtip.metamodel.uaf.Strategic.Capability;
import org.aero.mtip.metamodel.uaf.Strategic.CapabilityProperty;
import org.aero.mtip.metamodel.uaf.Strategic.Desirer;
import org.aero.mtip.metamodel.uaf.Strategic.EnduringTask;
import org.aero.mtip.metamodel.uaf.Strategic.EnterpriseGoal;
import org.aero.mtip.metamodel.uaf.Strategic.EnterprisePhase;
import org.aero.mtip.metamodel.uaf.Strategic.EnterpriseVision;
import org.aero.mtip.metamodel.uaf.Strategic.StrategicConnectivity;
import org.aero.mtip.metamodel.uaf.Strategic.StrategicConstraints;
import org.aero.mtip.metamodel.uaf.Strategic.StrategicStates;
import org.aero.mtip.metamodel.uaf.Strategic.StrategicStructure;
import org.aero.mtip.metamodel.uaf.Strategic.StrategicTaxonomy;
import org.aero.mtip.metamodel.uaf.Strategic.StructuralPart;
import org.aero.mtip.metamodel.uaf.Strategic.TemporalPart;
import org.aero.mtip.metamodel.uaf.Strategic.VisionStatement;
import org.aero.mtip.metamodel.uaf.Strategic.WholeLifeEnterprise;
import org.aero.mtip.metamodel.uaf.actualresources.ActualOrganization;
import org.aero.mtip.metamodel.uaf.actualresources.ActualPerson;
import org.aero.mtip.metamodel.uaf.actualresources.ActualPost;
import org.aero.mtip.metamodel.uaf.actualresources.ActualResource;
import org.aero.mtip.metamodel.uaf.actualresources.ActualResourcesConnectivity;
import org.aero.mtip.metamodel.uaf.actualresources.ActualResourcesStructure;
import org.aero.mtip.metamodel.uaf.actualresources.ActualResponsibility;
import org.aero.mtip.metamodel.uaf.actualresources.ActualService;
import org.aero.mtip.metamodel.uaf.actualresources.FieldedCapability;
import org.aero.mtip.metamodel.uaf.actualresources.ProvidedServiceLevel;
import org.aero.mtip.metamodel.uaf.actualresources.RequiredServiceLevel;
import org.aero.mtip.metamodel.uaf.personnel.Competence;
import org.aero.mtip.metamodel.uaf.personnel.Organization;
import org.aero.mtip.metamodel.uaf.personnel.Person;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelConnectivity;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelInteractionScenarios;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelInternalConnectivity;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelProcesses;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelStates;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelStructure;
import org.aero.mtip.metamodel.uaf.personnel.PersonnelTaxonomy;
import org.aero.mtip.metamodel.uaf.personnel.Post;
import org.aero.mtip.metamodel.uaf.personnel.Responsibility;
import org.aero.mtip.metamodel.uaf.security.ActualRisk;
import org.aero.mtip.metamodel.uaf.security.EnhancedSecurityControl;
import org.aero.mtip.metamodel.uaf.security.OperationalMitigation;
import org.aero.mtip.metamodel.uaf.security.ResourceMitigation;
import org.aero.mtip.metamodel.uaf.security.Risk;
import org.aero.mtip.metamodel.uaf.security.SecurityConnectivity;
import org.aero.mtip.metamodel.uaf.security.SecurityConstraint;
import org.aero.mtip.metamodel.uaf.security.SecurityConstraints;
import org.aero.mtip.metamodel.uaf.security.SecurityControl;
import org.aero.mtip.metamodel.uaf.security.SecurityControlFamily;
import org.aero.mtip.metamodel.uaf.security.SecurityEnclave;
import org.aero.mtip.metamodel.uaf.security.SecurityProcess;
import org.aero.mtip.metamodel.uaf.security.SecurityProcessAction;
import org.aero.mtip.metamodel.uaf.security.SecurityProcesses;
import org.aero.mtip.metamodel.uaf.security.SecurityProcessesFlow;
import org.aero.mtip.metamodel.uaf.security.SecurityStructure;
import org.aero.mtip.metamodel.uaf.security.SecurityTaxonomy;
import org.aero.mtip.metamodel.uaf.services.ServiceFunction;
import org.aero.mtip.metamodel.uaf.services.ServiceFunctionAction;
import org.aero.mtip.metamodel.uaf.services.ServiceInterface;
import org.aero.mtip.metamodel.uaf.services.ServiceMethod;
import org.aero.mtip.metamodel.uaf.services.ServiceParameter;
import org.aero.mtip.metamodel.uaf.services.ServicePolicy;
import org.aero.mtip.metamodel.uaf.services.ServicePort;
import org.aero.mtip.metamodel.uaf.services.ServiceSpecification;
import org.aero.mtip.metamodel.uaf.services.ServiceSpecificationRole;
import org.aero.mtip.metamodel.uaf.services.ServiceStateDescription;
import org.aero.mtip.metamodel.uaf.services.ServicesConnectivity;
import org.aero.mtip.metamodel.uaf.services.ServicesConstraintsDefinition;
import org.aero.mtip.metamodel.uaf.services.ServicesInteractionScenarios;
import org.aero.mtip.metamodel.uaf.services.ServicesProcesses;
import org.aero.mtip.metamodel.uaf.services.ServicesStates;
import org.aero.mtip.metamodel.uaf.services.ServicesStructure;
import org.aero.mtip.metamodel.uaf.services.ServicesTaxonomy;
import org.aero.mtip.metamodel.uaf.standards.Protocol;
import org.aero.mtip.metamodel.uaf.standards.ProtocolLayer;
import org.aero.mtip.metamodel.uaf.standards.ProtocolStack;
import org.aero.mtip.metamodel.uaf.standards.Standard;
import org.aero.mtip.metamodel.uaf.standards.StandardsStructure;
import org.aero.mtip.metamodel.uaf.standards.StandardsTaxonomy;
import org.aero.mtip.metamodel.uaf.summaryandoverview.SummaryAndOverview;
import org.aero.mtip.metamodel.uml.Artifact;
import org.aero.mtip.util.CameoUtils;

public class CommonElementsFactory {
  public CommonElement createElement(String type, String name, String EAID) {
    CommonElement element = null;

    switch (type) {
      case SysmlConstants.ACCEPT_EVENT_ACTION:
        element = new AcceptEventAction(name, EAID);
        break;
      case SysmlConstants.ACTION:
        element = new Action(name, EAID);
        break;
      case SysmlConstants.ACTIVITY:
        element = new Activity(name, EAID);
        break;
      case SysmlConstants.ACTIVITY_FINAL_NODE:
        element = new ActivityFinalNode(name, EAID);
        break;
      case SysmlConstants.ACTIVITY_PARAMETER_NODE:
        element = new ActivityParameterNode(name, EAID);
        break;
      case SysmlConstants.ACTIVITY_PARTITION:
        element = new ActivityPartition(name, EAID);
        break;
      case SysmlConstants.ACTOR:
        element = new Actor(name, EAID);
        break;
      case SysmlConstants.ANY_RECEIVE_EVENT:
        element = new AnyReceiveEvent(name, EAID);
        break;
      case UmlConstants.ARTIFACT:
        element = new Artifact(name, EAID);
        break;
      case SysmlConstants.ASSOCIATION_BLOCK:
        element = new AssociationBlock(name, EAID);
        break;
      case SysmlConstants.BOUND_REFERENCE:
        element = new BoundReference(name, EAID);
        break;
      case SysmlConstants.BLOCK:
        element = new Block(name, EAID);
        break;
      case SysmlConstants.BUSINESS_REQUIREMENT:
        element = new BusinessRequirement(name, EAID);
        break;
      case SysmlConstants.CALL_BEHAVIOR_ACTION:
        element = new CallBehaviorAction(name, EAID);
        break;
      case SysmlConstants.CALL_EVENT:
        element = new CallEvent(name, EAID);
        break;
      case SysmlConstants.CALL_OPERATION_ACTION:
        element = new CallOperationAction(name, EAID);
        break;
      case SysmlConstants.CENTRAL_BUFFER_NODE:
        element = new CentralBufferNode(name, EAID);
        break;
      case SysmlConstants.CHOICE_PSEUDO_STATE:
        element = new ChoicePseudoState(name, EAID);
        break;
      case SysmlConstants.CHANGE_EVENT:
        element = new ChangeEvent(name, EAID);
        break;
      case SysmlConstants.CLASS:
        element = new Class(name, EAID);
        break;
      case SysmlConstants.CLASSIFIER_BEHAVIOR_PROPERTY:
        element = new ClassifierBehaviorProperty(name, EAID);
        break;
      case SysmlConstants.COLLABORATION:
        element = new Collaboration(name, EAID);
        break;
      case SysmlConstants.COMBINED_FRAGMENT:
        element = new CombinedFragment(name, EAID);
        break;
      case SysmlConstants.COMMENT:
        element = new Comment(name, EAID);
        break;
      case SysmlConstants.CONDITIONAL_NODE:
        element = new ConditionalNode(name, EAID);
        break;
      case SysmlConstants.CONNECTION_POINT_REFERENCE:
        element = new ConnectionPointReference(name, EAID);
        break;
      case SysmlConstants.CONSTRAINT:
        element = new Constraint(name, EAID);
        break;
      case SysmlConstants.CONSTRAINT_BLOCK:
        element = new ConstraintBlock(name, EAID);
        break;
      case SysmlConstants.CONSTRAINT_PARAMETER:
        element = new ConstraintParameter(name, EAID);
        break;
      case SysmlConstants.CONSTRAINT_PROPERTY:
        element = new ConstraintProperty(name, EAID);
        break;
      case SysmlConstants.CREATE_OBJECT_ACTION:
        element = new CreateObjectAction(name, EAID);
        break;
      case SysmlConstants.CUSTOMIZATION:
        element = new Customization(name, EAID);
        break;
      case SysmlConstants.DATA_STORE_NODE:
        element = new DataStoreNode(name, EAID);
        break;
      case SysmlConstants.DATA_TYPE:
        element = new DataType(name, EAID);
        break;
      case SysmlConstants.DECISION_NODE:
        element = new DecisionNode(name, EAID);
        break;
      case SysmlConstants.DEEP_HISTORY:
        element = new DeepHistory(name, EAID);
        break;
      case SysmlConstants.DESIGN_CONSTRAINT:
        element = new DesignConstraint(name, EAID);
        break;
      case SysmlConstants.DESTROY_OBJECT_ACTION:
        element = new DestroyObjectAction(name, EAID);
        break;
      case SysmlConstants.DESTRUCTION_OCCURRENCE_SPECIFICATION:
        element = new DestructionOccurrenceSpecification(name, EAID);
        break;
      // case SysmlConstants.DURATION:
      // element = new Duration(name, EAID);
      // break;
      case SysmlConstants.DURATION_CONSTRAINT:
        element = new DurationConstraint(name, EAID);
        break;
      case SysmlConstants.DURATION_INTERVAL:
        element = new DurationInterval(name, EAID);
        break;
      case SysmlConstants.DURATION_OBSERVATION:
        element = new DurationObservation(name, EAID);
        break;
      case SysmlConstants.ENTRY_POINT:
        element = new EntryPoint(name, EAID);
        break;
      case SysmlConstants.ENUMERATION:
        element = new Enumeration(name, EAID);
        break;
      case SysmlConstants.ENUMERATION_LITERAL:
        element = new EnumerationLiteral(name, EAID);
        break;
      case SysmlConstants.EXIT_POINT:
        element = new ExitPoint(name, EAID);
        break;
      case SysmlConstants.EXTENDED_REQUIREMENT:
        element = new ExtendedRequirement(name, EAID);
        break;
      case SysmlConstants.EXTENSION_POINT:
        element = new ExtensionPoint(name, EAID);
        break;
      case SysmlConstants.FINAL_STATE:
        element = new FinalState(name, EAID);
        break;
      case SysmlConstants.FLOW_FINAL_NODE:
        element = new FlowFinalNode(name, EAID);
        break;
      case SysmlConstants.FORK:
        element = new Fork(name, EAID);
        break;
      case SysmlConstants.FLOW_PORT:
        element = new FlowPort(name, EAID);
        break;
      case SysmlConstants.FLOW_PROPERTY:
        element = new FlowProperty(name, EAID);
        break;
      case SysmlConstants.FLOW_SPECFICATION:
        element = new FlowSpecification(name, EAID);
        break;
      case SysmlConstants.FORK_NODE:
        element = new ForkNode(name, EAID);
        break;
      case SysmlConstants.FULL_PORT:
        element = new FullPort(name, EAID);
        break;
      case SysmlConstants.FUNCTIONAL_REQUIREMENT:
        element = new FunctionalRequirement(name, EAID);
        break;
      case SysmlConstants.FUNCTION_BEHAVIOR:
        element = new FunctionBehavior(name, EAID);
        break;
      case SysmlConstants.INFORMATION_ITEM:
        element = new InformationItem(name, EAID);
        break;
      case SysmlConstants.INITIAL_NODE:
        element = new InitialNode(name, EAID);
        break;
      case SysmlConstants.INITIAL_PSEUDO_STATE:
        element = new InitialPseudoState(name, EAID);
        break;
      case SysmlConstants.INPUT_PIN:
        element = new InputPin(name, EAID);
        break;
      case SysmlConstants.INSTANCE_SPECIFICATION:
        element = new InstanceSpecification(name, EAID);
        break;
      case SysmlConstants.INTERACTION:
        element = new Interaction(name, EAID);
        break;
      case SysmlConstants.INTERACTION_OPERAND:
        element = new InteractionOperand(name, EAID);
        break;
      case SysmlConstants.INTERACTION_USE:
        element = new InteractionUse(name, EAID);
        break;
      case SysmlConstants.INTERFACE:
        element = new Interface(name, EAID);
        break;
      case SysmlConstants.INTERFACE_BLOCK:
        element = new InterfaceBlock(name, EAID);
        break;
      case SysmlConstants.INTERFACE_REALIZATION:
        element = new InterfaceRealization(name, EAID);
        break;
      case SysmlConstants.INTERFACE_REQUIREMENT:
        element = new InterfaceRequirement(name, EAID);
        break;
      case SysmlConstants.INTERRUPTIBLE_ACTIVITY_REGION:
        element = new InterruptibleActivityRegion(name, EAID);
        break;
      case SysmlConstants.ITEM_FLOW:
        element = new ItemFlow(name, EAID);
        break;
      case SysmlConstants.JOIN:
        element = new Join(name, EAID);
        break;
      case SysmlConstants.JOIN_NODE:
        element = new JoinNode(name, EAID);
        break;
      case SysmlConstants.LIFELINE:
        element = new Lifeline(name, EAID);
        break;
      case SysmlConstants.LINK:
        element = new Link(name, EAID);
        break;
      case SysmlConstants.LOOP_NODE:
        element = new LoopNode(name, EAID);
        break;
      case SysmlConstants.METACLASS:
        element = new MetaClass(name, EAID);
        break;
      case SysmlConstants.MERGE_NODE:
        element = new MergeNode(name, EAID);
        break;
      case SysmlConstants.MESSAGE:
        element = new Message(name, EAID);
        break;
      case SysmlConstants.MESSAGE_OCCURRENCE_SPECIFICATION:
        element = new MessageOccurrenceSpecification(name, EAID);
        break;
      case SysmlConstants.MODEL:
        element = new Model(name, EAID);
        break;
      // case SysmlConstants.NOTE:
      // element = new Note(name, EAID);
      // break;
      case SysmlConstants.OPAQUE_ACTION:
        element = new OpaqueAction(name, EAID);
        break;
      case SysmlConstants.OPAQUE_BEHAVIOR:
        element = new OpaqueBehavior(name, EAID);
        break;
      case SysmlConstants.OPAQUE_EXPRESSION:
        element = new OpaqueExpression(name, EAID);
        break;
      case SysmlConstants.OPERATION:
        element = new Operation(name, EAID);
        break;
      case SysmlConstants.OUTPUT_PIN:
        element = new OutputPin(name, EAID);
        break;
      case SysmlConstants.PACKAGE:
        element = new SysmlPackage(name, EAID);
        break;
      case SysmlConstants.PARAMETER:
        element = new Parameter(name, EAID);
        break;
      case SysmlConstants.PARTICIPANT_PROPERTY:
        element = new ParticipantProperty(name, EAID);
        break;
      case SysmlConstants.PART_PROPERTY:
        element = new PartProperty(name, EAID);
        break;
      case SysmlConstants.PERFORMANCE_REQUIREMENT:
        element = new PerformanceRequirement(name, EAID);
        break;
      case SysmlConstants.PHYSICAL_REQUIREMENT:
        element = new PhysicalRequirement(name, EAID);
        break;
      case SysmlConstants.PORT:
        element = new Port(name, EAID);
        break;
      case SysmlConstants.PROFILE:
        element = new Profile(name, EAID);
        break;
      case SysmlConstants.PROPERTY:
        element = new Property(name, EAID);
        break;
      case SysmlConstants.PROXY_PORT:
        element = new ProxyPort(name, EAID);
        break;
      case SysmlConstants.QUANTITY_KIND:
        element = new QuantityKind(name, EAID);
        break;
      case SysmlConstants.REFERENCE_PROPERTY:
        element = new ReferenceProperty(name, EAID);
        break;
      case SysmlConstants.REGION:
        element = new Region(name, EAID);
        break;
      // case SysmlConstants.REQUIRED_INTERFACE:
      // element = new RequiredInterface(name, EAID);
      // break;
      case SysmlConstants.REQUIREMENT:
        element = new Requirement(name, EAID);
        break;
      case SysmlConstants.SEND_SIGNAL_ACTION:
        element = new SendSignalAction(name, EAID);
        break;
      case SysmlConstants.SHALLOW_HISTORY:
        element = new ShallowHistory(name, EAID);
        break;
      case SysmlConstants.SIGNAL:
        element = new Signal(name, EAID);
        break;
      case SysmlConstants.SIGNAL_EVENT:
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
      case SysmlConstants.STATE_INVARIANT:
        element = new StateInvariant(name, EAID);
        break;
      case SysmlConstants.STATE_MACHINE:
        element = new StateMachine(name, EAID);
        break;
      case SysmlConstants.STEREOTYPE:
        element = new Stereotype(name, EAID);
        break;
      case SysmlConstants.STRUCTURED_ACTIVITY_NODE:
        element = new StructuredActivityNode(name, EAID);
        break;
      case SysmlConstants.TERMINATE:
        element = new Terminate(name, EAID);
        break;
      case SysmlConstants.TIME_CONSTRAINT:
        element = new TimeConstraint(name, EAID);
        break;
      case SysmlConstants.TIME_EVENT:
        element = new TimeEvent(name, EAID);
        break;
      case SysmlConstants.TIME_EXPRESSION:
        element = new TimeExpression(name, EAID);
        break;
      case SysmlConstants.TIME_OBSERVATION:
        element = new TimeObservation(name, EAID);
        break;
      case SysmlConstants.TRIGGER:
        element = new Trigger(name, EAID);
        break;
      case SysmlConstants.UNIT:
        element = new Unit(name, EAID);
        break;
      case SysmlConstants.USABILITY_REQUIREMENT:
        element = new UsabilityRequirement(name, EAID);
        break;
      case SysmlConstants.USE_CASE:
        element = new UseCase(name, EAID);
        break;
      case SysmlConstants.VALUE_PROPERTY:
        element = new ValueProperty(name, EAID);
        break;
      case SysmlConstants.VALUE_TYPE:
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
      case SysmlConstants.SYSTEM_CONTEXT:
        element = new SystemContext(name, EAID);
        break;

      // DIAGRAMS *********************************************************************
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

      // Tables *********************************************************************
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
      // Matrices *********************************************************************
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
      // case UAFConstants.STRATEGIC_TAXONOMY_PACKAGE:
      // element = new StrategicTaxonomyPackage(name, EAID);
      // break;
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
      // Operational
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

      // Security
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
      case UAFConstants.SERVICE_FUNCTION:
        element = new ServiceFunction(name, EAID);
        break;
      case UAFConstants.SERVICE_FUNCTION_ACTION:
        element = new ServiceFunctionAction(name, EAID);
        break;
      case UAFConstants.SERVICE_INTERFACE:
        element = new ServiceInterface(name, EAID);
        break;
      case UAFConstants.SERVICE_METHOD:
        element = new ServiceMethod(name, EAID);
        break;
      case UAFConstants.SERVICE_PARAMETER:
        element = new ServiceParameter(name, EAID);
        break;
      case UAFConstants.SERVICE_POLICY:
        element = new ServicePolicy(name, EAID);
        break;
      case UAFConstants.SERVICE_PORT:
        element = new ServicePort(name, EAID);
        break;
      case UAFConstants.SERVICE_SPECIFICATION:
        element = new ServiceSpecification(name, EAID);
        break;
      case UAFConstants.SERVICE_SPECIFICATION_ROLE:
        element = new ServiceSpecificationRole(name, EAID);
        break;
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
      case UAFConstants.PERSONNEL_INTERACTION_SCENARIOS_DIAGRAM:
        element = new PersonnelInteractionScenarios(name, EAID);
        break;
      case UAFConstants.PERSONNEL_INTERNAL_CONNECTIVITY_DIAGRAM:
        element = new PersonnelInternalConnectivity(name, EAID);
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
      // Projects Diagrams
      // case UAFConstants.PROJECTS_TAXONOMY_DIAGRAM:
      // element = new ProjectsTaxonomy(name, EAID);
      // break;
      // case UAFConstants.PROJECTS_STRUCTURE_DIAGRAM:
      // element = new ProjectsStructure(name, EAID);
      // break;
      // case UAFConstants.PROJECTS_CONNECTIVITY_DIAGRAM:
      // element = new ProjectsConnectivity(name, EAID);
      // break;
      // case UAFConstants.PROJECTS_PROCESSES_DIAGRAM:
      // element = new ProjectsProcesses(name, EAID);
      // break;
      // Resources Diagrams
      // case UAFConstants.RESOURCES_CONNECTIVITY_DIAGRAM:
      // element = new ResourcesConnectivity(name, EAID);
      // break;
      // case UAFConstants.RESOURCES_INTERACTION_SCENARIOS_DIAGRAM:
      // element = new ResourcesInteractionScenarios(name, EAID);
      // break;
      // case UAFConstants.RESOURCES_PROCESSES_DIAGRAM:
      // element = new ResourcesProcesses(name, EAID);
      // break;
      // case UAFConstants.RESOURCES_STATES_DIAGRAM:
      // element = new ResourcesStates(name, EAID);
      // break;
      // case UAFConstants.RESOURCES_STRUCTURE_DIAGRAM:
      // element = new ResourcesStructure(name, EAID);
      // break;
      // case UAFConstants.RESOURCES_TAXONOMY_DIAGRAM:
      // element = new ResourcesTaxonomy(name, EAID);
      // break;
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
      case UAFConstants.SECURITY_PROCESSES_DIAGRAM:
        element = new SecurityProcesses(name, EAID);
        break;
      case UAFConstants.SECURITY_PROCESSES_FLOW_DIAGRAM:
        element = new SecurityProcessesFlow(name, EAID);
        break;
      case UAFConstants.SECURITY_CONSTRAINTS_DIAGRAM:
        element = new SecurityConstraints(name, EAID);
        break;

      // Services Diagrams
      case UAFConstants.SERVICES_CONNECTIVITY_DIAGRAM:
        element = new ServicesConnectivity(name, EAID);
        break;
      case UAFConstants.SERVICES_CONSTRAINTS_DEFINITION_DIAGRAM:
        element = new ServicesConstraintsDefinition(name, EAID);
        break;
      case UAFConstants.SERVICES_INTERACTION_SCENARIOS_DIAGRAM:
        element = new ServicesInteractionScenarios(name, EAID);
        break;
      case UAFConstants.SERVICES_PROCESSES_DIAGRAM:
        element = new ServicesProcesses(name, EAID);
        break;
      case UAFConstants.SERVICES_STATES_DIAGRAM:
        element = new ServicesStates(name, EAID);
        break;
      case UAFConstants.SERVICES_STRUCTURE_DIAGRAM:
        element = new ServicesStructure(name, EAID);
        break;
      case UAFConstants.SERVICES_TAXONOMY_DIAGRAM:
        element = new ServicesTaxonomy(name, EAID);
        break;
      // Summary and Overview Diagrams
      case UAFConstants.SUMMARY_AND_OVERVIEW_DIAGRAM:
        element = new SummaryAndOverview(name, EAID);
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
      // Operational Diagrams
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
      case UAFConstants.OPERATIONAL_PROCESSES_DIAGRAM:
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
      case UAFConstants.OPERATIONAL_INTERNAL_CONNECTIVITY:
        element = new OperationalInternalConnectivity(name, EAID);
        break;
      case UAFConstants.OPERATIONAL_PARAMETRIC:
        element = new OperationalParametric(name, EAID);
        break;
      case UAFConstants.OPERATIONAL_STATES:
        element = new OperationalStates(name, EAID);
        break;
      // Parameter Diagrams
      case UAFConstants.ENVIRONMENT_DIAGRAM:
        element = new EnvironmentDiagram(name, EAID);
        break;
      // Resources
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
      // TODO: Fix conflcit between UAFConstants.SYSTEM and MD System
      // case UAFConstants.SYSTEM:
      // element = new System(name, EAID);
      // break;
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
      // Definition
      case UAFConstants.DEFINITION:
        element = new Definition(name, EAID);
        break;
      case UAFConstants.ALIAS:
        element = new Alias(name, EAID);
        break;
      case UAFConstants.INFORMATION:
        element = new Information(name, EAID);
        break;
      // Standards
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

      // Parameters
      case UAFConstants.ACTUAL_CONDITION:
        element = new ActualCondition(name, EAID);
        break;
      case UAFConstants.ACTUAL_ENVIRONMENT:
        element = new ActualEnvironment(name, EAID);
        break;
      case UAFConstants.ACTUAL_LOCATION:
        element = new ActualLocation(name, EAID);
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
      case UAFConstants.ACTUAL_MEASUREMENT:
        element = new ActualMeasurement(name, EAID);
        break;
      case UAFConstants.ENVIRONMENT:
        element = new Environment(name, EAID);
        break;
      case UAFConstants.ENVIRONMENT_KIND:
        element = new EnvironmentKind(name, EAID);
        break;
      case UAFConstants.CONDITION:
        element = new Condition(name, EAID);
        break;
      case UAFConstants.GEO_POLITICAL_EXTENT_TYPE:
        element = new GeoPoliticalExtentType(name, EAID);
        break;
      case UAFConstants.LOCATION:
        element = new Location(name, EAID);
        break;
      case UAFConstants.MEASUREMENT_SET:
        element = new MeasurementSet(name, EAID);
        break;
      case UAFConstants.MEASUREMENT:
        element = new Measurement(name, EAID);
        break;

      // Metadata
      case UAFConstants.METADATA:
        element = new Metadata(name, EAID);
        break;
      case UAFConstants.DATA_MODEL_KIND:
        element = new DataModelKind(name, EAID);
        break;

      default:
        break;
    }

    if (element == null) {
      CameoUtils.logGui(
          String.format("Element of type %s not supported by CommonElementsFactory.", type));
    }

    return element;
  }
}
