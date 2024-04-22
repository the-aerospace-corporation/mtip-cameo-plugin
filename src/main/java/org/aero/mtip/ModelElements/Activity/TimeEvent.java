/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class TimeEvent extends CommonElement {
	public TimeEvent(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.TIME_EVENT;
		this.xmlConstant = XmlTagConstants.TIMEEVENT;
		this.element = f.createTimeEventInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		setWhen(project, xmlElement);
		
		return element;
	}
	
	private void setWhen(Project project, XMLItem xmlElement) {
		if (!xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_WHEN)) {
			return;
		}
		
		String createdId = Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_WHEN));
		
		if (createdId == null || createdId.trim().isEmpty()) {
			Logger.log(String.format("Time event when element created id not found for time event %s.", xmlElement.getImportId()));
			return;
		}
		
		Element timeExpression = (Element) project.getElementByID(createdId);
		
		if (timeExpression == null || !(timeExpression instanceof com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression)) {
			Logger.log(String.format("Time expression element not found with created id %s.", createdId ));
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent timeEvent = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent)element;
		timeEvent.setWhen((com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression)timeExpression);
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if (!modelElement.hasListAttributes(XmlTagConstants.ATTRIBUTE_NAME_WHEN)) {
			return;
		}
		
		String importId = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_WHEN);
		
		if (importId.trim().isEmpty()) {
			Logger.log(String.format("Id is blank for when attribute of TimeEvent %s", modelElement.getImportId()));
			return;
		}
		
		Importer.getInstance().buildEntity(parsedXML, parsedXML.get(importId));
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeWhen(attributes, element);
		
		return data;
	}
	
	private void writeWhen(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent timeEvent = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.TimeEvent)element;
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.TimeExpression timeExpression = timeEvent.getWhen();
		
		if (timeExpression == null) {
			return;
		}
		
		org.w3c.dom.Element whenTag = XmlWriter.createMtipStringAttribute( XmlTagConstants.ATTRIBUTE_NAME_WHEN, timeExpression.getID());
		XmlWriter.add(attributes, whenTag);
	}
}
