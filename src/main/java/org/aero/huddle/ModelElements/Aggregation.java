package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Aggregation extends CommonRelationship{
	public Aggregation(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Aggregation Relationship");
		}
		
		Association association = project.getElementsFactory().createAssociationInstance();
		ModelHelper.setClientElement(association, client);
		ModelHelper.setSupplierElement(association, supplier);
		association.setName(name);
		association.setOwner(owner);
		
		//To create aggregation, the property of the end of the association relationship must be set to the shared aggregation enumeration
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(association);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(association);
		ModelHelper.setNavigable(firstMemberEnd, true);
		ModelHelper.setNavigable(secondMemberEnd, true);
		firstMemberEnd.setAggregation(AggregationKindEnum.SHARED);

		SessionManager.getInstance().closeSession(project);
		return association;
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, Stereotype stereotype) {
		return null;
	}
}
