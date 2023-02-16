/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.Map;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdassociationclasses.AssociationClass;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class AssociationBlock extends CommonElement {
	protected Element supplier;
	protected Element client;
	
	public AssociationBlock(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ASSOCIATIONBLOCK;
		this.xmlConstant = XmlTagConstants.ASSOCIATIONBLOCK;
		this.sysmlElement = f.createAssociationClassInstance();
		
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		AssociationClass associationClass = (AssociationClass) super.createElement(project, owner, xmlElement);
		
		if(xmlElement.hasSupplierElement()) {
			this.supplier = xmlElement.getSupplierElement();
		}
		if(xmlElement.hasClientElement()) {
			this.client = xmlElement.getClientElement();
		}
		
		// Owning package must be package where client and supplier are located. EA Imports have no hasParent.
		// Owner will default to main model but needs to be the lower level package.
		if(supplier != null) {
			if(owner.equals(project.getPrimaryModel())) {
				owner = CameoUtils.findNearestPackage(project, supplier);
			}
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
		return (Element)associationClass;
	}
	
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasClient()) {
			String clientID = modelElement.getClient();
			if(parsedXML.containsKey(clientID)) {
				Element client = ImportXmlSysml.getOrBuildElement(project, parsedXML, clientID);
				modelElement.setClientElement(client);
				modelElement.addAttribute("client", client.getID());
			} else {
				CameoUtils.logGUI("No data tag found for client id: " + clientID);
			}
		} else {
			CameoUtils.logGUI("No client tag/id found in element's data tag.");
		}
		if(modelElement.hasSupplier()) {
			String supplierID = modelElement.getSupplier();
			if(parsedXML.containsKey(supplierID)) {
				Element supplier = ImportXmlSysml.getOrBuildElement(project, parsedXML, modelElement.getSupplier());
				modelElement.setSupplierElement(supplier);
				modelElement.addAttribute("supplier",  supplier.getID());
			} else {
				CameoUtils.logGUI("No data tag found for supplier id: " + supplierID);
			}
		} else {
			CameoUtils.logGUI("No supplier tag/id found in element's data tag.");
		}
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());		
		
		supplier = ModelHelper.getSupplierElement(element);
		if(supplier != null) {
			org.w3c.dom.Element supplierRel = createRel(xmlDoc, this.supplier, XmlTagConstants.SUPPLIER);
			relationships.appendChild(supplierRel);
		} else {
			CameoUtils.logGUI("No supplier element found.\n");
		
		}
		
		client = ModelHelper.getClientElement(element);
		if(client != null) {
			org.w3c.dom.Element clientRel = createRel(xmlDoc, this.client, XmlTagConstants.CLIENT);
			relationships.appendChild(clientRel);
		} else {
			CameoUtils.logGUI("No client element found.\n");
		}
		
		return data;
	}
}
