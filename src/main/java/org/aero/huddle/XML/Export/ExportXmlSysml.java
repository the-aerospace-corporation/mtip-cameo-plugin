package org.aero.huddle.XML.Export;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.ModelElements.CommonElementsFactory;
import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.ModelElements.CommonRelationshipsFactory;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ExportLog;
import org.aero.huddle.util.SysmlConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.dependencymatrix.DependencyMatrix;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramType;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
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
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency;
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Usage;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.Interface;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.InterfaceRealization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.ChangeEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationObservation;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeObservation;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdcollaborations.Collaboration;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector;
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
	private static HashMap<String, String> exportedElements = new HashMap<String, String>();
	public static Project project;
	
	public static void buildXML(Document xmlDoc, File file, Package packageElement) {
		project = Application.getInstance().getProject();
		exportedElements = new HashMap<String, String>();
		org.w3c.dom.Element root = xmlDoc.createElement("packet");
		xmlDoc.appendChild(root);
		
		Project project = Application.getInstance().getProject();
		Package primary = project.getPrimaryModel();
		if(packageElement != null) {
			exportPackageRecursive((Package)packageElement, project, xmlDoc);
		} else {
			exportPackageRecursive(primary, project, xmlDoc);
		}
		ExportLog.save();
		ExportLog.reset();
	}
	
	public static void exportPackageRecursive(Package pack, Project project, Document xmlDoc) {
		//Write Package to xml here so parent is written before child
		
		//Look for child packages and child elements to recursively export
		Collection<Element> elementsInPackage = new ArrayList<Element> ();
		Collection<Package> packagesInPackage = new ArrayList<Package> ();
		
		boolean noPackages = false;
		boolean noElements = false;
		
		exportPackage(pack, project, xmlDoc);
		
		try {
			elementsInPackage = pack.getOwnedElement();
		} catch(NullPointerException e) {
			noElements = true;
		}
		
		try {
			packagesInPackage = pack.getNestedPackage();
		} catch(NullPointerException e) {
			noPackages = true;
		}
						
		if(!noPackages) {
			for(Package nextPackage : packagesInPackage) {
				//Description: Get stereotypes of package. Packages with stereotypes (Ex. auxiliaryResource, modellibrary) should not be exported
				Profile magicdrawProfile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
				Stereotype auxiliaryStereotype = StereotypesHelper.getStereotype(project,  "auxiliaryResource", magicdrawProfile);
				List<Stereotype> packageStereotypes = StereotypesHelper.getStereotypes(nextPackage);
				
				// Check if package is editable -- external dependencies and imported projects will be read-only (not editable) 
				if(!packageStereotypes.contains(auxiliaryStereotype) && !nextPackage.getHumanName().equals("Package Unit Imports")) {
//					CameoUtils.logGUI("Package with name " + nextPackage.getHumanName() + "\twith type: " + nextPackage.getHumanType());
//					JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Exporting Package " + nextPackage.getHumanName());
					exportPackageRecursive(nextPackage, project, xmlDoc);					
				}
			}
		}
		
		if(!noElements) {
			for(Element element : elementsInPackage) {
				//export elements that are not packages
				String elementName = element.getHumanName();
				if(!(element instanceof Package) && !elementName.equals("Package Import") && !elementName.equals("Profile Application")) {
					CameoUtils.logGUI("Exporting " + element.getHumanType() + element.getHumanName());
					exportElementRecursive(element, project, xmlDoc);
				}
			}
		}
	}
	
	public static void exportElementRecursive(Element element, Project project, Document xmlDoc) {
		//Write to XML here so parents appear before children
		
		//Find any child elements and recursively export		
		boolean noElements = false;
		Collection<Element> ownedElements = new ArrayList<Element> ();
		try {
			ownedElements = element.getOwnedElement();
		} catch (NullPointerException npe) {
			noElements = true;
		}

		exportElement(element, project, xmlDoc);

		// If value property allow only comments or attached files 
		// Let owned elements that are relationships call exportElementRecursive but children of relationships cannot be exported
		if(!noElements ) {
			for(Element ownedElement : ownedElements) {
				// Check that ownedElement is not a package, otherwise will cause repeats -- element also must be a NamedElement to ignore unecessary instance specifications, opaque expressions, etc.
				if (!(ownedElement instanceof Package) && !(element instanceof Relationship)) {
					exportElementRecursive(ownedElement, project, xmlDoc);
				}
			}
		}
	}
	
	public static void exportPackage(Element pkg, Project project, Document xmlDoc) {
		CameoUtils.logGUI("Exporting package " + pkg.getHumanName());
		CommonElement commonElement = null;
		CommonElementsFactory cef = new CommonElementsFactory();
		NamedElement namedElement = (NamedElement)pkg;
		//Check if ID already exists from previous import
		
		//Check if package is a model and export as model if it is
		if(CameoUtils.isModel(pkg, project)) {
			commonElement = cef.createElement(SysmlConstants.MODEL,  namedElement.getName(), pkg.getLocalID());
		} else if(CameoUtils.isProfile(pkg, project)) {
			commonElement = cef.createElement(SysmlConstants.PROFILE, namedElement.getName(), pkg.getLocalID());
		} else{		
			commonElement = cef.createElement(SysmlConstants.PACKAGE,  namedElement.getName(), pkg.getLocalID());
		}
		commonElement.writeToXML(pkg, project, xmlDoc);
	}
	
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
		} else if(SysMLProfile.isConstraintBlock(element)) {
			commonElementType = SysmlConstants.CONSTRAINTBLOCK;
			CameoUtils.logGUI("Exporting Constraint Block");
		} else if(MDCustomizationForSysMLProfile.isConstraintParameter(element)) {
			commonElementType = SysmlConstants.CONSTRAINTPARAMETER;
			CameoUtils.logGUI("Exporting Constraint Parameter");
		} else if (MDCustomizationForSysMLProfile.isConstraintProperty(element)) {
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
		} else if(SysMLProfile.isParticipantProperty(element)) {
			commonElementType = SysmlConstants.PARTICIPANTPROPERTY;
			CameoUtils.logGUI("Exporting Participant Property");
		} else if(MDCustomizationForSysMLProfile.isPartProperty(element)) {
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
		} else if(MDCustomizationForSysMLProfile.isQuantityKind(element)) {
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
		} else if(MDCustomizationForSysMLProfile.isUnit(element)) {
			commonElementType = SysmlConstants.UNIT;
			CameoUtils.logGUI("Exporting Unit");
		} else if(element instanceof Usage) {
			commonRelationshipType = SysmlConstants.USAGE;
			CameoUtils.logGUI("Exporting Usage");
		} else if(element instanceof UseCase) {
			commonElementType = SysmlConstants.USECASE;
			CameoUtils.logGUI("Exporting Use Case");
		} else if(MDCustomizationForSysMLProfile.isValueProperty(element)) {
			commonElementType = SysmlConstants.VALUEPROPERTY;
			CameoUtils.logGUI("Exporting Value Property");
		} else if(CameoUtils.isValueType(element, project)) {
			commonElementType = SysmlConstants.VALUETYPE;
			CameoUtils.logGUI("Exporting Value Type");
		} else if(CameoUtils.isVerify(element, project)) {
			commonRelationshipType = SysmlConstants.VERIFY;
			CameoUtils.logGUI("Exporting Verify");
			
			
			
		//Super classes listed below as to not to override their children	
		} else if(CameoUtils.isProperty(element, project)) {
				commonElementType = SysmlConstants.PROPERTY;
				CameoUtils.logGUI("Exporting Property");	
		} else if (element instanceof InstanceSpecification) {
			NamedElement namedElement = (NamedElement)element;
			if(!namedElement.getName().equals("") && !namedElement.getName().equals(null) && !Arrays.asList(SysmlConstants.RESERVEINSTANCESPECIFICATION).contains(namedElement.getName())) {
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
			DiagramPresentationElement presentationDiagram;

			presentationDiagram = project.getDiagram(diag);
			DiagramType diagType = presentationDiagram.getDiagramType();
			String diagTypeStr = presentationDiagram.getRealType();
			
			CameoUtils.logGUI("ACTUAL diagram type: " + diagType.getType());
			CameoUtils.logGUI("MAPPED diagram type: " + AbstractDiagram.diagramToType.get( diagType.getType()));
			
			commonElementType = AbstractDiagram.diagramToType.get( diagType.getType());
			
//			List<String> it = DiagramType.getAllDiagramTypes();
//			for (String str: it) {
//				CameoUtils.logGUI("DiagramList type: " + str);
//			}
			
		}
		else {
			String message = "Element with type: " + element.getHumanType() + " is not supported yet!!!";
			ExportLog.log(message);
			CameoUtils.logGUI(message);
		}
		if(!exportedElements.containsKey(element.getLocalID())) {
			if(commonElementType != null) {
				CommonElementsFactory cef = new CommonElementsFactory();
				CommonElement commonElement = null;
				
				if(element instanceof NamedElement) {
					//Check if ID already exists from previous import
					CameoUtils.logGUI("\tElement named: " +  ((NamedElement)element).getName() + " with id: " + element.getLocalID());
					commonElement = cef.createElement(commonElementType,  ((NamedElement)element).getName(), element.getLocalID());
				} else {
					CameoUtils.logGUI("\tElement named: " +  element.getHumanName() + " with id: " + element.getLocalID());
					commonElement = cef.createElement(commonElementType, "", element.getLocalID());
				}
				commonElement.writeToXML(element, project, xmlDoc);
				exportedElements.put(element.getLocalID(), "");
			}
			
			if(commonRelationshipType != null) {
				CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
				String name = "";
				
				CommonRelationship commonRelationship = null;
				if(element instanceof NamedElement) {
					name = ((NamedElement)element).getName();
					CameoUtils.logGUI("\tRelationship named: " +  name + " with id: " + element.getLocalID());
					commonRelationship = crf.createElement(commonRelationshipType, name, element.getLocalID());
				} else {
					name = element.getHumanName();
					CameoUtils.logGUI("\tRelationship named: " +  name + " with id: " + element.getLocalID());
					commonRelationship = crf.createElement(commonRelationshipType, "", element.getLocalID());
				}
				commonRelationship.writeToXML(element, project, xmlDoc);
				exportedElements.put(element.getLocalID(), "");
				// Check if supplier and client are created - important for UML Metaclasses and SysML Profile objects referenced in extension and generalization relationships
				if(!exportedElements.containsKey(commonRelationship.getSupplier().getLocalID())) {
					exportElement(commonRelationship.getSupplier(), project, xmlDoc);
				}
				if(!exportedElements.containsKey(commonRelationship.getClient().getLocalID())) {
					exportElement(commonRelationship.getClient(), project, xmlDoc);
				}
			}
		} else {
			CameoUtils.logGUI("Duplicate element with id " + element.getLocalID() + " not exported.");
		}
		
	}

	public static String getElementType(Element element) {
		String commonElementType = null;
		String commonRelationshipType = null;		
		
		//Use SysMLProfile.is<SysmlElementName> to check types for sysml elements 
		if(element instanceof AcceptEventAction) {
			commonElementType = SysmlConstants.ACCEPTEVENTACTION;
		} else if(element instanceof Activity) {
			commonElementType = SysmlConstants.ACTIVITY;
		} else if(element instanceof ActivityFinalNode) {
			commonElementType = SysmlConstants.ACTIVITYFINALNODE;
		} else if(element instanceof ActivityParameterNode) {
			commonElementType = SysmlConstants.ACTIVITYPARAMETERNODE;
		} else if(element instanceof ActivityPartition) {
			commonElementType = SysmlConstants.ACTIVITYPARTITION;
		} else if(element instanceof Actor) {
			commonElementType = SysmlConstants.ACTOR;
		} else if(CameoUtils.isAssociationBlock(element, project)) {
			commonElementType = SysmlConstants.ASSOCIATIONBLOCK;
		} else if (SysMLProfile.isBindingConnector(element)) {
			commonRelationshipType = SysmlConstants.BINDINGCONNECTOR;
		} else if (SysMLProfile.isBoundReference(element)) {
			commonElementType = SysmlConstants.BOUNDREFERENCE;
		} else if (SysMLProfile.isClassifierBehaviorProperty(element)) {
			commonElementType = SysmlConstants.CLASSIFIERBEHAVIORPROPERTY;
		} else if (element instanceof CallBehaviorAction) {
			commonElementType = SysmlConstants.CALLBEHAVIORACTION;
		} else if (element instanceof CallOperationAction) {
			commonElementType = SysmlConstants.CALLOPERATIONACTION;
		} else if (element instanceof ChangeEvent) {
			commonElementType = SysmlConstants.CHANGEEVENT;
		} else if(CameoUtils.isCopy(element, project)) {
			commonRelationshipType = SysmlConstants.COPY;
		} else if(element instanceof Collaboration) {
			commonElementType = SysmlConstants.COLLABORATION;
		} else if(element instanceof CombinedFragment) {
			commonElementType = SysmlConstants.COMBINEDFRAGMENT;
	//	} else if(element instanceof Comment) {
	//		commonElementType = SysmlConstants.COMMENT;
	//		CameoUtils.logGUI("Exporting Comment");
		} else if(element instanceof ConditionalNode) { 
			commonElementType = SysmlConstants.CONDITIONALNODE;
		} else if (element instanceof ConnectionPointReference) {
			commonElementType = SysmlConstants.CONNECTIONPOINTREFERENCE;
		} else if (element instanceof Connector) {
			commonRelationshipType = SysmlConstants.CONNECTOR;
		} else if(element instanceof Constraint) {
			Constraint constraint = (Constraint)element;
			commonElementType = SysmlConstants.CONSTRAINT;
		} else if(SysMLProfile.isConstraintBlock(element)) {
			commonElementType = SysmlConstants.CONSTRAINTBLOCK;
		} else if(MDCustomizationForSysMLProfile.isConstraintParameter(element)) {
			commonElementType = SysmlConstants.CONSTRAINTPARAMETER;
		} else if (MDCustomizationForSysMLProfile.isConstraintProperty(element)) {
			commonElementType = SysmlConstants.CONSTRAINTPROPERTY;
		} else if(element instanceof ControlFlow) {
			commonRelationshipType = SysmlConstants.CONTROLFLOW;
		} else if(element instanceof CreateObjectAction) {
			commonElementType = SysmlConstants.CREATEOBJECTACTION;
		} else if(CameoUtils.isCustomization(project, element)) {
			commonElementType = SysmlConstants.CUSTOMIZATION;
		} else if(element instanceof DataStoreNode) {
			commonElementType = SysmlConstants.DATASTORENODE;
		} else if(element instanceof DecisionNode) {
			commonElementType = SysmlConstants.DECISIONNODE;
		} else if(CameoUtils.isDeriveRequirement(element, project)) {
			commonRelationshipType = SysmlConstants.DERIVEREQUIREMENT;		
		} else if(CameoUtils.isDesignConstraint(element, project)) {
			commonElementType = SysmlConstants.DESIGNCONSTRAINT;
		} else if(element instanceof DestroyObjectAction) {
			commonElementType = SysmlConstants.DESTROYOBJECTACTION;
		} else if(element instanceof DestructionOccurrenceSpecification) {
			commonElementType = SysmlConstants.DESTRUCTIONOCCURRENCESPECIFICATION;
		} else if(SysMLProfile.isDomain(element)) {
			commonElementType = SysmlConstants.DOMAIN;
		} else if(element instanceof DurationConstraint) {
			commonElementType = SysmlConstants.DURATIONCONSTRAINT;
		} else if(element instanceof DurationObservation) {
			commonElementType = SysmlConstants.DURATIONOBSERVATION;
		} else if(element instanceof Enumeration) {
			commonElementType = SysmlConstants.ENUMERATION;
		} else if(element instanceof EnumerationLiteral) {
			commonElementType = SysmlConstants.ENUMERATIONLITERAL;
		} else if(element instanceof Extend) {
			commonRelationshipType = SysmlConstants.EXTEND;
		} else if(CameoUtils.isExtendedRequirement(element, project)) {
			commonElementType = SysmlConstants.EXTENDEDREQUIREMENT;
		} else if(element instanceof Extension) {
			commonRelationshipType = SysmlConstants.EXTENSION;
		} else if(element instanceof ExtensionPoint) {
			commonElementType = SysmlConstants.EXTENSIONPOINT;
		} else if (SysMLProfile.isExternal(element)) {
			commonElementType = SysmlConstants.EXTERNAL;
		} else if(element instanceof FinalState) {
			commonElementType = SysmlConstants.FINALSTATE;
		} else if(element instanceof FlowFinalNode) {
			commonElementType = SysmlConstants.FLOWFINALNODE;
		} else if(SysMLProfile.isFlowPort(element)) {
			commonElementType = SysmlConstants.FLOWPORT;
		} else if(SysMLProfile.isFlowSpecification(element)) {
			commonElementType = SysmlConstants.FLOWSPECIFICATION;
		} else if(element instanceof ForkNode) {
			commonElementType = SysmlConstants.FORKNODE;
		} else if(SysMLProfile.isFullPort(element)) {
			commonElementType = SysmlConstants.FULLPORT;
		} else if(CameoUtils.isFunctionalRequirement(element, project)) {
			commonElementType = SysmlConstants.FUNCTIONALREQUIREMENT;
		} else if(element instanceof FunctionBehavior) {
			commonElementType = SysmlConstants.FUNCTIONBEHAVIOR;
		} else if(element instanceof Generalization) {
			commonRelationshipType = SysmlConstants.GENERALIZATION;
		} else if(element instanceof Include) {
			commonRelationshipType = SysmlConstants.INCLUDE;
		} else if(element instanceof Interaction) {
			commonElementType = SysmlConstants.INTERACTION;
		} else if(element instanceof InteractionOperand) {
			commonElementType = SysmlConstants.INTERACTIONOPERAND;
		} else if(element instanceof InteractionUse) {
			commonElementType = SysmlConstants.INTERACTIONUSE;
		} else if(element instanceof InitialNode) {
			commonElementType = SysmlConstants.INITIALNODE;
		} else if(element instanceof Pseudostate) {
			commonElementType = CameoUtils.getPseudoState(element);		
		} else if(element instanceof InputPin) {
			commonElementType = SysmlConstants.INPUTPIN;
		} else if(element instanceof Interface) {
			commonElementType = SysmlConstants.INTERFACE;
		} else if(CameoUtils.isInterfaceBlock(element, project)) {
			commonElementType = SysmlConstants.INTERFACEBLOCK;
		} else if(element instanceof InterfaceRealization) {
			commonElementType = SysmlConstants.INTERFACEREALIZATION;
		} else if(CameoUtils.isInterfaceRequirement(element, project)) {
			commonElementType = SysmlConstants.INTERFACEREQUIREMENT;
		} else if (SysMLProfile.isItemFlow(element)) {
			commonRelationshipType = SysmlConstants.ITEMFLOW;
		} else if(element instanceof JoinNode) {
			commonElementType = SysmlConstants.JOINNODE;
		} else if(element instanceof Lifeline) {
			commonElementType = SysmlConstants.LIFELINE;
		} else if(element instanceof LoopNode) {
			commonElementType = SysmlConstants.LOOPNODE;
		} else if(element instanceof MergeNode) {
			commonElementType = SysmlConstants.MERGENODE;
		} else if(element instanceof Message) {
			commonElementType = SysmlConstants.MESSAGE;
		} else if(element instanceof MessageOccurrenceSpecification) {
			commonElementType = SysmlConstants.MESSAGEOCCURRENCESPECIFICATION;
		} else if(CameoUtils.isModel(element, project)) {
			commonElementType = SysmlConstants.MODEL;
		} else if(element instanceof ObjectFlow) {
			commonRelationshipType = SysmlConstants.OBJECTFLOW;
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
			commonElementType = SysmlConstants.OPAQUEEXPRESSION;
		} else if(element instanceof OpaqueAction) {
			commonElementType = SysmlConstants.OPAQUEACTION;
		} else if(element instanceof OpaqueBehavior) {
			commonElementType = SysmlConstants.OPAQUEBEHAVIOR;
		} else if(element instanceof Operation) {
			commonElementType = SysmlConstants.OPERATION;
		} else if(SysMLProfile.isParticipantProperty(element)) {
			commonElementType = SysmlConstants.PARTICIPANTPROPERTY;
		} else if(MDCustomizationForSysMLProfile.isPartProperty(element)) {
			commonElementType = SysmlConstants.PARTPROPERTY;
		} else if(element instanceof Profile) {
			commonElementType = SysmlConstants.PROFILE;
		} else if(element instanceof OutputPin) {
			commonElementType = SysmlConstants.OUTPUTPIN;
		} else if(element instanceof Package) {
			commonElementType = SysmlConstants.PACKAGE;
		} else if(CameoUtils.isPerformanceRequirement(element, project)) {
			commonElementType = SysmlConstants.PERFORMANCEREQUIREMENT;
		} else if(CameoUtils.isPhysicalRequirement(element, project)) {
			commonElementType = SysmlConstants.PHYSICALREQUIREMENT;
		//Proxy Port must come before Port as all specialized ports are instanceof Port
		} else if(SysMLProfile.isProxyPort(element)) {
			commonElementType = SysmlConstants.PROXYPORT;
		} else if(element instanceof Port) {
			commonElementType = SysmlConstants.PORT;
		} else if(MDCustomizationForSysMLProfile.isQuantityKind(element)) {
			commonElementType = SysmlConstants.QUANTITYKIND;
		} else if(element instanceof Region) {
			commonElementType = SysmlConstants.REGION;
		} else if(CameoUtils.isRefine(element, project)) {
			commonRelationshipType = SysmlConstants.REFINE;
		} else if(CameoUtils.isRequirement(element, project)) {
			commonElementType = SysmlConstants.REQUIREMENT;
		} else if(CameoUtils.isSatisfy(element, project)) {
			commonRelationshipType = SysmlConstants.SATISFY;
		} else if(element instanceof SendSignalAction) {
			commonElementType = SysmlConstants.SENDSIGNALACTION;
		} else if(element instanceof Signal) {
			commonElementType = SysmlConstants.SIGNAL;
		} else if(element instanceof SignalEvent) {
			commonElementType = SysmlConstants.SIGNALEVENT;
		} else if(element instanceof State) {
			commonElementType = SysmlConstants.STATE;
		} else if(element instanceof StateInvariant) {
			commonElementType = SysmlConstants.STATEINVARIANT;
		} else if(element instanceof StateMachine) {
			commonElementType = SysmlConstants.STATEMACHINE;
		} else if(element instanceof Stereotype) {
			commonElementType = SysmlConstants.STEREOTYPE;
		} else if(CameoUtils.isTrace(element, project)) {
			commonRelationshipType = SysmlConstants.TRACE;
		} else if(SysMLProfile.isSubsystem(element)) {
			commonElementType = SysmlConstants.SUBSYSTEM;
		} else if(SysMLProfile.isSystemcontext(element)) {
			commonElementType = SysmlConstants.SYSTEMCONTEXT;
		} else if(SysMLProfile.isSystem(element)) {
			commonElementType = SysmlConstants.SYSTEM;
		} else if (element instanceof TimeEvent) {
			commonElementType = SysmlConstants.TIMEEVENT;
		} else if (element instanceof TimeObservation) {
			commonElementType = SysmlConstants.TIMEOBSERVATION;
		} else if (element instanceof Transition) {
			commonRelationshipType = SysmlConstants.TRANSITION;
		} else if (element instanceof Trigger) {
			commonElementType = SysmlConstants.TRIGGER;
		} else if(MDCustomizationForSysMLProfile.isUnit(element)) {
			commonElementType = SysmlConstants.UNIT;
		} else if(element instanceof Usage) {
			commonRelationshipType = SysmlConstants.USAGE;
		} else if(element instanceof UseCase) {
			commonElementType = SysmlConstants.USECASE;
		} else if(MDCustomizationForSysMLProfile.isValueProperty(element)) {
			commonElementType = SysmlConstants.VALUEPROPERTY;
		} else if(CameoUtils.isValueType(element, project)) {
			commonElementType = SysmlConstants.VALUETYPE;
		} else if(CameoUtils.isVerify(element, project)) {
			commonRelationshipType = SysmlConstants.VERIFY;		
			
		//Super classes listed below as to not to override their children	
		} else if(CameoUtils.isProperty(element, project)) {
				commonElementType = SysmlConstants.PROPERTY;	
		} else if (element instanceof InstanceSpecification) {
			NamedElement namedElement = (NamedElement)element;
			if(!namedElement.getName().equals("") && !namedElement.getName().equals(null) && !Arrays.asList(SysmlConstants.RESERVEINSTANCESPECIFICATION).contains(namedElement.getName())) {
				commonElementType = SysmlConstants.INSTANCESPECIFICATION;
			}
		// Check ActionClass last as any child action class will be an instance of ActionClass
		} else if(element instanceof ActionClass) {
			commonElementType = SysmlConstants.ACTION;
		} else if(element instanceof CentralBufferNode) {
			commonElementType = SysmlConstants.CENTRALBUFFERNODE;
			
		//	Class is a super class of some elements (any requirement stereotype elements) - must come after
		} else if(element instanceof Association) {
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd((Association)element);
			com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd((Association)element);
			if(firstMemberEnd.getAggregation() == AggregationKindEnum.SHARED || secondMemberEnd.getAggregation() == AggregationKindEnum.SHARED) {
				commonRelationshipType = SysmlConstants.AGGREGATION;
			} else if(firstMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE || secondMemberEnd.getAggregation() == AggregationKindEnum.COMPOSITE) {
				commonRelationshipType = SysmlConstants.COMPOSITION;
			} else {
				commonRelationshipType = SysmlConstants.ASSOCIATION;
			}
		// Block with overwrite Constraint Block since ConstraintBlock is a subclass of Block
		} else if(SysMLProfile.isBlock(element)) {
			commonElementType = SysmlConstants.BLOCK;
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class) {
			// Check if Metaclass
			if(CameoUtils.isMetaclass(element)) {
				commonElementType = SysmlConstants.METACLASS;
			} else {
				commonElementType = SysmlConstants.CLASS;
			}
		} else if(element instanceof Dependency) {
			commonRelationshipType = SysmlConstants.DEPENDENCY;
		} else if(element instanceof Diagram) {
			Diagram diag = (Diagram) element;
			DiagramPresentationElement presentationDiagram;
	
			presentationDiagram = project.getDiagram(diag);
			DiagramType diagType = presentationDiagram.getDiagramType();
			String diagTypeStr = presentationDiagram.getRealType();
			
			CameoUtils.logGUI("ACTUAL diagram type: " + diagType.getType());
			CameoUtils.logGUI("MAPPED diagram type: " + AbstractDiagram.diagramToType.get( diagType.getType()));
			
			commonElementType = AbstractDiagram.diagramToType.get( diagType.getType());
		}
		if(commonElementType != null) {
			return commonElementType;
		} else {
			return commonRelationshipType;
		}
	}
}
