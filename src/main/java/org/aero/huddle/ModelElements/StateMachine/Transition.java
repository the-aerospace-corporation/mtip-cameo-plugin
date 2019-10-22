package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Transition extends CommonRelationship {
	public Transition(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Transition Relationship");
		}
		
		Element copy = project.getElementsFactory().createTransitionInstance();
		
		ModelHelper.setClientElement(copy, client);
		ModelHelper.setSupplierElement(copy, supplier);
		((NamedElement)copy).setName(name);
		copy.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return copy;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}
}
