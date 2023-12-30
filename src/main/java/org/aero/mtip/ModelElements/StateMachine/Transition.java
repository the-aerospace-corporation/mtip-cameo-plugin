/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.Collection;
import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Vertex;

public class Transition extends CommonRelationship {
	public Transition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.TRANSITION;
		this.sysmlConstant = SysmlConstants.TRANSITION;
		this.element = f.createTransitionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition transition = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		
		try {
			if(xmlElement.hasGuard()) {
				Constraint constraint = f.createConstraintInstance();
				LiteralString specification = f.createLiteralStringInstance();
				specification.setValue(xmlElement.getGuard());
				constraint.setSpecification(specification);			
				transition.setGuard(constraint);
			}
			
			if(xmlElement.hasEffect()) {
				FunctionBehavior functionBehavior = f.createFunctionBehaviorInstance();
				functionBehavior.getBody().add(xmlElement.getAttribute("effect"));
				transition.setEffect(functionBehavior);
			}
		} catch(NullPointerException npe) {
			String logMessage = "Error creating transition " + name + " with id " + EAID;
			ImportLog.log(logMessage);
			if(transition != null) {
				transition.dispose();
			}
			return null;
		}
		try {
			transition.setSource((Vertex) supplier);
			transition.setTarget((Vertex) client);
		} catch(ClassCastException cce) {
			String logMessage = "Invalid supplier or client. Supplier and client must be sub-classes of Vertex. Transition " + name + " with id " + EAID + " not created";
			ImportLog.log(logMessage);
			if(transition != null) {
				transition.dispose();
			}
			return null;
		}
		
//		if(xmlElement.hasElement(XmlTagConstants.TRIGGER_TAG)) {
//			com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (Trigger) xmlElement.getElement(XmlTagConstants.TRIGGER_TAG);
//			transition.getTrigger().add(trigger);
//		}
		return transition;
	}
	
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
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
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeTrigger(relationships, element);

		return data;
	}
	
	protected void writeTrigger(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition tr = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		Collection<com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger> triggers = tr.getTrigger();
		
		if(triggers.isEmpty()) {
			return;
		}
			
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = triggers.iterator().next();
		org.w3c.dom.Element triggerTag = XmlWriter.createMtipRelationship(trigger, XmlTagConstants.TRIGGER_TAG);
		XmlWriter.add(relationships, triggerTag);
	}
	
	@Override
	public void setOwner(Element owner) {
		owner = CameoUtils.findNearestRegion(project, supplier);
		element.setOwner(owner);
	}
	@Override
	public void setSupplier() {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition transition = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		transition.setSource((Vertex) supplier);
	}
	
	@Override
	public void setClient() {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition transition = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		transition.setTarget((Vertex) client);
	}

	@Override
	public void getSupplier(Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition cameoRelationship = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		this.supplier = cameoRelationship.getSource();
	}

	@Override
	public void getClient(Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition cameoRelationship = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		this.client = cameoRelationship.getTarget();
	}
	
	@Override
	protected void writeParent(org.w3c.dom.Element relationships) {
		Element owner = element.getOwner().getOwner();
		
		if(owner == null) {
			ExportLog.log(String.format("No parent found for transition %s with id %s", element.getHumanName(), element.getID()));
			return;
		}
		
		org.w3c.dom.Element hasParentTag = XmlWriter.createMtipRelationship(owner, XmlTagConstants.HAS_PARENT);
		XmlWriter.add(relationships, hasParentTag);
	}
}
