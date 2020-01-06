package org.aero.huddle.ModelElements;

import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Dependency extends CommonRelationship {

	public Dependency(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Dependency Relationship");
		}
		
		com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency dependency = project.getElementsFactory().createDependencyInstance();
		ModelHelper.setClientElement(dependency, client);
		ModelHelper.setSupplierElement(dependency, supplier);
		dependency.setName(name);
		dependency.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return dependency;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.DEPENDENCY));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
