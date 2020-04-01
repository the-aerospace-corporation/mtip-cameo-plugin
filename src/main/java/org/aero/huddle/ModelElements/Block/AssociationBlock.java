package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class AssociationBlock extends CommonElement {

	public AssociationBlock(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Association Block Relationship");
		}
		AssociationClass associationClass = project.getElementsFactory().createAssociationClassInstance();
		associationClass.setOwner(owner);
		
		String supplierID = xmlElement.getSupplier();
		String clientID = xmlElement.getClient();
		
		Element supplier = null;
		Element client = null;
		
		if(!supplierID.equals("")) {
			supplier = (Element) project.getElementByID(supplierID);
		}
		if(!clientID.equals("")) {
			client = (Element)project.getElementByID(clientID);
		}
		
		if(client != null && supplier != null) {
			ModelHelper.setSupplierElement(associationClass, supplier);
			ModelHelper.setClientElement(associationClass, client);
			ModelHelper.setNavigable(ModelHelper.getFirstMemberEnd(associationClass), true);
			ModelHelper.setNavigable(ModelHelper.getSecondMemberEnd(associationClass), true);
		} else {
			CameoUtils.logGUI("Supplier or client was not set. Association block " + xmlElement.getAttribute("name") + " not created.");
		}
		
		StereotypesHelper.addStereotype(associationClass, SysMLProfile.getInstance(project).getBlock());
		SessionManager.getInstance().closeSession(project);
		return (Element)associationClass;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.ASSOCIATIONBLOCK));
		data.appendChild(type);
		
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());		
		
		Element supplier = ModelHelper.getSupplierElement(element);
		org.w3c.dom.Element supplierID = xmlDoc.createElement("supplier_id");
		supplierID.appendChild(xmlDoc.createTextNode(supplier.getLocalID()));
		relationships.appendChild(supplierID);	
		
		Element client = ModelHelper.getClientElement(element);
		org.w3c.dom.Element clientID = xmlDoc.createElement("client_id");
		clientID.appendChild(xmlDoc.createTextNode(client.getLocalID()));
		relationships.appendChild(clientID);	
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
