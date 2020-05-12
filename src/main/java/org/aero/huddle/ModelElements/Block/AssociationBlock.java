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
		Element supplier = null;
		Element client = null;
		
		if(xmlElement.hasSupplierElement()) {
			supplier = xmlElement.getSupplierElement();
		}
		if(xmlElement.hasClientElement()) {
			client = xmlElement.getClientElement();
		}
		
		// Owning package must be package where client and supplier are located. EA Imports have no hasParent.
		// Owner will default to main model but needs to be the lower level package.
		if(supplier != null) {
			if(owner.equals(project.getPrimaryModel())) {
				owner = CameoUtils.findNearestPackage(project, supplier);
			}
		}

		associationClass.setOwner(owner);
		associationClass.setName(name);
		
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
		org.w3c.dom.Element supplierID = xmlDoc.createElement(XmlTagConstants.SUPPLIER);
		supplierID.appendChild(xmlDoc.createTextNode(supplier.getLocalID()));
		relationships.appendChild(supplierID);	
		
		Element client = ModelHelper.getClientElement(element);
		org.w3c.dom.Element clientID = xmlDoc.createElement(XmlTagConstants.CLIENT);
		clientID.appendChild(xmlDoc.createTextNode(client.getLocalID()));
		relationships.appendChild(clientID);	
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
