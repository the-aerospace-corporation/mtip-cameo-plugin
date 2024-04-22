/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.util.HashMap;

import javax.annotation.CheckForNull;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DirectedRelationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;

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
			return element;
		}  catch(ClassCastException cce) {
			String logMessage = "Invalid client/supplier for relationship " + name + " with id " + EAID + ".";
			CameoUtils.logGui(logMessage);
			Logger.log(logMessage);
			element.dispose();
			return null;
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent invalid for element " + name + " with id " + EAID + ". Supplier and client are also invalid parents. Element could not be placed in model.";
			CameoUtils.logGui(logMessage);
			Logger.log(logMessage);
			element.dispose();
			return null;
		}
	}
	
	@Override
	public void setOwner(Element owner) {
		if (owner != null) {
			try {
				element.setOwner(owner);
				return;
			} catch(IllegalArgumentException iaeOwner) {
				Logger.log(String.format("Owner of type %s invalid for %s.", owner.getHumanType(), element.getHumanType()));
			}
		}
		
		Logger.log(String.format("...Attempting to set supplier or client as owner for %s.", element.getHumanType()));
			
		if(supplier != null) {
			try {
				element.setOwner(supplier);
				return;
			} catch(IllegalArgumentException iaeOwner) {
				Logger.log(String.format("...Supplier of type %s invalid for %s.", owner.getHumanType(), element.getHumanType()));
			}
		}
		
		if(client != null) {
			try {
				element.setOwner(supplier);
				return;
			} catch(IllegalArgumentException iaeOwner) {
				Logger.log(String.format("...Client of type %s invalid for %s.", owner.getHumanType(), element.getHumanType()));
			}
		}
	}

	public org.w3c.dom.Element createBaseXML(Element element, Project project, Document xmlDoc) {
		return null;
	}
	
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		
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
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeSupplier(relationships, element);
		writeClient(relationships, element);
		
		writeMultiplicity(attributes, getSupplierMultiplicity(element));
		writeMultiplicity(attributes, getClientMultiplicity(element));
		
		return data;
	}
	
	public void writeSupplier(org.w3c.dom.Element relationships, Element element) {
		Element supplier = getSupplier(element);
		
		if(supplier == null) {
			Logger.log(String.format("No supplier element found for relationship of type %s with id %s.", element.getHumanType(), element.getID()));
			return;
		}
		
		org.w3c.dom.Element supplierTag = XmlWriter.createMtipRelationship(supplier, XmlTagConstants.SUPPLIER);
		XmlWriter.add(relationships, supplierTag);
	}
	
	public void writeClient(org.w3c.dom.Element relationships, Element element) {
		Element client = getClient(element);
		
		if(client == null) {
			Logger.log(String.format("No client element found for relationship of type %s with id %s.", element.getHumanType(), element.getID()));
			return;
		}
		
		org.w3c.dom.Element clientTag = XmlWriter.createMtipRelationship(client, XmlTagConstants.CLIENT);
		XmlWriter.add(relationships, clientTag);
	}
	
	public void writeMultiplicity(org.w3c.dom.Element attributes, String multiplicity) {
		if(multiplicity == null || multiplicity.trim().isEmpty()) {
			return;
		}
		
		org.w3c.dom.Element multiplicityTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.SUPPLIER_MULTIPLICITY, multiplicity);
		XmlWriter.add(attributes, multiplicityTag);
	}
	
	@CheckForNull
	public Element getSupplier() {
		return this.supplier;
	}
	
	@CheckForNull
	public Element getClient() {
		return this.client;
	}
	
	public Element getSupplier(Element element) {
		return ModelHelper.getSupplierElement(element);
		
	}
	public Element getClient(Element element) {
		return ModelHelper.getClientElement(element);
	}
	
	public String getSupplierMultiplicity(Element element) {
		return null;
	}
	
	public String getClientMultiplicity(Element element) {
		return null;
	}
	
	public void setSupplier() {
		if(element instanceof DirectedRelationship) {
			DirectedRelationship directedRelationship = (DirectedRelationship)element;
			directedRelationship.getSource().add(supplier);
		} else {
			ModelHelper.setSupplierElement(element, supplier);
		}
	}
	
	public void setClient() {
		if(element instanceof DirectedRelationship) {
			DirectedRelationship directedRelationship = (DirectedRelationship)element;
			directedRelationship.getTarget().add(client);
		}  else {
			ModelHelper.setClientElement(element, client);
		}
	}
	
	public static String getName(Element element) {
		if (element instanceof NamedElement) {
			return ((NamedElement)element).getName();
		}
		
		return element.getHumanName();
	}
}
