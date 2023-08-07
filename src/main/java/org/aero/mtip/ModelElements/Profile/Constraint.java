/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Constraint extends CommonElement {
	public static final String VALUE_SPECIFICATION = "valueSpecification";
	public static final String CONSTRAINED_ELEMENT = "constrainedElement";
	public Constraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CONSTRAINT;
		this.xmlConstant = XmlTagConstants.CONSTRAINT;
		this.sysmlElement = f.createConstraintInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)sysmlElement;
		
		ValueSpecification oe = (ValueSpecification) project.getElementByID(xmlElement.getNewValueSpecification());
		constraint.setSpecification(oe);

		for(String constrainedElement : xmlElement.getNewConstrainedElements()) {
			Element constrainedCameoElement = (Element) project.getElementByID(constrainedElement);
			constraint.getConstrainedElement().add(constrainedCameoElement);
		}
		
		return constraint;
	}
	
	public Element createElement(Project project, Element owner) {
		super.createElement(project, owner, null);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)sysmlElement;
				
		return constraint;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		for(String constrainedElement : modelElement.getConstrainedElements()) {
			if(constrainedElement.contentEquals("_9_0_62a020a_1105704885343_144138_7929")) {
				Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
				modelElement.addNewConstrainedElement(constrainedCameoElement.getID());
			} else if(constrainedElement.contentEquals("_9_0_62a020a_1105704885473_18793_7971")) { 
				Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Association");
				modelElement.addNewConstrainedElement(constrainedCameoElement.getID());
			} else {
				Element constrainedCameoElement = ImportXmlSysml.getOrBuildElement(project, parsedXML, constrainedElement);
				modelElement.addNewConstrainedElement(constrainedCameoElement.getID());
			}
			
		}
		if(modelElement.hasValueSpecification()) {
			Element valueSpecification = ImportXmlSysml.getOrBuildElement(project, parsedXML, modelElement.getValueSpecification());
			modelElement.setNewValueSpecification(valueSpecification.getID());
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)element;
		ValueSpecification vs = constraint.getSpecification();
		
		if(vs instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
			org.w3c.dom.Element valueSpecTag = createRel(xmlDoc, vs, Constraint.VALUE_SPECIFICATION);
			relationships.appendChild(valueSpecTag);
		}
		// Check here for other value specification types to handle differently if needed
		
		//Export constrained Element
		List<Element> constrainedElements = constraint.getConstrainedElement();
		for(Element constrainedElement : constrainedElements) {
			org.w3c.dom.Element constrainedElementTag = createRel(xmlDoc, constrainedElement, Constraint.CONSTRAINED_ELEMENT);
			relationships.appendChild(constrainedElementTag);
		}
		
		List<Stereotype> stereotypes = StereotypesHelper.getStereotypes(element);
		com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile validationProfile = StereotypesHelper.getProfile(project,  "Validation Profile");
		Stereotype validationRule = StereotypesHelper.getStereotype(project, "validationRule", validationProfile);
		
		//Must check if constraint has validation rule stereotype that holds these attributes
		if(stereotypes.contains(validationRule)) {
			//Export Error Message
			
			List<String> errorMessages = StereotypesHelper.getStereotypePropertyValueAsString(element, validationRule, "errorMessage");
			if(!errorMessages.isEmpty()) {
				String errorMessage = errorMessages.get(0);
				org.w3c.dom.Element errorMsgTag = xmlDoc.createElement("errorMessage");
				errorMsgTag.appendChild(xmlDoc.createTextNode(errorMessage));
				attributes.appendChild(errorMsgTag);
			}
			
			//Export severity
			String severity = "";
			Object severityObj = StereotypesHelper.getStereotypePropertyFirst(element, validationRule, "severity");
			if(severityObj instanceof EnumerationLiteral) {
				EnumerationLiteral el = (EnumerationLiteral)severityObj;
				severity = el.getName();
			}

			// Check for other severities here
			if(!severity.isEmpty()) {
				org.w3c.dom.Element severityTag = xmlDoc.createElement("severity");
				severityTag.appendChild(xmlDoc.createTextNode(severity));
				attributes.appendChild(severityTag);
			}
		}		
		return data;
	}
}
