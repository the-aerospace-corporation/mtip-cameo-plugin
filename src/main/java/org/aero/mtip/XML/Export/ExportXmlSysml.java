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
import org.aero.mtip.profiles.MDForSysMLExtensions;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.MTIPUtils;
import org.aero.mtip.util.SysmlConstants;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
<<<<<<< HEAD
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
=======
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
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
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DataType;
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
	
<<<<<<< HEAD
	public static void exportEntity(Element element) {
		if(exportedElements.contains(element.getID())) {
			ExportLog.log(String.format("Element already exported with name %s and id %s.", element.getHumanName(), element.getID()));
=======
	public static void exportElement(Element element, Project project, Document xmlDoc) {
		String commonElementType = null;
		String commonRelationshipType = null;		
		
		//Use SysMLProfile.is<SysmlElementName> to check types for sysml elements 
		if(element instanceof AcceptEventAction) {
			commonElementType = SysmlConstants.ACCEPTEVENTACTION;
			CameoUtils.logGUI("Exporting Accept Event Action");
		} else if(element instanceof Activity) {
			commonElementType = SysmlConstants.ACTIVITY;
			CameoUtils.logGUI("Exporting Activity");
		} else if(element instanceof ActivityFinalNode) {
			commonElementType = SysmlConstants.ACTIVITYFINALNODE;
			CameoUtils.logGUI("Exporting Activity Final Node");
		} else if(element instanceof ActivityParameterNode) {
			commonElementType = SysmlConstants.ACTIVITYPARAMETERNODE;
			CameoUtils.logGUI("Exporting Activity Parameter Node");
		} else if(element instanceof ActivityPartition) {
			commonElementType = SysmlConstants.ACTIVITYPARTITION;
			CameoUtils.logGUI("Exporting Activity Partition");
		} else if(element instanceof Actor) {
			commonElementType = SysmlConstants.ACTOR;
			CameoUtils.logGUI("Exporting Actor");
//		} else if(element instanceof Aggregation) {
//			commonRelationshipType = SysmlConstants.AGGREGATION;
//			CameoUtils.logGUI("Exporting Aggregation");
		} else if(CameoUtils.isAssociationBlock(element, project)) {
			commonElementType = SysmlConstants.ASSOCIATIONBLOCK;
			CameoUtils.logGUI("Exporting Association Block");
		} else if (SysMLProfile.isBindingConnector(element)) {
			commonRelationshipType = SysmlConstants.BINDINGCONNECTOR;
			CameoUtils.logGUI("Exporting Binding Connector");
		} else if (SysMLProfile.isBoundReference(element)) {
			commonElementType = SysmlConstants.BOUNDREFERENCE;
			CameoUtils.logGUI("Exporting Bound Reference");
		} else if (SysMLProfile.isClassifierBehaviorProperty(element)) {
			commonElementType = SysmlConstants.CLASSIFIERBEHAVIORPROPERTY;
			CameoUtils.logGUI("Exporting Classifier Behavior Property");
		} else if (element instanceof CallBehaviorAction) {
			commonElementType = SysmlConstants.CALLBEHAVIORACTION;
			CameoUtils.logGUI("Exporting Call Behavior Action");
		} else if (element instanceof CallOperationAction) {
			commonElementType = SysmlConstants.CALLOPERATIONACTION;
			CameoUtils.logGUI("Exporting Call Operation Action");
		} else if (element instanceof ChangeEvent) {
			commonElementType = SysmlConstants.CHANGEEVENT;
			CameoUtils.logGUI("Exporting Change Event");
		} else if(CameoUtils.isCopy(element, project)) {
			commonRelationshipType = SysmlConstants.COPY;
			CameoUtils.logGUI("Exporting Copy Relation");
		} else if(element instanceof Collaboration) {
			commonElementType = SysmlConstants.COLLABORATION;
			CameoUtils.logGUI("Exporting Collaboration");
		} else if(element instanceof CombinedFragment) {
			commonElementType = SysmlConstants.COMBINEDFRAGMENT;
			CameoUtils.logGUI("Exporting Combined Fragment");
//		} else if(element instanceof Comment) {
//			commonElementType = SysmlConstants.COMMENT;
//			CameoUtils.logGUI("Exporting Comment");
		} else if(element instanceof ConditionalNode) { 
			commonElementType = SysmlConstants.CONDITIONALNODE;
			CameoUtils.logGUI("Exporting Conditional Node");
		} else if (element instanceof ConnectionPointReference) {
			commonElementType = SysmlConstants.CONNECTIONPOINTREFERENCE;
			CameoUtils.logGUI("Exporting Connection Point Reference");
		} else if (element instanceof Connector) {
			commonRelationshipType = SysmlConstants.CONNECTOR;
			CameoUtils.logGUI("Exporting Connector Relationship");
		}  else if(SysMLProfile.isConstraintBlock(element)) {
			commonElementType = SysmlConstants.CONSTRAINTBLOCK;
			CameoUtils.logGUI("Exporting Constraint Block");
		} else if(CameoUtils.isConstraintParameter(element, project)) {
			commonElementType = SysmlConstants.CONSTRAINTPARAMETER;
			CameoUtils.logGUI("Exporting Constraint Parameter");
		} else if (CameoUtils.isConstraintProperty(element, project)) {
			commonElementType = SysmlConstants.CONSTRAINTPROPERTY;
			CameoUtils.logGUI("Exporting Constraint Property");
		} else if(element instanceof ControlFlow) {
			commonRelationshipType = SysmlConstants.CONTROLFLOW;
			CameoUtils.logGUI("Exporting Control Flow");
		} else if(element instanceof CreateObjectAction) {
			commonElementType = SysmlConstants.CREATEOBJECTACTION;
			CameoUtils.logGUI("Exporting Create Object Action");
		} else if(CameoUtils.isCustomization(project, element)) {
			commonElementType = SysmlConstants.CUSTOMIZATION;
			CameoUtils.logGUI("Exporting Customization");
		} else if(element instanceof DataStoreNode) {
			commonElementType = SysmlConstants.DATASTORENODE;
			CameoUtils.logGUI("Exporting Data Store Node");
		} else if(element instanceof DataType) {
			commonElementType = SysmlConstants.DATA_TYPE;
			CameoUtils.logGUI("Exporting Data Type");
		} else if(element instanceof DecisionNode) {
			commonElementType = SysmlConstants.DECISIONNODE;
			CameoUtils.logGUI("Exporting Decision Node");
		} else if(CameoUtils.isDeriveRequirement(element, project)) {
			commonRelationshipType = SysmlConstants.DERIVEREQUIREMENT;
			CameoUtils.logGUI("Exporting Derive Requirement Relation");
		} else if(CameoUtils.isDesignConstraint(element, project)) {
			commonElementType = SysmlConstants.DESIGNCONSTRAINT;
			CameoUtils.logGUI("Exporting Design Constraint");
		} else if(element instanceof DestroyObjectAction) {
			commonElementType = SysmlConstants.DESTROYOBJECTACTION;
			CameoUtils.logGUI("Exporting Destroy Object Action");
		} else if(element instanceof DestructionOccurrenceSpecification) {
			commonElementType = SysmlConstants.DESTRUCTIONOCCURRENCESPECIFICATION;
			CameoUtils.logGUI("Exporting Destruction Occurrence Specification");
		} else if(SysMLProfile.isDomain(element)) {
			commonElementType = SysmlConstants.DOMAIN;
			CameoUtils.logGUI("Exporting Domain as block with domain stereotype");
		} else if(element instanceof DurationConstraint) {
			commonElementType = SysmlConstants.DURATIONCONSTRAINT;
			CameoUtils.logGUI("Exporting Duration Constraint");
		} else if(element instanceof DurationObservation) {
			commonElementType = SysmlConstants.DURATIONOBSERVATION;
			CameoUtils.logGUI("Exporting Duration Observation");
		} else if(element instanceof Enumeration) {
			commonElementType = SysmlConstants.ENUMERATION;
			CameoUtils.logGUI("Exporting Enumeration");
		} else if(element instanceof EnumerationLiteral) {
			commonElementType = SysmlConstants.ENUMERATIONLITERAL;
			CameoUtils.logGUI("Exporting Enumeration Literal");
		} else if(element instanceof Extend) {
			commonRelationshipType = SysmlConstants.EXTEND;
			CameoUtils.logGUI("Exporting Extend");
		} else if(CameoUtils.isExtendedRequirement(element, project)) {
			commonElementType = SysmlConstants.EXTENDEDREQUIREMENT;
			CameoUtils.logGUI("Exporting Extended Requirement");
		} else if(element instanceof Extension) {
			commonRelationshipType = SysmlConstants.EXTENSION;
			CameoUtils.logGUI("Exporting Extension Relationship");
		} else if(element instanceof ExtensionPoint) {
			commonElementType = SysmlConstants.EXTENSIONPOINT;
			CameoUtils.logGUI("Exporting Extension Point");
		} else if (SysMLProfile.isExternal(element)) {
			commonElementType = SysmlConstants.EXTERNAL;
			CameoUtils.logGUI("Exporting External as block with external stereotype");
		} else if(element instanceof FinalState) {
			commonElementType = SysmlConstants.FINALSTATE;
			CameoUtils.logGUI("Exporting Final State");
		} else if(element instanceof FlowFinalNode) {
			commonElementType = SysmlConstants.FLOWFINALNODE;
			CameoUtils.logGUI("Exporting Flow Final Node");
		} else if(SysMLProfile.isFlowPort(element)) {
			commonElementType = SysmlConstants.FLOWPORT;
			CameoUtils.logGUI("Exporting Flow Port");
		} else if(SysMLProfile.isFlowProperty(element)) {
			commonElementType = SysmlConstants.FLOWPROPERTY;
			CameoUtils.logGUI("Exporting Flow Property");
		} else if(SysMLProfile.isFlowSpecification(element)) {
			commonElementType = SysmlConstants.FLOWSPECIFICATION;
			CameoUtils.logGUI("Exporting Flow Specification");
		} else if(element instanceof ForkNode) {
			commonElementType = SysmlConstants.FORKNODE;
			CameoUtils.logGUI("Exporting Fork Node");
		} else if(SysMLProfile.isFullPort(element)) {
			commonElementType = SysmlConstants.FULLPORT;
			CameoUtils.logGUI("Exporting Full Port");
		} else if(CameoUtils.isFunctionalRequirement(element, project)) {
			commonElementType = SysmlConstants.FUNCTIONALREQUIREMENT;
			CameoUtils.logGUI("Exporting Functional Requirement");
		} else if(element instanceof FunctionBehavior) {
			commonElementType = SysmlConstants.FUNCTIONBEHAVIOR;
			CameoUtils.logGUI("Exporting Function Behavior");
		} else if(element instanceof Generalization) {
			commonRelationshipType = SysmlConstants.GENERALIZATION;
			CameoUtils.logGUI("Exporting Generalization");
		} else if(element instanceof InformationItem) {
			commonElementType = SysmlConstants.INFORMATIONITEM;
			CameoUtils.logGUI("Exporting Information Item");
		} else if(element instanceof Include) {
			commonRelationshipType = SysmlConstants.INCLUDE;
			CameoUtils.logGUI("Exporting Include");
		} else if(element instanceof Interaction) {
			commonElementType = SysmlConstants.INTERACTION;
			CameoUtils.logGUI("Exporting Interaction");
		} else if(element instanceof InteractionOperand) {
			commonElementType = SysmlConstants.INTERACTIONOPERAND;
			CameoUtils.logGUI("Exporting Interaction Operand");
		} else if(element instanceof InteractionUse) {
			commonElementType = SysmlConstants.INTERACTIONUSE;
			CameoUtils.logGUI("Exporting Interaction Use");
		} else if(element instanceof InitialNode) {
			commonElementType = SysmlConstants.INITIALNODE;
			CameoUtils.logGUI("Exporting Initial Node");
		} else if(element instanceof InterruptibleActivityRegion) {
			commonElementType = SysmlConstants.INTERRUPTIBLEACTIVITYREGION;
		} else if(element instanceof Pseudostate) {
			commonElementType = CameoUtils.getPseudoState(element);
			CameoUtils.logGUI("Exporting Pseudo State of type " + commonElementType);			
		} else if(element instanceof InputPin) {
			commonElementType = SysmlConstants.INPUTPIN;
			CameoUtils.logGUI("Exporting Input Pin");
		} else if(element instanceof Interface) {
			commonElementType = SysmlConstants.INTERFACE;
			CameoUtils.logGUI("Exporting Interface");
		} else if(CameoUtils.isInterfaceBlock(element, project)) {
			commonElementType = SysmlConstants.INTERFACEBLOCK;
			CameoUtils.logGUI("Exporting Interface Block");
		} else if(element instanceof InterfaceRealization) {
			commonElementType = SysmlConstants.INTERFACEREALIZATION;
			CameoUtils.logGUI("Exporting Interface Realization");
		} else if(CameoUtils.isInterfaceRequirement(element, project)) {
			commonElementType = SysmlConstants.INTERFACEREQUIREMENT;
			CameoUtils.logGUI("Exporting Interface Requirement");
		} else if (SysMLProfile.isItemFlow(element)) {
			commonRelationshipType = SysmlConstants.ITEMFLOW;
			CameoUtils.logGUI("Exporting Item Flow");
		} else if(element instanceof JoinNode) {
			commonElementType = SysmlConstants.JOINNODE;
			CameoUtils.logGUI("Exporting Join Node");
		} else if(element instanceof Lifeline) {
			commonElementType = SysmlConstants.LIFELINE;
			CameoUtils.logGUI("Exporting Lifeline");
		} else if(element instanceof LoopNode) {
			commonElementType = SysmlConstants.LOOPNODE;
			CameoUtils.logGUI("Exporting Loop Node");
		} else if(element instanceof MergeNode) {
			commonElementType = SysmlConstants.MERGENODE;
			CameoUtils.logGUI("Exporting Merge Node");
		} else if(element instanceof Message) {
			commonElementType = SysmlConstants.MESSAGE;
			CameoUtils.logGUI("Exporting Message");
		} else if(element instanceof MessageOccurrenceSpecification) {
			commonElementType = SysmlConstants.MESSAGEOCCURRENCESPECIFICATION;
			CameoUtils.logGUI("Exporint Message Occurrence Specification");
		} else if(CameoUtils.isModel(element, project)) {
			commonElementType = SysmlConstants.MODEL;
			CameoUtils.logGUI("Exporting Model");
		} else if(element instanceof ObjectFlow) {
			commonRelationshipType = SysmlConstants.OBJECTFLOW;
			CameoUtils.logGUI("Exporting Object Flow");
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
			commonElementType = SysmlConstants.OPAQUEEXPRESSION;
			CameoUtils.logGUI("Exporting Opaque Expression");
		} else if(element instanceof OpaqueAction) {
			commonElementType = SysmlConstants.OPAQUEACTION;
			CameoUtils.logGUI("Exporting Opaque Action");
		} else if(element instanceof OpaqueBehavior) {
			commonElementType = SysmlConstants.OPAQUEBEHAVIOR;
			CameoUtils.logGUI("Exporting Opaque Behavior");
		} else if(element instanceof Operation) {
			commonElementType = SysmlConstants.OPERATION;
			CameoUtils.logGUI("Exporting Operation");
		} else if(element instanceof PackageImport) {
			commonRelationshipType = SysmlConstants.PACKAGEIMPORT;
			CameoUtils.logGUI("Exporting Package Import");
		} else if(SysMLProfile.isParticipantProperty(element)) {
			commonElementType = SysmlConstants.PARTICIPANTPROPERTY;
			CameoUtils.logGUI("Exporting Participant Property");
		} else if(CameoUtils.isPartProperty(element, project)) {
			commonElementType = SysmlConstants.PARTPROPERTY;
			CameoUtils.logGUI("Exporting Part Property Requirement");
		} else if(element instanceof Profile) {
			commonElementType = SysmlConstants.PROFILE;
			CameoUtils.logGUI("Exporting Profile");
		} else if(element instanceof OutputPin) {
			commonElementType = SysmlConstants.OUTPUTPIN;
			CameoUtils.logGUI("Exporting Output Pin");
		} else if(element instanceof Package) {
			CameoUtils.logGUI("PACKAGE SHOULD NOT BE EXPORTING HERE");
		} else if(element instanceof Parameter) {
			commonElementType = SysmlConstants.PARAMETER;
		} else if(CameoUtils.isPerformanceRequirement(element, project)) {
			commonElementType = SysmlConstants.PERFORMANCEREQUIREMENT;
			CameoUtils.logGUI("Exporting Performance Requirement");
		} else if(CameoUtils.isPhysicalRequirement(element, project)) {
			commonElementType = SysmlConstants.PHYSICALREQUIREMENT;
			CameoUtils.logGUI("Exporting Physical Requirement");
		//Proxy Port must come before Port as all specialized ports are instanceof Port
		} else if(SysMLProfile.isProxyPort(element)) {
			commonElementType = SysmlConstants.PROXYPORT;
			CameoUtils.logGUI("Exporting Proxy Port");
		} else if(element instanceof Port) {
			commonElementType = SysmlConstants.PORT;
			CameoUtils.logGUI("Exporting Port");
		} else if(CameoUtils.isQuantityKind(element, project)) {
			commonElementType = SysmlConstants.QUANTITYKIND;
			CameoUtils.logGUI("Exporting Quantity Kind");
		} else if(element instanceof Region) {
			commonElementType = SysmlConstants.REGION;
			CameoUtils.logGUI("Exporting Region");
		} else if(CameoUtils.isRefine(element, project)) {
			commonRelationshipType = SysmlConstants.REFINE;
			CameoUtils.logGUI("Exporting Refine Relation");
		} else if(CameoUtils.isRequirement(element, project)) {
			commonElementType = SysmlConstants.REQUIREMENT;
			CameoUtils.logGUI("Exporting Requirement");
		} else if(CameoUtils.isSatisfy(element, project)) {
			commonRelationshipType = SysmlConstants.SATISFY;
			CameoUtils.logGUI("Exporting Satisfy Relation");
		} else if(element instanceof SendSignalAction) {
			commonElementType = SysmlConstants.SENDSIGNALACTION;
			CameoUtils.logGUI("Exporting Send Signal Action");
		} else if(element instanceof Signal) {
			commonElementType = SysmlConstants.SIGNAL;
			CameoUtils.logGUI("Exporting Signal");
		} else if(element instanceof SignalEvent) {
			commonElementType = SysmlConstants.SIGNALEVENT;
		} else if(element instanceof Slot) {
			Element parent = element.getOwner();
			if(parent instanceof InstanceSpecification) {
				InstanceSpecification is = (InstanceSpecification)parent;
				List<Classifier> classifiers = is.getClassifier();
				boolean hasStereotypeClassifier = false;
				for(Classifier classifier : classifiers) {
					if(classifier instanceof Stereotype) {
						hasStereotypeClassifier = true;
					}
				}
				// Slots of stereotypes are captured as tagged values in the attributes block.
				if(!hasStereotypeClassifier) {
					commonElementType = SysmlConstants.SLOT;
					CameoUtils.logGUI("Exporting Slot");
				}
			}
		} else if(SysMLProfile.isStakeholder(element)) {
			commonElementType = SysmlConstants.STAKEHOLDER;
			CameoUtils.logGUI("Exporting Stakeholder");
		} else if(element instanceof State) {
			commonElementType = SysmlConstants.STATE;
			CameoUtils.logGUI("Exporting State");
		} else if(element instanceof StateInvariant) {
			commonElementType = SysmlConstants.STATEINVARIANT;
			CameoUtils.logGUI("Exporting State Invariant");
		} else if(element instanceof StateMachine) {
			commonElementType = SysmlConstants.STATEMACHINE;
			CameoUtils.logGUI("Exporting State Machine");
		} else if(element instanceof Stereotype) {
			commonElementType = SysmlConstants.STEREOTYPE;
			CameoUtils.logGUI("Exporting Stereotype");
		} else if(CameoUtils.isTrace(element, project)) {
			commonRelationshipType = SysmlConstants.TRACE;
			CameoUtils.logGUI("Exporting Trace Relation");
		} else if(SysMLProfile.isSubsystem(element)) {
			commonElementType = SysmlConstants.SUBSYSTEM;
			CameoUtils.logGUI("Exporting Subystem as block with subsystem stereotype");
		} else if(SysMLProfile.isSystemcontext(element)) {
			commonElementType = SysmlConstants.SYSTEMCONTEXT;
			CameoUtils.logGUI("Exporting system context as a block with system context stereotype");
		} else if(SysMLProfile.isSystem(element)) {
			commonElementType = SysmlConstants.SYSTEM;
			CameoUtils.logGUI("Exporting system as a block with system stereotype");
		} else if (element instanceof TimeEvent) {
			commonElementType = SysmlConstants.TIMEEVENT;
			CameoUtils.logGUI("Exporting Time Event");
		} else if (element instanceof TimeObservation) {
			commonElementType = SysmlConstants.TIMEOBSERVATION;
		} else if (element instanceof Transition) {
			commonRelationshipType = SysmlConstants.TRANSITION;
			CameoUtils.logGUI("Exporting Transition");
		} else if (element instanceof Trigger) {
			commonElementType = SysmlConstants.TRIGGER;
			CameoUtils.logGUI("Exporting Trigger");
		} else if(CameoUtils.isUnit(element, project)) {
			commonElementType = SysmlConstants.UNIT;
			CameoUtils.logGUI("Exporting Unit");
		} else if(element instanceof Usage) {
			commonRelationshipType = SysmlConstants.USAGE;
			CameoUtils.logGUI("Exporting Usage");
		} else if(element instanceof UseCase) {
			commonElementType = SysmlConstants.USECASE;
			CameoUtils.logGUI("Exporting Use Case");
		} else if(CameoUtils.isValueProperty(element, project)) {
			commonElementType = SysmlConstants.VALUEPROPERTY;
			CameoUtils.logGUI("Exporting Value Property");
		} else if(CameoUtils.isValueType(element, project)) {
			commonElementType = SysmlConstants.VALUETYPE;
			CameoUtils.logGUI("Exporting Value Type");
		} else if(CameoUtils.isVerify(element, project)) {
			commonRelationshipType = SysmlConstants.VERIFY;
			CameoUtils.logGUI("Exporting Verify");
		} else if(SysMLProfile.isView(element)) {
			commonElementType = SysmlConstants.VIEW;
			CameoUtils.logGUI("Exporting View");
		} else if(SysMLProfile.isViewpoint(element)) {
			commonElementType = SysmlConstants.VIEWPOINT;
			CameoUtils.logGUI("Exporitng Viewpoint");		
			
			
		//Super classes listed below as to not to override their children
		} else if(element instanceof Constraint) {
			Constraint constraint = (Constraint)element;
			ValueSpecification vs = constraint.getSpecification();
			if(vs instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
				OpaqueExpression oe = (OpaqueExpression)vs;
				exportElement(oe, project, xmlDoc);
			}
			// Add support for other types of Value Specifications here
			commonElementType = SysmlConstants.CONSTRAINT;
			CameoUtils.logGUI("Exporting Constraint");
		} else if(CameoUtils.isProperty(element, project)) {
				commonElementType = SysmlConstants.PROPERTY;
				CameoUtils.logGUI("Exporting Property");	
		} else if (element instanceof InstanceSpecification) {
			NamedElement namedElement = (NamedElement)element;
			if (!namedElement.getName().equals("") && !namedElement.getName().equals(null) && !Arrays.asList(SysmlConstants.RESERVEINSTANCESPECIFICATION).contains(namedElement.getName())) {
				commonElementType = SysmlConstants.INSTANCESPECIFICATION;
				CameoUtils.logGUI("Exporting Instance Specification");
			}
		// Check ActionClass last as any child action class will be an instance of ActionClass
		} else if(element instanceof ActionClass) {
			commonElementType = SysmlConstants.ACTION;
			CameoUtils.logGUI("Exporting Action");
		} else if(element instanceof CentralBufferNode) {
			commonElementType = SysmlConstants.CENTRALBUFFERNODE;
			CameoUtils.logGUI("Exporting Central Buffer Node");
			
		//	Class is a super class of some elements (any requirement stereotype elements) - must come after
		} else if(element instanceof Association) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd((Association)element);
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd((Association)element);
			if(firstMemberEnd.getAggregation() == AggregationKindEnum.SHARED || secondMemberEnd.getAggregation() == AggregationKindEnum.SHARED) {
				commonRelationshipType = SysmlConstants.AGGREGATION;
				CameoUtils.logGUI("Exporting Aggregation");
			} else if(firstMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE || secondMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE) {
				commonRelationshipType = SysmlConstants.COMPOSITION;
				CameoUtils.logGUI("Exporting Composition");
			} else {
				commonRelationshipType = SysmlConstants.ASSOCIATION;
				CameoUtils.logGUI("Exporting Association");
			}
		// Block with overwrite Constraint Block since ConstraintBlock is a subclass of Block
		} else if(SysMLProfile.isBlock(element)) {
			commonElementType = SysmlConstants.BLOCK;
			CameoUtils.logGUI("Exporting Block");	
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class) {
			// Check if Metaclass
			if(CameoUtils.isMetaclass(element)) {
				commonElementType = SysmlConstants.METACLASS;
				CameoUtils.logGUI("Exporting Metaclass");
			} else {
				commonElementType = SysmlConstants.CLASS;
				CameoUtils.logGUI("Exporting Class");
			}
		} else if(element instanceof Dependency) {
			commonRelationshipType = SysmlConstants.DEPENDENCY;
			CameoUtils.logGUI("Exporting Dependency");
		} else if(element instanceof Diagram) {
			Diagram diag = (Diagram) element;
			DiagramPresentationElement presentationDiagram = project.getDiagram(diag);
			DiagramType diagType = presentationDiagram.getDiagramType();
			String diagTypeStr = presentationDiagram.getRealType();
			
			CameoUtils.logGUI("ACTUAL diagram type: " + diagType.getType());
			CameoUtils.logGUI("MAPPED diagram type: " + AbstractDiagram.diagramToType.get( diagType.getType()));
			String diagramType = AbstractDiagram.diagramToType.get(diagType.getType());
			
			if(diagramType == null) {
				commonElementType = SysmlConstants.CUSTOM_DIAGRAM;
			} else {
				commonElementType = AbstractDiagram.diagramToType.get( diagType.getType());
			}
			
//			List<String> it = DiagramType.getAllDiagramTypes();
//			for (String str: it) {
//				CameoUtils.logGUI("DiagramList type: " + str);
//			}
			
		} else {
			if(!CameoUtils.isNotExplicitlySupported(element)) {
				String message = "Element with type: " + element.getHumanType() + " is not supported yet!!!";
				ExportLog.log(message);
				CameoUtils.logGUI(message);
			}
		}
		if(!exportedElements.containsKey(element.getID())) {
			if(commonElementType != null) {
				CommonElementsFactory cef = new CommonElementsFactory();
				CommonElement commonElement = null;
				
				if(element instanceof NamedElement) {
					//Check if ID already exists from previous import
					CameoUtils.logGUI("\tElement named: " +  ((NamedElement)element).getName() + " with id: " + element.getID());
					commonElement = cef.createElement(commonElementType,  ((NamedElement)element).getName(), element.getID());
				} else {
					CameoUtils.logGUI("\tElement named: " +  element.getHumanName() + " with id: " + element.getID());
					commonElement = cef.createElement(commonElementType, "", element.getID());
				}
				commonElement.writeToXML(element, project, xmlDoc);
				exportedElements.put(element.getID(), "");
			}
			
			if(commonRelationshipType != null) {
				CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
				String name = "";
				
				CommonRelationship commonRelationship = null;
				if(element instanceof NamedElement) {
					name = ((NamedElement)element).getName();
					CameoUtils.logGUI("\tRelationship named: " +  name + " with id: " + element.getID());
					commonRelationship = crf.createElement(commonRelationshipType, name, element.getID());
				} else {
					name = element.getHumanName();
					CameoUtils.logGUI("\tRelationship named: " +  name + " with id: " + element.getID());
					commonRelationship = crf.createElement(commonRelationshipType, "", element.getID());
				}
				commonRelationship.writeToXML(element, project, xmlDoc);
				exportedElements.put(element.getID(), "");
				// Check if supplier and client are created - important for UML Metaclasses and SysML Profile objects referenced in extension and generalization relationships
				if(!exportedElements.containsKey(commonRelationship.getSupplier().getID())) {
					exportElement(commonRelationship.getSupplier(), project, xmlDoc);
				}
				if(!exportedElements.containsKey(commonRelationship.getClient().getID())) {
					exportElement(commonRelationship.getClient(), project, xmlDoc);
				}
			}
		} else {
			CameoUtils.logGUI("Duplicate element with id " + element.getID() + " not exported.");
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
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
<<<<<<< HEAD
			return SysmlConstants.CONNECTION_POINT_REFERENCE;
		} else if(SysML.isConstraintBlock(element)) {
			return SysmlConstants.CONSTRAINT_BLOCK;
		} else if(MDCustomizationForSysMLProfile.isConstraintParameter(element)) {
			return SysmlConstants.CONSTRAINT_PARAMETER;
		} else if (MDCustomizationForSysMLProfile.isConstraintProperty(element)) {
			return SysmlConstants.CONSTRAINT_PROPERTY;
=======
			commonElementType = SysmlConstants.CONNECTIONPOINTREFERENCE;
		} else if (element instanceof Connector) {
			commonRelationshipType = SysmlConstants.CONNECTOR;
		} else if(element instanceof Constraint) {
			Constraint constraint = (Constraint)element;
			commonElementType = SysmlConstants.CONSTRAINT;
		} else if(SysMLProfile.isConstraintBlock(element)) {
			commonElementType = SysmlConstants.CONSTRAINTBLOCK;
		} else if(CameoUtils.isConstraintParameter(element, project)) {
			commonElementType = SysmlConstants.CONSTRAINTPARAMETER;
		} else if (CameoUtils.isConstraintProperty(element, project)) {
			commonElementType = SysmlConstants.CONSTRAINTPROPERTY;
		} else if(element instanceof ControlFlow) {
			commonRelationshipType = SysmlConstants.CONTROLFLOW;
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
		} else if(element instanceof CreateObjectAction) {
			return SysmlConstants.CREATE_OBJECT_ACTION;
		} else if(CameoUtils.isCustomization(project, element)) {
			return SysmlConstants.CUSTOMIZATION;
		} else if(element instanceof DataStoreNode) {
<<<<<<< HEAD
			return SysmlConstants.DATA_STORE_NODE;
=======
			commonElementType = SysmlConstants.DATASTORENODE;
		} else if(element instanceof DataType) {
			commonElementType = SysmlConstants.DATA_TYPE;
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
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
<<<<<<< HEAD
			return SysmlConstants.OPERATION;
		} else if(SysML.isParticipantProperty(element)) {
			return SysmlConstants.PARTICIPANT_PROPERTY;
		} else if(MDCustomizationForSysMLProfile.isPartProperty(element)) {
			return SysmlConstants.PART_PROPERTY;
=======
			commonElementType = SysmlConstants.OPERATION;
		} else if(element instanceof PackageImport) {
			commonRelationshipType = SysmlConstants.PACKAGEIMPORT;
		} else if(SysMLProfile.isParticipantProperty(element)) {
			commonElementType = SysmlConstants.PARTICIPANTPROPERTY;
		} else if(CameoUtils.isPartProperty(element, project)) {
			commonElementType = SysmlConstants.PARTPROPERTY;
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
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
<<<<<<< HEAD
			return SysmlConstants.PORT;
		} else if(MDCustomizationForSysMLProfile.isQuantityKind(element)) {
			return SysmlConstants.QUANTITY_KIND;
=======
			commonElementType = SysmlConstants.PORT;
		} else if(CameoUtils.isQuantityKind(element, project)) {
			commonElementType = SysmlConstants.QUANTITYKIND;
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
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
<<<<<<< HEAD
			return SysmlConstants.TRIGGER;
		} else if(MDCustomizationForSysMLProfile.isUnit(element)) {
			return SysmlConstants.UNIT;
		} else if(MDCustomizationForSysMLProfile.isValueProperty(element)) {
			return SysmlConstants.VALUE_PROPERTY;
		} else if(SysML.isValueType(element)) {
			return SysmlConstants.VALUE_TYPE;
		
=======
			commonElementType = SysmlConstants.TRIGGER;
		} else if(CameoUtils.isUnit(element, project)) {
			commonElementType = SysmlConstants.UNIT;
		} else if(element instanceof Usage) {
			commonRelationshipType = SysmlConstants.USAGE;
		} else if(element instanceof UseCase) {
			commonElementType = SysmlConstants.USECASE;
		} else if(CameoUtils.isValueProperty(element, project)) {
			commonElementType = SysmlConstants.VALUEPROPERTY;
		} else if(CameoUtils.isValueType(element, project)) {
			commonElementType = SysmlConstants.VALUETYPE;
		} else if(CameoUtils.isVerify(element, project)) {
			commonRelationshipType = SysmlConstants.VERIFY;		
			
>>>>>>> 9766897 (Rebase with latest updates from MTIP v1.0.7 working version.)
		//Super classes listed below as to not to override their children	
		} else if(element instanceof Constraint) {
			return SysmlConstants.CONSTRAINT;
		} else if(MDForSysMLExtensions.isProperty(element)) {
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
