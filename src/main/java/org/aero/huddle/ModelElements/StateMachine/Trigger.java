package org.aero.huddle.ModelElements.StateMachine;

import javax.annotation.CheckForNull;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Event;
import com.nomagic.uml2.impl.ElementsFactory;

public class Trigger extends CommonElement {

	public Trigger(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, @CheckForNull XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Trigger Element");
		}
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = f.createTriggerInstance();

		((NamedElement)trigger).setName(name);
		if(owner != null) {
			if(owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition) {
				trigger.set_transitionOfTrigger((com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)owner);
			}
//			trigger.setOwner(owner);
		} else {
			trigger.setOwner(project.getPrimaryModel());
		}
		if(xmlElement != null) {
			if(xmlElement.hasAcceptEventAction()) {
				CameoUtils.logGUI("Setting accept event action of Trigger to AcceptEventAction with id: " + xmlElement.getNewAcceptEventAction());
				trigger.set_acceptEventActionOfTrigger((AcceptEventAction) project.getElementByID(xmlElement.getNewAcceptEventAction()));
			}
			if(xmlElement.hasEvent()) {
				CameoUtils.logGUI("Setting Trigger Event to event with id: " + xmlElement.getNewEvent());
				trigger.setEvent((Event) project.getElementByID(xmlElement.getNewEvent()));
			}
			//Set transition of trigger if it has a transition
		}
		
		SessionManager.getInstance().closeSession(project);
		return trigger;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.TRIGGER));
		data.appendChild(type);
		
		//Add reference to Event type of the trigger - since element is child of Trigger's parent's Activity, this must be explicitly written here
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger)element;
		Event event = trigger.getEvent();
		if(event != null) {
			org.w3c.dom.Element eventTag = xmlDoc.createElement("event");
			eventTag.appendChild(xmlDoc.createTextNode(event.getLocalID()));
			attributes.appendChild(eventTag);
		}
		
		AcceptEventAction aea = trigger.get_acceptEventActionOfTrigger();
		if(aea != null) {
			org.w3c.dom.Element aeaTag = xmlDoc.createElement("acceptEventAction");
			aeaTag.appendChild(xmlDoc.createTextNode(aea.getLocalID()));
			attributes.appendChild(aeaTag);
		}
		

		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);	
	}
}
