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
import org.aero.mtip.ModelElements.Profile.DataType;
import org.aero.mtip.ModelElements.Profile.MetaClass;
import org.aero.mtip.ModelElements.Profile.OpaqueExpression;
import org.aero.mtip.ModelElements.Profile.PackageDiagram;
import org.aero.mtip.ModelElements.Profile.ParametricDiagram;
import org.aero.mtip.ModelElements.Profile.Profile;
import org.aero.mtip.ModelElements.Profile.ProfileDiagram;
import org.aero.mtip.ModelElements.Profile.Stereotype;
import org.aero.mtip.ModelElements.Requirements.BusinessRequirement;
import org.aero.mtip.ModelElements.Requirements.DesignConstraint;
import org.aero.mtip.ModelElements.Requirements.ExtendedRequirement;
import org.aero.mtip.ModelElements.Requirements.FunctionalRequirement;
import org.aero.mtip.ModelElements.Requirements.InterfaceRequirement;
import org.aero.mtip.ModelElements.Requirements.PerformanceRequirement;
import org.aero.mtip.ModelElements.Requirements.PhysicalRequirement;
import org.aero.mtip.ModelElements.Requirements.Requirement;
import org.aero.mtip.ModelElements.Requirements.RequirementsDiagram;
import org.aero.mtip.ModelElements.Requirements.UsabilityRequirement;
import org.aero.mtip.ModelElements.Sequence.Collaboration;
import org.aero.mtip.ModelElements.Sequence.CombinedFragment;
import org.aero.mtip.ModelElements.Sequence.DestructionOccurrenceSpecification;
import org.aero.mtip.ModelElements.Sequence.DurationConstraint;
import org.aero.mtip.ModelElements.Sequence.DurationInterval;
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
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;

public class CommonElementsFactory {
	public CommonElement createElement(String type, String name, String EAID) {
		CommonElement element = null;
		switch(type) {
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
			case SysmlConstants.ASSOCIATION_BLOCK:
				element = new AssociationBlock(name, EAID);
				break;
			case SysmlConstants.BOUND_REFERENCE:
				element = new BoundReference(name, EAID);
				break;
			case SysmlConstants.BLOCK:
				element = new Block(name, EAID);
				break;
			case SysmlConstants.CALL_BEHAVIOR_ACTION:
				element = new CallBehaviorAction(name, EAID);
				break;
			case SysmlConstants.BUSINESS_REQUIREMENT:
				element = new BusinessRequirement(name, EAID);
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
			case SysmlConstants.DURATION:
				element = new Duration(name, EAID);
				break;
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
//			case SysmlConstants.NOTE:
//				element = new Note(name, EAID);
//				break;
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
			case SysmlConstants.REFERENCE_POINT:
				element = new ReferenceProperty(name, EAID);
				break;
			case SysmlConstants.REGION:
				element = new Region(name, EAID);
				break;
//			case SysmlConstants.REQUIRED_INTERFACE:
//				element = new RequiredInterface(name, EAID);
//				break;
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
			default:
				break;
		}
		
		if(element == null) {
			CameoUtils.logGUI(String.format("Element of type %s not supported by CommonElementsFactory.", type));
		}
		
		return element;	
	}
}
