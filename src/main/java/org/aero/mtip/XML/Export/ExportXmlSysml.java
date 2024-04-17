/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Export;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.annotation.CheckForNull;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.ModelElements.CommonRelationshipsFactory;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.MTIPUtils;
import org.aero.mtip.util.SysmlConstants;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.uml.DiagramType;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.ActionClass;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallBehaviorAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.CallOperationAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.InputPin;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OpaqueAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.OutputPin;
import com.nomagic.uml2.ext.magicdraw.actions.mdbasicactions.SendSignalAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdintermediateactions.CreateObjectAction;
import com.nomagic.uml2.ext.magicdraw.actions.mdintermediateactions.DestroyObjectAction;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityFinalNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityParameterNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.InitialNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow;
import com.nomagic.uml2.ext.magicdraw.activities.mdcompleteactivities.DataStoreNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdcompleteactivities.InterruptibleActivityRegion;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.CentralBufferNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.FlowFinalNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ForkNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.JoinNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.MergeNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdstructuredactivities.ConditionalNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdstructuredactivities.LoopNode;
import com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdinformationflows.InformationItem;
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency;
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Usage;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.Interface;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.InterfaceRealization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.PackageImport;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.ChangeEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationInterval;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationObservation;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeConstraint;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeObservation;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdcollaborations.Collaboration;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.DestructionOccurrenceSpecification;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Interaction;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageOccurrenceSpecification;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.StateInvariant;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionUse;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Extension;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Actor;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Extend;
import com.nomagic.uml2.ext.magicdraw.mdusecases.ExtensionPoint;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Include;
import com.nomagic.uml2.ext.magicdraw.mdusecases.UseCase;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.ConnectionPointReference;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.FinalState;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition;

public class ExportXmlSysml {
	private static HashSet<String> exportedElements = new HashSet<String>();
	private static HashSet<String> implicitElements = new HashSet<String>();
	public static Project project;
	
	public static void buildXML(File file, Package packageElement) {
		project = Application.getInstance().getProject();
		exportedElements = new HashSet<String>();
		implicitElements = new HashSet<String>();
		
		if (packageElement == null) {
			packageElement = project.getPrimaryModel();
		}		
		
		exportPackageRecursive((Package)packageElement);
		
		ExportLog.logSummary(exportedElements);
		ExportLog.save();
		ExportLog.reset();
	}
	
	public static void buildXMLFromDiagram(File file, DiagramPresentationElement diagramPresentationElement) {
		project = Application.getInstance().getProject();
		exportedElements = new HashSet<String>();
		implicitElements = new HashSet<String>();
		
		Element diagramElement = diagramPresentationElement.getElement();
		exportElementRecursiveUp(diagramElement);
		
		//Add hook to get nested presentation elements due to encapsulation of diagrams
		List<PresentationElement> presentationElements = diagramPresentationElement.getPresentationElements();
		for(int i = 0; i < presentationElements.size(); i++) {
			Element element = presentationElements.get(i).getElement();
			
			if(element == null) {
				continue;
			} 

			exportElementRecursiveUp(element);			
		}
		
		ExportLog.logSummary(exportedElements);
		ExportLog.save();
		ExportLog.reset();		
	}
	
	/**
	 * 
	 * @param element Element to be exported. This begins at an arbitrary level of nested within the model.
	 * @param project Current project being exported
	 * @param xmlDoc XML Document representing the model being exported
	 */
	public static void exportElementRecursiveUp(Element element) {
		Element parent = element.getOwner();
		
		if(parent != null) {
			exportElementRecursiveUp(parent);
		}
		
		if(element instanceof TypedElement) {
			TypedElement typedElement = (TypedElement)element;
			Type type = typedElement.getType();
			
			if(type != null) {
				exportElementRecursiveUp(type);
			}
		}
		
		if(element instanceof Package) {
			exportPackage(element);
			return;
		}
			
		exportEntity(element);
	}
	
	
	public static void exportPackageRecursive(Package pkg) {
		if (pkg == null) {
			return;
		}
		
		exportPackage(pkg);
		
		Collection<Element> elementsInPackage = pkg.getOwnedElement();
		Collection<Package> packagesInPackage = pkg.getNestedPackage();

		for(Package nextPackage : packagesInPackage) {
			if (isExternalPackage(nextPackage)) {
				continue;
			}
			
			exportPackageRecursive(nextPackage);
		}
		
		for(Element element : elementsInPackage) {
			if (isPackage(element)) {
				continue;
			}
					
			exportElementRecursive(element);
		}
	}
	
	public static void exportElementRecursive(Element element) {
		//Find any child elements and recursively export		
		boolean noElements = false;
		Collection<Element> ownedElements = new ArrayList<Element> ();
		try {
			ownedElements = element.getOwnedElement();
		} catch (NullPointerException npe) {
			noElements = true;
		}

		exportEntity(element);

		// If value property allow only comments or attached files 
		// Let owned elements that are relationships call exportElementRecursive but children of relationships cannot be exported
		if(!noElements ) {
			for(Element ownedElement : ownedElements) {
				// Check that ownedElement is not a package, otherwise will cause repeats -- element also must be a NamedElement to ignore unecessary instance specifications, opaque expressions, etc.
				if (!(ownedElement instanceof Package) && !(element instanceof Relationship)) {
					exportElementRecursive(ownedElement);
				}
			}
		}
	}
	
	public static void exportPackage(Element pkg) {
		if (exportedElements.contains(pkg.getID())) {
			ExportLog.log(String.format("%s package, profile, or model with id %s already exported.", pkg.getHumanName(), pkg.getID()));
			return;
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		String packageType = getPackageType(pkg);
		
		CommonElement commonElement = cef.createElement(packageType, ((NamedElement)pkg).getName(), pkg.getID());
		commonElement.writeToXML(pkg);
		
		exportedElements.add(pkg.getID());
	}
	
	public static void exportEntity(Element element) {
		if(exportedElements.contains(element.getID())) {
			ExportLog.log(String.format("Element already exported with name %s and id %s.", element.getHumanName(), element.getID()));
		}
		
		String commonElementType = getEntityType(element);
		
		if (commonElementType == null) {
			if (!isImplicitlySupported(element)) {
				ExportLog.log(String.format("%s type could not be identified. Not currently supported.", element.getHumanType()));
			}
			
			return;
		}
		
		if (MTIPUtils.isSupportedElement(commonElementType)) {
			exportElement(element, commonElementType);
			return;
		}
		
		if (MTIPUtils.isSupportedRelationship(commonElementType)) {
			exportRelationship(element, commonElementType);
			return;
		}
		
		if (MTIPUtils.isSupportedDiagram(commonElementType)) {
			exportElement(element, commonElementType);
			return;
		}		
		
		ExportLog.log(String.format("%s is not categorized as an element, relationship, or diagram.", commonElementType));
	}
	
	public static void exportElement(Element element, String elementType) {
		if (elementType == null) {
			ExportLog.log(String.format("Element type not found for %s with id %s", element.getHumanName(), element.getID()));
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonElement commonElement =  cef.createElement(elementType, ((NamedElement)element).getName(), element.getID());
		
		if (commonElement == null) {
			return;
		}
		
//		ExportLog.log(String.format("Exporting element %s of type %s with id %s.", element.getHumanName(), element.getHumanType(), element.getID()));
		commonElement.writeToXML(element);
		exportedElements.add(element.getID());
	}
	
	public static void exportRelationship(Element element, String relationshipType) {
		CommonRelationshipsFactory crf = new CommonRelationshipsFactory();		
		CommonRelationship commonRelationship = crf.createElement(relationshipType, CommonRelationship.getName(element), element.getID());
		
		commonRelationship.writeToXML(element);
		exportedElements.add(element.getID());
		
		// Check if supplier and client are created - important for UML Metaclasses and SysML Profile objects referenced in extension and generalization relationships
		if(commonRelationship.getSupplier() != null && !exportedElements.contains(commonRelationship.getSupplier().getID())) {
			exportEntity(commonRelationship.getSupplier());
		}
		
		if(commonRelationship.getClient() != null && !exportedElements.contains(commonRelationship.getClient().getID())) {
			exportEntity(commonRelationship.getClient());
		}		
	}
	
	public static String getElementType(Element element) {
		if(element instanceof AcceptEventAction) {
			return SysmlConstants.ACCEPT_EVENT_ACTION;
		} else if(element instanceof Activity) {
			return SysmlConstants.ACTIVITY;
		} else if(element instanceof ActivityFinalNode) {
			return SysmlConstants.ACTIVITY_FINAL_NODE;
		} else if(element instanceof ActivityParameterNode) {
			return SysmlConstants.ACTIVITY_PARAMETER_NODE;
		} else if(element instanceof ActivityPartition) {
			return SysmlConstants.ACTIVITY_PARTITION;
		} else if(element instanceof Actor) {
			return SysmlConstants.ACTOR;
		} else if (SysML.isBoundReference(element)) {
			return SysmlConstants.BOUND_REFERENCE;
		} else if (SysML.isClassifierBehavior(element)) {
			return SysmlConstants.CLASSIFIER_BEHAVIOR_PROPERTY;
		} else if (element instanceof CallBehaviorAction) {
			return SysmlConstants.CALL_BEHAVIOR_ACTION;
		} else if (element instanceof CallOperationAction) {
			return SysmlConstants.CALL_OPERATION_ACTION;
		} else if (element instanceof ChangeEvent) {
			return SysmlConstants.CHANGE_EVENT;
		} else if(element instanceof Collaboration) {
			return SysmlConstants.COLLABORATION;
		} else if(element instanceof CombinedFragment) {
			return SysmlConstants.COMBINED_FRAGMENT;
	//	} else if(element instanceof Comment) {
	//		commonElementType = SysmlConstants.COMMENT;
		} else if(element instanceof ConditionalNode) { 
			return SysmlConstants.CONDITIONAL_NODE;
		} else if (element instanceof ConnectionPointReference) {
			return SysmlConstants.CONNECTION_POINT_REFERENCE;
		} else if(SysML.isConstraintBlock(element)) {
			return SysmlConstants.CONSTRAINT_BLOCK;
		} else if(MDCustomizationForSysMLProfile.isConstraintParameter(element)) {
			return SysmlConstants.CONSTRAINT_PARAMETER;
		} else if (MDCustomizationForSysMLProfile.isConstraintProperty(element)) {
			return SysmlConstants.CONSTRAINT_PROPERTY;
		} else if(element instanceof CreateObjectAction) {
			return SysmlConstants.CREATE_OBJECT_ACTION;
		} else if(CameoUtils.isCustomization(project, element)) {
			return SysmlConstants.CUSTOMIZATION;
		} else if(element instanceof DataStoreNode) {
			return SysmlConstants.DATA_STORE_NODE;
		} else if(element instanceof DecisionNode) {
			return SysmlConstants.DECISION_NODE;
		} else if(SysML.isDeriveRequirement(element)) {
			return SysmlConstants.DERIVE_REQUIREMENT;		
		} else if(SysML.isDesignConstraint(element)) {
			return SysmlConstants.DESIGN_CONSTRAINT;
		} else if(element instanceof DestroyObjectAction) {
			return SysmlConstants.DESTROY_OBJECT_ACTION;
		} else if(element instanceof DestructionOccurrenceSpecification) {
			return SysmlConstants.DESTRUCTION_OCCURRENCE_SPECIFICATION;
		} else if(SysML.isDomain(element)) {
			return SysmlConstants.DOMAIN;
		} else if(element instanceof DurationConstraint) {
			return SysmlConstants.DURATION_CONSTRAINT;
		} else if(element instanceof DurationInterval) {
			return SysmlConstants.DURATION_INTERVAL;
		} else if(element instanceof DurationObservation) {
			return SysmlConstants.DURATION_OBSERVATION;
		} else if(element instanceof Enumeration) {
			return SysmlConstants.ENUMERATION;
		} else if(element instanceof EnumerationLiteral) {
			return SysmlConstants.ENUMERATION_LITERAL;
		} else if(SysML.isExtendedRequirement(element)) {
			return SysmlConstants.EXTENDED_REQUIREMENT;
		} else if(element instanceof ExtensionPoint) {
			return SysmlConstants.EXTENSION_POINT;
		} else if (SysML.isExternal(element)) {
			return SysmlConstants.EXTERNAL;
		} else if(element instanceof FinalState) {
			return SysmlConstants.FINAL_STATE;
		} else if(element instanceof FlowFinalNode) {
			return SysmlConstants.FLOW_FINAL_NODE;
		} else if(SysML.isFlowPort(element)) {
			return SysmlConstants.FLOW_PORT;
		} else if(SysML.isFlowProperty(element)) {
			return SysmlConstants.FLOW_PROPERTY;
		} else if(SysML.isFlowSpecification(element)) {
			return SysmlConstants.FLOW_SPECFICATION;
		} else if(element instanceof ForkNode) {
			return SysmlConstants.FORK_NODE;
		} else if(SysML.isFullPort(element)) {
			return SysmlConstants.FULL_PORT;
		} else if(SysML.isFunctionalRequirement(element)) {
			return SysmlConstants.FUNCTIONAL_REQUIREMENT;
		} else if(element instanceof FunctionBehavior) {
			return SysmlConstants.FUNCTION_BEHAVIOR;
		} else if(element instanceof InformationItem) {
			return SysmlConstants.INFORMATION_ITEM;
		} else if(element instanceof Interaction) {
			return SysmlConstants.INTERACTION;
		} else if(element instanceof InteractionOperand) {
			return SysmlConstants.INTERACTION_OPERAND;
		} else if(element instanceof InteractionUse) {
			return SysmlConstants.INTERACTION_USE;
		} else if(element instanceof InitialNode) {
			return SysmlConstants.INITIAL_NODE;
		} else if(element instanceof Pseudostate) {
			return CameoUtils.getPseudoState(element);		
		} else if(element instanceof InputPin) {
			return SysmlConstants.INPUT_PIN;
		} else if(element instanceof Interface) {
			return SysmlConstants.INTERFACE;
		} else if(SysML.isInterfaceBlock(element)) {
			return SysmlConstants.INTERFACE_BLOCK;
		} else if(SysML.isInterfaceRequirement(element)) {
			return SysmlConstants.INTERFACE_REQUIREMENT;
		} else if(element instanceof InterruptibleActivityRegion) {
			return SysmlConstants.INTERRUPTIBLE_ACTIVITY_REGION;
		} else if(element instanceof JoinNode) {
			return SysmlConstants.JOIN_NODE;
		} else if(element instanceof Lifeline) {
			return SysmlConstants.LIFELINE;
		} else if(element instanceof LoopNode) {
			return SysmlConstants.LOOP_NODE;
		} else if(element instanceof MergeNode) {
			return SysmlConstants.MERGE_NODE;
		} else if(element instanceof MessageOccurrenceSpecification) {
			return SysmlConstants.MESSAGE_OCCURRENCE_SPECIFICATION;
		} else if(CameoUtils.isModel(element, project)) {
			return SysmlConstants.MODEL;
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
			return SysmlConstants.OPAQUE_EXPRESSION;
		} else if(element instanceof OpaqueAction) {
			return SysmlConstants.OPAQUE_ACTION;
		} else if(element instanceof OpaqueBehavior) {
			return SysmlConstants.OPAQUE_BEHAVIOR;
		} else if(element instanceof Operation) {
			return SysmlConstants.OPERATION;
		} else if(SysML.isParticipantProperty(element)) {
			return SysmlConstants.PARTICIPANT_PROPERTY;
		} else if(MDCustomizationForSysMLProfile.isPartProperty(element)) {
			return SysmlConstants.PART_PROPERTY;
		} else if(element instanceof Profile) {
			return SysmlConstants.PROFILE;
		} else if(element instanceof OutputPin) {
			return SysmlConstants.OUTPUT_PIN;
		} else if(element instanceof Package) {
			return SysmlConstants.PACKAGE;
		} else if(SysML.isPerformanceRequirement(element)) {
			return SysmlConstants.PERFORMANCE_REQUIREMENT;
		} else if(SysML.isPhysicalRequirement(element)) {
			return SysmlConstants.PHYSICAL_REQUIREMENT;
		//Proxy Port must come before Port as all specialized ports are instanceof Port
		} else if(SysML.isProxyPort(element)) {
			return SysmlConstants.PROXY_PORT;
		} else if(element instanceof Port) {
			return SysmlConstants.PORT;
		} else if(MDCustomizationForSysMLProfile.isQuantityKind(element)) {
			return SysmlConstants.QUANTITY_KIND;
		} else if(element instanceof Region) {
			return SysmlConstants.REGION;
		} else if(SysML.isRefine(element)) {
			return SysmlConstants.REFINE;
		} else if(SysML.isRequirement(element)) {
			return SysmlConstants.REQUIREMENT;
		} else if(element instanceof SendSignalAction) {
			return SysmlConstants.SEND_SIGNAL_ACTION;
		} else if(element instanceof Signal) {
			return SysmlConstants.SIGNAL;
		} else if(element instanceof SignalEvent) {
			return SysmlConstants.SIGNAL_EVENT;
		} else if(element instanceof State) {
			return SysmlConstants.STATE;
		} else if(element instanceof StateInvariant) {
			return SysmlConstants.STATE_INVARIANT;
		} else if(element instanceof StateMachine) {
			return SysmlConstants.STATE_MACHINE;
		} else if(element instanceof Stereotype) {
			return SysmlConstants.STEREOTYPE;
		} else if(SysML.isSubsystem(element)) {
			return SysmlConstants.SUBSYSTEM;
		} else if(SysML.isSystemContext(element)) {
			return SysmlConstants.SYSTEM_CONTEXT;
		} else if(SysML.isSystem(element)) {
			return SysmlConstants.SYSTEM;
		} else if (element instanceof TimeConstraint) {
			return SysmlConstants.TIME_CONSTRAINT;
		} else if (element instanceof TimeEvent) {
			return SysmlConstants.TIME_EVENT;
		} else if (element instanceof TimeExpression) {
			return SysmlConstants.TIME_EXPRESSION;
		} else if (element instanceof TimeObservation) {
			return SysmlConstants.TIME_OBSERVATION;
		} else if (element instanceof Trigger) {
			return SysmlConstants.TRIGGER;
		} else if(MDCustomizationForSysMLProfile.isUnit(element)) {
			return SysmlConstants.UNIT;
		} else if(MDCustomizationForSysMLProfile.isValueProperty(element)) {
			return SysmlConstants.VALUE_PROPERTY;
		} else if(SysML.isValueType(element)) {
			return SysmlConstants.VALUE_TYPE;
		
		//Super classes listed below as to not to override their children	
		} else if(element instanceof Constraint) {
			return SysmlConstants.CONSTRAINT;
		} else if(MDCustomizationForSysML.isProperty(element)) {
			return SysmlConstants.PROPERTY;	
		} else if (element instanceof InstanceSpecification && CameoUtils.isSupportedInstanceSpecification(element)) {
			return SysmlConstants.INSTANCE_SPECIFICATION;
		// Check ActionClass last as any child action class will be an instance of ActionClass
		} else if(element instanceof ActionClass) {
			return SysmlConstants.ACTION;
		} else if(element instanceof CentralBufferNode) {
			return SysmlConstants.CENTRAL_BUFFER_NODE;
		} else if(SysML.isBlock(element)) {
			return SysmlConstants.BLOCK;
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class) {
			// Check if Metaclass
			if(CameoUtils.isMetaclass(element)) {
				return SysmlConstants.METACLASS;
			} else {
				return SysmlConstants.CLASS;
			}
		}
		
		return null;
	}
		
	@CheckForNull
	public static String getRelationshipType(Element element) {
		if(SysML.isAssociationBlock(element)) {
			return SysmlConstants.ASSOCIATION_BLOCK;
		} else if (SysML.isBindingConnector(element)) {
			return SysmlConstants.BINDING_CONNECTOR;
		} else if(SysML.isCopy(element)) {
			return SysmlConstants.COPY;
		} else if (element instanceof Connector) {
			return SysmlConstants.CONNECTOR;
		} else if(element instanceof ControlFlow) {
			return SysmlConstants.CONTROL_FLOW;
		} else if(element instanceof Extend) {
			return SysmlConstants.EXTEND;
		} else if(element instanceof Extension) {
			return SysmlConstants.EXTENSION;
		} else if(element instanceof Generalization) {
			return SysmlConstants.GENERALIZATION;
		} else if(element instanceof Include) {
			return SysmlConstants.INCLUDE;
		} else if(element instanceof InterfaceRealization) {
			return SysmlConstants.INTERFACE_REALIZATION;
		} else if (SysML.isItemFlow(element)) {
			return SysmlConstants.ITEM_FLOW;
		} else if(element instanceof Message) {
			return SysmlConstants.MESSAGE;
		} else if(element instanceof ObjectFlow) {
			return SysmlConstants.OBJECT_FLOW;
		} else if(element instanceof PackageImport) {
			return SysmlConstants.PACKAGE_IMPORT;
		} else if(SysML.isSatisfy(element)) {
			return SysmlConstants.SATISFY;
		} else if(SysML.isTrace(element)) {
			return SysmlConstants.TRACE;
		} else if (element instanceof Transition) {
			return SysmlConstants.TRANSITION;
		} else if(element instanceof Usage) {
			return SysmlConstants.USAGE;
		} else if(element instanceof UseCase) {
			return SysmlConstants.USE_CASE;
		} else if(SysML.isVerify(element)) {
			return SysmlConstants.VERIFY;
			
		// Elements that are supertype must be checked after their subtypes
		} else if(element instanceof Association) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd((Association)element);
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd((Association)element);
			if(firstMemberEnd.getAggregation() == AggregationKindEnum.SHARED || secondMemberEnd.getAggregation() == AggregationKindEnum.SHARED) {
				return SysmlConstants.AGGREGATION;
			} else if(firstMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE || secondMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE) {
				return SysmlConstants.COMPOSITION;
			} else {
				return SysmlConstants.ASSOCIATION;
			}
		} else if(element instanceof Dependency) {
			return SysmlConstants.DEPENDENCY;
		}
		
		return null;
	}
		
	@CheckForNull
	public static String getDiagramType(Element element) {
		if (!(element instanceof Diagram)) {
			return null;
		}

		DiagramPresentationElement presentationDiagram = project.getDiagram((Diagram)element);
		
		if (presentationDiagram == null) {
			ExportLog.log(String.format("Diagram type not found. Could not find DiagramPresentationElement for diagram %s.", element.getID()));
			return null;
		}
		
		DiagramType diagramType = presentationDiagram.getDiagramType();
		
		if(diagramType == null) {
			return SysmlConstants.CUSTOM_DIAGRAM;
		} 
		
		return AbstractDiagram.diagramToType.get(diagramType.getType());
	}

	@CheckForNull
	public static String getEntityType(Element element) {
		if (element instanceof Diagram) {
			return getDiagramType(element);
		}
		
		if (element instanceof Relationship || element instanceof Message) {
			return getRelationshipType(element);
		}
		
		return getElementType(element);
	}
	
	public static String getPackageType(Element pkg) {
		if(CameoUtils.isModel(pkg, project)) {
			return SysmlConstants.MODEL;
		} 
		
		if(CameoUtils.isProfile(pkg, project)) {
			return SysmlConstants.PROFILE;
		} 
		
		return SysmlConstants.PACKAGE;
	}
	
	public static boolean isImplicitlySupported(Element element) {
		if (element instanceof ElementValue 
				|| element instanceof LiteralReal
			    || element instanceof LiteralBoolean
			    || element instanceof LiteralInteger 
			    || element instanceof LiteralString
			    || element instanceof LiteralUnlimitedNatural 
			    || element instanceof InstanceValue 
			    || element instanceof ConnectorEnd
			    || element instanceof Slot
			    || element instanceof Comment
			    || !CameoUtils.isSupportedInstanceSpecification(element)
			    || MDCustomizationForSysMLProfile.isReferenceProperty(element)) {
			
			addImplicitElement(element);
			return true;
		}
		return false;
	}
	
	public static boolean isPackage(Element element) {
		if(element instanceof Package
				|| element.getHumanName().equals("Profile Application")
				|| element instanceof PackageImport 
				|| element instanceof Profile) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isExternalPackage(Package pkg) {
		if (StereotypesHelper.hasStereotype(pkg, MagicDraw.getAuxiliaryResourceStereotype())
				|| pkg.getHumanName().equals("Package Unit Imports")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Tracks elements not explicitly exported (with their own data tag) to check sum with explicit elements
	 * and total exported elements where: Total = implicit + explicit
	 */
	public static void addImplicitElement(Element element) {
		implicitElements.add(element.getID());
	}
}
