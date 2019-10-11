package org.aero.huddle.ModelElements;

import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Composition extends CommonRelationship{
	public Composition(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Allocate Relationship");
		}
		
		Association association = project.getElementsFactory().createAssociationInstance();
		ModelHelper.setSupplierElement(association, supplier);
		ModelHelper.setClientElement(association, client);
		association.setName(name);
		association.setOwner(owner);
		
		//To create composition, the property of the end of the association relationship must be set to the composite aggregation enumeration
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(association);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(association);
		ModelHelper.setNavigable(firstMemberEnd, true);
		ModelHelper.setNavigable(secondMemberEnd, true);
		firstMemberEnd.setAggregation(AggregationKindEnum.COMPOSITE);

		SessionManager.getInstance().closeSession(project);
		return association;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}
}
