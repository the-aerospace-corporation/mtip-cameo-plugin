package org.aero.huddle.ModelElements;

import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.impl.ElementsFactory;

public class Generalization extends CommonRelationship {
	public Generalization(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element supplier, Element client) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Port Element");
		}
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization generalization = f.createGeneralizationInstance();
		
		if(supplier != null) {
			generalization.setOwner(supplier);
		} else {
			generalization.setOwner(project.getPrimaryModel());
		}
		
		ModelHelper.setSupplierElement(generalization, supplier);
		ModelHelper.setClientElement(generalization, client);
		
		SessionManager.getInstance().closeSession(project);
		return generalization;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}
}
