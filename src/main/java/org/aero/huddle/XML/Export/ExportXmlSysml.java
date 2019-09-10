package org.aero.huddle.XML.Export;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

public class ExportXmlSysml {
	public static void buildXML(Document doc) {
		Project project = Application.getInstance().getProject();
		Element owner = project.getPrimaryModel();
	}
	
	public void walkPackage(Package pack, Project project) {
		Collection<Element> elementsInPackage = new ArrayList<Element> ();
		Collection<Package> packagesInPackage = new ArrayList<Package> ();
		
		boolean noPackages = false;
		boolean noElements = false;
		boolean hasRequirement = false;

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
				walkPackage(nextPackage, project);
			}
		}
		
		if(!noElements) {
			for(Element element : elementsInPackage) {
				//export package
				walkElement(element, project);
			}
		}
	}
	
	public void walkElement(Element elem, Project project) {
		
	}
}
