package org.aero.huddle.XML.Export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.aero.huddle.util.CameoUtils;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ExportXmlSysml {
	public static void buildXML(Document doc) {
		Project project = Application.getInstance().getProject();
		Package primary = project.getPrimaryModel();
		exportPackage(primary, project);
	}
	
	public static void exportPackage(Package pack, Project project) {
		//Write Package to xml here so parent is written before child
		
		//Look for child packages and child elements to recursively export
		Collection<Element> elementsInPackage = new ArrayList<Element> ();
		Collection<Package> packagesInPackage = new ArrayList<Package> ();
		
		boolean noPackages = false;
		boolean noElements = false;

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
				if(packageStereotypes == null || !packageStereotypes.contains(auxiliary)) {
					if(!nextPackage.getHumanName().equals("Package Unit Imports")) {
						CameoUtils.logGUI("Package with name " + nextPackage.getHumanName());
						exportPackage(nextPackage, project);
					}					
				}
			}
		}
		
		if(!noElements) {
			for(Element element : elementsInPackage) {
				//export elements that are not packages
				String elementName = element.getHumanName();
				if(!elementName.contains("Package")) {
					exportElement(element, project);
				}
			}
		}
	}
	
	public static void exportElement(Element element, Project project) {
		//Write to XML here so parents appear before children
		
		//Find any child elements and recursively export		
		boolean noElements = false;
		Collection<Element> ownedElements = new ArrayList<Element> ();
		try {
			ownedElements = element.getOwnedElement();
		} catch (NullPointerException npe) {
			noElements = true;
		}
		
		if(!noElements) {
			for(Element ownedElement : ownedElements) {
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogOwner(), "Looking for children elements of element " + element.getHumanName());
				CameoUtils.logGUI("Found element: " + ownedElement.getHumanName());
				// Check that ownedElement is not a package, otherwise will cause infinite loop
				String elementName = ownedElement.getHumanName();
				if(!elementName.contains("Package")) {
					exportElement(ownedElement, project);
				}
			}
		}
		
		
	}
}
