package org.aero.huddle.XML.Export;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.ModelElements.CommonElementsFactory;
import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.ModelElements.CommonRelationshipsFactory;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
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
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.Interface;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdcollaborations.Collaboration;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Interaction;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionUse;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Actor;
import com.nomagic.uml2.ext.magicdraw.mdusecases.UseCase;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.FinalState;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition;

public class ExportXmlSysml {
	public static void buildXML(Document xmlDoc, File file) {
		org.w3c.dom.Element root = xmlDoc.createElement("packet");
		xmlDoc.appendChild(root);
		
		Project project = Application.getInstance().getProject();
		Package primary = project.getPrimaryModel();
		exportPackageRecursive(primary, project, xmlDoc);		
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
				Stereotype auxiliary = CameoUtils.getStereotype("auxiliary");
				List<Stereotype> packageStereotypes = StereotypesHelper.getStereotypes(nextPackage);
				
				// Check if package is editable -- external dependencies and imported projects will be read-only (not editable) 
				if(nextPackage.isEditable() && !packageStereotypes.contains(auxiliary) && !nextPackage.getHumanName().contains("ModelLibrary") && !nextPackage.getHumanName().contains("Profile")) {
					if(!nextPackage.getHumanName().equals("Package Unit Imports")) {
//						CameoUtils.logGUI("Package with name " + nextPackage.getHumanName() + "\twith type: " + nextPackage.getHumanType());
//						JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Exporting Package " + nextPackage.getHumanName());
						exportPackageRecursive(nextPackage, project, xmlDoc);
					}					
				}
			}
		}
		
		if(!noElements) {
			for(Element element : elementsInPackage) {
				//export elements that are not packages
				String elementName = element.getHumanName();
				if(!(element instanceof Package) && !elementName.equals("Package Import") && !elementName.equals("Profile Application")) {
					CameoUtils.logGUI("Exporting " + element.getHumanName());
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
		if(!noElements && !(element instanceof Relationship)) {
			for(Element ownedElement : ownedElements) {
				if(ownedElement instanceof NamedElement) {
//					CameoUtils.logGUI("Found element: " + ownedElement.getHumanName() + "\twith type: " + ownedElement.getHumanType());
					// Check that ownedElement is not a package, otherwise will cause repeats -- element also must be a NamedElement to ignore unecessary instance specifications, opaque expressions, etc.
					if (!(ownedElement instanceof Package)) {
						exportElementRecursive(ownedElement, project, xmlDoc);
					}
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
		} else if(element instanceof Association) {
			commonRelationshipType = SysmlConstants.ASSOCIATION;
			CameoUtils.logGUI("Exporting Association");
		} else if(CameoUtils.isAssociationBlock(element, project)) {
			commonElementType = SysmlConstants.ASSOCIATIONBLOCK;
			CameoUtils.logGUI("Exporting Association Block");
		} else if(CameoUtils.isBlock(element, project)) {
			commonElementType = SysmlConstants.BLOCK;
			CameoUtils.logGUI("Exporting Block");
//	Class is a super class of some elements (any requirement stereotype elements) - create method to check if another type
//		} else if(element instanceof Class) {
//			CameoUtils.logGUI("Exporting Class");
		} else if (element instanceof CallBehaviorAction) {
			commonElementType = SysmlConstants.CALLBEHAVIORACTION;
			CameoUtils.logGUI("Exporting Call Behavior Action");
		} else if (element instanceof CallOperationAction) {
			commonElementType = SysmlConstants.CALLOPERATIONACTION;
			CameoUtils.logGUI("Exporting Call Operation Action");
		} else if(CameoUtils.isCopy(element, project)) {
			commonRelationshipType = SysmlConstants.COPY;
			CameoUtils.logGUI("Exporting Copy Relation");
		} else if(element instanceof Collaboration) {
			commonElementType = SysmlConstants.COLLABORATION;
			CameoUtils.logGUI("Exporting Collaboration");
		} else if(element instanceof CombinedFragment) {
			commonElementType = SysmlConstants.COMBINEDFRAGMENT;
			CameoUtils.logGUI("Exporting Combined Fragment");
		} else if(element instanceof ConditionalNode) { 
			commonElementType = SysmlConstants.CONDITIONALNODE;
			CameoUtils.logGUI("Exporting Conditional Node");
		} else if(SysMLProfile.isConstraintBlock(element)) {
			commonElementType = SysmlConstants.CONSTRAINTBLOCK;
			CameoUtils.logGUI("Exporting Constraint Block");
		} else if(element instanceof ControlFlow) {
			commonRelationshipType = SysmlConstants.CONTROLFLOW;
			CameoUtils.logGUI("Exporting Control Flow");
		} else if(element instanceof CreateObjectAction) {
			commonElementType = SysmlConstants.CREATEOBJECTACTION;
			CameoUtils.logGUI("Exporting Create Object Action");
		} else if(element instanceof DataStoreNode) {
			commonElementType = SysmlConstants.DATASTORENODE;
			CameoUtils.logGUI("Exporting Data Store Node");
		} else if(element instanceof DecisionNode) {
			commonElementType = SysmlConstants.DECISIONNODE;
			CameoUtils.logGUI("Exporting Decision Node");
		} else if(CameoUtils.isDeriveRequirement(element, project)) {
			commonRelationshipType = SysmlConstants.DERIVEREQUIREMENT;
			CameoUtils.logGUI("Exporting Derive Requirement Relation");
		} else if(element instanceof DestroyObjectAction) {
			commonElementType = SysmlConstants.DESTROYOBJECTACTION;
			CameoUtils.logGUI("Exporting Destroy Object Action");
		} else if(CameoUtils.isDesignConstraint(element, project)) {
			commonElementType = SysmlConstants.DESIGNCONSTRAINT;
			CameoUtils.logGUI("Exporting Design Constraint");
		} else if(SysMLProfile.isDomain(element)) {
			commonElementType = SysmlConstants.DOMAIN;
			CameoUtils.logGUI("Exporting Domain as block with domain stereotype");
		} else if(element instanceof Enumeration) {
			commonElementType = SysmlConstants.ENUMERATION;
			CameoUtils.logGUI("Exporting Enumeration");
		} else if(CameoUtils.isExtendedRequirement(element, project)) {
			commonElementType = SysmlConstants.EXTENDEDREQUIREMENT;
			CameoUtils.logGUI("Exporting Extended Requirement");
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
		} else if(element instanceof ForkNode) {
			commonElementType = SysmlConstants.FORKNODE;
			CameoUtils.logGUI("Exporting Fork Node");
		} else if(SysMLProfile.isFullPort(element)) {
			commonElementType = SysmlConstants.FULLPORT;
			CameoUtils.logGUI("Exporting Full Port");
		} else if(CameoUtils.isFunctionalRequirement(element, project)) {
			commonElementType = SysmlConstants.FUNCTIONALREQUIREMENT;
			CameoUtils.logGUI("Exporting Functional Requirement");
		} else if(element instanceof Interaction) {
			commonElementType = SysmlConstants.INTERACTION;
			CameoUtils.logGUI("Exporting Interaction");
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
		} else if (element instanceof InstanceSpecification) {
			NamedElement namedElement = (NamedElement)element;
			if(!namedElement.getName().equals("") && !namedElement.getName().equals(null) && !Arrays.asList(SysmlConstants.RESERVEINSTANCESPECIFICATION).contains(namedElement.getName())) {
				commonElementType = SysmlConstants.INSTANCESPECIFICATION;
				CameoUtils.logGUI("Exporting Instance Specification");
			}
		} else if(element instanceof Interface) {
			commonElementType = SysmlConstants.INTERFACE;
			CameoUtils.logGUI("Exporting Interface");
		} else if(CameoUtils.isInterfaceBlock(element, project)) {
			commonElementType = SysmlConstants.INTERFACEBLOCK;
			CameoUtils.logGUI("Exporting Interface Block");
		} else if(CameoUtils.isInterfaceRequirement(element, project)) {
			commonElementType = SysmlConstants.INTERFACEREQUIREMENT;
			CameoUtils.logGUI("Exporting Interface Requirement");
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
		} else if(CameoUtils.isModel(element, project)) {
			commonElementType = SysmlConstants.MODEL;
			CameoUtils.logGUI("Exporting Model");
		} else if(element instanceof ObjectFlow) {
			commonRelationshipType = SysmlConstants.OBJECTFLOW;
			CameoUtils.logGUI("Exporting Object Flow");
		} else if(element instanceof OpaqueAction) {
			commonElementType = SysmlConstants.OPAQUEACTION;
			CameoUtils.logGUI("Exporting Opaque Action");
		} else if(element instanceof Operation) {
			commonElementType = SysmlConstants.OPERATION;
			CameoUtils.logGUI("Exporting Operation");
		} else if(CameoUtils.isPartProperty(element, project)) {
			commonElementType = SysmlConstants.PARTPROPERTY;
			CameoUtils.logGUI("Exporting Part Property Requirement");
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
		} else if(CameoUtils.isQuantityKind(element, project)) {
			commonElementType = SysmlConstants.QUANTITYKIND;
			CameoUtils.logGUI("Exporting Quantity Kind");
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
		} else if(element instanceof State) {
			commonElementType = SysmlConstants.STATE;
			CameoUtils.logGUI("Exporting State");
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
		} else if (element instanceof Transition) {
			commonRelationshipType = SysmlConstants.TRANSITION;
			CameoUtils.logGUI("Exporting Transition");
		} else if(CameoUtils.isUnit(element, project)) {
			commonElementType = SysmlConstants.UNIT;
			CameoUtils.logGUI("Exporting Unit");
		} else if(element instanceof UseCase) {
			commonElementType = SysmlConstants.USECASE;
			CameoUtils.logGUI("Exporting Use Case");
		} else if(CameoUtils.isValueProperty(element, project)) {
			commonElementType = SysmlConstants.VALUEPROPERTY;
			CameoUtils.logGUI("Exporting Value Property");
		} else if(CameoUtils.isValueType(element, project)) {
//			commonElementType = SysmlConstants.VALUETYPE;
			CameoUtils.logGUI("Exporting Value Type");
		} else if(CameoUtils.isVerify(element, project)) {
			commonRelationshipType = SysmlConstants.VERIFY;
			CameoUtils.logGUI("Exporting Verify");
		//Super classes listed below as to not to override their children	
		} else if(CameoUtils.isProperty(element, project)) {
				commonElementType = SysmlConstants.PROPERTY;
				CameoUtils.logGUI("Exporting Property");	
		// Check ActionClass last as any child action class will be an instance of ActionClass
		} else if(element instanceof ActionClass) {
			commonElementType = SysmlConstants.ACTION;
			CameoUtils.logGUI("Exporting Action");
		} else if(element instanceof CentralBufferNode) {
			commonElementType = SysmlConstants.CENTRALBUFFERNODE;
			CameoUtils.logGUI("Exporting Central Buffer Node");
		}
		else if(element instanceof Diagram) {
		commonElementType = SysmlConstants.BDD;
			CameoUtils.logGUI("Exporting Block Definition Diagram");
		}
		else {
			CameoUtils.logGUI("Element with type: " + element.getHumanType() + " is not supported yet!!!");
		}
		
		if(commonElementType != null) {
			CommonElementsFactory cef = new CommonElementsFactory();
			NamedElement namedElement = (NamedElement)element;
			//Check if ID already exists from previous import
			CameoUtils.logGUI("\tElement named: " +  namedElement.getName() + " with id: " + element.getLocalID());
			CommonElement commonElement = cef.createElement(commonElementType,  namedElement.getName(), element.getLocalID());
			commonElement.writeToXML(element, project, xmlDoc);
		}
		
		if(commonRelationshipType != null) {
			CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
			NamedElement namedRelationship = (NamedElement)element;
			CameoUtils.logGUI("\tRelationship named: " +  namedRelationship.getName() + " with id: " + element.getLocalID());
			CommonRelationship commonRelationship = crf.createElement(commonRelationshipType, namedRelationship.getName(), element.getLocalID());
			commonRelationship.writeToXML(element, project, xmlDoc);
		}
		
	}
}
