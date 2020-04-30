package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Link extends CommonRelationship {
	public Link(String name, String EAID) {
		super(name, EAID);
	}
	/**
		CURRENTLY NOT SUPPORTED - MAY BE DEPRECATED
	*/
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Allocate Relationship");
		}
		
		Element allocate = project.getElementsFactory().createAssociationInstance();
		ModelHelper.setClientElement(allocate, client);
		ModelHelper.setSupplierElement(allocate, supplier);
		((NamedElement)allocate).setName(name);
		allocate.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return allocate;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
	}
}
