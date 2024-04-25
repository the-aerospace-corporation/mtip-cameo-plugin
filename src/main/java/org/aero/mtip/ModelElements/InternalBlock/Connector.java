/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import java.util.HashMap;
import java.util.List;
import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectableElement;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Connector extends CommonRelationship {
	public Connector(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CONNECTOR;
		this.sysmlConstant = SysmlConstants.CONNECTOR;
		this.element = f.createConnectorInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector connector = (com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) element;
		
		// Cameo considers client as the second connector end [ends.get(1)] and supplier as first. Must reverse to fit supplier/client defined in Huddle.
		try {
			ModelHelper.setClientElement(element, supplier);
			ModelHelper.setSupplierElement(element, client);
			
		}catch(ClassCastException cce) {
			Logger.log("Invalid supplier/client for connector with id: " + this.EAID + ". Supplier/client must be ConnectableElements.");
			element.dispose();
			return null;
		}
		

		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype nestedConnectorEndStereotype = StereotypesHelper.getStereotype(project, "NestedConnectorEnd", sysmlProfile);
		Stereotype elementPropertyPathStereotype = StereotypesHelper.getStereotype(project, "ElementPropertyPath", sysmlProfile);
		
//		if(supplier instanceof ConnectableElement && client instanceof ConnectableElement) {
//			connector.getEnd().get(0).setRole((ConnectableElement) supplier);
//			connector.getEnd().get(1).setRole((ConnectableElement) client);
//		} else {
//			Logger.log("Unable to create connector with id: " + this.EAID + ". Supplier or client not a ConnectableElement (Part, port, etc.)");
//			sysmlElement.dispose();
//			return null;
//		}
		
		ConnectorEnd firstMemberEnd = connector.getEnd().get(0);
		ConnectorEnd secondMemberEnd = connector.getEnd().get(1);
		
		Element supplierPart = (Element) project.getElementByID(Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.SUPPLIER_PART_WITH_PORT)));
		if(supplierPart != null) {
			CameoUtils.logGui("Supplier part found with id: " + supplierPart.getID());
			firstMemberEnd.setPartWithPort((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)supplierPart);
			firstMemberEnd.setRole((ConnectableElement) supplier);
			StereotypesHelper.addStereotype(firstMemberEnd, nestedConnectorEndStereotype);
			StereotypesHelper.setStereotypePropertyValue(firstMemberEnd, elementPropertyPathStereotype, "propertyPath", supplierPart);
		} else {
			CameoUtils.logGui("Supplier port not from part property.");
			firstMemberEnd.setRole((ConnectableElement) supplier);
		}
		
		Element clientPart = (Element) project.getElementByID(Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.CLIENT_PART_WITH_PORT)));
		if(clientPart != null) {
			CameoUtils.logGui("Client part found with id: " + clientPart.getID());
			secondMemberEnd.setPartWithPort((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)clientPart);
			secondMemberEnd.setRole(((List<ConnectorEnd>) ((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property) clientPart).get_connectorEndOfPartWithPort()).get(0).getRole());
			StereotypesHelper.addStereotype(secondMemberEnd, nestedConnectorEndStereotype);
			StereotypesHelper.setStereotypePropertyValue(secondMemberEnd, elementPropertyPathStereotype, "propertyPath", clientPart);
		} else {
			CameoUtils.logGui("Client port not from part property.");
			secondMemberEnd.setRole((ConnectableElement)client);
		}		
		
		Element typeElement = (Element) project.getElementByID(Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.TYPED_BY)));
		if(typeElement != null) {
			try {
				connector.setType((Association) typeElement);
				CameoUtils.logGui("Connector type set to element with id " + typeElement.getID());
			} catch(ClassCastException cce) {
				CameoUtils.logGui("Connector type is not an association. Type not set for connector with id " + this.EAID);
			}
		}
		
		return connector;
	}
	@Override
	public void setSupplier() {
		
	}
	
	@Override
	public void setClient() {
		
	}
	
	@Override
	public void setOwner(Element owner) {
		if(owner == null || !(SysML.isBlock(owner))) {
			owner = CameoUtils.findNearestBlock(project, owner);
		}
		
		if(owner == null) {
			Logger.log(String.format("Invalid parent. Parent must be block %s with id %s. No parents found in ancestors. Element could not be placed in model.", name, EAID));
			return;
		}
		
		try {
			element.setOwner(owner);
		} catch(IllegalArgumentException iae) {
			Logger.log(String.format("Invalid parent. Parent must be block %s with id %s. Element could not be placed in model.", name, EAID));
		}
	}
	
	@Override
	public void getSupplier(Element element) {
		if(element instanceof com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) {
			List<ConnectorEnd> connectorEnds = ((com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector)element).getEnd();
			if(connectorEnds.size() > 1) {
				this.supplier = connectorEnds.get(0).getRole();					
			}
		}
	}
	@Override
	public void getClient(Element element) {
		if(element instanceof com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) {
			List<ConnectorEnd> connectorEnds = ((com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector)element).getEnd();
			if(connectorEnds.size() > 1) {
				this.client = connectorEnds.get(1).getRole();
			}
		}
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGui("\t...Creating dependent elements for connector with id: " + modelElement.getImportId());
		String supplierPartWithPortID = modelElement.getAttribute(XmlTagConstants.SUPPLIER_PART_WITH_PORT);
		String clientPartWithPortID = modelElement.getAttribute(XmlTagConstants.CLIENT_PART_WITH_PORT);
		String typedByID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
		
		if(supplierPartWithPortID != null) {
			Element supplierPartWithPort = Importer.getInstance().buildElement(parsedXML, parsedXML.get(supplierPartWithPortID));
		}
		
		if(clientPartWithPortID != null) {
			Element clientPartWithPort = Importer.getInstance().buildElement(parsedXML, parsedXML.get(clientPartWithPortID));
		}
		
		if(typedByID != null) {
			Element typedBy = Importer.getInstance().buildElement(parsedXML, parsedXML.get(typedByID));
		}
	}
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeSupplierPartWithPort(relationships, element);
		writeClientPartWithPort(relationships, element);
		writeConnectorType(relationships, element);

		return data;
	}
	
	private void writeSupplierPartWithPort(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector connector = (com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) element;
		Element supplierPart = connector.getEnd().get(0).getPartWithPort();
		
		if(supplierPart == null) {
			return;
		}
		
		org.w3c.dom.Element supplierPartWithPortTag = XmlWriter.createMtipRelationship(supplierPart, XmlTagConstants.SUPPLIER_PART_WITH_PORT);
		XmlWriter.add(relationships, supplierPartWithPortTag);
	}
	
	private void writeClientPartWithPort(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector connector = (com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) element;
		Element clientPart = connector.getEnd().get(1).getPartWithPort();
		
		if(clientPart == null) {
			return;
		}
		
		org.w3c.dom.Element clientPartWithPortTag = XmlWriter.createMtipRelationship(clientPart, XmlTagConstants.CLIENT_PART_WITH_PORT);
		XmlWriter.add(relationships, clientPartWithPortTag);
	}
	
	private void writeConnectorType(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector connector = (com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) element;
		Association type = connector.getType();

		if(type == null) {
			return;
		}
		
		org.w3c.dom.Element typedByTag = XmlWriter.createMtipRelationship(type, XmlTagConstants.TYPED_BY);
		XmlWriter.add(relationships, typedByTag);
	}
}
