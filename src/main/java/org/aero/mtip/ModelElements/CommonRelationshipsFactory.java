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
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.uaf.Dictionary.SameAs;
import org.aero.mtip.uaf.Operational.ArbitraryConnector;
import org.aero.mtip.uaf.Operational.OperationalAssociation;
import org.aero.mtip.uaf.Operational.OperationalConnector;
import org.aero.mtip.uaf.Operational.OperationalControlFlow;
import org.aero.mtip.uaf.Operational.OperationalExchange;
import org.aero.mtip.uaf.Operational.OperationalObjectFlow;
import org.aero.mtip.uaf.Projects.MilestoneDependency;
import org.aero.mtip.uaf.Projects.ProjectSequence;
import org.aero.mtip.uaf.Resources.Forecast;
import org.aero.mtip.uaf.Resources.FunctionControlFlow;
import org.aero.mtip.uaf.Resources.FunctionObjectFlow;
import org.aero.mtip.uaf.Resources.ResourceConnector;
import org.aero.mtip.uaf.Resources.ResourceExchange;
import org.aero.mtip.uaf.Resources.VersionSuccession;
import org.aero.mtip.uaf.Strategic.AchievedEffect;
import org.aero.mtip.uaf.Strategic.CapabilityForTask;
import org.aero.mtip.uaf.Strategic.DesiredEffect;
import org.aero.mtip.uaf.Strategic.Exhibits;
import org.aero.mtip.uaf.Strategic.MapsToCapability;
import org.aero.mtip.uaf.Strategic.OrganizationInEnterprise;
import org.aero.mtip.uaf.actualresources.ActualResourceRelationship;
import org.aero.mtip.uaf.actualresources.FillsPost;
import org.aero.mtip.uaf.actualresources.OwnsProcess;
import org.aero.mtip.uaf.actualresources.ProvidesCompetence;
import org.aero.mtip.uaf.personnel.Command;
import org.aero.mtip.uaf.personnel.CompetenceToConduct;
import org.aero.mtip.uaf.personnel.Control;
import org.aero.mtip.uaf.personnel.RequiresCompetence;
import org.aero.mtip.uaf.security.Affects;
import org.aero.mtip.uaf.security.AffectsInContext;
import org.aero.mtip.uaf.security.Enhances;
import org.aero.mtip.uaf.security.Mitigates;
import org.aero.mtip.uaf.security.OwnsRisk;
import org.aero.mtip.uaf.security.OwnsRiskInContext;
import org.aero.mtip.uaf.security.Protects;
import org.aero.mtip.uaf.security.ProtectsInContext;

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
				
			// Actual Resources
			case UAFConstants.ACTUAL_RESOURCE_RELATIONSHIP:
				relationship = new ActualResourceRelationship(name, EAID);
				break;
			case UAFConstants.FILLS_POST:
				relationship = new FillsPost(name, EAID);
				break;
			case UAFConstants.OWNS_PROCESS:
				relationship = new OwnsProcess(name, EAID);
				break;
			case UAFConstants.PROVIDES_COMPETENCE:
				relationship = new ProvidesCompetence(name, EAID);
				break;
			
			// Personnel
			case UAFConstants.COMMAND:
				relationship = new Command(name, EAID);
				break;
			case UAFConstants.COMPETENCE_TO_CONDUCT:
				relationship = new CompetenceToConduct(name, EAID);
				break;
			case UAFConstants.CONTROL:
				relationship = new Control(name, EAID);
				break;
			case UAFConstants.REQUIRES_COMPETENCE:
				relationship = new RequiresCompetence(name, EAID);
				break;
				
			// Security
			case UAFConstants.AFFECTS:
				relationship = new Affects(name, EAID);
				break;
			case UAFConstants.AFFECTS_IN_CONTEXT:
				relationship = new AffectsInContext(name, EAID);
				break;
			case UAFConstants.ENHANCES:
				relationship = new Enhances(name, EAID);
				break;
			case UAFConstants.MITIGATES:
				relationship = new Mitigates(name, EAID);
				break;
			case UAFConstants.OWNS_RISK:
				relationship = new OwnsRisk(name, EAID);
				break;
			case UAFConstants.OWNS_RISK_IN_CONTEXT:
				relationship = new OwnsRiskInContext(name, EAID);
				break;
			case UAFConstants.PROTECTS:
				relationship = new Protects(name, EAID);
				break;
			case UAFConstants.PROTECTS_IN_CONTEXT:
				relationship = new ProtectsInContext(name, EAID);
				break;
				
			default:
				break;
		}
		return relationship;
	}
}
