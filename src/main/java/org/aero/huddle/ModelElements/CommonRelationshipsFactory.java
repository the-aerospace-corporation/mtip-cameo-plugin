package org.aero.huddle.ModelElements;

import org.aero.huddle.ModelElements.Activity.ControlFlow;
import org.aero.huddle.ModelElements.Activity.ObjectFlow;
import org.aero.huddle.ModelElements.InternalBlock.BindingConnector;
import org.aero.huddle.ModelElements.InternalBlock.Connector;
import org.aero.huddle.ModelElements.InternalBlock.InformationFlow;
import org.aero.huddle.ModelElements.InternalBlock.ItemFlow;
import org.aero.huddle.ModelElements.Profile.Extension;
import org.aero.huddle.ModelElements.Requirements.Copy;
import org.aero.huddle.ModelElements.Requirements.DeriveRequirement;
import org.aero.huddle.ModelElements.Requirements.Refine;
import org.aero.huddle.ModelElements.Requirements.Satisfy;
import org.aero.huddle.ModelElements.Requirements.Trace;
import org.aero.huddle.ModelElements.Requirements.Verify;
import org.aero.huddle.ModelElements.StateMachine.Transition;
import org.aero.huddle.ModelElements.UseCase.Extend;
import org.aero.huddle.ModelElements.UseCase.Include;
import org.aero.huddle.util.SysmlConstants;

public class CommonRelationshipsFactory {
	public CommonRelationship createElement(String type, String name, String EAID) {
		CommonRelationship relationship = null;
		switch(type) {
			case SysmlConstants.ABSTRACTION:
				relationship = new Abstraction(name, EAID);
				break;
			case SysmlConstants.AGGREGATION:
				relationship = new Aggregation(name, EAID);
				break;
			case SysmlConstants.ASSOCIATION:
				relationship = new Association(name, EAID);
				break;
			case SysmlConstants.BINDINGCONNECTOR:
				relationship = new BindingConnector(name, EAID);
				break;
			case SysmlConstants.COMPOSITION:
				relationship = new Composition(name, EAID);
				break;
			case SysmlConstants.COPY:
				relationship = new Copy(name, EAID);
				break;
			case SysmlConstants.CONNECTOR:
				relationship = new Connector(name, EAID);
				break;
			case SysmlConstants.CONTROLFLOW:
				relationship = new ControlFlow(name, EAID);
				break;
			case SysmlConstants.DERIVEREQUIREMENT:
				relationship = new DeriveRequirement(name, EAID);
				break;
			case SysmlConstants.DEPENDENCY:
				relationship = new Dependency(name, EAID);
				break;
			case SysmlConstants.EXTEND:
				relationship = new Extend(name, EAID);
				break;
			case SysmlConstants.EXTENSION:
				relationship = new Extension(name, EAID);
				break;
			case "Generalization":
				relationship = new Generalization(name, EAID);
				break;
			case SysmlConstants.INCLUDE:
				relationship = new Include(name, EAID);
				break;
			case SysmlConstants.INFORMATIONFLOW:
				relationship = new InformationFlow(name, EAID);
				break;
			case SysmlConstants.ITEMFLOW:
				relationship = new ItemFlow(name, EAID);
				break;
			case "ObjectFlow":
				relationship = new ObjectFlow(name, EAID);
				break;
			case "Refine":
				relationship = new Refine(name, EAID);
				break;
			case "Satisfy":
				relationship = new Satisfy(name, EAID);
				break;
			case "Trace":
				relationship = new Trace(name, EAID);
				break;
			case "Transition":
				relationship = new Transition(name, EAID);
				break;
			case SysmlConstants.USAGE:
				relationship = new Usage(name, EAID);
				break;
			case "Verify":
				relationship = new Verify(name, EAID);
				break;
			default:
				break;
		}
		return relationship;
	}
}
