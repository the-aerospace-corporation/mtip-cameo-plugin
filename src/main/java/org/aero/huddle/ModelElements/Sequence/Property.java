package org.aero.huddle.ModelElements.Sequence;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdinterfaces.Interface;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.VisibilityKindEnum;

public class Property extends CommonElement {

	public Property(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PROPERTY;
		this.xmlConstant = XmlTagConstants.PROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property property = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)sysmlElement;
		
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE)) {
			String defaultValue = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE);
		
			try {
				if(property.getType() != null) {
					if(xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.BOOLEAN)) {
						boolean boolVal = Boolean.valueOf(defaultValue);
						LiteralBoolean valueSpecification = (LiteralBoolean)ModelHelper.createValueSpecification(project, property.getType(), boolVal, null);
						property.setDefaultValue(valueSpecification);
					} else if (xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.INTEGER)) {
						int intVal = Integer.parseInt(defaultValue);
						ValueSpecification valueSpecification = ModelHelper.createValueSpecification(project, property.getType(), intVal, null);
						property.setDefaultValue(valueSpecification);
					} else if (xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.REAL)) {
						double realVal = Double.parseDouble(defaultValue);
						LiteralReal valueSpecification = (LiteralReal)ModelHelper.createValueSpecification(project, property.getType(), realVal, null);
						valueSpecification.setValue(realVal);
						property.setDefaultValue(valueSpecification);
					} else if(xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.STRING)) {
						LiteralString valueSpecification = (LiteralString)ModelHelper.createValueSpecification(project, property.getType(), defaultValue, null);
						property.setDefaultValue(valueSpecification);
					} else {
						CameoUtils.logGUI("Primitive type not recognized: " + xmlElement.getAttribute(XmlTagConstants.TYPED_BY));
					}
				} else {
					CameoUtils.logGUI("Property type is null. Cannot set default value.");
				}
			} catch(Exception exception) {
				StringWriter writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter( writer );
				exception.printStackTrace(printWriter);
				printWriter.flush();

				String stackTrace = writer.toString();
				String message = "Error assigning default value to property with id: " + this.EAID + " see stack trace:";
				CameoUtils.logGUI(message);
				ImportLog.log(message);
				ImportLog.log(stackTrace);
			}
		}
		
		if(owner instanceof Interface) {
			((NamedElement)sysmlElement).setVisibility(VisibilityKindEnum.PUBLIC);
		}
		((NamedElement) sysmlElement).setVisibility(VisibilityKindEnum.PUBLIC);
		
		return sysmlElement;
	}
	
	// Create Dependent Element default value if isElement()
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		// Get default Value and write to attributes
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property property = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property)element;
		ValueSpecification vs = property.getDefaultValue();
		Object obj = ModelHelper.getValueSpecificationValue(vs);
		Element instance = null;
		
		if(obj instanceof String) {
			String defaultValue = (String)obj;
			org.w3c.dom.Element defaultValueTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, defaultValue);
			attributes.appendChild(defaultValueTag);
		}
		
		if(obj instanceof Boolean) {
			boolean defaultValue = (boolean)obj;
			org.w3c.dom.Element defaultValueTag = createBoolAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, Boolean.toString(defaultValue));
			attributes.appendChild(defaultValueTag);
		}
		
		if(obj instanceof Integer) {
			int defaultValue = (int)obj;
			org.w3c.dom.Element defaultValueTag = createIntAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, Integer.toString(defaultValue));
			attributes.appendChild(defaultValueTag);
		}
		
		if(obj instanceof Double) {
			Double defaultValue = (Double)obj;
			org.w3c.dom.Element defaultValueTag = createFloatAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, Double.toString(defaultValue));
			attributes.appendChild(defaultValueTag);
		}
		
		if(vs instanceof InstanceValue) {
			InstanceValue iv = (InstanceValue)vs;
			instance = iv.getInstance();
			if(instance != null) {
				org.w3c.dom.Element defaultValueTag = createRel(xmlDoc, instance, XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE);
				relationships.appendChild(defaultValueTag);
			}
		}
		
		return data;
	}
}
