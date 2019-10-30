package org.aero.huddle.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLItem {
	private String type = "";
	private String category = "";
	private String ea_id = "";
	private String cameo_id = "";
	private String parent = "";
	private String client = "";
	private String supplier = "";
	private String name = "";
	private HashMap<String, String> attributes = new HashMap<String, String>();
	private List<String> childElements = new ArrayList<String>();
	
	public XMLItem() {
		
	}
	
	public void setType(String type) {
		this.type = type;
		setCategory();
	}
	public void setEAID(String ea_id) {
		this.ea_id = ea_id;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public void addAttribute(String key, String value) {
		if(key.equals("supplier_id")) {
			setSupplier(value);
		} else if (key.equals("client_id")) {
			setClient(value);
		} else if (key.equals("name")) {
			value = value.replace("(",  "").replace(")", "");
			setName(value);
			this.attributes.put(key, value);
		} else {
			this.attributes.put(key, value);
		}
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
	public String getCameoID() {
		return cameo_id;
	}
	
	public String getName() {
		return name;
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
	
	public boolean isCreated() {
		if(cameo_id.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
