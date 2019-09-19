package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class Connector extends CommonRelationship {
	public Connector(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Connector Relation");
		}
		ElementsFactory ef = project.getElementsFactory();
		Element connector = ef.createConnectorInstance();
		if(client != null) {
			ModelHelper.setClientElement(connector, client);
		} else {
			// log client null creating connector with EAID: #
		}
		if(supplier != null) {
			ModelHelper.setSupplierElement(connector, supplier);
		} else {
			// log supplier null creating connector with EAID: #
		}
		
		((NamedElement)connector).setName(name);
		connector.setOwner(owner);
		
		return connector;
	}
}
