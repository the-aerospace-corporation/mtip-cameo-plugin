package org.aero.huddle.ModelElements;

import java.util.Collection;

import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.DirectedRelationship;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class CommonRelationship {
	protected String name;
	protected String EAID;
	
	public CommonRelationship(String name, String EAID) {
		this.EAID = EAID;
		this.name = name;
	}
	
	public abstract Element createElement(Project project, Element owner, Element client, Element supplier);

	public org.w3c.dom.Element createBaseXML(Element element, Project project, Document xmlDoc) {
		return null;
	}
	
	public abstract void writeToXML(Element element, Project project, Document xmlDoc);
	
	public org.w3c.dom.Element createBaseXML(Element element, Document xmlDoc) {
		org.w3c.dom.Element data = xmlDoc.createElement("data");
		
		//Add attributes
		org.w3c.dom.Element attributes = xmlDoc.createElement("attributes");
		
		//Add Name
		org.w3c.dom.Element name = xmlDoc.createElement("name");
		name.appendChild(xmlDoc.createTextNode(this.name));
		attributes.appendChild(name);
		
		
		//Add ID
		org.w3c.dom.Element id = xmlDoc.createElement("id");
		org.w3c.dom.Element cameoID = xmlDoc.createElement("cameo");
		cameoID.appendChild(xmlDoc.createTextNode(element.getLocalID()));
		id.appendChild(cameoID);
		data.appendChild(id);
		
		//Add Supplier
		DirectedRelationship cameoRelationship = (DirectedRelationship)element;
		Collection<Element> suppliers = cameoRelationship.getSource();
		Element supplier = null;
		if(suppliers.iterator().hasNext()) {
			 supplier = suppliers.iterator().next();
		}
		
		org.w3c.dom.Element supplierID = xmlDoc.createElement("supplier_id");
		supplierID.appendChild(xmlDoc.createTextNode(supplier.getLocalID()));
		attributes.appendChild(supplierID);		
		
		//Add Client		
		Collection<Element> clients = cameoRelationship.getTarget();
		Element client = null;
		if(suppliers.iterator().hasNext()) {
			 client = clients.iterator().next();
		}
		
		org.w3c.dom.Element clientID = xmlDoc.createElement("client_id");
		clientID.appendChild(xmlDoc.createTextNode(client.getLocalID()));
		attributes.appendChild(clientID);
		
		//Add parent relationship
		org.w3c.dom.Element relationship = xmlDoc.createElement("relationships");
		
		if(element.getOwner() != null) {
			org.w3c.dom.Element hasParent = xmlDoc.createElement("hasParent");
			Element parent = element.getOwner();
			hasParent.appendChild(xmlDoc.createTextNode(parent.getLocalID()));
			relationship.appendChild(hasParent);
		}
		
		data.appendChild(attributes);
		data.appendChild(relationship);
		
		return data;
	}
}
