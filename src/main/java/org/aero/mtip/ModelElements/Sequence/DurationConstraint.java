/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.Duration;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationInterval;

public class DurationConstraint extends CommonElement {

	public DurationConstraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.DURATION_CONSTRAINT;
		this.xmlConstant = XmlTagConstants.DURATION_CONSTRAINT;
		this.element = f.createDurationConstraintInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		if (xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_SPECIFICATION)) {
			setSpecification(xmlElement);
		}
		
		if (xmlElement.hasListAttributes(XmlTagConstants.ATTRIBUTE_CONSTRAINED_ELEMENT)) {
			setConstrainedElements(project, xmlElement);
		}

		return element;
	}
	
	private void setSpecification(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		Element specificationElement = ImportXmlSysml.getImportedElement(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_SPECIFICATION));
		
		if (specificationElement == null) {
			return;
		}
		
		if (!(specificationElement instanceof DurationInterval)) {
			return;
		}
		
		DurationInterval di = (DurationInterval)specificationElement;
		dc.setSpecification(di);
	}
	
	private void setConstrainedElements(Project project, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		List<Element> constrainedElements = dc.getConstrainedElement();
		
		for (String importId : xmlElement.getListAttributes(XmlTagConstants.ATTRIBUTE_CONSTRAINED_ELEMENT)) {
			String createdId = ImportXmlSysml.idConversion(importId);
			
			if (importId == null) {
				ImportLog.log(String.format("Created id not found for import id %s setting constrained elements for %s.", importId, xmlElement.getEAID()));
				continue;
			}
			
			Element constrainedElement = (Element) project.getElementByID(createdId);
			
			if (constrainedElement == null) {
				ImportLog.log(String.format("Element with import id %s not created before assigning to constrained elements of %s", importId, xmlElement.getEAID()));
				continue;
			}
			
			constrainedElements.add(constrainedElement);
		}
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if (modelElement.hasListAttributes(XmlTagConstants.ATTRIBUTE_CONSTRAINED_ELEMENT)) {
			for (String importId : modelElement.getListAttributes(XmlTagConstants.ATTRIBUTE_CONSTRAINED_ELEMENT)) {
				ImportXmlSysml.buildEntity(parsedXML, parsedXML.get(importId));
			}
		}
		
		if (modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_SPECIFICATION)) {
			ImportXmlSysml.buildEntity(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_SPECIFICATION)));
		}		
	}
	
	private void createDurationInterval(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		
		DurationInterval di = f.createDurationIntervalInstance();
		di.setOwner(element);
		
		dc.setSpecification(di);
	}
	
	private void setMinDuration(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		
		String minDuration = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_MIN);
		
		if (minDuration == null || minDuration.trim().isEmpty()) {
			return;
		}
		
		DurationInterval di = dc.getSpecification();
		// TODO: Add check for value type of value specification
		
		Duration dur = f.createDurationInstance();
		
		LiteralString vs = f.createLiteralStringInstance();
		vs.setValue(minDuration);
		
		dur.setExpr(vs);
		di.setMin(dur);	
		dc.setSpecification(di);
	}
	
	private void setMaxDuration(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		
		String maxDuration = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_KEY_MAX);
		
		if (maxDuration == null || maxDuration.trim().isEmpty()) {
			return;
		}
		
		DurationInterval di = dc.getSpecification();
		// TODO: Add check for value type of value specification
		
		Duration dur = f.createDurationInstance();
		
		LiteralString vs = f.createLiteralStringInstance();
		vs.setValue(maxDuration);
		
		dur.setExpr(vs);
		di.setMax(dur);
		dc.setSpecification(di);
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeConstrainedElements(attributes, element);
		writeSpecification(attributes, element);
		
		return data;
	}
	
	private void writeSpecification(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		
		DurationInterval di = dc.getSpecification();
		
		if(di == null) {
			return;
		}
		
		org.w3c.dom.Element durationIntervalTag = XmlWriter.createAttributeFromValueSpecification(di, XmlTagConstants.ATTRIBUTE_NAME_SPECIFICATION);
		
		if (durationIntervalTag == null) {
			return;
		}
		
		XmlWriter.add(attributes, durationIntervalTag);
	}
	
	private void writeConstrainedElements(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)element;
		
		for (Element constrainedElement : dc.getConstrainedElement()) {
			org.w3c.dom.Element constrainedElementTag 
				= XmlWriter.createMtipStringAttribute(
						XmlTagConstants.ATTRIBUTE_CONSTRAINED_ELEMENT, 
						constrainedElement.getID());
			
			XmlWriter.add(attributes, constrainedElementTag);
		}
	}
}
