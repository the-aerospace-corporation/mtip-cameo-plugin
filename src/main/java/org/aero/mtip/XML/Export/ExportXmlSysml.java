/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Export;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.ModelElements.CommonRelationshipsFactory;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramType;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.shapes.DiagramLegendShape;
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
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Operation;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.PackageImport;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
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

import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.magicdraw.uml.ClassTypes;

public class ExportXmlSysml {
	private static HashMap<String, String> exportedElements = new HashMap<String, String>();
	public static Project project;
	public static String metamodel;

	public static void buildXML(Document xmlDoc, File file, Package packageElement) {
		project = Application.getInstance().getProject();
		exportedElements = new HashMap<String, String>();
		org.w3c.dom.Element root = xmlDoc.createElement("packet");
		xmlDoc.appendChild(root);

		Project project = Application.getInstance().getProject();
		Package primary = project.getPrimaryModel();

		metamodel = CameoUtils.determineMetamodel(project);

		CameoUtils.logGUI("Exporting project with " + metamodel + " metamodel.");

		if(packageElement != null) {
			exportPackageRecursive((Package)packageElement, project, xmlDoc);
		} else {
			exportPackageRecursive(primary, project, xmlDoc);
		}
		ExportLog.save();
		ExportLog.reset();
	}

	public static void buildXMLFromDiagram(Document xmlDoc, File file, DiagramPresentationElement diagramPresentationElement) {
		//TODO: Export dependent elements such as behaviors, etc.
		project = Application.getInstance().getProject();
		exportedElements = new HashMap<String, String>();
		org.w3c.dom.Element root = xmlDoc.createElement("packet");
		xmlDoc.appendChild(root);
		ExportXmlSysml.metamodel = CameoUtils.determineMetamodel(project);

		Element diagramElement = diagramPresentationElement.getElement();
		exportElementRecursiveUp(diagramElement, xmlDoc);
		
		// Ensures elements at the sibling level of the diagram are captured regardless of nesting object structure of presentation elements
		exportElementRecursive(diagramElement.getOwner(), project, xmlDoc);

		//Add hook to get nested presentation elements due to encapsulation of diagrams
		exportDiagramElementRecursive(xmlDoc, file, diagramPresentationElement);
		
		ExportLog.save();
		ExportLog.reset();		
	}
	
	public static void exportDiagramElementRecursive(Document xmlDoc, File file, PresentationElement presentationElement) {
		List<PresentationElement> presentationElements = presentationElement.getPresentationElements();		
		for(int i = 0; i < presentationElements.size(); i++) {
			Element element = presentationElements.get(i).getElement();
			if(element != null) {
				exportElementRecursiveUp(element, xmlDoc);
			} 
			if(!(presentationElement instanceof DiagramLegendShape)) {
				exportDiagramElementRecursive(xmlDoc, file, presentationElements.get(i));
			}		
		}
	}
	/**
	 * 
	 * @param element Element to be exported. This begins at an arbitrary level of nested within the model.
	 * @param project Current project being exported
	 * @param xmlDoc XML Document representing the model being exported
	 */
	public static void exportElementRecursiveUp(Element element, Document xmlDoc) {
		//Find any parent element and export recursively
		Element parent = element.getOwner();
		if(parent != null) {
			if(!parent.equals(project.getPrimaryModel())) {
				exportElementRecursiveUp(parent, xmlDoc);
			}
			ExportLog.log("Parent element of element with id " + element.getID() + " is null.");
			CameoUtils.logGUI("Parent element of element with id " + element.getID() + " is null.");
		}
		
		if(ModelHelper.isRelationship(element)) {
			Element client = ModelHelper.getClientElement(element);
			if(client != null) {
				exportElementRecursiveUp(client, xmlDoc);
			}
			Element supplier = ModelHelper.getSupplierElement(element);
			if(client != null) {
				exportElementRecursiveUp(supplier, xmlDoc);
			}
		}
		
		if(element instanceof TypedElement) {
			TypedElement typedElement = (TypedElement)element;
			Type type = typedElement.getType();
			if(type != null) {
				exportElementRecursiveUp(type, xmlDoc);
			}
		}
		
		if(element instanceof Package) {
			exportPackage(element, project, xmlDoc);
		} else {
			exportElement(element, project, xmlDoc);
		}
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
			if(elementsInPackage != null) {
				CameoUtils.logGUI(elementsInPackage.size() + " elements found in package.");
			}
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

				if(!(element instanceof Package) && !elementName.equals("Package Import") && !elementName.equals("Profile Application") && !element.getHumanType().equals("Legend")) {
					CameoUtils.logGUI("Exporting " + element.getHumanType() + element.getHumanName());
					exportElementRecursive(element, project, xmlDoc);
				}
			}
		}
	}

	public static void exportElementRecursive(Element element, Project project, Document xmlDoc) {
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
		
		// Add check here if adding explicit support for UAF/DoDAF.UPDM package types

		commonElement.writeToXML(pkg, project, xmlDoc);

	}

	public static String getMetaModel(Element element)
	{
		String elementType = ExportXmlSysml.getElementType(element);

		String [] dodafElements = DoDAFConstants.DODAF_ELEMENTS;
		for (String type : dodafElements)
		{
			if(elementType.equals(type))
			{
				return "dodaf";
			}
		}
		String [] dodafRelationships = DoDAFConstants.DODAF_RELATIONSHIPS;
		for (String type : dodafRelationships)
		{
			if (elementType.equals(type))
			{
				return "dodaf";
			}
		}

		String [] dodafDiagrams = DoDAFConstants.DODAF_DIAGRAMS;
		for (String type : dodafDiagrams)
		{
			if (elementType.equals(type))
			{
				return "dodaf";
			}
		}

		String [] uafElements = UAFConstants.UAF_ELEMENTS;
		for (String type : uafElements)
		{
			if (elementType.equals(type))
			{
				return "uaf";
			}
		}
		String [] uafRelationships = UAFConstants.UAF_RELATIONSHIPS;
		for (String type: uafRelationships)
		{
			if (elementType.equals(type))
			{
				return "uaf";
			}
		}

		String [] uafDiagrams = UAFConstants.UAF_DIAGRAMS;
		for(String type : uafDiagrams)
		{
			if(elementType.equals(type))
			{
				return "uaf";
			}
		}

		String [] sysmlElements = SysmlConstants.SYSMLELEMENTS;
		for (String type: sysmlElements)
		{
			if(elementType.equals(type))
			{
				return "sysml";
			}
		}

		String [] sysmlRelationships = SysmlConstants.SYSMLRELATIONSHIPS;
		for (String type: sysmlRelationships)
		{
			if(elementType.equals(type))
			{
				return "sysml";
			}
		}
		String [] sysmlDiagrams = SysmlConstants.SYSMLDIAGRAMS;
		for (String type: sysmlDiagrams)
		{
			if(elementType.equals(type))
			{
				return "sysml";
			}
		}
		return "null";
	}

	public static boolean isRelationship(Element element, Project project)
	{
		boolean isRelationship = false;
		//commented out code for now but this should handle resource messages to be relationships not elements

		/*Class elementClassType = element.getClassType();
		Class messageClass = ClassTypes.getClassType("Message");

		//message class to return true when it is a relationship
		if (elementClassType == messageClass)
		{
			isRelationship = true;
		}
		else
		{
			isRelationship = ModelHelper.isRelationship(element);
		}

		return isRelationship;*/
		isRelationship = ModelHelper.isRelationship(element);
		return isRelationship;
	}

	public static boolean exportElement(Element element, Project project, Document xmlDoc) {
		//if(element.getOwner()!= null) {
		String elementType = null;
		boolean isRelationship = isRelationship(element, project);
		CameoUtils.logGUI(element.toString());

		elementType = getElementType(element);

		if(element instanceof Diagram) {
			Diagram diag = (Diagram) element;
			DiagramPresentationElement presentationDiagram = project.getDiagram(diag);
			DiagramType diagType = presentationDiagram.getDiagramType();
			elementType = AbstractDiagram.diagramToType.get(diagType.getType());
		}

		if(!exportedElements.containsKey(element.getLocalID())) {
			if(elementType != null) {
				if(isRelationship) {
					CommonRelationshipsFactory crf = new CommonRelationshipsFactory();
					String name = "";
					CommonRelationship commonRelationship = null;

					if(element instanceof NamedElement) {
						name = ((NamedElement)element).getName();
						CameoUtils.logGUI("\tRelationship named 1: " +  name +" Type:"+elementType +" with id: " + element.getLocalID());

						commonRelationship = crf.createElement(elementType, name, element.getLocalID());
					} else {
						name = element.getHumanName();
						CameoUtils.logGUI("\tRelationship named 2: " +  name + " Type:"+elementType +" with id: " + element.getLocalID());
						commonRelationship = crf.createElement(elementType, name, element.getLocalID());
					}
					
					boolean isSupplierExported = false;
					boolean isClientExported = false;


					// Check if supplier and client are created - important for UML Metaclasses and SysML Profile objects referenced in extension and generalization relationships
					if(!exportedElements.containsKey(commonRelationship.getSupplier(element).getLocalID())) {
						isSupplierExported = exportElement(commonRelationship.getSupplier(element), project, xmlDoc);
					} else {
						isSupplierExported = true;
					}
					if(!exportedElements.containsKey(commonRelationship.getClient(element).getLocalID())) {
						isClientExported = exportElement(commonRelationship.getClient(element), project, xmlDoc);
					} else {
						isClientExported = true;
					}

					if(isSupplierExported && isClientExported) {
						commonRelationship.writeToXML(element, project, xmlDoc);
						exportedElements.put(element.getLocalID(), "");
						return true;
					}
					else {
						String logMsg = "Relationship with: "+element.getHumanName()+"not exported for the following reason(s): ";
						if(!isSupplierExported) {
							logMsg+="Supplier failed to export. SupplierId:"+commonRelationship.getSupplier().getLocalID()+" ";
						}
						if(!isClientExported) {
							logMsg+="Client failed to export. Clientd: "+commonRelationship.getSupplier().getLocalID()+" ";
						}
						ExportLog.log(logMsg);
					}
					return false;
				} else {				
					CommonElementsFactory cef = new CommonElementsFactory();
					CommonElement commonElement = null;

					if(element instanceof NamedElement) {
						//Check if ID already exists from previous import
						CameoUtils.logGUI("\tElement named 1: " +  ((NamedElement)element).getName() + " with id: " + element.getLocalID());
						commonElement = cef.createElement(elementType,  ((NamedElement)element).getName(), element.getLocalID());
					} else {
						CameoUtils.logGUI("\tElement named 2: " +  element.getHumanName() + " with id: " + element.getLocalID());
						commonElement = cef.createElement(elementType, "", element.getLocalID());
					}
					if(commonElement != null) {
						commonElement.writeToXML(element, project, xmlDoc);
						exportedElements.put(element.getLocalID(), "");
						return true;
					} else {
						ExportLog.log("Common Elements Factory return null for element of type " + element.getHumanType() );
						return false;
					}

				}
			} else {
				CameoUtils.logGUI("Element type " + element.getHumanType() + " not supported!!!");
				ExportLog.log("Element type " + element.getHumanType() + " not supported!!!");
				return false;
			}
		} else {
			CameoUtils.logGUI("Duplicate element with id " + element.getLocalID() + " not exported.");
			return true;
		}
	}

	public static String getElementType(Element element) {
		if (ExportXmlSysml.metamodel == null)
		{
			CameoUtils.logGUI(element.getHumanName()+" ExportXmlSysml.getElementType metamodel is null");
			return "null";
		}
		if (ExportXmlSysml.metamodel.contentEquals(UAFConstants.UAF))  {
			ExportLog.log("Getting UAF Element Type");
			String elementType = getUAFElementType(element);
			if(elementType == null) {
				return getSysMLElementType(element);
			} else {
				return elementType;
			}
		} else {
			return getSysMLElementType(element);
		}
	}

	public static String getUAFElementType(Element element) {
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element)
				.stream()
				.filter(x -> UAFProfile.getName(x) != null)
				.collect(Collectors.toList());
		
		if (stereotypes.size() == 0) {
			return null;
		}
		
		if (stereotypes.size() == 1) {
			return UAFProfile.getName(stereotypes.get(0));
		}
		
		ExportLog.log(String.format("UAF type nout found. Ambiguous type for element with id %s. Multiple UAF stereotypes found", element.getID()));
		return null;
	}

	public static String getUAFPackageType(Element element) {
		// UAF Package types not in the UAFP v1.1 specification. May need to reconsider logic here for import
		// to apply appropriate package stereotypes for enforced Cameo Parent-child valdiation rules.
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);

//		if(stereotypes.contains(UAFProfile.STRATEGIC_TAXONOMY_PACKAGE_STEREOTYPE)) {
//			return UAFConstants.STRATEGIC_TAXONOMY_PACKAGE;
//		}
		
		return SysmlConstants.PACKAGE;
	}

	public static String getSysMLElementType(Element element) {
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
		} else if(element instanceof Comment) {
			commonElementType = SysmlConstants.COMMENT;
		} else if(element instanceof ConditionalNode) { 
			commonElementType = SysmlConstants.CONDITIONALNODE;
		} else if (element instanceof ConnectionPointReference) {
			commonElementType = SysmlConstants.CONNECTIONPOINTREFERENCE;
		} else if (element instanceof Connector) {
			commonRelationshipType = SysmlConstants.CONNECTOR;
		} else if(element instanceof Constraint) {
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
		} else if(SysMLProfile.isFlowProperty(element)) {
			commonElementType = SysmlConstants.FLOWPROPERTY;
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
		} else if(element instanceof InformationItem) {
			commonElementType = SysmlConstants.INFORMATIONITEM;
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
		} else if(element instanceof InterruptibleActivityRegion) {
			commonElementType = SysmlConstants.INTERRUPTIBLEACTIVITYREGION;
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
		} else if(element instanceof PackageImport) {
			commonRelationshipType = SysmlConstants.PACKAGEIMPORT;
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
