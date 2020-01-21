package org.aero.huddle.ModelElements;

import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;

public class Abstraction extends CommonRelationship {

	public Abstraction(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Abstraction Relationship");
		}
		
		Element abstraction = project.getElementsFactory().createAbstractionInstance();
		ModelHelper.setClientElement(abstraction, client);
		ModelHelper.setSupplierElement(abstraction, supplier);
		((NamedElement)abstraction).setName(name);
		abstraction.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return abstraction;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Element createElement(Project project, Element owner, Element client, Element supplier, Stereotype stereotype) {
//		Profile mdCustomSysml = StereotypesHelper.getProfile(project, "SysML");
//		Stereotype allocateStereotype = StereotypesHelper.getStereotype(project,  "Allocate", mdCustomSysml);
//		
//		if (!SessionManager.getInstance().isSessionCreated(project)) {
//			SessionManager.getInstance().createSession(project, "Create Allocate Relationship");
//		}
//		
//		Element allocate = project.getElementsFactory().createAbstractionInstance();
//		ModelHelper.setClientElement(allocate, client);
//		ModelHelper.setSupplierElement(allocate, supplier);
//		((NamedElement)allocate).setName(name);
//		allocate.setOwner(owner);
//		
//		StereotypesHelper.addStereotype(allocate, allocateStereotype);
//		
//		SessionManager.getInstance().closeSession(project);
//		return allocate;
//	}

}
