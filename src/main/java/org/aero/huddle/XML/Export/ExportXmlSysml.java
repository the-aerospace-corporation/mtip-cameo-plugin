package org.aero.huddle.XML.Export;

import java.io.File;
import java.util.ArrayList;
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
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityParameterNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.InitialNode;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.auxiliaryconstructs.mdmodels.Model;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdcollaborations.Collaboration;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Interaction;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionUse;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.mdusecases.Actor;
import com.nomagic.uml2.ext.magicdraw.mdusecases.UseCase;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.FinalState;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;

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
				//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Exporting Element: " + element.getHumanName());
				if(!elementName.contains("Package")) {
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
		if(!noElements) {
			for(Element ownedElement : ownedElements) {
				if(ownedElement instanceof NamedElement) {
//					CameoUtils.logGUI("Found element: " + ownedElement.getHumanName() + "\twith type: " + ownedElement.getHumanType());
					// Check that ownedElement is not a package, otherwise will cause repeats
					if (!(ownedElement instanceof Package)) {
						exportElementRecursive(ownedElement, project, xmlDoc);
					}
				}
			}
		}
	}
	
	public static void exportPackage(Element pkg, Project project, Document xmlDoc) {
		CameoUtils.logGUI("Exporting package " + pkg.getHumanName());
		CommonElementsFactory cef = new CommonElementsFactory();
		NamedElement namedElement = (NamedElement)pkg;
		//Check if ID already exists from previous import
		CommonElement commonElement = cef.createElement(SysmlConstants.PACKAGE,  namedElement.getName(), pkg.getLocalID());
		commonElement.writeToXML(pkg, project, xmlDoc);
	}
	
	public static void exportElement(Element element, Project project, Document xmlDoc) {
		String commonElementType = null;
		String commonRelationshipType = null;
		if(element instanceof Activity) {
			commonElementType = SysmlConstants.ACTIVITY;
			CameoUtils.logGUI("Exporting Activity");
		} else if(element instanceof ActivityParameterNode) {
			commonElementType = SysmlConstants.ACTIVITYPARAMETERNODE;
			CameoUtils.logGUI("Exporting Activity Parameter Node");
		} else if(element instanceof Actor) {
			commonElementType = SysmlConstants.ACTOR;
			CameoUtils.logGUI("Exporting Actor");
		} else if(CameoUtils.isBlock(element, project)) {
			commonElementType = SysmlConstants.BLOCK;
			CameoUtils.logGUI("Exporting Block");
//	Class is a super class of some elements (any requirement stereotype elements) - create method to check if another type
//		} else if(element instanceof Class) {
//			CameoUtils.logGUI("Exporting Class");
		} else if(CameoUtils.isCopy(element, project)) {
			commonRelationshipType = SysmlConstants.COPY;
			CameoUtils.logGUI("Exporting Copy Relation");
		} else if(element instanceof Collaboration) {
			commonElementType = SysmlConstants.COLLABORATION;
			CameoUtils.logGUI("Exporting Collaboration");
		} else if(element instanceof CombinedFragment) {
			commonElementType = SysmlConstants.COMBINEDFRAGMENT;
			CameoUtils.logGUI("Exporting Combined Fragment");
		} else if(CameoUtils.isDeriveRequirement(element, project)) {
			commonRelationshipType = SysmlConstants.DERIVEREQUIREMENT;
			CameoUtils.logGUI("Exporting Derive Requirement Relation");
		} else if(CameoUtils.isDesignConstraint(element, project)) {
			commonElementType = SysmlConstants.DESIGNCONSTRAINT;
			CameoUtils.logGUI("Exporting Design Constraint");
		} else if(CameoUtils.isExtendedRequirement(element, project)) {
			commonElementType= SysmlConstants.EXTENDEDREQUIREMENT;
			CameoUtils.logGUI("Exporting Extended Requirement");
		} else if(element instanceof FinalState) {
			commonElementType = SysmlConstants.FINALSTATE;
			CameoUtils.logGUI("Exporting Final State");
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
			commonElementType = SysmlConstants.INITIALPSEUDOSTATE;
			CameoUtils.logGUI("Exporting Initial Node");
		} else if(CameoUtils.isInterfaceBlock(element, project)) {
			commonElementType = SysmlConstants.INTERFACEBLOCK;
			CameoUtils.logGUI("Exporting Final State");
		} else if(CameoUtils.isInterfaceRequirement(element, project)) {
			commonElementType = SysmlConstants.INTERFACEREQUIREMENT;
			CameoUtils.logGUI("Exporting Interface Requirement");
		} else if(element instanceof Lifeline) {
			commonElementType = SysmlConstants.LIFELINE;
			CameoUtils.logGUI("Exporting Lifeline");
		} else if(element instanceof Model) {
			commonElementType = SysmlConstants.MODEL;
			CameoUtils.logGUI("Exporting Model");
		} else if(element instanceof Operation) {
			commonElementType = SysmlConstants.OPERATION;
			CameoUtils.logGUI("Exporting Operation");
		} else if(CameoUtils.isPartProperty(element, project)) {
			commonElementType = SysmlConstants.PARTPROPERTY;
			CameoUtils.logGUI("Exporting Part Property Requirement");
		} else if(element instanceof Package) {
			CameoUtils.logGUI("PACKAGE SHOULD NOT BE EXPORTING HERE");
		} else if(CameoUtils.isPerformanceRequirement(element, project)) {
			commonElementType = SysmlConstants.PERFORMANCEREQUIREMENT;
			CameoUtils.logGUI("Exporting Performance Requirement");
		} else if(CameoUtils.isPhysicalRequirement(element, project)) {
			commonElementType = SysmlConstants.PHYSICALREQUIREMENT;
			CameoUtils.logGUI("Exporting Physical Requirement");
		} else if(element instanceof Port) {
			commonElementType = SysmlConstants.PORT;
			CameoUtils.logGUI("Exporting Port");
		} else if(element instanceof Property) {
			commonElementType = SysmlConstants.PROPERTY;
			CameoUtils.logGUI("Exporting Property");
		} else if(CameoUtils.isRefine(element, project)) {
			commonRelationshipType = SysmlConstants.REFINE;
			CameoUtils.logGUI("Exporting Refine Relation");
		} else if(CameoUtils.isRequirement(element, project)) {
			commonElementType = SysmlConstants.REQUIREMENT;
			CameoUtils.logGUI("Exporting Requirement");
		} else if(CameoUtils.isSatisfy(element, project)) {
			commonRelationshipType = SysmlConstants.SATISFY;
			CameoUtils.logGUI("Exporting Satisfy Relation");
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
		} else {
			CameoUtils.logGUI("Element with type: " + element.getHumanType() + " is not supported yet!!!");
		}
		
		if(commonElementType != null) {
			CommonElementsFactory cef = new CommonElementsFactory();
			NamedElement namedElement = (NamedElement)element;
			//Check if ID already exists from previous import
			CommonElement commonElement = cef.createElement(commonElementType,  namedElement.getName(), element.getLocalID());
			commonElement.writeToXML(element, project, xmlDoc);
		}
		
		if(commonRelationshipType != null) {
			CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
			NamedElement namedRelationship = (NamedElement)element;
			CommonRelationship commonRelationship = crf.createElement(commonRelationshipType, namedRelationship.getName(), element.getLocalID());
			commonRelationship.writeToXML(element, project, xmlDoc);
		}
		
	}
}
