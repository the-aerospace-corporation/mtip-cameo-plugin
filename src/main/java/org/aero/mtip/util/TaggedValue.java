/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.util.ArrayList;
import java.util.List;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TaggedValue {
	private String stereotypeName;
	private String profileName;
	private String valueName;
	private String valueType;
	private String value;
	private boolean isMultiValue = false;
	private List<String> values = new ArrayList<String> ();
	
	public TaggedValue(String stereotypeName, String profileName, String valueName, String valueType, List<String> values) {
		setStereotypeName(stereotypeName);
		setProfileName(profileName);
		setValueName(valueName);
		setValueType(valueType);
		setValues(values);
		isMultiValue = true;
	}
	
	public TaggedValue(Node attribute) {
		stereotypeName = Importer.getDirectChildByKey(attribute, XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME).getTextContent();
		profileName = Importer.getDirectChildByKey(attribute, XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME).getTextContent();
		valueName = Importer.getDirectChildByKey(attribute, XmlTagConstants.TAGGED_VALUE_NAME).getTextContent();
		valueType = Importer.getDirectChildByKey(attribute, XmlTagConstants.TAGGED_VALUE_TYPE).getTextContent();
		Node taggedValueValue = Importer.getDirectChildByKey(attribute, XmlTagConstants.TAGGED_VALUE_VALUE);
		
		String valueTypeAttribute = ((org.w3c.dom.Element)taggedValueValue).getAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE);
		if(valueTypeAttribute.contentEquals(XmlTagConstants.ATTRIBUTE_TYPE_LIST)) {
			NodeList valueNodes = taggedValueValue.getChildNodes();
			for(int i = 0; i < valueNodes.getLength(); i++) {
				Node valueNode = valueNodes.item(i);
				if(valueNode.getNodeType() == Node.ELEMENT_NODE) {
					values.add(valueNode.getTextContent());
				}
			}
			isMultiValue = true;
		} else if(valueTypeAttribute.contentEquals(XmlTagConstants.ATTRIBUTE_TYPE_STRING)) { 
			value = Importer.getDirectChildByKey(attribute, XmlTagConstants.VALUE).getTextContent();
		}
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
	
	public String getValue() {
		return value;
	}
	
	public List<String> getValues() {
		return values;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public void setValues(List<String> values) {
		this.values= values;
	}
	
	public boolean isMultiValue() {
		return isMultiValue;
	}
	
	@Override
	public String toString() {
		String outputString = "Stereotype name " + getStereotypeName() + "\n" +
			   "Profile name " + getProfileName() + "\n" + 
			   "Value name " + getValueName() + "\n" + 
			   "Value type " + getValueType() + "\n";
			   if(isMultiValue) {
				   for(String value : values) {
					   outputString = outputString + "Value " + value + "\n";
				   }
			   } else {
				   outputString = outputString + "Value " + getValue() + "\n";
			   }
		return outputString;
	}
}
