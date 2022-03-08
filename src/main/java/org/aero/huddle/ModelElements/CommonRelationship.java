/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements;

import java.util.Map;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DirectedRelationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class CommonRelationship extends CommonElement {
	protected Element supplier = null;
	protected Element client = null;
	protected String supplierClientType;
	
	public static String INVALID_CLIENT_SUPPLIER_MESSAGE = "Invalid Client or Supplier - Not SysML Compliant";
	
	public CommonRelationship(String name, String EAID) {
		super(name, EAID);
		this.f = Application.getInstance().getProject().getElementsFactory();
		
	}
	
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		this.supplier = supplier;
		this.client = client;
		try {
			super.createElement(project, owner, xmlElement);
			setSupplier();
			setClient();
			return sysmlElement;
		}  catch(ClassCastException cce) {
			String logMessage = "Invalid client/supplier for relationship " + name + " with id " + EAID + ".";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			sysmlElement.dispose();
			return null;
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent invalid for element " + name + " with id " + EAID + ". Supplier and client are also invalid parents. Element could not be placed in model.";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			sysmlElement.dispose();
			return null;
		}
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		if(this.supplier != null && this.client != null) {
			if(owner != null) {
				try {
					sysmlElement.setOwner(owner);
				} catch(IllegalArgumentException iaeOwner) {
					CameoUtils.logGUI("No owner found for " + name + " with id " + EAID + ". Attempting to set supplier or client as parent.");
				}
			}
			else {
				try {
					sysmlElement.setOwner(supplier);
				} catch(IllegalArgumentException iae) {
					try {
						sysmlElement.setOwner(client);
					} catch(IllegalArgumentException iae2) {
						String logMessage = "Invalid parent. No parent provided and supplier and client invalid parent for " + name + " with id " + EAID + ". Relationship could not be placed in model.";
						CameoUtils.logGUI(logMessage);
						ImportLog.log(logMessage);
						throw new IllegalArgumentException("Invalid Parent");
					}
				}
			}
		}
	}

	public org.w3c.dom.Element createBaseXML(Element element, Project project, Document xmlDoc) {
		return null;
	}
	
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	public org.w3c.dom.Element getAttributes(NodeList dataNodes) {
		org.w3c.dom.Element attributes = null;
		for(int i = 0; i < dataNodes.getLength(); i++) {
			Node dataNode = dataNodes.item(i);
			if(dataNode.getNodeType() == Node.ELEMENT_NODE) {
				if(dataNode.getNodeName().equals(XmlTagConstants.ATTRIBUTES)) {
					attributes = (org.w3c.dom.Element) dataNode;
				}
			}
		}
		return attributes;
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		if(relationships == null) {
			relationships = createRelationships(xmlDoc, element);
		}
		
		getSupplier(element);
		getClient(element);
		
		if(supplier != null) {
			org.w3c.dom.Element supplierRel = createRel(xmlDoc, this.supplier, XmlTagConstants.SUPPLIER);
			relationships.appendChild(supplierRel);
		} else {
			CameoUtils.logGUI("No supplier element found.\n");
		}
		if(client != null) {
			org.w3c.dom.Element clientRel = createRel(xmlDoc, this.client, XmlTagConstants.CLIENT);
			relationships.appendChild(clientRel);
		} else {
			CameoUtils.logGUI("No client element found.\n");
		}
		
		return data;
	}
	
	public Element getSupplier() {
		return this.supplier;
	}
	
	public Element getClient() {
		return this.client;
	}
	
	public void getSupplier(Element element) {
		this.supplier = ModelHelper.getSupplierElement(element);
		
	}
	public void getClient(Element element) {
		this.client = ModelHelper.getClientElement(element);
	}
	
	public void setSupplier() {
		if(sysmlElement instanceof DirectedRelationship) {
			DirectedRelationship directedRelationship = (DirectedRelationship)sysmlElement;
			directedRelationship.getSource().add(supplier);
		} else {
			ModelHelper.setSupplierElement(sysmlElement, supplier);
		}
	}
	
	public void setClient() {
		if(sysmlElement instanceof DirectedRelationship) {
			DirectedRelationship directedRelationship = (DirectedRelationship)sysmlElement;
			directedRelationship.getTarget().add(client);
		}  else {
			ModelHelper.setClientElement(sysmlElement, client);
		}
	}
}
