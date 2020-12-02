package org.aero.huddle.ModelElements.InternalBlock;

import java.util.Map;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.properties.Property;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
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
		
		try {
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
		} catch (ClassCastException cce) {
			ImportLog.log(CommonRelationship.INVALID_CLIENT_SUPPLIER_MESSAGE + connector.getHumanName() + " cannot have " + client.getHumanType() + " as client or " + supplier.getHumanType() + " as supplier." );
			connector.dispose();
			return null;
		}
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype nestedConnectorEndStereotype = StereotypesHelper.getStereotype(project, "NestedConnectorEnd", sysmlProfile);
		Stereotype elementPropertyPathStereotype = StereotypesHelper.getStereotype(project, "ElementPropertyPath", sysmlProfile);
		
		ConnectorEnd firstMemberEnd = connector.getEnd().get(0);
		ConnectorEnd secondMemberEnd = connector.getEnd().get(1);
		
		StereotypesHelper.addStereotype(firstMemberEnd, nestedConnectorEndStereotype);
		StereotypesHelper.addStereotype(secondMemberEnd, nestedConnectorEndStereotype);
		
		String supplierPartWithPort = xmlElement.getAttribute(XmlTagConstants.SUPPLIER_PART_WITH_PORT);
		String clientPartWithPort = xmlElement.getAttribute(XmlTagConstants.CLIENT_PART_WITH_PORT);
		
		Element supplierPart = (Element) project.getElementByID(supplierPartWithPort);
		Element clientPart = (Element) project.getElementByID(clientPartWithPort);
		
		secondMemberEnd.setPartWithPort((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)supplierPart);
		firstMemberEnd.setPartWithPort((com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)project.getElementByID(clientPartWithPort));
		
		StereotypesHelper.setStereotypePropertyValue(firstMemberEnd, elementPropertyPathStereotype, "propertyPath", clientPart);
		StereotypesHelper.setStereotypePropertyValue(secondMemberEnd, elementPropertyPathStereotype, "propertyPath", supplierPart);
		
		
		((NamedElement)connector).setName(name);
		try {
			if(owner == null || !(SysMLProfile.isBlock(owner))) {
				owner = CameoUtils.findNearestBlock(project, supplier);
				if(owner == null) {
					String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". Element could not be placed in model.";
					ImportLog.log(logMessage);
					connector.dispose();
					return null;
				}
				connector.setOwner(owner);
			} else {
				connector.setOwner(owner);
			}
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent must be block " + name + " with id " + EAID + ". Element could not be placed in model.";
			ImportLog.log(logMessage);
			connector.dispose();
			return null;
		}
		
		
		SessionManager.getInstance().closeSession(project);
		return connector;
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for connector with id: " + modelElement.getEAID());
		String supplierPartWithPortID = modelElement.getAttribute(XmlTagConstants.SUPPLIER_CONNECTOR_END_TAG);
		String clientPartWithPortID = modelElement.getAttribute(XmlTagConstants.CLIENT_CONNECTOR_END_TAG);
		
		Element supplierPartWithPort = ImportXmlSysml.getOrBuildElement(project, parsedXML, supplierPartWithPortID);
		Element clientPartWithPort = ImportXmlSysml.getOrBuildElement(project, parsedXML, clientPartWithPortID);
		
		modelElement.addAttribute(XmlTagConstants.SUPPLIER_PART_WITH_PORT, supplierPartWithPort.getLocalID());
		modelElement.addAttribute(XmlTagConstants.CLIENT_PART_WITH_PORT, clientPartWithPort.getLocalID());
		
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
