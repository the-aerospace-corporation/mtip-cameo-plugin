package org.aero.huddle.ModelElements;

import org.aero.huddle.ModelElements.Activity.AcceptEventAction;
import org.aero.huddle.ModelElements.Activity.Action;
import org.aero.huddle.ModelElements.Activity.Activity;
import org.aero.huddle.ModelElements.Activity.ActivityDiagram;
import org.aero.huddle.ModelElements.Activity.ActivityFinalNode;
import org.aero.huddle.ModelElements.Activity.ActivityParameterNode;
import org.aero.huddle.ModelElements.Activity.ActivityPartition;
import org.aero.huddle.ModelElements.Activity.CallBehaviorAction;
import org.aero.huddle.ModelElements.Activity.CallOperationAction;
import org.aero.huddle.ModelElements.Activity.CentralBufferNode;
import org.aero.huddle.ModelElements.Activity.ChangeEvent;
import org.aero.huddle.ModelElements.Activity.ConditionalNode;
import org.aero.huddle.ModelElements.Activity.CreateObjectAction;
import org.aero.huddle.ModelElements.Activity.DataStoreNode;
import org.aero.huddle.ModelElements.Activity.DecisionNode;
import org.aero.huddle.ModelElements.Activity.DestroyObjectAction;
import org.aero.huddle.ModelElements.Activity.FlowFinalNode;
import org.aero.huddle.ModelElements.Activity.ForkNode;
import org.aero.huddle.ModelElements.Activity.InitialNode;
import org.aero.huddle.ModelElements.Activity.InputPin;
import org.aero.huddle.ModelElements.Activity.InterruptibleActivityRegion;
import org.aero.huddle.ModelElements.Activity.JoinNode;
import org.aero.huddle.ModelElements.Activity.LoopNode;
import org.aero.huddle.ModelElements.Activity.MergeNode;
import org.aero.huddle.ModelElements.Activity.OpaqueAction;
import org.aero.huddle.ModelElements.Activity.OutputPin;
import org.aero.huddle.ModelElements.Activity.SendSignalAction;
import org.aero.huddle.ModelElements.Activity.TimeEvent;
import org.aero.huddle.ModelElements.Activity.TimeExpression;
import org.aero.huddle.ModelElements.Block.AssociationBlock;
import org.aero.huddle.ModelElements.Block.Block;
import org.aero.huddle.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.huddle.ModelElements.Block.ConstraintBlock;
import org.aero.huddle.ModelElements.Block.Domain;
import org.aero.huddle.ModelElements.Block.Enumeration;
import org.aero.huddle.ModelElements.Block.External;
import org.aero.huddle.ModelElements.Block.FlowPort;
import org.aero.huddle.ModelElements.Block.FlowSpecification;
import org.aero.huddle.ModelElements.Block.FullPort;
import org.aero.huddle.ModelElements.Block.InformationItem;
import org.aero.huddle.ModelElements.Block.InstanceSpecification;
import org.aero.huddle.ModelElements.Block.Interface;
import org.aero.huddle.ModelElements.Block.InterfaceBlock;
import org.aero.huddle.ModelElements.Block.InterfaceRealization;
import org.aero.huddle.ModelElements.Block.Note;
import org.aero.huddle.ModelElements.Block.Operation;
import org.aero.huddle.ModelElements.Block.PartProperty;
import org.aero.huddle.ModelElements.Block.Port;
import org.aero.huddle.ModelElements.Block.ProxyPort;
import org.aero.huddle.ModelElements.Block.QuantityKind;
import org.aero.huddle.ModelElements.Block.Signal;
import org.aero.huddle.ModelElements.Block.Slot;
import org.aero.huddle.ModelElements.Block.Subsystem;
import org.aero.huddle.ModelElements.Block.System;
import org.aero.huddle.ModelElements.Block.SystemContext;
import org.aero.huddle.ModelElements.Block.Unit;
import org.aero.huddle.ModelElements.Block.ValueProperty;
import org.aero.huddle.ModelElements.Block.ValueType;
import org.aero.huddle.ModelElements.InternalBlock.BoundReference;
import org.aero.huddle.ModelElements.InternalBlock.ClassifierBehaviorProperty;
import org.aero.huddle.ModelElements.InternalBlock.ConstraintParameter;
import org.aero.huddle.ModelElements.InternalBlock.ConstraintProperty;
import org.aero.huddle.ModelElements.InternalBlock.FlowProperty;
import org.aero.huddle.ModelElements.InternalBlock.InternalBlockDiagram;
import org.aero.huddle.ModelElements.InternalBlock.ItemFlow;
import org.aero.huddle.ModelElements.InternalBlock.ParticipantProperty;
import org.aero.huddle.ModelElements.InternalBlock.ReferenceProperty;
import org.aero.huddle.ModelElements.InternalBlock.RequiredInterface;
import org.aero.huddle.ModelElements.Matrix.AllocationMatrix;
import org.aero.huddle.ModelElements.Matrix.DependencyMatrix;
import org.aero.huddle.ModelElements.Matrix.DeriveRequirementMatrix;
import org.aero.huddle.ModelElements.Matrix.RefineRequirementMatrix;
import org.aero.huddle.ModelElements.Matrix.SatisfyRequirementMatrix;
import org.aero.huddle.ModelElements.Matrix.VerifyRequirementMatrix;
import org.aero.huddle.ModelElements.Profile.Class;
import org.aero.huddle.ModelElements.Profile.ClassDiagram;
import org.aero.huddle.ModelElements.Profile.Constraint;
import org.aero.huddle.ModelElements.Profile.Customization;
import org.aero.huddle.ModelElements.Profile.MetaClass;
import org.aero.huddle.ModelElements.Profile.OpaqueExpression;
import org.aero.huddle.ModelElements.Profile.PackageDiagram;
import org.aero.huddle.ModelElements.Profile.ParametricDiagram;
import org.aero.huddle.ModelElements.Profile.Profile;
import org.aero.huddle.ModelElements.Profile.ProfileDiagram;
import org.aero.huddle.ModelElements.Profile.Stereotype;
import org.aero.huddle.ModelElements.Requirements.DesignConstraint;
import org.aero.huddle.ModelElements.Requirements.ExtendedRequirement;
import org.aero.huddle.ModelElements.Requirements.FunctionalRequirement;
import org.aero.huddle.ModelElements.Requirements.InterfaceRequirement;
import org.aero.huddle.ModelElements.Requirements.PerformanceRequirement;
import org.aero.huddle.ModelElements.Requirements.PhysicalRequirement;
import org.aero.huddle.ModelElements.Requirements.Requirement;
import org.aero.huddle.ModelElements.Requirements.RequirementsDiagram;
import org.aero.huddle.ModelElements.Sequence.Collaboration;
import org.aero.huddle.ModelElements.Sequence.CombinedFragment;
import org.aero.huddle.ModelElements.Sequence.DestructionOccurrenceSpecification;
import org.aero.huddle.ModelElements.Sequence.DurationConstraint;
import org.aero.huddle.ModelElements.Sequence.DurationObservation;
import org.aero.huddle.ModelElements.Sequence.Interaction;
import org.aero.huddle.ModelElements.Sequence.InteractionOperand;
import org.aero.huddle.ModelElements.Sequence.InteractionUse;
import org.aero.huddle.ModelElements.Sequence.Lifeline;
import org.aero.huddle.ModelElements.Sequence.Message;
import org.aero.huddle.ModelElements.Sequence.MessageOccurrenceSpecification;
import org.aero.huddle.ModelElements.Sequence.Property;
import org.aero.huddle.ModelElements.Sequence.SequenceDiagram;
import org.aero.huddle.ModelElements.Sequence.StateInvariant;
import org.aero.huddle.ModelElements.Sequence.TimeConstraint;
import org.aero.huddle.ModelElements.Sequence.TimeObservation;
import org.aero.huddle.ModelElements.StateMachine.ChoicePseudoState;
import org.aero.huddle.ModelElements.StateMachine.ConnectionPointReference;
import org.aero.huddle.ModelElements.StateMachine.DeepHistory;
import org.aero.huddle.ModelElements.StateMachine.EntryPoint;
import org.aero.huddle.ModelElements.StateMachine.ExitPoint;
import org.aero.huddle.ModelElements.StateMachine.FinalState;
import org.aero.huddle.ModelElements.StateMachine.Fork;
import org.aero.huddle.ModelElements.StateMachine.FunctionBehavior;
import org.aero.huddle.ModelElements.StateMachine.InitialPseudoState;
import org.aero.huddle.ModelElements.StateMachine.Join;
import org.aero.huddle.ModelElements.StateMachine.OpaqueBehavior;
import org.aero.huddle.ModelElements.StateMachine.Region;
import org.aero.huddle.ModelElements.StateMachine.ShallowHistory;
import org.aero.huddle.ModelElements.StateMachine.SignalEvent;
import org.aero.huddle.ModelElements.StateMachine.State;
import org.aero.huddle.ModelElements.StateMachine.StateMachine;
import org.aero.huddle.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.huddle.ModelElements.StateMachine.Terminate;
import org.aero.huddle.ModelElements.StateMachine.Trigger;
import org.aero.huddle.ModelElements.Table.GenericTable;
import org.aero.huddle.ModelElements.Table.GlossaryTable;
import org.aero.huddle.ModelElements.Table.InstanceTable;
import org.aero.huddle.ModelElements.Table.MetricTable;
import org.aero.huddle.ModelElements.Table.RequirementTable;
import org.aero.huddle.ModelElements.UseCase.Actor;
import org.aero.huddle.ModelElements.UseCase.ExtensionPoint;
import org.aero.huddle.ModelElements.UseCase.UseCase;
import org.aero.huddle.ModelElements.UseCase.UseCaseDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;

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
			case "Actor":
				element = new Actor(name, EAID);
				break;
//			case "Attribute":
//				element = new Attribute(name, EAID);
//				break;
			case "AssociationBlock":
				element = new AssociationBlock(name, EAID);
				break;
			case "BoundReference":
				element = new BoundReference(name, EAID);
				break;
			case "Block":
				element = new Block(name, EAID);
				break;
			case "CallBehaviorAction":
				element = new CallBehaviorAction(name, EAID);
				break;
			case "CallOperationAction":
				element = new CallOperationAction(name, EAID);
				break;
			case "CentralBufferNode":
				element = new CentralBufferNode(name, EAID);
				break;
			case SysmlConstants.CHOICEPSEUDOSTATE:
				element = new ChoicePseudoState(name, EAID);
				break;
			case SysmlConstants.CHANGEEVENT:
				element = new ChangeEvent(name, EAID);
				break;
			case "Class":
				element = new Class(name, EAID);
				break;
			case SysmlConstants.CLASSIFIERBEHAVIORPROPERTY:
				element = new ClassifierBehaviorProperty(name, EAID);
				break;
			case "Collaboration":
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
			case "Interaction":
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
			case "State":
				element = new State(name, EAID);
				break;
			case SysmlConstants.STATEINVARIANT:
				element = new StateInvariant(name, EAID);
				break;
			case "StateMachine":
				element = new StateMachine(name, EAID);
				break;
			case "Stereotype":
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
			case "Trigger":
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
			default:
				break;
		}
		if(element == null) {
			CameoUtils.logGUI("Element of type " + type + " not supported by CommonElementsFactory.");
		}
		return element;	
	}
}
