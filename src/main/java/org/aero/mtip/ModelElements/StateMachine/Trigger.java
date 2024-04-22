/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.HashMap;

import javax.annotation.CheckForNull;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Event;

public class Trigger extends CommonElement {
	public Trigger(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.TRIGGER;
		this.xmlConstant = XmlTagConstants.TRIGGER;
		this.element = f.createTriggerInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, @CheckForNull XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		if(xmlElement == null) {
			return null;
		}
		
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger) element;
		
		if(xmlElement.hasAcceptEventAction()) {
			AcceptEventAction aea = (AcceptEventAction) project.getElementByID(xmlElement.getNewAcceptEventAction());
			trigger.set_acceptEventActionOfTrigger(aea);
		}

		if(xmlElement.hasElement(XmlTagConstants.EVENT_TAG)) {
			Event event = (Event) xmlElement.getElement(XmlTagConstants.EVENT_TAG);
			trigger.setEvent(event);
		}
		
		return element;
	}
	
	@Override
	public void setOwner(Element owner) {
		if (owner == null) {
			return;
		}
		
		if(owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition) {
			com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger) element;
			trigger.set_transitionOfTrigger((com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)owner);
		}
		
		element.setOwner(owner);
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.ACCEPT_EVENT_ACTION)) {	
			Element acceptEventAction = Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getAcceptEventAction()));
			modelElement.setNewAcceptEventAction(acceptEventAction.getID());
		}
		
		if(modelElement.hasAttribute(XmlTagConstants.EVENT_TAG)) {
			String signal = modelElement.getAttribute(XmlTagConstants.EVENT_TAG);
			com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Event event = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.SignalEvent)Importer.getInstance().buildElement(parsedXML, parsedXML.get(signal));
			modelElement.addElement(XmlTagConstants.EVENT_TAG, event);
		}
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		//Add reference to Event type of the trigger - since element is child of Trigger's parent's Activity, this must be explicitly written here
		writeEvent(relationships, element);
		

		
//		Event se = trigger.getEvent();
//		if(se != null) {
//			CameoUtils.logGUI("Trigger Event type is: " + se.getHumanType());
//			org.w3c.dom.Element signalEventTag = xmlDoc.createElement(XmlTagConstants.SIGNAL_EVENT_TAG);
//			signalEventTag.appendChild(xmlDoc.createTextNode(se.getID()));
//			attributes.appendChild(signalEventTag);
//		}
		
		return data;
	}
	
	protected void writeEvent(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger)element;
		Event event = trigger.getEvent();
		
		if(event == null) {
			return;
		}
		
		org.w3c.dom.Element eventTag = XmlWriter.createMtipRelationship(event, XmlTagConstants.EVENT_TAG);
		XmlWriter.add(relationships, eventTag);
	}
	
	protected void writeAcceptEventAction(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger)element;
		AcceptEventAction aea = trigger.get_acceptEventActionOfTrigger();
		
		if(aea == null) {
			return;
		}
		
		org.w3c.dom.Element aeaTag = XmlWriter.createMtipRelationship(aea, XmlTagConstants.ACCEPT_EVENT_ACTION_TAG);
		XmlWriter.add(relationships, aeaTag);
	}
}
