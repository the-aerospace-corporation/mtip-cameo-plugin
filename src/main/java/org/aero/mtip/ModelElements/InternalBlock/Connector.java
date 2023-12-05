/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
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
		this.metamodelConstant = SysmlConstants.CONNECTOR;
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
			ImportLog.log("Invalid supplier/client for connector with id: " + this.EAID + ". Supplier/client must be ConnectableElements.");
			connector = null;
			element.dispose();
			return connector;
		}
		

		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype nestedConnectorEndStereotype = StereotypesHelper.getStereotype(project, "NestedConnectorEnd", sysmlProfile);
		Stereotype elementPropertyPathStereotype = StereotypesHelper.getStereotype(project, "ElementPropertyPath", sysmlProfile);
		
//		if(supplier instanceof ConnectableElement && client instanceof ConnectableElement) {
//			connector.getEnd().get(0).setRole((ConnectableElement) supplier);
//			connector.getEnd().get(1).setRole((ConnectableElement) client);
//		} else {
//			ImportLog.log("Unable to create connector with id: " + this.EAID + ". Supplier or client not a ConnectableElement (Part, port, etc.)");
//			sysmlElement.dispose();
//			return null;
//		}
		
		ConnectorEnd firstMemberEnd = connector.getEnd().get(0);
		ConnectorEnd secondMemberEnd = connector.getEnd().get(1);
		
		CameoUtils.logGUI("Connector.java: FirstMemberEnd: "+firstMemberEnd);
		CameoUtils.logGUI("Connetor.java: SecondMemberEnd: "+secondMemberEnd);
		
		Element supplierPart = (Element) project.getElementByID(ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.SUPPLIER_PART_WITH_PORT)));
		if(supplierPart != null) {
			CameoUtils.logGUI("Supplier part found with id: " + supplierPart.getID());
			firstMemberEnd.setPartWithPort((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)supplierPart);
			firstMemberEnd.setRole((ConnectableElement) supplier);
			StereotypesHelper.addStereotype(firstMemberEnd, nestedConnectorEndStereotype);
			StereotypesHelper.setStereotypePropertyValue(firstMemberEnd, elementPropertyPathStereotype, "propertyPath", supplierPart);
		} else {
			CameoUtils.logGUI("Supplier port not from part property.");
			firstMemberEnd.setRole((ConnectableElement) supplier);
		}
		
		Element clientPart = (Element) project.getElementByID(ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.CLIENT_PART_WITH_PORT)));
		if(clientPart != null) {
			CameoUtils.logGUI("Client part found with id: " + clientPart.getID());
			secondMemberEnd.setPartWithPort((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)clientPart);
			secondMemberEnd.setRole(((List<ConnectorEnd>) ((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property) clientPart).get_connectorEndOfPartWithPort()).get(0).getRole());
			StereotypesHelper.addStereotype(secondMemberEnd, nestedConnectorEndStereotype);
			StereotypesHelper.setStereotypePropertyValue(secondMemberEnd, elementPropertyPathStereotype, "propertyPath", clientPart);
		} else {
			CameoUtils.logGUI("Client port not from part property.");
			secondMemberEnd.setRole((ConnectableElement)client);
		}		
		
		Element typeElement = (Element) project.getElementByID(ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.TYPED_BY)));
		if(typeElement != null) {
			try {
				connector.setType((Association) typeElement);
				CameoUtils.logGUI("Connector type set to element with id " + typeElement.getID());
			} catch(ClassCastException cce) {
				CameoUtils.logGUI("Connector type is not an association. Type not set for connector with id " + this.EAID);
			}
		}
		try {
			setOwner(project, owner);
		} catch(IllegalArgumentException iae) {
			connector = null;
			element.dispose();
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
	public void setOwner(Project project, Element owner) {
		if(owner == null) {
			String logMessage = "Owner is null. Could not add connector with id: " + this.EAID + " to the model.";
			ImportLog.log(logMessage);
			throw new IllegalArgumentException("Invalid Parent");
		}
		try {
			if(!(SysMLProfile.isBlock(owner))) {
				owner = CameoUtils.findNearestBlock(project, owner);
				if(owner == null) {
					String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". No parents found in ancestors. Element could not be placed in model.";
					ImportLog.log(logMessage);

				}
				element.setOwner(owner);
			} else {
				element.setOwner(owner);
			}
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". Element could not be placed in model.";
			ImportLog.log(logMessage);
			throw new IllegalArgumentException("Invalid Parent");
		}
	}
	
	@Override
	public Element getSupplier(Element element) {
		if(element instanceof com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) {
			List<ConnectorEnd> connectorEnds = ((com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector)element).getEnd();
			if(connectorEnds.size() > 1) {
				return connectorEnds.get(0).getRole();					
			}
		}
		return null;
	}
	@Override
	public Element getClient(Element element) {
		if(element instanceof com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) {
			List<ConnectorEnd> connectorEnds = ((com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector)element).getEnd();
			if(connectorEnds.size() > 1) {
				return connectorEnds.get(1).getRole();
			}
		}
		return null;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for connector with id: " + modelElement.getEAID());
		String supplierPartWithPortID = modelElement.getAttribute(XmlTagConstants.SUPPLIER_PART_WITH_PORT);
		String clientPartWithPortID = modelElement.getAttribute(XmlTagConstants.CLIENT_PART_WITH_PORT);
		String typedByID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
		
		if(supplierPartWithPortID != null) {
			Element supplierPartWithPort = ImportXmlSysml.getOrBuildElement(project, parsedXML, supplierPartWithPortID);
		}
		
		if(clientPartWithPortID != null) {
			Element clientPartWithPort = ImportXmlSysml.getOrBuildElement(project, parsedXML, clientPartWithPortID);
		}
		
		if(typedByID != null) {
			Element typedBy = ImportXmlSysml.getOrBuildElement(project, parsedXML, typedByID);
		}
	}
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector connector = (com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector) element;
		Element supplierPart = connector.getEnd().get(0).getPartWithPort();
		Element clientPart = connector.getEnd().get(1).getPartWithPort();
		Association type = connector.getType();

		if(supplierPart != null) {
			org.w3c.dom.Element supplierPartWithPort = createRel(xmlDoc, supplierPart, XmlTagConstants.SUPPLIER_PART_WITH_PORT);
			relationships.appendChild(supplierPartWithPort);
		}
		
		if(clientPart != null) {
			org.w3c.dom.Element clientPartWithPort = createRel(xmlDoc, clientPart, XmlTagConstants.CLIENT_PART_WITH_PORT);
			relationships.appendChild(clientPartWithPort);
		}
		if(type != null) {
			org.w3c.dom.Element typedByTag = createRel(xmlDoc, type, XmlTagConstants.TYPED_BY);
			relationships.appendChild(typedByTag);
		}
		return data;
	}
}
