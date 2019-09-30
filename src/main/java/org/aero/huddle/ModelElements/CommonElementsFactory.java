package org.aero.huddle.ModelElements;

import org.aero.huddle.util.CameoUtils;

public class CommonElementsFactory {
	public CommonElement createElement(String type, String name, String EAID) {
		CommonElement element = null;
		switch(type) {
			case "ActivityParameter":
				element = new ActivityParameterNode(name, EAID);
				break;
			case "Package":
				element = new SysmlPackage(name, EAID);
				break;
			case "Block":
				element = new Block(name, EAID);
				break;
			case "Port":
				element = new Port(name, EAID);
				break;
			case "RequiredInterface":
				element = new RequiredInterface(name, EAID);
				break;
			case "Operation":
				element = new Operation(name, EAID);
				break;
			case "ValueProperty":
				element = new ValueProperty(name, EAID);
				break;
			case "StateMachine":
				element = new StateMachine(name, EAID);
				break;
			case "State":
				element = new State(name, EAID);
				break;
			case "InitialPseudoState":
				element = new InitialPseudoState(name, EAID);
				break;
			case "FinalState":
				element = new FinalState(name, EAID);
				break;
			case "Trigger":
				element = new Trigger(name, EAID);
				break;
			case "Activity":
				element = new Activity(name, EAID);
				break;
			case "Property":
				element = new PartProperty(name, EAID);
				break;
//			case "Attribute":
//				element = new Attribute(name, EAID);
//				break;
			case "InterfaceBlock":
				element = new InterfaceBlock(name, EAID);
				break;
			case "ValueType":
				element = new ValueType(name, EAID);
				break;
			case "Stereotype":
				element = new sysmlStereotype(name, EAID);
				break;
			case "Class":
				element = new Class(name, EAID);
				break;
			case "PartProperty":
				element = new PartProperty(name, EAID);
				break;
			case "Model":
				element = new Model(name, EAID);
				break;
			case "Note":
				element = new Note(name, EAID);
				break;
			case "Signal":
				element = new Signal(name, EAID);
				break;
			case "BlockDefinitionDiagram":
				element = new BlockDefinitionDiagram(name, EAID);
				break;
			case "ActivityDiagram":
				element = new ActivityDiagram(name, EAID);
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
