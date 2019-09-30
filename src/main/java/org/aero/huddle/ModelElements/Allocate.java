package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Allocate extends CommonRelationship{
	public Allocate(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		Profile mdCustomSysml = StereotypesHelper.getProfile(project, "SysML");
		Stereotype allocateStereotype = StereotypesHelper.getStereotype(project,  "Allocate", mdCustomSysml);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Allocate Relationship");
		}
		
		Element allocate = project.getElementsFactory().createAbstractionInstance();
		ModelHelper.setClientElement(allocate, client);
		ModelHelper.setSupplierElement(allocate, supplier);
		((NamedElement)allocate).setName(name);
		allocate.setOwner(owner);
		
		StereotypesHelper.addStereotype(allocate, allocateStereotype);
		
		SessionManager.getInstance().closeSession(project);
		return allocate;
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, Stereotype stereotype) {
		return null;
	}
}