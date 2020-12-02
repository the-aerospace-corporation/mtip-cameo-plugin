package org.aero.huddle.ModelElements.Profile;

import java.util.List;
import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Constraint extends CommonElement {
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
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		for(String constrainedElement : modelElement.getConstrainedElements()) {
			if(constrainedElement.contentEquals("_9_0_62a020a_1105704885343_144138_7929")) {
				Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
				modelElement.addNewConstrainedElement(constrainedCameoElement.getLocalID());
			} else if(constrainedElement.contentEquals("_9_0_62a020a_1105704885473_18793_7971")) { 
				Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Association");
				modelElement.addNewConstrainedElement(constrainedCameoElement.getLocalID());
			} else {
				Element constrainedCameoElement = ImportXmlSysml.getOrBuildElement(project, parsedXML, constrainedElement);
				modelElement.addNewConstrainedElement(constrainedCameoElement.getLocalID());
			}
			
		}
		if(modelElement.hasValueSpecification()) {
			Element valueSpecification = ImportXmlSysml.getOrBuildElement(project, parsedXML, modelElement.getValueSpecification());
			modelElement.setNewValueSpecification(valueSpecification.getLocalID());
		}
	}
	
	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)element;
		ValueSpecification vs = constraint.getSpecification();
		
		if(vs instanceof com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression) {
			org.w3c.dom.Element valueSpecTag = xmlDoc.createElement("valueSpecification");
			valueSpecTag.appendChild(xmlDoc.createTextNode(vs.getLocalID()));
			attributes.appendChild(valueSpecTag);
		}
		// Check here for other value specification types to handle differently if needed
		
		//Export constrained Element
		List<Element> constrainedElements = constraint.getConstrainedElement();
		for(Element constrainedElement : constrainedElements) {
			org.w3c.dom.Element constrainedElementTag = xmlDoc.createElement("constrainedElement");
			constrainedElementTag.appendChild(xmlDoc.createTextNode(constrainedElement.getLocalID()));
			attributes.appendChild(constrainedElementTag);
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
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(this.xmlConstant));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
