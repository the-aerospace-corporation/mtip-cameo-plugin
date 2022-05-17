/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.util;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class XMLItem {
	private String type = "";
	private String category = "";
	private List<String> ids = new ArrayList<String>( );
	private HashMap<String, String> idsByName = new HashMap<String, String> ();
	private String ea_id = "";
	private String cameo_id = "";
	private String parent = "";
	private String client = "";
	private String supplier = "";
	private String name = "";
	/** String id of the event for trigger element types in the importing XML file */
	private String event = "";
	/** String id of trigger for AcceptEventAction element types in the importing XML file */
	private String acceptEventAction = "";
	
	private String newEvent = "";
	private String newAcceptEventAction = "";
	private String operation = "";
	private String newOperation = "";
	private String submachine = "";
	private String newSubmachineID = "";
	private boolean newSubmachineCreated = false;
	private String guard = "";
	private String effect = "";
	private List<String> coveredBy = new ArrayList<String> ();
	private List<String> interactionOperands = new ArrayList<String> ();
	private List<String> newInteractionOperands = new ArrayList<String> ();
	private HashMap<String, String> stereotypes = new HashMap<String, String> ();
	private HashMap<String, String> attributes = new HashMap<String, String>();
	private HashMap<String, List<String>> listAttributes = new HashMap<String, List<String>>();
	
	private HashMap<String, Element> elements = new HashMap<String, Element>();
	private HashMap<String, String> coveredByMap = new HashMap<String, String> ();
	private HashMap<String, String> diagramParents = new HashMap<String, String> ();
	private HashMap<String, String> importIdMap = new HashMap<String, String> ();
	private List<String> childElements = new ArrayList<String>();
	private List<String> childRelationships = new ArrayList<String>();
	
	private String valueSpecificationID = "";
	private String newValueSpecificationID = "";

	private String relationshipStereotype = "";
	private String relationshipStereotypeProfile = "";
	
	private List<String> constrainedElements = new ArrayList<String> ();
	private List<String> newConstrainedElements = new ArrayList<String>();
	
	private HashMap<String, Rectangle> locations = new HashMap<String, Rectangle>();
	private HashMap<String, String> childElementType = new HashMap<String, String>();
	
	private Element supplierElement = null;
	private Element clientElement = null;
	
	private List<TaggedValue> taggedValues = new ArrayList<TaggedValue> ();
	
	public XMLItem() {
		
	}
	
	public void setType(String type) {
		this.type = type;
		setCategory();
	}
	public void addId(String id) {
		this.ids.add(id);
	}
	public void addIdWithType(String idName, String id) {
		this.idsByName.put(idName, id);
	}
	public List<String> getIds() {
		return this.ids;
	}
	public void setEAID(String ea_id) {
		this.ea_id = ea_id;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public void addAttribute(String key, String value) {
		if(key.equals("supplier_id") || key.contentEquals(XmlTagConstants.SUPPLIER)) {
			setSupplier(value);
		} else if (key.equals("client_id") || key.contentEquals(XmlTagConstants.CLIENT)) {
			setClient(value);
		} else if (key.equals("name")) {
			// Refactor with list of forbidden characters
			value = value.replace("(",  "").replace(")", "").replace("#", "No.").replace("\"", "");
			setName(value);
			this.attributes.put(key, value);
		} else if (key.equals(SysmlConstants.SUBMACHINE)) {
			submachine = value;
		} else if(key.contentEquals("acceptEventAction")) {
			acceptEventAction = value;
		} else if(key.contentEquals("event")) {
			event = value;
			this.attributes.put(XmlTagConstants.EVENT_TAG, value);
		} else if(key.contentEquals("operation")) {
			this.operation = value;
		} else if(key.contentEquals(XmlTagConstants.GUARD)) {
			this.guard = value;
			this.attributes.put(key, value);
		} else if(key.contentEquals("effect")) {
			this.effect = value;
		} else if(key.contentEquals("valueSpecification")) {
			this.newValueSpecificationID = value;
		} else if(key.contentEquals("constrainedElement")) {
				this.constrainedElements.add(value);
		} else if(key.contentEquals(XmlTagConstants.ATTRIBUTE_NAME_INTERACTION_OPERAND)) {
			this.interactionOperands.add(value);
		} else if(key.contentEquals(org.aero.mtip.ModelElements.Sequence.CombinedFragment.newInteractionOperand)) {
			this.newInteractionOperands.add(value);
		} else if(key.contentEquals(XmlTagConstants.ATTRIBUTE_NAME_COVERED_BY)) {
			this.coveredBy.add(value);
		} else {
			this.attributes.put(key, value);
		}
	}
	
	public void addListAttribute(String key, String value) {
		if(!this.listAttributes.containsKey(key)) {
			this.listAttributes.put(key, new ArrayList<String> ());
		} 
		this.listAttributes.get(key).add(value);
	}
	
	public List<String> getListAttributes(String key) {
		return this.listAttributes.get(key);
	}
	
	public boolean hasListAttributes(String key) {
		if(this.listAttributes.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	public void addLocation(String key, Rectangle value) {
		this.locations.put(key, value);
	}
	public void addChildElementType(String key, String value) {
		this.childElementType.put(key, value);
	}

	public Rectangle getLocation(String key) {
		Set<String> keys = locations.keySet();
		if (keys.contains(key)) {
			return this.locations.get(key);
		} else {
			return new Rectangle(-999, -999, -999, -999);
		}
	}
	public String getChildElementType(String key) {
		Set<String> keys = childElementType.keySet();
		if (keys.contains(key)) {
			return this.childElementType.get(key);
		} else {
			return "sysml.UNKNOWN_TYPE";
		}
	}
	
	public void addRelationshipStereotype(String profileName, String stereotypeName) {
		relationshipStereotypeProfile = profileName;
		relationshipStereotype = stereotypeName;
	}
	
	public String getRelationshipStereotype() {
		return relationshipStereotype;
	}
	
	public String getRelationshipStereotypeProfile() {
		return relationshipStereotypeProfile;
	}
	
	public void addNewConstrainedElement(String newConstrainedElement) {
		newConstrainedElements.add(newConstrainedElement);
	}
	
	public void addConstrainedElement(String constrainedElement) {
		constrainedElements.add(constrainedElement);
	}
	
	public List<String> getNewConstrainedElements() {
		return newConstrainedElements;
	}
	
	public List<String> getConstrainedElements() {
		return constrainedElements;
	}
	
	public String printAttributes() {
		String list = "";
		for(String key : attributes.keySet()) {
			list += key + ", ";
		}
		list = list.substring(0, list.length() - 2);
		return list;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public void setCameoID(String cameo_id) {
		this.cameo_id = cameo_id;
	}
	
	public void addChildElement(String element) {
		this.childElements.add(element);
	}
	
	public void addChildRelationship(String relationship) {
		this.childRelationships.add(relationship);
	}
	
	public void addStereotype(String stereotypeName, String profileName) {
		stereotypes.put(stereotypeName, profileName);
	}
	
	public HashMap<String, String> getStereotypes() {
		return stereotypes;
	}
	
	public boolean hasStereotypes() {
		return !stereotypes.isEmpty();
	}
	public boolean hasAttribute(String key) {
		Set<String> keys = attributes.keySet();
		if(keys.contains(key)) {
			return true;
		}
		return false;
	}
	public List<String> getChildElements(Map<String, XMLItem> parsedXML) {
		List<String> existingChildElements = new ArrayList<String>();
		for(String childElement : this.childElements) { 
	    	if(parsedXML.containsKey(childElement)) {
	    		existingChildElements.add(childElement);
	    	} else {
	    		CameoUtils.logGUI("Element with id : " + childElement + " was not added to diagram as it does not have a data tag.");
	    	}
	    }
		return existingChildElements;
	}
	
	public List<String> getChildRelationships(Map<String, XMLItem> parsedXML) {
		List<String> existingChildRelationships = new ArrayList<String>();
		for(String childRelationship : this.childRelationships) { 
	    	if(parsedXML.containsKey(childRelationship)) {
	    		existingChildRelationships.add(childRelationship);
	    	} else {
	    		CameoUtils.logGUI("Element with id : " + childRelationship + " was not added to diagram as it does not have a data tag.");
	    	}
	    }
		return existingChildRelationships;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public String getCategory() {
		return category;
	}
	public String getEAID() {
		return ea_id;
	}
	public String getParent() {
		return parent;
	}
	public String getAttribute(String key) {
		return attributes.get(key);
	}
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	public String getClient() {
		return client;
	}
	public String getSupplier() {
		return supplier;
	}
	
	public boolean hasClient() {
		if(!client.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean hasSupplier() {
		if(!supplier.isEmpty()) {
			return true;
		}
		return false;
	}
	public String getCameoID() {
		return cameo_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setNewEvent(String newEvent) {
		this.newEvent = newEvent;
	}
	
	public String getEvent() {
		return event;
	}
	
	public boolean hasEvent() {
		if(event.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean hasAcceptEventAction() {
		if(acceptEventAction.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public String getNewOperation() {
		return newOperation;
	}
	
	public void setNewOperation(String newOperation) {
		this.newOperation = newOperation;
	}
	
	public void setNewAcceptEventAction(String newAcceptEventAction) {
		this.newAcceptEventAction = newAcceptEventAction;
	}
	
	public String getNewEvent() {
		return newEvent;
	}
	
	public String getNewAcceptEventAction() {
		return newAcceptEventAction;
	}
	
	
//	public void setTrigger(String trigger) {
//		this.trigger = trigger;
//	}
	
	public boolean hasValueSpecification() {
		if(valueSpecificationID.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void setValueSpecification(String valueSpecificationID) {
		this.valueSpecificationID = valueSpecificationID;
	}
	
	public String getValueSpecification() {
		return valueSpecificationID;
	}
	
	public void setNewValueSpecification(String newValueSpecificationID) {
		this.newValueSpecificationID = newValueSpecificationID;
	}
	
	public String getNewValueSpecification() {
		return newValueSpecificationID;
	}
	
	public String getAcceptEventAction() {
		return acceptEventAction;
	}
	
	public void setNewSubmachineID(String newSubmachineID) {
		this.newSubmachineID = newSubmachineID;
		this.newSubmachineCreated = true;
	}
	public String getNewSubmachine() {
		return newSubmachineID;
	}
	
	public String getSubmachine() {
		return submachine;
	}
	
	public boolean newSubmachineCreated() {
		return newSubmachineCreated;
	}
	public boolean isSubmachine() {
		if(StringUtils.isBlank(submachine)) {
			return false;
		}
		return true;
	}
	
	public boolean hasGuard() {
		if(StringUtils.isBlank(guard)) {
			return false;
		}
		return true;
	}
	
	public String getGuard() {
		return guard;
	}
	
	public void setGuard(String guard) {
		this.guard = guard;
	}
	
	public boolean hasEffect() {
		if(StringUtils.isBlank(effect)) {
			return false;
		}
		return true;
	}
	
	public String getEffect() {
		return effect;
	}
	
	public void setEffect(String effect) {
		this.effect = effect;
	}
	private void setCategory() {
		if(Arrays.asList(SysmlConstants.SYSMLELEMENTS).contains(type)) {
			category = SysmlConstants.ELEMENT;
		}
		if(Arrays.asList(SysmlConstants.SYSMLRELATIONSHIPS).contains(type)) {
			category = SysmlConstants.RELATIONSHIP;
		}
		if(Arrays.asList(SysmlConstants.SYSMLDIAGRAMS).contains(type)) {
			category = SysmlConstants.DIAGRAM;
		}
	}
	
	public void setSupplierElement(Element element) {
		this.supplierElement = element;
	}
	
	public void setClientElement(Element element) {
		this.clientElement = element;
	}
	
	public Element getSupplierElement() {
		return supplierElement;
	}
	
	public Element getClientElement() {
		return clientElement;
	}
	
	public boolean hasClientElement() {
		if(clientElement != null) {
			return true;
		}
		return false;
	}
	
	public boolean hasSupplierElement() {
		if(supplierElement != null) {
			return true;
		}
		return false;
	}
	
	public void addElement(String name, Element element) {
		this.elements.put(name, element);
	}
	
	public Element getElement(String name) {
		return elements.get(name);
	}
	
	public boolean hasElement(String name) {
		if(elements.containsKey(name)) {
			return true;
		}
		return false;
	}
	
	public boolean isCreated() {
		if(cameo_id.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasInteractionOperands() {
		if(!this.interactionOperands.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public List<String> getInteractionOperands() {
		return this.interactionOperands;
	}
	
	public String getInteractionOperatorKind() {
		return this.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_INTERACTION_OPERATOR_KIND);
	}
	
	public List<String> getNewInteractionOperands() {
		return this.newInteractionOperands;
	}
	public List<String> getCoveredBy() {
		return this.coveredBy;
	}
	
	public void setCoveredByID(String oldID, String newID) {
		this.coveredByMap.put(oldID, newID);
	}
	
	public String getCoveredByID(String oldID) {
		return this.coveredByMap.get(oldID);
	}
	
	public String toString() {
		String allInfo = "\nParent: " + this.getParent()
		+ "\nType: " + this.getType()
		+ "\nName: " + this.getName()
		+ "\nID: " + this.getEAID()
		+ "\nOwner: " + this.getParent();
		return allInfo;
	}
	
	public void addDiagramParent(String id, String parentID) {
		diagramParents.put(id,  parentID);
	}
	
	public String getDiagramParent(String id) {
		return diagramParents.get(id);
	}
	
	public void addImportID(String cameoID, String importID) {
		importIdMap.put(cameoID, importID);
	}
	
	public String getImportID(String cameoID) {
		return importIdMap.get(cameoID);
	}
	
	public void addStereotypeTaggedValue(TaggedValue tv) {
		taggedValues.add(tv);
	}
	
	public List<TaggedValue> getTaggedValues() {
		return taggedValues;
	}
	
	public boolean hasTaggedValues() {
		if(taggedValues.isEmpty()) {
			return false;
		}
		return true;
	}
}
