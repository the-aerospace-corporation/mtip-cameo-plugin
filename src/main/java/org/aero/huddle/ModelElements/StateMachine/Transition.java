package org.aero.huddle.ModelElements.StateMachine;

import java.util.Collection;
import java.util.Map;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Vertex;
import com.nomagic.uml2.impl.ElementsFactory;

public class Transition extends CommonRelationship {
	public Transition(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Transition Relationship");
		}
		ElementsFactory ef = project.getElementsFactory();
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition transition = ef.createTransitionInstance();
		
		if(xmlElement.hasGuard()) {
			Constraint constraint = ef.createConstraintInstance();
			LiteralString specification = ef.createLiteralStringInstance();
			specification.setValue(xmlElement.getGuard());
			constraint.setSpecification(specification);			
			transition.setGuard(constraint);
		}
		
		if(xmlElement.hasEffect()) {
			FunctionBehavior functionBehavior = ef.createFunctionBehaviorInstance();
			functionBehavior.getBody().add(xmlElement.getAttribute("effect"));
			transition.setEffect(functionBehavior);
		}
		try {
			transition.setSource((Vertex) supplier);
			transition.setTarget((Vertex) client);
		} catch(ClassCastException cce) {
			String logMessage = "Invalid supplier or client. Supplier and client must be sub-classes of Vertex. Transition " + name + " with id " + EAID + " not created";
			ImportLog.log(logMessage);
			transition.dispose();
			return null;
		}
		
		((NamedElement)transition).setName(name);
		transition.setOwner(owner);
		
//		if(xmlElement.hasElement(XmlTagConstants.TRIGGER_TAG)) {
//			com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (Trigger) xmlElement.getElement(XmlTagConstants.TRIGGER_TAG);
//			transition.getTrigger().add(trigger);
//		}
		
		SessionManager.getInstance().closeSession(project);
		return transition;
	}
	
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
//		CameoUtils.logGUI("Creating dependent elements for transition...");
//		if(modelElement.hasAttribute(XmlTagConstants.TRIGGER_TAG)) {
//			String triggerID = modelElement.getAttribute(XmlTagConstants.TRIGGER_TAG);
//			com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger)ImportXmlSysml.getOrBuildElement(project, parsedXML, triggerID);
//			modelElement.addElement(XmlTagConstants.TRIGGER_TAG, trigger);
//			CameoUtils.logGUI("Trigger found and added to transition XML.");
//		} else {
//			CameoUtils.logGUI("No trigger found in XML for trigger with id: " + EAID);
//		}
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.TRANSITION));
		data.appendChild(type);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition tr = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		Collection<com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger> triggers = tr.getTrigger();
		if(!triggers.isEmpty()) {
			com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = triggers.iterator().next();
			org.w3c.dom.Element triggerTag = xmlDoc.createElement(XmlTagConstants.TRIGGER_TAG);
			triggerTag.appendChild(xmlDoc.createTextNode(trigger.getLocalID()));
			attributes.appendChild(triggerTag);
		}
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);	
	}
}
