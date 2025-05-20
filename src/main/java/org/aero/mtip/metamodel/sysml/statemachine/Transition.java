/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.metamodel.sysml.statemachine;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.metamodel.core.CommonDirectedRelationship;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Vertex;

public class Transition extends CommonDirectedRelationship {
	public Transition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.xmlConstant = XmlTagConstants.TRANSITION;
		this.metamodelConstant = SysmlConstants.TRANSITION;
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
			
			if(xmlElement.hasElement(XmlTagConstants.TRIGGER_TAG)) {

			}
		} catch(NullPointerException npe) {
			Logger.log(String.format("Error creating transition %s with id %s. See stack trace: ", name, EAID));
			Logger.logException(npe);
			
			if(transition != null) {
				ModelHelper.dispose(Arrays.asList(transition));
			}
			
			return null;
		}
		
		try {
			transition.setSource((Vertex) supplier);
			transition.setTarget((Vertex) client);
		} catch(ClassCastException cce) {
			Logger.log(String.format("Invalid supplier or client. Supplier and client must be sub-classes of Vertex. Transition %s with id %s not created. See stack trace: ", name, EAID));
			Logger.logException(cce);
			
			if(transition != null) {
				ModelHelper.dispose(Arrays.asList(transition));
			}
			return null;
		}
		
		return transition;
	}
	
	public void createReferencedElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(!modelElement.hasAttribute(XmlTagConstants.TRIGGER_TAG)) {
			return;
		}
		
		String triggerID = modelElement.getAttribute(XmlTagConstants.TRIGGER_TAG);
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger trigger = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger)Importer.getInstance().buildElement(parsedXML, parsedXML.get(triggerID));
		
		if (trigger == null) {
			Logger.log(String.format("Trigger data found, but failed to create trigger for transition %s", EAID));
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition transition = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		transition.getTrigger().add(trigger);
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
	public Element getSupplier(Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition cameoRelationship = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		return cameoRelationship.getSource();
	}

	@Override
	public Element getClient(Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition cameoRelationship = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition)element;
		return cameoRelationship.getTarget();
	}
	
	@Override
	protected void writeParent(org.w3c.dom.Element relationships) {
		Element owner = element.getOwner().getOwner();
		
		if(owner == null) {
			Logger.log(String.format("No parent found for transition %s with id %s", element.getHumanName(), MtipUtils.getId(element)));
			return;
		}
		
		org.w3c.dom.Element hasParentTag = XmlWriter.createMtipRelationship(owner, XmlTagConstants.HAS_PARENT);
		XmlWriter.add(relationships, hasParentTag);
	}
}
