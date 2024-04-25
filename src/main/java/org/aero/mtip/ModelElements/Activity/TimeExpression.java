/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.Collection;
import java.util.HashMap;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.Observation;

public class TimeExpression extends CommonElement {

	public TimeExpression(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.TIME_EXPRESSION;
		this.xmlConstant = XmlTagConstants.TIMEEXPRESSION;
		this.element = f.createTimeExpressionInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		setExpr(project, xmlElement);
		setObservation(project, xmlElement);
		
		return element;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	private void setExpr(Project project, XMLItem xmlElement) {
		if(!xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_EXPR)) {
			return;
		}
		
		// TODO: decide value specification format to cotain value and value type.
		String valueType = "";
		String value = "";
		
		ValueSpecification vs = createValueSpecification(valueType, value);
		
		if (vs == null) {
			Logger.log(String.format("Failed to set expr for time expression %s", xmlElement.getImportId()));
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression timeExpression = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression)element;
		timeExpression.setExpr(vs);
	}
	
	private void setObservation(Project project, XMLItem xmlElement) {
		if (!xmlElement.hasListAttributes(XmlTagConstants.ATTRIBUTE_NAME_OBSERVATION)) {
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression timeExpression = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression)element;
		Collection<Observation> observations = timeExpression.getObservation();
		
		for (String importId : xmlElement.getListAttributes(XmlTagConstants.ATTRIBUTE_NAME_OBSERVATION)) {
			String createdId = Importer.idConversion(importId);
			
			if (createdId == null || createdId.trim().isEmpty()) {
				Logger.log(String.format("Observation not created or has no id for time expression %s.", xmlElement.getImportId()));
				continue;
			}
			
			Element element = (Element) project.getElementByID(createdId);
			
			if (element == null || !(element instanceof Observation)) {
				Logger.log(String.format("Observation %s not created or not an observation for Time Expression %s", importId, xmlElement.getImportId()));
				continue;
			}
			
			Observation observation = (Observation)element;
			observations.add(observation);			
		}		
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeExpr(relationships, element);
		writeObservation(relationships, element);
		
		return data;
	}
	
	private void writeExpr(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression timeExpression = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression)element;
		
		ValueSpecification vs = timeExpression.getExpr();
		
		if (vs == null) {
			return;
		}
		
		org.w3c.dom.Element exprTag = XmlWriter.createMtipRelationship(vs, XmlTagConstants.ATTRIBUTE_NAME_EXPR);		
		XmlWriter.add(relationships, exprTag);
	}
	
	private void writeObservation(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression timeExpression = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression)element;
		Collection<Observation> observations = timeExpression.getObservation();
		
		for (Observation observation : observations) {
			org.w3c.dom.Element whenTag = XmlWriter.createMtipRelationship(observation, XmlTagConstants.ATTRIBUTE_NAME_OBSERVATION);
			XmlWriter.add(relationships, whenTag);
		}
	}
}
