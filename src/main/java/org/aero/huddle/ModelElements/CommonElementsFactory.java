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
import org.aero.huddle.ModelElements.Activity.ConditionalNode;
import org.aero.huddle.ModelElements.Activity.CreateObjectAction;
import org.aero.huddle.ModelElements.Activity.DataStoreNode;
import org.aero.huddle.ModelElements.Activity.DecisionNode;
import org.aero.huddle.ModelElements.Activity.DestroyObjectAction;
import org.aero.huddle.ModelElements.Activity.FlowFinalNode;
import org.aero.huddle.ModelElements.Activity.ForkNode;
import org.aero.huddle.ModelElements.Activity.InitialNode;
import org.aero.huddle.ModelElements.Activity.InputPin;
import org.aero.huddle.ModelElements.Activity.JoinNode;
import org.aero.huddle.ModelElements.Activity.LoopNode;
import org.aero.huddle.ModelElements.Activity.MergeNode;
import org.aero.huddle.ModelElements.Activity.OpaqueAction;
import org.aero.huddle.ModelElements.Activity.OutputPin;
import org.aero.huddle.ModelElements.Activity.SendSignalAction;
import org.aero.huddle.ModelElements.Block.AssociationBlock;
import org.aero.huddle.ModelElements.Block.Block;
import org.aero.huddle.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.huddle.ModelElements.Block.Class;
import org.aero.huddle.ModelElements.Block.ConstraintBlock;
import org.aero.huddle.ModelElements.Block.Domain;
import org.aero.huddle.ModelElements.Block.Enumeration;
import org.aero.huddle.ModelElements.Block.External;
import org.aero.huddle.ModelElements.Block.FlowPort;
import org.aero.huddle.ModelElements.Block.FullPort;
import org.aero.huddle.ModelElements.Block.InstanceSpecification;
import org.aero.huddle.ModelElements.Block.Interface;
import org.aero.huddle.ModelElements.Block.InterfaceBlock;
import org.aero.huddle.ModelElements.Block.InternalBlockDiagram;
import org.aero.huddle.ModelElements.Block.Note;
import org.aero.huddle.ModelElements.Block.Operation;
import org.aero.huddle.ModelElements.Block.PartProperty;
import org.aero.huddle.ModelElements.Block.Port;
import org.aero.huddle.ModelElements.Block.ProxyPort;
import org.aero.huddle.ModelElements.Block.QuantityKind;
import org.aero.huddle.ModelElements.Block.Signal;
import org.aero.huddle.ModelElements.Block.Subsystem;
import org.aero.huddle.ModelElements.Block.System;
import org.aero.huddle.ModelElements.Block.SystemContext;
import org.aero.huddle.ModelElements.Block.Unit;
import org.aero.huddle.ModelElements.Block.ValueProperty;
import org.aero.huddle.ModelElements.Block.ValueType;
import org.aero.huddle.ModelElements.InternalBlock.RequiredInterface;
import org.aero.huddle.ModelElements.Requirements.DesignConstraint;
import org.aero.huddle.ModelElements.Requirements.ExtendedRequirement;
import org.aero.huddle.ModelElements.Requirements.FunctionalRequirement;
import org.aero.huddle.ModelElements.Requirements.InterfaceRequirement;
import org.aero.huddle.ModelElements.Requirements.PerformanceRequirement;
import org.aero.huddle.ModelElements.Requirements.PhysicalRequirement;
import org.aero.huddle.ModelElements.Requirements.Requirement;
import org.aero.huddle.ModelElements.Requirements.RequirementsDiagram;
import org.aero.huddle.ModelElements.Sequence.CECombinedFragment;
import org.aero.huddle.ModelElements.Sequence.Collaboration;
import org.aero.huddle.ModelElements.Sequence.Interaction;
import org.aero.huddle.ModelElements.Sequence.InteractionUse;
import org.aero.huddle.ModelElements.Sequence.Lifeline;
import org.aero.huddle.ModelElements.Sequence.Property;
import org.aero.huddle.ModelElements.Sequence.SequenceDiagram;
import org.aero.huddle.ModelElements.StateMachine.ChoicePseudoState;
import org.aero.huddle.ModelElements.StateMachine.DeepHistory;
import org.aero.huddle.ModelElements.StateMachine.EntryPoint;
import org.aero.huddle.ModelElements.StateMachine.ExitPoint;
import org.aero.huddle.ModelElements.StateMachine.FinalState;
import org.aero.huddle.ModelElements.StateMachine.Fork;
import org.aero.huddle.ModelElements.StateMachine.InitialPseudoState;
import org.aero.huddle.ModelElements.StateMachine.Join;
import org.aero.huddle.ModelElements.StateMachine.ShallowHistory;
import org.aero.huddle.ModelElements.StateMachine.State;
import org.aero.huddle.ModelElements.StateMachine.StateMachine;
import org.aero.huddle.ModelElements.StateMachine.StateMachineDiagram;
import org.aero.huddle.ModelElements.StateMachine.Terminate;
import org.aero.huddle.ModelElements.StateMachine.Trigger;
import org.aero.huddle.ModelElements.UseCase.Actor;
import org.aero.huddle.ModelElements.UseCase.UseCase;
import org.aero.huddle.ModelElements.UseCase.UseCaseDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;

public class CommonElementsFactory {
	public CommonElement createElement(String type, String name, String EAID) {
		CommonElement element = null;
		switch(type) {
			case "AcceptEventAction":
				element = new AcceptEventAction(name, EAID);
				break;
			case "Action":
				element = new Action(name, EAID);
				break;
			case "Activity":
				element = new Activity(name, EAID);
				break;
			case "ActivityFinalNode":
				element = new ActivityFinalNode(name, EAID);
				break;
			case "ActivityParameter":
				element = new ActivityParameterNode(name, EAID);
				break;
			case "ActivityPartition":
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
			case "Class":
				element = new Class(name, EAID);
				break;
			case "Collaboration":
				element = new Collaboration(name, EAID);
				break;
			case "CombinedFragment":
				element = new CECombinedFragment(name, EAID);
				break;
			case "ConditionalNode":
				element = new ConditionalNode(name, EAID);
				break;
			case "ConstraintBlock":
				element = new ConstraintBlock(name, EAID);
				break;
			case "CreateObjectAction":
				element = new CreateObjectAction(name, EAID);
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
			case SysmlConstants.ENTRYPOINT:
				element = new EntryPoint(name, EAID);
				break;
			case "Enumeration":
				element = new Enumeration(name, EAID);
				break;
			case "ExitPoint":
				element = new ExitPoint(name, EAID);
				break;
			case "ExtendedRequirement":
				element = new ExtendedRequirement(name, EAID);
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
			case "FlowPort":
				element = new FlowPort(name, EAID);
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
			case "InteractionUse":
				element = new InteractionUse(name, EAID);
				break;
			case SysmlConstants.INTERFACE:
				element = new Interface(name, EAID);
				break;
			case "InterfaceBlock":
				element = new InterfaceBlock(name, EAID);
				break;
			case "InterfaceRequirement":
				element = new InterfaceRequirement(name, EAID);
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
			case "LoopNode":
				element = new LoopNode(name, EAID);
				break;
			case "MergeNode":
				element = new MergeNode(name, EAID);
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
			case "Operation":
				element = new Operation(name, EAID);
				break;
			case "OutputPin":
				element = new OutputPin(name, EAID);
				break;
			case "Package":
				element = new SysmlPackage(name, EAID);
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
			case "RequiredInterface":
				element = new RequiredInterface(name, EAID);
				break;
			case "Requirement":
				element = new Requirement(name, EAID);
				break;
			case "SendSignalAction":
				element = new SendSignalAction(name, EAID);
				break;
			case SysmlConstants.SHALLOWHISTORY:
				element = new ShallowHistory(name, EAID);
				break;
			case "Signal":
				element = new Signal(name, EAID);
				break;
			case "State":
				element = new State(name, EAID);
				break;
			case "StateMachine":
				element = new StateMachine(name, EAID);
				break;
			case "Stereotype":
				element = new sysmlStereotype(name, EAID);
				break;
			case SysmlConstants.TERMINATE:
				element = new Terminate(name, EAID);
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
			case "ActivityDiagram":
				element = new ActivityDiagram(name, EAID);
				break;
			case "BlockDefinitionDiagram":
				element = new BlockDefinitionDiagram(name, EAID);
				break;
			case "StateMachineDiagram":
				element = new StateMachineDiagram(name, EAID);
				break;
			case "InternalBlockDiagram":
				element = new InternalBlockDiagram(name, EAID);
				break;
			case "UseCaseDiagram":
				element = new UseCaseDiagram(name, EAID);
				break;
			case "RequirementsDiagram":
				element = new RequirementsDiagram(name, EAID);
				break;
			case "SequenceDiagram":
				element = new SequenceDiagram(name, EAID);
				break;
				
//			case "ParametricDiagram":
//				element = new ParametricDiagram(name, EAID);
//				break;
//			case "PackageDiagram":
//				element = new PackageDiagram(name, EAID);
//				break;
//				
			default:
				break;
		}
		if(element == null) {
			CameoUtils.logGUI("Element of type " + type + " not supported by CommonElementsFactory.");
		}
		return element;	
	}
}
