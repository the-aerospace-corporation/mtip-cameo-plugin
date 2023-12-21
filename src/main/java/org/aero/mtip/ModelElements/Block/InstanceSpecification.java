/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.apache.commons.lang.ArrayUtils;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Classifier;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class InstanceSpecification extends CommonElement {
	protected List<Classifier> classifiers = new ArrayList<Classifier> ();
	public InstanceSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INSTANCESPECIFICATION;
		this.xmlConstant = XmlTagConstants.INSTANCESPECIFICATION;
		this.sysmlElement = f.createInstanceSpecificationInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		for(Classifier classifier : this.classifiers) { 
			ModelHelper.setClassifierForInstanceSpecification(classifier, (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification) sysmlElement, true);
		}
		
		return sysmlElement;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.CLASSIFIED_BY)) {
			if(parsedXML.containsKey(modelElement.getAttribute(XmlTagConstants.CLASSIFIED_BY))) {
				Classifier classifier = (Classifier)ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.CLASSIFIED_BY)));
				if(classifier != null) {
					classifiers.add(classifier);
				}
			} else {
				Classifier classifier = (Classifier) project.getElementByID(XmlTagConstants.CLASSIFIED_BY);
				if(classifier != null) {
					classifiers.add(classifier);
				}
			}
		}
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification is = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification)element;
		List<Classifier> classifiers = is.getClassifier();
		
		for(Classifier classifier : classifiers) {
			// Filter out default classifiers for derived classes such as QuantityKind and Unit which are added by default
			if(!ArrayUtils.contains(SysmlConstants.defaultClassifiers, classifier.getName())) {
				org.w3c.dom.Element classifierTag = createRel(xmlDoc, classifier, XmlTagConstants.CLASSIFIED_BY);
				relationships.appendChild(classifierTag);
			}
		}
		return data;
	}
}
