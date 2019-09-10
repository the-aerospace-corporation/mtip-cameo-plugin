package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Association extends CommonRelationship{
	public Association(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Association Relationship");
		}
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association association = project.getElementsFactory().createAssociationInstance();
		ModelHelper.setClientElement(association, client);
		ModelHelper.setSupplierElement(association, supplier);
		association.setName(name);
		association.setOwner(owner);
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(association);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(association);
		ModelHelper.setNavigable(firstMemberEnd, true);
		ModelHelper.setNavigable(secondMemberEnd, true);
		
		SessionManager.getInstance().closeSession(project);
		return association;
	}
}
