/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.XML.Export;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.ModelElements.CommonElementsFactory;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.ModelElements.CommonRelationshipsFactory;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.MDCustomizationForSysMLProfile;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.PackageImport;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class Exporter {	
	Project project;
	
	HashSet<String> exportedElements = new HashSet<String>();
	HashSet<String> implicitElements = new HashSet<String>();
	HashSet<String> unsupportedElements = new HashSet<String>();
	
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
	 * @param project Current project being exported
	 * @param xmlDoc XML Document representing the model being exported
	 */
	public void exportElementRecursiveUp(Element element) {
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
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		CommonElement commonElement =  cef.createElement(elementType, CameoUtils.getElementName(element), element.getID());
		
		if (commonElement == null) {
			return;
		}
		
//		ExportLog.log(String.format("Exporting element %s of type %s with id %s.", element.getHumanName(), element.getHumanType(), element.getID()));
		commonElement.writeToXML(element);
		exportedElements.add(element.getID());
	}
	
	public void exportRelationship(Element element, String relationshipType) {
		CommonRelationshipsFactory crf = new CommonRelationshipsFactory();		
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
			    || !CameoUtils.isSupportedInstanceSpecification(element)
			    || MDCustomizationForSysMLProfile.isReferenceProperty(element)) {
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
