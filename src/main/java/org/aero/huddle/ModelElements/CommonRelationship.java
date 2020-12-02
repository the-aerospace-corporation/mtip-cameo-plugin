package org.aero.huddle.ModelElements;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DirectedRelationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.Connector;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectorEnd;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Extension;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition;
import com.nomagic.uml2.impl.ElementsFactory;

public abstract class CommonRelationship {
	protected String name;
	protected String EAID;
	protected String creationType;
	protected String xmlConstant;
	protected String sysmlConstant;
	protected Element sysmlRelationship;
	protected ElementsFactory f;
	protected Element supplier = null;
	protected Element client = null;
	
	public static String INVALID_CLIENT_SUPPLIER_MESSAGE = "Invalid Client or Supplier - Not SysML Compliant";
	
	public CommonRelationship(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
		this.f = Application.getInstance().getProject().getElementsFactory();
		
	}
	
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create " + this.sysmlConstant + " Element");
		}
		
		if(supplier != null && client != null) {
			if(owner != null) {
				try {
					sysmlRelationship.setOwner(owner);
				} catch(IllegalArgumentException iaeOwner) {
					CameoUtils.logGUI("No owner found for " + name + " with id " + EAID + ". Attempting to set supplier or client as parent.");
				}
			}
			if(sysmlRelationship.getOwner() == null) {
				try {
					sysmlRelationship.setOwner(supplier);
				} catch(IllegalArgumentException iae) {
					try {
						sysmlRelationship.setOwner(client);
					} catch(IllegalArgumentException iae2) {
						String logMessage = "Invalid parent. No parent provided and supplier and client invalid parent for " + name + " with id " + EAID + ". Relationship could not be placed in model.";
						CameoUtils.logGUI(logMessage);
						ImportLog.log(logMessage);
						sysmlRelationship.dispose();
						return null;
					}
				}
			}
		}
		if(sysmlRelationship instanceof DirectedRelationship) {
			DirectedRelationship directedRelationship = (DirectedRelationship)sysmlRelationship;
			directedRelationship.getSource().add(supplier);
			directedRelationship.getTarget().add(client);
		} else {
			ModelHelper.setSupplierElement(sysmlRelationship, supplier);
			ModelHelper.setClientElement(sysmlRelationship, client);
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlRelationship;
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
	
	public abstract void writeToXML(Element element, Project project, Document xmlDoc);
	
	public org.w3c.dom.Element createBaseXML(Element element, Document xmlDoc) {
		org.w3c.dom.Element data = xmlDoc.createElement("data");
		
		//Add attributes
		org.w3c.dom.Element attributes = xmlDoc.createElement("attributes");
		
		//Add Name
		if(!name.equals("") && !name.equals(null)) {
			org.w3c.dom.Element name = xmlDoc.createElement("name");
			name.appendChild(xmlDoc.createTextNode(this.name));
			attributes.appendChild(name);
		} else {
			org.w3c.dom.Element name = xmlDoc.createElement("name");
			attributes.appendChild(name);
		}
		
		//Add ID
		org.w3c.dom.Element id = xmlDoc.createElement("id");
		org.w3c.dom.Element cameoID = xmlDoc.createElement("cameo");
		cameoID.appendChild(xmlDoc.createTextNode(element.getLocalID()));
		id.appendChild(cameoID);
		data.appendChild(id);
		
		Element supplier = null;
		Element client = null;
		
		if(element instanceof DirectedRelationship) {
			//Get Supplier Element
			DirectedRelationship cameoRelationship = (DirectedRelationship)element;
			Collection<Element> suppliers = cameoRelationship.getSource();
			if(suppliers.iterator().hasNext()) {
				 this.supplier = suppliers.iterator().next();
			}

			//Get Client element	
			Collection<Element> clients = cameoRelationship.getTarget();
			if(clients.iterator().hasNext()) {
				 this.client = clients.iterator().next();
			}
		} else if(element instanceof ActivityEdge) {
			ActivityEdge cameoRelationship = (ActivityEdge)element;
			this.supplier = cameoRelationship.getSource();
			this.client = cameoRelationship.getTarget();

		} else if(element instanceof Transition) {
			Transition cameoRelationship = (Transition)element;
			this.supplier = cameoRelationship.getSource();
			this.client = cameoRelationship.getTarget();
		} else if(element instanceof Connector) {
			List<ConnectorEnd> connectorEnds = ((Connector)element).getEnd();
			if(connectorEnds.size() > 1) {
				CameoUtils.logGUI("Checking connector ends for supplier and client.");
				this.supplier = connectorEnds.get(0).getPartWithPort();
				this.client = connectorEnds.get(1).getPartWithPort();
				if(supplier == null) {
					this.supplier = connectorEnds.get(0).getRole();
				}
				if(client == null) {
					this.client = connectorEnds.get(1).getRole();
				}
			}
		} else if(element instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association) {
				this.client = ModelHelper.getClientElement(element);
				this.supplier = ModelHelper.getSupplierElement(element);
		} else {
			CameoUtils.logGUI("Unable to cast relationship to DirectedRelationship or ActivityEdge to find client and supplier.");
		}
		
		//Add Relationship Tag
		org.w3c.dom.Element relationship = xmlDoc.createElement("relationships");
		
		if(this.supplier != null) {
			org.w3c.dom.Element supplierID = xmlDoc.createElement(XmlTagConstants.SUPPLIER);
			supplierID.appendChild(xmlDoc.createTextNode(this.supplier.getLocalID()));
			relationship.appendChild(supplierID);	
		} else {
			CameoUtils.logGUI("No supplier element found.");
		}
		
		if(this.client != null) {
			org.w3c.dom.Element clientID = xmlDoc.createElement(XmlTagConstants.CLIENT);
			clientID.appendChild(xmlDoc.createTextNode(this.client.getLocalID()));
			relationship.appendChild(clientID);
		} else {
			CameoUtils.logGUI("No client element found");
		}
		
		if(element.getOwner() != null) {
			org.w3c.dom.Element hasParent = xmlDoc.createElement("hasParent");
			Element parent = null;
			
			if(element instanceof Transition) {
				Element region = element.getOwner();
				parent = region.getOwner();
			} else {
				parent = element.getOwner();
			}
			
			hasParent.appendChild(xmlDoc.createTextNode(parent.getLocalID()));
			relationship.appendChild(hasParent);
		}
		
		data.appendChild(attributes);
		data.appendChild(relationship);
		
		return data;
	}
	
	public Element getSupplier() {
		return this.supplier;
	}
	
	public Element getClient() {
		return this.client;
	}
}
