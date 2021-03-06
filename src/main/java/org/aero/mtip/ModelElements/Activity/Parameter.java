package org.aero.mtip.ModelElements.Activity;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ParameterDirectionKind;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ParameterDirectionKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class Parameter extends CommonElement {
	// Parameter Direction Kind Enumerations
	public static final String IN = "in";
	public static final String OUT = "out";
	public static final String INOUT = "inout";
	public static final String RETURN = "return";
			
	public Parameter(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PARAMETER;
		this.xmlConstant = XmlTagConstants.SYSML_PARAMETER;
		this.sysmlElement = f.createParameterInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter parameter = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter)sysmlElement;
		
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION)) {
			if(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION).contentEquals(IN)) {
				parameter.setDirection(ParameterDirectionKindEnum.IN);
			} else if(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION).contentEquals(OUT)) {
				parameter.setDirection(ParameterDirectionKindEnum.OUT);
			} else if(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION).contentEquals(INOUT)) {
				parameter.setDirection(ParameterDirectionKindEnum.INOUT);
			} else if(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION).contentEquals(RETURN)) {
				parameter.setDirection(ParameterDirectionKindEnum.RETURN);
			}
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE)) {
			String defaultValue = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE);
		
			try {
				if(parameter.getType() != null) {
					if(xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.BOOLEAN)) {
						boolean boolVal = Boolean.valueOf(defaultValue);
						LiteralBoolean valueSpecification = (LiteralBoolean)ModelHelper.createValueSpecification(project, parameter.getType(), boolVal, null);
						parameter.setDefaultValue(valueSpecification);
					} else if (xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.INTEGER)) {
						int intVal = Integer.parseInt(defaultValue);
						ValueSpecification valueSpecification = ModelHelper.createValueSpecification(project, parameter.getType(), intVal, null);
						parameter.setDefaultValue(valueSpecification);
					} else if (xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.REAL)) {
						double realVal = Double.parseDouble(defaultValue);
						LiteralReal valueSpecification = (LiteralReal)ModelHelper.createValueSpecification(project, parameter.getType(), realVal, null);
						valueSpecification.setValue(realVal);
						parameter.setDefaultValue(valueSpecification);
					} else if(xmlElement.getAttribute(XmlTagConstants.TYPED_BY).contentEquals(SysmlConstants.STRING)) {
						LiteralString valueSpecification = (LiteralString)ModelHelper.createValueSpecification(project, parameter.getType(), defaultValue, null);
						parameter.setDefaultValue(valueSpecification);
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
		return sysmlElement;
	}
	
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter parameter = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter)element;
		ParameterDirectionKind dk = parameter.getDirection();
		
		org.w3c.dom.Element textTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_KEY_DIRECTION, dk.toString());
		attributes.appendChild(textTag);	
		
		ValueSpecification vs = parameter.getDefaultValue();
		createDefaultValueTag(xmlDoc, vs, attributes, relationships);

		return data;
	}
}
