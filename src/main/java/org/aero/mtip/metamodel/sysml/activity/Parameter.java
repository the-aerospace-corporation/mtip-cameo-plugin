package org.aero.mtip.metamodel.sysml.activity;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.XMLItem;

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
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.PARAMETER;
		this.xmlConstant = XmlTagConstants.SYSML_PARAMETER;
		this.element = f.createParameterInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter parameter = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter)element;
		
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
						CameoUtils.logGui("Primitive type not recognized: " + xmlElement.getAttribute(XmlTagConstants.TYPED_BY));
					}
				} else {
					CameoUtils.logGui("Property type is null. Cannot set default value.");
				}
			} catch(Exception exception) {
				Logger.log(String.format("Error assigning default value to property with id: %s see stack trace: ", EAID));
				Logger.logException(exception);
			}
		}
		return element;
	}
	
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeDirection(attributes, element);
		writeDefaultValue(relationships, element);

		return data;
	}
	
	public void writeDirection(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter parameter = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter)element;
		ParameterDirectionKind dk = parameter.getDirection();
		
		org.w3c.dom.Element directionTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_DIRECTION, dk.toString());
		XmlWriter.add(attributes, directionTag);
	}
	
	public void writeDefaultValue(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter parameter = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Parameter)element;
		ValueSpecification vs = parameter.getDefaultValue();
		
		if (vs == null) {
			return;
		}
		
		org.w3c.dom.Element defaultValueTag = XmlWriter.createDefaultValueTag(vs);
		
		if (defaultValueTag == null) {
			return;
		}
		
		XmlWriter.add(relationships, defaultValueTag);
	}
}
