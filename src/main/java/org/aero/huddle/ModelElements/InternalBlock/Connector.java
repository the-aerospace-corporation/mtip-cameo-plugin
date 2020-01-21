package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

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
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Connector Relation");
		}
		ElementsFactory ef = project.getElementsFactory();
		com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector connector = ef.createConnectorInstance();
		
		
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
		SessionManager.getInstance().closeSession(project);
		return connector;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.CONNECTOR));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
