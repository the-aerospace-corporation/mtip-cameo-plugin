package org.aero.huddle.ModelElements.Profile;

import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.validation.ValidationRuleHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;
import com.nomagic.uml2.transaction.ModelValidationResult;
import com.nomagic.uml2.transaction.ModelValidationResult.Severity;

public class Constraint extends CommonElement {
	public Constraint(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.CONSTRAINT;
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Constraint Element");
		}
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = f.createConstraintInstance();
		((NamedElement)constraint).setName(name);
		
		if(owner != null) {
			constraint.setOwner(owner);
		} else {
			constraint.setOwner(project.getPrimaryModel());
		}
		
		ValueSpecification oe = (ValueSpecification) project.getElementByID(xmlElement.getNewValueSpecification());
		constraint.setSpecification(oe);

		for(String constrainedElement : xmlElement.getNewConstrainedElements()) {
			Element constrainedCameoElement = (Element) project.getElementByID(constrainedElement);
			constraint.getConstrainedElement().add(constrainedCameoElement);
		}
		
		SessionManager.getInstance().closeSession(project);
		return constraint;
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
