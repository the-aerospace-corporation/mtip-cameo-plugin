/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.util;

public class TaggedValue {
	private String stereotypeName;
	private String profileName;
	private String valueName;
	private String valueType;
	private String value;
	
	public TaggedValue(String stereotypeName, String profileName, String valueName, String valueType, String value) {
		setStereotypeName(stereotypeName);
		setProfileName(profileName);
		setValueName(valueName);
		setValueType(valueType);
		setValue(value);
	}
	
	public String getStereotypeName() {
		return stereotypeName;
	}

	public void setStereotypeName(String stereotypeName) {
		this.stereotypeName = stereotypeName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		setStereotypeName(stereotypeName);
		setProfileName(profileName);
		setValueName(valueName);
		setValueType(valueType);
		setValue(value);
		return "Stereotype name " + getStereotypeName() + "\n" +
			   "Profile name " + getProfileName() + "\n" + 
			   "Value name " + getValueName() + "\n" + 
			   "Value type " + getValueType() + "\n" + 
			   "Value " + getValue() + "\n";
	}
}
