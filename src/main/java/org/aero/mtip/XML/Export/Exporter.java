/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Export;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import javax.annotation.CheckForNull;
import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.ModelElements.CommonRelationshipsFactory;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.profiles.DslCustomization;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
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
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TaggedValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.OpaqueBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.AnyReceiveEvent;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.CallEvent;
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

public class Exporter {	
	Project project;
	
	HashSet<String> exportedElements = new HashSet<String>();
	HashSet<String> implicitElements = new HashSet<String>();
	HashSet<String> unsupportedElements = new HashSet<String>();
	
	CommonElementsFactory cef;
	CommonRelationshipsFactory crf;
	
	public static void exportModel(File file) throws IOException {
		exportModelFromPackage(file, null);
	}
	
	public static void exportModelFromPackage(File file, Package packageElement) {
		XmlWriter.initialize();
		
		Exporter exporter = new Exporter();
		exporter.buildXML(file, packageElement);
		Logger.logSummary(exporter);
	}
	
	public static void exportModelFromDiagram(File file, DiagramPresentationElement diagramPresentationElement) {
		XmlWriter.initialize();
		
		Exporter exporter = new Exporter();
		exporter.buildXMLFromDiagram(file, diagramPresentationElement);
	}
	
	public Exporter() {
		project = Application.getInstance().getProject();
		cef = new CommonElementsFactory();
		crf = new CommonRelationshipsFactory();
		
		Logger.createNewExportLogger();
	}
	
	public void buildXML(File file, Package packageElement) {		
		if (packageElement == null) {
			packageElement = project.getPrimaryModel();
		}		
		
		exportPackageRecursive((Package)packageElement);
	}
	
	public void buildXMLFromDiagram(File file, DiagramPresentationElement diagramPresentationElement) {
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
		
		Logger.logSummary(this);
	}
	
	public void exportDiagramElementRecursive(File file, PresentationElement presentationElement) {
		List<PresentationElement> presentationElements = presentationElement.getPresentationElements();		
		for(int i = 0; i < presentationElements.size(); i++) {
			Element element = presentationElements.get(i).getElement();
			
			if(element == null) {
				continue;
			} 

			exportElementRecursiveUp(element);			
		}
		
		Logger.logSummary(this);
	}
	
	/**
	 * 
	 * @param element Element to be exported. This begins at an arbitrary level of nested within the model.
	 */
	public void exportElementRecursiveUp(Element element) {
  	    if (element == null) {
  	        return;
  	    }
  	    
		Element parent = element.getOwner();
		exportElementRecursiveUp(parent);
		
		if(element instanceof Package) {
			exportPackage(element);
			return;
		}
			
		exportEntity(element);
	}
	
	public void exportPackageRecursive(Package pkg) {
		if (pkg == null) {
			return;
		}
		
		exportPackage(pkg);

		for(Package nextPackage : pkg.getNestedPackage()) {
			if (isExternalPackage(nextPackage)) {
				continue;
			}
			
			exportPackageRecursive(nextPackage);
		}
		
		for(Element element : pkg.getOwnedElement()) {
			if (isPackage(element)) {
				continue;
			}
					
			exportElementRecursive(element);
		}
	}
	
	public void exportElementRecursive(Element element) {
		if (element == null) {
			return;
		}
		
		exportEntity(element);
		
		for(Element ownedElement : element.getOwnedElement()) {
			if (ownedElement instanceof Package || element instanceof Relationship) {
				continue;
			}
			
			exportElementRecursive(ownedElement);
		}
	}
	
	public void exportPackage(Element pkg) {
		if (exportedElements.contains(pkg.getID())) {
			Logger.log(String.format("%s package, profile, or model with id %s already exported.", pkg.getHumanName(), pkg.getID()));
			return;
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		String packageType = MtipUtils.getPackageType(pkg);
		
		CommonElement commonElement = cef.createElement(packageType, ((NamedElement)pkg).getName(), pkg.getID());
		commonElement.writeToXML(pkg);
		
		exportedElements.add(pkg.getID());
	}
	
	public void exportEntity(Element element) {
		if(exportedElements.contains(element.getID())) {
			Logger.log(String.format("Element already exported with name %s and id %s.", element.getHumanName(), element.getID()));
			return;
		}
		
		String commonElementType = MtipUtils.getEntityType(element);
		
		if (commonElementType == null) {
			if (isImplicitlySupported(element)) {
				addImplicitElement(element);
				return;
			}
			
			unsupportedElements.add(element.getID());
			Logger.log(String.format("%s type could not be identified. Not currently supported.", element.getHumanType()));
			return;
		}
		
		if (MtipUtils.isSupportedElement(commonElementType)) {
			exportElement(element, commonElementType);
			return;
		}
		
		if (MtipUtils.isSupportedRelationship(commonElementType)) {
			exportRelationship(element, commonElementType);
			return;
		}
		
		if (MtipUtils.isSupportedDiagram(commonElementType)) {
			exportElement(element, commonElementType);
			return;
		}		
		
		Logger.log(String.format("%s is not categorized as an element, relationship, or diagram.", commonElementType));
	}
	
	public void exportElement(Element element, String elementType) {
		if (elementType == null) {
			Logger.log(String.format("Element type not found for %s with id %s", element.getHumanName(), element.getID()));
			return;
		}
		 
		CommonElement commonElement =  cef.createElement(elementType, ((NamedElement)element).getName(), element.getID());
		
		if (commonElement == null) {
			return;
		}
		
		commonElement.writeToXML(element);
		exportedElements.add(element.getID());
		
		exportReferencedElements(element);
	}
	
	public void exportRelationship(Element element, String relationshipType) {
	    if (relationshipType == null) {
	         Logger.log(String.format("Relationship type not found for %s with id %s", element.getHumanName(), element.getID()));
	         return;
	    }
	    
		CommonRelationship commonRelationship = crf.createElement(relationshipType, CommonRelationship.getName(element), element.getID());
		
		if (commonRelationship == null) {
			Logger.log(String.format("CommonRelationship not defined in CommonRelationshipFactory. Please check implementation for %s", relationshipType));
			return;
		}
		
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
	
    public void exportReferencedElements(Element element) {
      if (element instanceof TypedElement) {
        Type type = ((TypedElement) element).getType();

        if (type == null) {
          return;
        }

        if (CameoUtils.isPredefinedElement(type)) {
          return;
        }

        exportElementRecursiveUp(type);
      }
    }
	
	public String getPackageType(Element pkg) {
		if(CameoUtils.isModel(pkg)) {
			return SysmlConstants.MODEL;
		} 
		
		if(CameoUtils.isProfile(pkg)) {
			return SysmlConstants.PROFILE;
		} 
		
		return SysmlConstants.PACKAGE;
	}
	
	public boolean isImplicitlySupported(Element element) {
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
			    || element instanceof TaggedValue
			    || MDCustomizationForSysML.isReferenceProperty(element)) {
			return true;
		}
		
		return false;
	}
	
	public boolean isPackage(Element element) {
		if(element instanceof Package
				|| element.getHumanName().equals("Profile Application")
				|| element instanceof PackageImport 
				|| element instanceof Profile) {
			return true;
		}
		
		return false;
	}
	
	public boolean isExternalPackage(Package pkg) {
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
	public void addImplicitElement(Element element) {
		implicitElements.add(element.getID());
	}

	public HashSet<String> getExportedElements() {
		return exportedElements;
	}

	public HashSet<String> getImplicitElements() {
		return implicitElements;
	}

	public HashSet<String> getUnsupportedElements() {
		return unsupportedElements;
	}
}
