package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.impl.ElementsFactory;

public class Generalization extends CommonRelationship {
	public Generalization(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CLASS;
		this.xmlConstant = XmlTagConstants.CLASS;
		this.sysmlElement = f.createGeneralizationInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element supplier, Element client, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Generalization Element");
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
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.GENERALIZATION));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
