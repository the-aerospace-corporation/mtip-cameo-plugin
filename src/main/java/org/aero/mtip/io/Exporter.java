/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.io;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.metamodel.core.CommonElementsFactory;
import org.aero.mtip.metamodel.core.CommonRelationship;
import org.aero.mtip.metamodel.core.CommonRelationshipsFactory;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Comment;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
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
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Relationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Slot;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TaggedValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.deployments.mdartifacts.Artifact;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;

public class Exporter {	
	Project project;
	
	HashSet<String> exportedElements = new HashSet<String>();
	HashSet<String> implicitElements = new HashSet<String>();
	HashSet<String> unsupportedElements = new HashSet<String>();
	
	CommonElementsFactory cef;
	CommonRelationshipsFactory crf;
	
	boolean isUafModel = false;
	
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
	    org.aero.mtip.profiles.Profile.clearAllProfiles();
	    
		project = Application.getInstance().getProject();
		cef = new CommonElementsFactory();
		crf = new CommonRelationshipsFactory();
		
		Logger.createNewExportLogger();
		Logger.logConfigOptions();
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
		
		Logger.log(String.format("Attempting export of %d used model elements.", 
		    diagramPresentationElement.getUsedModelElements().size()));
		
		for (Element element : diagramPresentationElement.getUsedModelElements()) {
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
  	    
		exportElementRecursiveUp(element.getOwner());
		
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
			if (ownedElement instanceof Package
			      || (element instanceof Relationship && ownedElement instanceof Property)) {
				continue;
			}
			
			exportElementRecursive(ownedElement);
		}
	}
	
	public void exportPackage(Element pkg) {
		if (exportedElements.contains(MtipUtils.getId(pkg))) {
			return;
		}
		
		CommonElementsFactory cef = new CommonElementsFactory();
		String packageType = MtipUtils.getPackageType(pkg);
		
		CommonElement commonElement = cef.createElement(packageType, ((NamedElement)pkg).getName(), MtipUtils.getId(pkg));
		commonElement.writeToXML(pkg);
		
		exportedElements.add(MtipUtils.getId(pkg));
	}
	
	public void exportEntity(Element element) {
		if(element == null || exportedElements.contains(MtipUtils.getId(element))) {
			return;
		}
		
		String commonElementType = MtipUtils.getEntityType(element);

		if (commonElementType == null) {
			if (isImplicitlySupported(element)) {
				addImplicitElement(element);
				return;
			}
			
			if (isExplicitlyUnsupported(element)) {
			  return;
			}
			
			unsupportedElements.add(MtipUtils.getId(element));
			Logger.log(String.format("%s type could not be identified. Not currently supported.", MtipUtils.getCameoElementType(element)));
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
		
		unsupportedElements.add(MtipUtils.getId(element));
		Logger.log(String.format("%s is not categorized as an element, relationship, or diagram.", commonElementType));
	}
	
	public void exportElement(Element element, String elementType) {
		if (elementType == null) {
			Logger.log(String.format("Element type not found for %s with id %s", element.getHumanName(), MtipUtils.getId(element)));
			return;
		}
		 
		CommonElement commonElement =  cef.createElement(elementType, CameoUtils.getElementName(element), MtipUtils.getId(element));
		
		if (commonElement == null) {
			return;
		}
		
		commonElement.writeToXML(element);
		exportedElements.add(MtipUtils.getId(element));
		
		exportReferencedElements(element);
	}
	
	public void exportRelationship(Element element, String relationshipType) {
	    if (relationshipType == null) {
	         Logger.log(String.format("Relationship type not found for %s with id %s", element.getHumanName(), MtipUtils.getId(element)));
	         return;
	    }
	    
		CommonRelationship commonRelationship = crf.createElement(relationshipType, CommonRelationship.getName(element), MtipUtils.getId(element));
		
		if (commonRelationship == null) {
			Logger.log(String.format("CommonRelationship not defined in CommonRelationshipFactory. Please check implementation for %s", relationshipType));
			return;
		}
		
		commonRelationship.writeToXML(element);
		exportedElements.add(MtipUtils.getId(element));
		
		// Check if supplier and client are created - important for UML Metaclasses and SysML Profile objects referenced in extension and generalization relationships
		if(commonRelationship.getSupplier() != null && !exportedElements.contains(MtipUtils.getId(commonRelationship.getSupplier()))) {
			exportEntity(commonRelationship.getSupplier());
		}
		
		if(commonRelationship.getClient() != null && !exportedElements.contains(MtipUtils.getId(commonRelationship.getClient()))) {
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
			    || element instanceof Comment
			    || element instanceof TaggedValue
			    || MDCustomizationForSysML.isReferenceProperty(element)) {
			return true;
		}
		
		return false;
	}
	
	public boolean isExplicitlyUnsupported(Element element) {
	  if (element instanceof Artifact || element instanceof Comment) {
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
		implicitElements.add(MtipUtils.getId(element));
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
