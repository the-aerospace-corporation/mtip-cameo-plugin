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
import org.aero.mtip.ModelElements.StateMachine.Transition;
import org.aero.mtip.ModelElements.UseCase.Extend;
import org.aero.mtip.ModelElements.UseCase.Include;
import org.aero.mtip.util.SysmlConstants;

import uaf.UAFConstants;
import uaf.Dictionary.SameAs;
import uaf.Operational.ArbitraryConnector;
import uaf.Operational.OperationalAssociation;
import uaf.Operational.OperationalConnector;
import uaf.Operational.OperationalControlFlow;
import uaf.Operational.OperationalExchange;
import uaf.Operational.OperationalObjectFlow;
import uaf.Projects.MilestoneDependency;
import uaf.Projects.ProjectSequence;
import uaf.Resources.Forecast;
import uaf.Resources.FunctionControlFlow;
import uaf.Resources.FunctionObjectFlow;
import uaf.Resources.ResourceConnector;
import uaf.Resources.ResourceExchange;
import uaf.Resources.VersionSuccession;
import uaf.Strategic.AchievedEffect;
import uaf.Strategic.CapabilityForTask;
import uaf.Strategic.DesiredEffect;
import uaf.Strategic.Exhibits;
import uaf.Strategic.MapsToCapability;
import uaf.Strategic.OrganizationInEnterprise;

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
				
				
			//UAF
			case UAFConstants.ACHIEVED_EFFECT:
				relationship = new AchievedEffect(name, EAID);
				break;
			case UAFConstants.CAPABILITY_FOR_TASK:
				relationship = new CapabilityForTask(name, EAID);
				break;
			case UAFConstants.DESIRED_EFFECT:
				relationship = new DesiredEffect(name, EAID);
				break;

			case UAFConstants.EXHIBITS:
				relationship = new Exhibits(name, EAID);
				break;
			case UAFConstants.MAPS_TO_CAPABILITY:
				relationship = new MapsToCapability(name, EAID);
				break;
			case UAFConstants.ORGANIZATION_IN_ENTERPRISE:
				relationship = new OrganizationInEnterprise(name, EAID);
				break;
			//Operational
			/*
			case UAFConstants.OPERATIONAL_CONNECTOR:
				relationship = new OperationalConnector(name, EAID);
				break;*/
		    /*case UAFConstants.INFORMATION_FLOW:
				relationship = new uaf.Operational.InformationFlow(name, EAID);
				break;
			case UAFConstants.OBJECT_FLOW:
				relationship = new uaf.Operational.ObjectFlow(name, EAID);
				break;*/
			/*case UAFConstants.OPERATIONAL_MESSAGE:
				relationship = new OperationalMessage(name, EAID);
				break;*/
			case UAFConstants.OPERATIONAL_EXCHANGE:
				relationship = new OperationalExchange(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_CONNECTOR:
				relationship = new OperationalConnector(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_OBJECT_FLOW:
				relationship = new OperationalObjectFlow(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_CONTROL_FLOW:
				relationship = new OperationalControlFlow(name, EAID);
				break;
			case UAFConstants.OPERATIONAL_ASSOCIATION:
				relationship = new OperationalAssociation(name, EAID);
				break;
			case UAFConstants.ARBITRARY_CONNECTOR:
				relationship = new ArbitraryConnector(name, EAID);
				break;
			case UAFConstants.RESOURCE_CONNECTOR:
				relationship = new ResourceConnector(name, EAID);
				break;
			case UAFConstants.RESOURCE_EXCHANGE:
				relationship = new ResourceExchange(name, EAID);
				break;
			case UAFConstants.FUNCTION_CONTROL_FLOW:
				relationship = new FunctionControlFlow(name, EAID);
				break;
			case UAFConstants.FUNCTION_OBJECT_FLOW:
				relationship = new FunctionObjectFlow(name, EAID);
				break;
			/*case UAFConstants.RESOURCE_MESSAGE:
				relationship = new ResourceMessage(name, EAID);
				break;*/
			case UAFConstants.FORECAST:
				relationship = new Forecast(name, EAID);
				break;
			case UAFConstants.VERSION_SUCCESSION:
				relationship = new VersionSuccession(name, EAID);
				break;
			case UAFConstants.MILESTONE_DEPENDENCY:
				relationship = new MilestoneDependency(name, EAID);
				break;
			case UAFConstants.PROJECT_SEQUENCE:
				relationship = new ProjectSequence(name, EAID);
				break;
			case UAFConstants.SAME_AS:
				relationship = new SameAs(name, EAID);
				break;
				
			default:
				break;
		}
		return relationship;
	}
}
