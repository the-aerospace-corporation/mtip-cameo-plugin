package org.aero.huddle.ModelElements.Activity;

import java.util.Collection;
import java.util.Iterator;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Trigger;

public class AcceptEventAction extends ActivityNode {

	public AcceptEventAction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACCEPTEVENTACTION;
		this.xmlConstant = XmlTagConstants.ACCEPTEVENTACTION;
		this.sysmlElement = f.createAcceptEventActionInstance();
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());

		
		Collection<Trigger> triggers = ((com.nomagic.uml2.ext.magicdraw.actions.mdcompleteactions.AcceptEventAction)element).getTrigger();
		Iterator<Trigger> triggerIter = triggers.iterator();
		Trigger trigger = null;
		
		if(triggerIter.hasNext()) {
			trigger = triggerIter.next();
			org.w3c.dom.Element triggerTag = createRel(xmlDoc, trigger, XmlTagConstants.TRIGGER_TAG);
			relationships.appendChild(triggerTag);
		}
		return data;
	}
}
