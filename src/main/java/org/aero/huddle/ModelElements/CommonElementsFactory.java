package org.aero.huddle.ModelElements;

import org.aero.huddle.ModelElements.Activity.Activity;
import org.aero.huddle.ModelElements.Requirements.DesignConstraint;
import org.aero.huddle.ModelElements.Requirements.ExtendedRequirement;
import org.aero.huddle.ModelElements.Requirements.FunctionalRequirement;
import org.aero.huddle.ModelElements.Requirements.InterfaceRequirement;
import org.aero.huddle.ModelElements.Requirements.PerformanceRequirement;
import org.aero.huddle.ModelElements.Requirements.PhysicalRequirement;
import org.aero.huddle.ModelElements.Requirements.Requirement;
import org.aero.huddle.ModelElements.Sequence.CECombinedFragment;
import org.aero.huddle.ModelElements.Sequence.Collaboration;
import org.aero.huddle.ModelElements.Sequence.Interaction;
import org.aero.huddle.ModelElements.Sequence.InteractionUse;
import org.aero.huddle.ModelElements.Sequence.Lifeline;
import org.aero.huddle.ModelElements.UseCase.Actor;
import org.aero.huddle.ModelElements.UseCase.UseCase;
import org.aero.huddle.util.CameoUtils;

public class CommonElementsFactory {
	public CommonElement createElement(String type, String name, String EAID) {
		CommonElement element = null;
		switch(type) {
			case "Activity":
				element = new Activity(name, EAID);
				break;
			case "ActivityParameter":
				element = new ActivityParameterNode(name, EAID);
				break;
			case "Actor":
				element = new Actor(name, EAID);
				break;
//			case "Attribute":
//				element = new Attribute(name, EAID);
//				break;
			case "Block":
				element = new Block(name, EAID);
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
			case "DesignConstraint":
				element = new DesignConstraint(name, EAID);
				break;
			case "ExtendedRequirement":
				element = new ExtendedRequirement(name, EAID);
				break;
			case "FinalState":
				element = new FinalState(name, EAID);
				break;
			case "FunctionalRequirement":
				element = new FunctionalRequirement(name, EAID);
				break;
			case "Interaction":
				element = new Interaction(name, EAID);
				break;
			case "InteractionUse":
				element = new InteractionUse(name, EAID);
				break;
			case "InitialPseudoState":
				element = new InitialPseudoState(name, EAID);
				break;
			case "InterfaceBlock":
				element = new InterfaceBlock(name, EAID);
				break;
			case "InterfaceRequirement":
				element = new InterfaceRequirement(name, EAID);
				break;
			case "Lifeline":
				element = new Lifeline(name, EAID);
				break;
			case "Model":
				element = new Model(name, EAID);
				break;
			case "Note":
				element = new Note(name, EAID);
				break;
			case "Operation":
				element = new Operation(name, EAID);
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
			// Property vs PartProperty - EA treats the same? Check if parent is block?
			case "Property":
				element = new PartProperty(name, EAID);
				break;
			case "RequiredInterface":
				element = new RequiredInterface(name, EAID);
				break;
			case "Requirement":
				element = new Requirement(name, EAID);
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
			case "Trigger":
				element = new Trigger(name, EAID);
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
			default:
				break;
		}
		if(element == null) {
			CameoUtils.logGUI("Element of type " + type + " not supported by CommonElementsFactory.");
		}
		return element;	
	}
}
