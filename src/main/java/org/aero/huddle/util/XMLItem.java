package org.aero.huddle.util;

import java.util.Arrays;
import java.util.HashMap;

public class XMLItem {
	private String type = "";
	private String category = "";
	private String ea_id = "";
	private String parent = "";
	private String client = "";
	private String supplier = "";
	private HashMap<String, String> attributes = new HashMap<String, String>();
	
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
		this.attributes.put(key, value);
	}
	public void setClient(String client) {
		this.client = client;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
}
