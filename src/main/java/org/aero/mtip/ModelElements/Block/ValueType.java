/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.CheckForNull;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Classifier;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization;

public class ValueType extends CommonElement {
	public ValueType(String name, String EAID)  {
		super(name, EAID);
		creationType = XmlTagConstants.ELEMENTSFACTORY;
		sysmlConstant = SysmlConstants.VALUE_TYPE;
		xmlConstant = XmlTagConstants.VALUETYPE;
		element = f.createDataTypeInstance();
		initialStereotypes = Arrays.asList(SysML.getValueTypeStereotype());
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);		
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());

		writeBaseClassifiers(relationships, element);
		
		return data;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		createBaseClassifier(parsedXML, modelElement);
	}
	
	protected void createBaseClassifier(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if (!modelElement.hasAttribute(XmlTagConstants.CLASSIFIED_BY)
				|| CameoUtils.isPrimitiveValueType(modelElement.getAttribute(XmlTagConstants.CLASSIFIED_BY))) {
			return;
		}
		
		ImportXmlSysml.buildEntity(parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.CLASSIFIED_BY)));
	}
	
	@Override
	public void applyClassifier() {
		if (xmlElement == null) {
			ImportLog.log(String.format("ERROR: ValueType.xmlElement is null for value type %s. Unable to apply classifier", EAID));
			return;
		}
		
		if (!xmlElement.hasAttribute(XmlTagConstants.CLASSIFIED_BY)) {
			return;
		}
		
		Element baseClassifier = getBaseClassifier();
		
		if (baseClassifier == null) {
			return;
		}
				
		Generalization generalization = project.getElementsFactory().createGeneralizationInstance();
		generalization.setOwner(element);
		generalization.getSource().add(element);
		generalization.getTarget().add(baseClassifier);		
	}
	
	@CheckForNull
	protected Element getBaseClassifier() {
		String valueTypeId = xmlElement.getAttribute(XmlTagConstants.CLASSIFIED_BY);
		
		if (CameoUtils.isPrimitiveValueType(valueTypeId)) {
			return CameoUtils.getPrimitiveValueType(valueTypeId);
		}
		
		String createdId = ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.CLASSIFIED_BY));
		return (Element) project.getElementByID(createdId);
	}
	
	protected void writeBaseClassifiers(org.w3c.dom.Element relationships, Element element) {
		List<Classifier> classifiers = ModelHelper.getGeneralClassifiers((Classifier)element);
		
		for (Classifier classifier : classifiers) {
			writeBaseClassifier(relationships, classifier);
		}
	}
	
	protected void writeBaseClassifier(org.w3c.dom.Element relationships, Classifier classifier) {
		if (CameoUtils.isPrimitiveValueType(classifier)) {
			org.w3c.dom.Element classifiedByTag = XmlWriter.createClassifiedByPrimitiveTag(classifier);
			XmlWriter.add(relationships, classifiedByTag);
			return;
		}
		
		org.w3c.dom.Element classifiedByTag = XmlWriter.createMtipRelationship(classifier, XmlTagConstants.CLASSIFIED_BY);
		XmlWriter.add(relationships, classifiedByTag);
	}
}