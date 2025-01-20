/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.metamodel.sysml.activity;

import java.util.Collection;
import java.util.Iterator;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;

public class AcceptEventAction extends ActivityNode {

	public AcceptEventAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.ACCEPT_EVENT_ACTION;
		this.xmlConstant = XmlTagConstants.ACCEPT_EVENT_ACTION;
		this.element = f.createAcceptEventActionInstance();
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());

		writeTrigger(relationships, element);
		
		return data;
	}
	
	public void writeTrigger(org.w3c.dom.Element relationships, Element element) {
		Collection<Trigger> triggers = ((com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction)element).getTrigger();
		Iterator<Trigger> triggerIter = triggers.iterator();
		
		if(!triggerIter.hasNext()) {
			return;
		}
		
		Trigger trigger = triggerIter.next();
		org.w3c.dom.Element triggerTag = XmlWriter.createMtipRelationship(trigger, XmlTagConstants.TRIGGER_TAG);
		XmlWriter.add(relationships, triggerTag);
	}
}
