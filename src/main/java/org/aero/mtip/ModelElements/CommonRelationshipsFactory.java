/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import org.aero.mtip.ModelElements.Activity.ControlFlow;
import org.aero.mtip.ModelElements.Activity.ObjectFlow;
import org.aero.mtip.ModelElements.Block.PackageImport;
import org.aero.mtip.ModelElements.InternalBlock.BindingConnector;
import org.aero.mtip.ModelElements.InternalBlock.Connector;
import org.aero.mtip.ModelElements.InternalBlock.InformationFlow;
import org.aero.mtip.ModelElements.InternalBlock.ItemFlow;
import org.aero.mtip.ModelElements.Profile.Extension;
import org.aero.mtip.ModelElements.Requirements.Copy;
import org.aero.mtip.ModelElements.Requirements.DeriveRequirement;
import org.aero.mtip.ModelElements.Requirements.Refine;
import org.aero.mtip.ModelElements.Requirements.Satisfy;
import org.aero.mtip.ModelElements.Requirements.Trace;
import org.aero.mtip.ModelElements.Requirements.Verify;
import org.aero.mtip.ModelElements.Sequence.Message;
import org.aero.mtip.ModelElements.StateMachine.Transition;
import org.aero.mtip.ModelElements.UseCase.Extend;
import org.aero.mtip.ModelElements.UseCase.Include;
import org.aero.mtip.util.SysmlConstants;

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
			case SysmlConstants.GENERALIZATION:
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
			case SysmlConstants.MESSAGE:
				relationship = new Message(name, EAID);
				break;
			case SysmlConstants.OBJECTFLOW:
				relationship = new ObjectFlow(name, EAID);
				break;
			case SysmlConstants.PACKAGEIMPORT:
				relationship = new PackageImport(name, EAID);
				break;
			case SysmlConstants.REFINE:
				relationship = new Refine(name, EAID);
				break;
			case SysmlConstants.SATISFY:
				relationship = new Satisfy(name, EAID);
				break;
			case SysmlConstants.TRACE:
				relationship = new Trace(name, EAID);
				break;
			case SysmlConstants.TRANSITION:
				relationship = new Transition(name, EAID);
				break;
			case SysmlConstants.USAGE:
				relationship = new Usage(name, EAID);
				break;
			case SysmlConstants.VERIFY:
				relationship = new Verify(name, EAID);
				break;
			default:
				break;
		}
		return relationship;
	}
}
